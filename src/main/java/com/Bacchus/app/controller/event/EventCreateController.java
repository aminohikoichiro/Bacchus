package com.Bacchus.app.controller.event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Bacchus.app.components.LabelValueDto;
import com.Bacchus.app.form.event.EventCreateForm;
import com.Bacchus.app.service.CommonService;
import com.Bacchus.app.service.LoggerService;
import com.Bacchus.app.service.SystemPropertyService;
import com.Bacchus.app.service.event.EventCreateService;
import com.Bacchus.app.service.event.EventService;
import com.Bacchus.app.util.DateUtil;
import com.Bacchus.app.util.MessageKeyUtil;
import com.Bacchus.dbflute.exbhv.UserTBhv;
import com.Bacchus.dbflute.exbhv.UserTypeMBhv;
import com.Bacchus.webbase.appbase.BaseController;
import com.Bacchus.webbase.common.constants.DisplayIdConstants;
import com.Bacchus.webbase.common.constants.MessageKeyConstants;
import com.Bacchus.webbase.common.constants.MessageKeyConstants.GlueNetValidator;
import com.Bacchus.webbase.common.constants.ProcConstants;
import com.Bacchus.webbase.common.constants.ProcConstants.Operation;
import com.Bacchus.webbase.common.constants.SystemCodeConstants;
import com.Bacchus.webbase.common.constants.SystemCodeConstants.MessageType;

/**
 * イベント登録用コントローラ。
 *
 * @author sagawa_k
 */
@Controller
@RequestMapping(value = ProcConstants.EVENT)
public class EventCreateController extends BaseController {

    /** ロガーロジック */
    @Autowired
    LoggerService loggerService;

    @Autowired
    SystemPropertyService systemPropertyService;

    @Autowired
    EventCreateService eventCreateService;

    @Autowired
    UserTBhv userTBhv;

    @Autowired
    UserTypeMBhv userTypeMBhv;

    @Autowired
    CommonService commonService;

    @Autowired
    EventService eventService;

    /**
     * イベント登録画面初期表示.
     *
     * @param model
     * @return "/event/eventCreate"
     * @throws Exception
     */
    @RequestMapping(value = Operation.CREATE, method = RequestMethod.GET)
    public String create(@ModelAttribute("form") EventCreateForm form, Model model) throws Exception {

        super.setDisplayTitle(model, DisplayIdConstants.Event.BACCHUS_0202);

        model.addAttribute("form", form);
        setPullDownList(model);

        return ProcConstants.EVENT + ProcConstants.Operation.CREATE;

    }

