package kuit.springbasic.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class ForwardController {

    @RequestMapping("/user/form")
    public String forwardToUserForm() {
        log.info("ForwardController.forwardToUserForm");
        return "/user/form";
    }

    @RequestMapping("/user/loginForm")
    public String forwardToUserLoginForm() {
        log.info("ForwardController.forwardToUserLoginForm");
        return "/user/login";
    }

    @RequestMapping("/user/loginFailed")
    public String forwardToUserLoginFailed() {
        log.info("ForwardController.forwardToUserLoginFailed");
        return "/user/loginFailed";
    }

}
