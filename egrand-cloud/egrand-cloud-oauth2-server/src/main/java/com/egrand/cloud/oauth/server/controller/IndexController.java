package com.egrand.cloud.oauth.server.controller;

import com.egrand.cloud.oauth.server.service.feign.UserServiceClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: liuyadu
 * @date: 2018/10/29 15:59
 * @description:
 */
@Controller
public class IndexController {
//    @Autowired
//    private BaseAppServiceClient baseAppRemoteService;

    @Autowired
    private UserServiceClient userServiceClient;

    /**
     * 欢迎页
     *
     * @return
     */
    @GetMapping("/")
    public String welcome() {
        return "welcome";
    }

    /**
     * 登录页
     *
     * @return
     */
    @PreAuthorize("hasAuthority('URL_YHGL')")
    @GetMapping("/login")
    public String login(HttpServletRequest request) {
        System.out.println("=======list======");
        System.out.println("result = " + this.userServiceClient.feign("web_study"));
        System.out.println("=======list======");
        return "login";
    }

//    /**
//     * 确认授权页
//     * @param request
//     * @param session
//     * @param model
//     * @return
//     */
//    @RequestMapping("/oauth/confirm_access")
//    public String confirm_access(HttpServletRequest request, HttpSession session, Map model) {
//        Map<String, String> scopes = (Map<String, String>) (model.containsKey("scopes") ? model.get("scopes") : request.getAttribute("scopes"));
//        List<String> scopeList = new ArrayList<String>();
//        for (String scope : scopes.keySet()) {
//            scopeList.add(scope);
//        }
//        model.put("scopeList", scopeList);
//        Object auth = session.getAttribute("authorizationRequest");
//        if (auth != null) {
//            try {
//                AuthorizationRequest authorizationRequest = (AuthorizationRequest) auth;
//                ClientDetails clientDetails = baseAppRemoteService.getAppClientInfo(authorizationRequest.getClientId()).getData();
//                model.put("app", clientDetails.getAdditionalInformation());
//            } catch (Exception e) {
//
//            }
//        }
//        return "confirm_access";
//    }

    /**
     * 自定义oauth2错误页
     * @param request
     * @return
     */
    @RequestMapping("/oauth/error")
    @ResponseBody
    public Object handleError(HttpServletRequest request) {
        Object error = request.getAttribute("error");
        return error;
    }
}