    /**
     * イベント登録処理のコントローラー.
     *
     * @param form
     * @param bindingResult
     *            BindingResult
     * @param redirectAttributes
     *            RedirectAttributes
     * @param model
     *            Model
     * @return "/event/eventCreate"
     * @throws Exception
     */
    @RequestMapping(value = Operation.STORE, method = RequestMethod.POST)
    public String store(@Validated @ModelAttribute("form") EventCreateForm form, BindingResult bindingResult,
            RedirectAttributes redirectAttributes, Model model) throws Exception {

        super.setDisplayTitle(model, DisplayIdConstants.Event.BACCHUS_0202);

        // 幹事のユーザIDの存在チェック
        if (!bindingResult.hasFieldErrors("userId")) {
            if (StringUtils.isNotEmpty(form.getUserId())) {
                if (!isExistsUser(Integer.parseInt(form.getUserId()))) {
                    bindingResult.rejectValue("userId",
                            MessageKeyUtil.encloseStringDelete(GlueNetValidator.INVALID), null, "");
                }
            }
        }

        // 経費可否の妥当性チェック
        if (!bindingResult.hasFieldErrors("auxiliaryFlg")) {
            if (StringUtils.isNotEmpty(form.getAuxiliaryFlg())) {
                if (!commonService.isExistsGenCode(SystemCodeConstants.GeneralCodeKbn.AUXILIARY_DIV,
                        form.getAuxiliaryFlg())) {
                    bindingResult.rejectValue("auxiliaryFlg",
                            MessageKeyUtil.encloseStringDelete(GlueNetValidator.INVALID), null, "");
                }
            }
        }

        // イベント種別の妥当性チェック
        if (!bindingResult.hasFieldErrors("eventTypeId")) {
            if (form.getEventTypeId() != null) {
                if (!eventService.isExistsEventType(form.getEventTypeId())) {
                    bindingResult.rejectValue("eventTypeId",
                            MessageKeyUtil.encloseStringDelete(GlueNetValidator.INVALID), null, "");
                }
            }
        }

        // 候補日の形式チェック
        if (form.getStartDate() != null && form.getStartDate().length > 0) {
            for (int i = 0; i < form.getStartDate().length; i++) {
                String fieldName = "startDate[" + i + "]";
                if (!bindingResult.hasFieldErrors(fieldName)) {
                    if (StringUtils.isNotEmpty(form.getStartDate()[i])) {
                        if (!DateUtil.isValidDateFormat(form.getStartDate()[i])) {
                            bindingResult.rejectValue(fieldName,
                                    MessageKeyUtil.encloseStringDelete(GlueNetValidator.DATEFORMAT_MESSAGE),
                                    new String[]{DateUtil.DATE_TIME_FORMAT_YYYYMMDD}, "");
                        }
                    }
                }
            }
        }

        // 確定ボタンを選択した候補日が空白でないかの判定 空白ならtrue
        if (eventCreateService.isFixCandidate(form)) {

            // エラー文のセット
            bindingResult.rejectValue("startDate[" + Integer.parseInt(form.getFixDate()) + "]",
                    MessageKeyUtil.encloseStringDelete(GlueNetValidator.NOTBLANK_WITH_FIELD),
                    new Object[] { "確定対象の日付" }, "");
        }

        // validation確認
        if (bindingResult.hasErrors()) {

            model.addAttribute(MODEL_KEY_FORM, form);
            model.addAttribute("errors", bindingResult);

            setPullDownList(model);

            return ProcConstants.EVENT + ProcConstants.Operation.CREATE;
        }

        // DB登録
        eventCreateService.store(form);

        // 完了メッセージを設定
        String message = messageSource.getMessage(
                MessageKeyUtil.encloseStringDelete(MessageKeyConstants.Success.CREATE), null, Locale.getDefault());

        List<String> successMessageList = new ArrayList<String>(Arrays.asList(message));
        redirectAttributes.addFlashAttribute(MessageType.SUCCESS, successMessageList);

        return redirect(ProcConstants.EVENT + ProcConstants.Operation.INDEX);
    }

    /**
     * プルダウン項目の設定
     * @param model
     */
    private void setPullDownList(Model model) {
        // ユーザー名のプルダウン取得.
        List<LabelValueDto> userNameSelectList = eventCreateService.userNamePullDown();
        model.addAttribute("userNameSelectList", userNameSelectList);

        // 経費補助のプルダウン
        List<LabelValueDto> auxiliaryFlgSelectList = commonService.creatOptionalLabelValueList(
                SystemCodeConstants.GeneralCodeKbn.AUXILIARY_DIV,
                SystemCodeConstants.PLEASE_SELECT_MSG);
        model.addAttribute("auxiliaryFlgSelectList", auxiliaryFlgSelectList);

        // イベント種別のプルダウン
        List<LabelValueDto> eventTypeList = eventService.creatEventTypeLabelValueList();
        model.addAttribute("eventTypeList", eventTypeList);
    }


    private boolean isExistsUser(Integer userId) {
        int resultCount = userTBhv.selectCount(cb -> {
            cb.query().setUserId_Equal(userId);
        });

        if (resultCount > 0) {
            return true;
        }
        return false;
    }

}
