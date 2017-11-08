package com.Bacchus.app.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.Bacchus.app.form.LoginNameForm;
import com.Bacchus.app.service.LoggerService;
import com.Bacchus.app.util.EncryptUtil;
import com.Bacchus.app.util.MessageKeyUtil;
import com.Bacchus.dbflute.exbhv.UserTBhv;
import com.Bacchus.webbase.appbase.BaseController;
import com.Bacchus.webbase.appbase.BeforeLogin;
import com.Bacchus.webbase.common.constants.LogMessageKeyConstants;
import com.Bacchus.webbase.common.constants.MessageKeyConstants;
import com.Bacchus.webbase.common.constants.SystemCodeConstants;
import com.Bacchus.webbase.common.constants.SystemCodeConstants.MessageType;
import com.Bacchus.webbase.common.constants.SystemCodeConstants.Permissions;

/**
 * ログイン用コントローラ。
 *
 * @author ishigouoka_k
 */
@BeforeLogin
@Controller
@RequestMapping(value = "/login")
public class LoginController extends BaseController {

    @Autowired
    LoggerService loggerService;

    @Autowired
    UserTBhv userTBhv;

    @RequestMapping(value = "/lineLogin", method = RequestMethod.GET)
    public String lineLogin() throws Exception {

        String authorizeUrl = "https://access.line.me/oauth2/v2.1/authorize";

        String responsetype = "response_type=code";
        String client_id = "client_id=1545279597";
        String redirect_uri = "redirect_uri=https%3A%2F%2Fglue-bacchus.herokuapp.com%2Fcallback";
        String state = "state=12345abcde";
        String scope = "scope=openid%20profile";

        StringBuffer sb = new StringBuffer();
        sb.append(authorizeUrl);
        sb.append("?");
        sb.append(responsetype);
        sb.append("&");
        sb.append(client_id);
        sb.append("&");
        sb.append(redirect_uri);
        sb.append("&");
        sb.append(state);
        sb.append("&");
        sb.append(scope);

        return super.redirect(sb.toString());
    }

    @RequestMapping(value = "/callback", method = RequestMethod.GET)
    public String callback(@RequestParam("code") String code,
            @RequestParam("state") String state) throws Exception {

        System.out.println("code : " + code);
        System.out.println("state : " + state);

        return "/";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String loginName(@ModelAttribute("form") LoginNameForm form, BindingResult bindingResult,
            RedirectAttributes redirectAttributes, Model model) throws Exception {

        String userName = form.getUserName();
        String encPassword = EncryptUtil.saltHash(form.getPassword(), EncryptUtil.EncryptType.MD5);

        userTBhv.selectEntity(cb -> {
            cb.query().setUserName_Equal(userName);
            cb.query().setPassword_Equal(encPassword);
        }).ifPresent(userT -> {
            // called if present
            userInfo.setUserId(userT.getUserId());
            userInfo.setLogined(true);
            userInfo.setUserName(userT.getUserName());
            userInfo.setEmail(userT.getEmail());

            Permissions permissions = Permissions.getPermissions(userT.getAuthLevel());

            if (permissions == SystemCodeConstants.Permissions.ADMIN){
                userInfo.setAdminFlg(true);
            } else {
                userInfo.setGeneralFlg(true);
            }

            Set<SystemCodeConstants.Permissions> permissionsSet = new HashSet<SystemCodeConstants.Permissions>();
            permissionsSet.add(permissions);
            userInfo.setPermissions(permissionsSet);

            loggerService.outLog(LogMessageKeyConstants.Info.I_00_0001, new Object[] {
                    userInfo.getUserId(),
                    userInfo.getUserName(),
                    userInfo.getEmail()
                    });

        }).orElse(() -> {
            // called if not present
            userInfo.setLogined(false);

            String message = messageSource.getMessage(
                    MessageKeyUtil.encloseStringDelete(MessageKeyConstants.Error.LOGIN),
                    null,
                    Locale.getDefault());
            List<String> errorMessageList = new ArrayList<String>(Arrays.asList(message));
            redirectAttributes.addFlashAttribute(MessageType.ERROR, errorMessageList);

        });


        return redirect("/");
    }
}
