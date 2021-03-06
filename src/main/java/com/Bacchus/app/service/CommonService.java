package com.Bacchus.app.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Bacchus.app.Exception.AbnormalRecordsDetection;
import com.Bacchus.app.Exception.RecordNotFoundException;
import com.Bacchus.app.components.GeneralCodeDto;
import com.Bacchus.app.components.LabelValueDto;
import com.Bacchus.dbflute.cbean.GeneralCodeMCB;
import com.Bacchus.dbflute.exbhv.GeneralCodeMBhv;
import com.Bacchus.dbflute.exentity.GeneralCodeM;
import com.Bacchus.webbase.common.constants.SystemCodeConstants;
import com.Bacchus.webbase.common.constants.SystemCodeConstants.Flag;
import com.Bacchus.webbase.common.constants.SystemPropertyKeyConstants;

/**
 * 共通サービスクラス。
 *
 * @author ishigouoka_k
 * $Id:$
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class CommonService {

    @Autowired
    GeneralCodeMBhv generalCodeMBhv;

    @Autowired
    SystemPropertyService systemPropertyService;

    /**
     * 開発モードの判定。
     * <p>
     * 開発モードの場合、Line等の外部メッセージを使用しない挙動となる。
     *
     * @return true:開発モード, false:非開発モード
     * @throws RecordNotFoundException
     */
    public boolean isDevelopMode() throws RecordNotFoundException {
        String testModeFlg = systemPropertyService.getSystemPropertyValue(
                SystemPropertyKeyConstants.DEVELOP_MODE_FLG);

        if (StringUtils.equals(testModeFlg, Flag.ON.getStringValue())) {
            return true;
        }
        return false;
    }


    /**
     * 入力されたコードに関連する[汎用_M]の名称を取得
     *
     * @param codeDiv
     * @param code
     * @return 汎用名称
     * @throws AbnormalRecordsDetection
     */
    public String getGeneralName(String codeDiv , String code) throws AbnormalRecordsDetection{

        if (StringUtils.isEmpty(code)) {
            return null;
        }
        GeneralCodeMCB generalCodeMCB = new GeneralCodeMCB();
        generalCodeMCB.query().setCodeDiv_Equal(codeDiv);
        generalCodeMCB.query().setCode_Equal(code);
        List<GeneralCodeM> generalCodeMList = generalCodeMBhv.readList(generalCodeMCB);

        if (generalCodeMList.size() != 1) {
            throw new AbnormalRecordsDetection(1, generalCodeMList.size());
        }
        GeneralCodeM GeneralCodeM = generalCodeMList.get(0);
        String generalName = GeneralCodeM.getName();
        return generalName;
    }

    /**
     * 入力されたコードに関連する[汎用_M]の備考を取得
     *
     * @param codeDiv
     * @param code
     * @return 汎用備考
     * @throws AbnormalRecordsDetection
     */
    public String getGeneralRemarks(String codeDiv, String code){

        if (StringUtils.isEmpty(code)) {
            return null;
        }
        GeneralCodeMCB generalCodeMCB = new GeneralCodeMCB();
        generalCodeMCB.query().setCodeDiv_Equal(codeDiv);
        generalCodeMCB.query().setCode_Equal(code);
        List<GeneralCodeM> generalCodeMList = generalCodeMBhv.readList(generalCodeMCB);

        if (generalCodeMList.size() != 1) {
            return null;
        }
        GeneralCodeM GeneralCodeM = generalCodeMList.get(0);
        String generalRemarks = GeneralCodeM.getRemarks();
        return generalRemarks;
    }

    public List<LabelValueDto> creatOptionalLabelValueList(String codeKbn, String optionalNullText) {

        List<LabelValueDto> resultList = new ArrayList<LabelValueDto>();
        LabelValueDto labelValueDto = new LabelValueDto();
        labelValueDto.setValue("");
        labelValueDto.setLabel(optionalNullText);
        resultList.add(labelValueDto);

        List<GeneralCodeDto> generalCodeDtoList = getGeneralCodeListByCodeKbn(codeKbn);

        for (GeneralCodeDto generalCodeDto :generalCodeDtoList) {
            labelValueDto = new LabelValueDto();
            labelValueDto.setValue(generalCodeDto.getCode());
            labelValueDto.setLabel(generalCodeDto.getName());

            resultList.add(labelValueDto);
        }
        return resultList;
    }

    /**
     * 指定したコード区分の汎用コードマスタのコードと名称のMapを取得。
     * @param codeKbn コード区分
     * @return Map<コード, 名称>
     */
    public Map<String, String> getGeneralCodeMapByCodeKbn(String codeKbn) {

        List<GeneralCodeDto> generalCodeDtoList = this.getGeneralCodeListByCodeKbn(codeKbn);

        Map<String, String> resultMap = new HashMap<String, String>();

        for (GeneralCodeDto generalCodeDto : generalCodeDtoList) {
            resultMap.put(generalCodeDto.getCode(), generalCodeDto.getName());
        }

        return resultMap;
    }

    /**
     * 指定したコード区分の汎用コードマスタのリストを取得。
     * @param codeKbn
     * @return 汎用コードマスタのリスト
     */
    public List<GeneralCodeDto> getGeneralCodeListByCodeKbn(String codeKbn) {
        GeneralCodeMCB wlmcb = new GeneralCodeMCB();
        wlmcb.query().setCodeDiv_Equal(codeKbn);
        wlmcb.query().setDelFlg_Equal(SystemCodeConstants.Flag.OFF.getIntegerValue());
        wlmcb.query().addOrderBy_OrderNumber_Asc();
        // 取得したレコードのエンティティをDTOに変換してListを再構築
        return convertGeneralCodeMListToDtoList(generalCodeMBhv.readList(wlmcb));
    }

    /**
     * 指定した複数のコード区分の汎用コードマスタのリストを取得。
     * @param codeKbnList
     * @return 汎用コードマスタのリスト
     */
    public List<GeneralCodeDto> getGeneralCodeListByCodeKbn(List<String> codeKbnList) {
        GeneralCodeMCB wlmcb = new GeneralCodeMCB();
        wlmcb.query().setCodeDiv_InScope(codeKbnList);
        wlmcb.query().setDelFlg_Equal(SystemCodeConstants.Flag.OFF.getIntegerValue());
        wlmcb.query().addOrderBy_OrderNumber_Asc();
        // 取得したレコードのエンティティをDTOに変換してListを再構築
        return convertGeneralCodeMListToDtoList(generalCodeMBhv.readList(wlmcb));
    }

    /**
     * [汎用コード_M]のエンティティリストからDTOを作成
     * @param generalCodeMList [汎用コード_M]のエンティティリスト
     * @return DTOリスト
     */
    private static List<GeneralCodeDto> convertGeneralCodeMListToDtoList(List<GeneralCodeM> generalCodeMList) {
        List<GeneralCodeDto> dtoList = new ArrayList<>();
        for (GeneralCodeM entity : generalCodeMList) {
            GeneralCodeDto dto = new GeneralCodeDto();
            dto.setCodeId(entity.getCodeId());
            dto.setCodeDiv(entity.getCodeDiv());
            dto.setCode(entity.getCode());
            dto.setName(entity.getName());
            dto.setOrderNumber(entity.getOrderNumber());
            dto.setRemarks(entity.getRemarks());
            dto.setDelFlg(entity.getDelFlg());
            dtoList.add(dto);
        }
        return dtoList;
    }

    /**
     * 指定して汎用コードが存在するかを確認。
     *
     * @param codeDiv コード区分
     * @param code コード値
     * @return true:存在, false:存在しない
     */
    public boolean isExistsGenCode(String codeDiv, String code) {
        int resultCount = generalCodeMBhv.selectCount(cb -> {
            cb.query().setCodeDiv_Equal(codeDiv);
            cb.query().setCode_Equal(code);

        });

        if (resultCount > 0 ) {
            return true;
        }

        return false;
    }

}
