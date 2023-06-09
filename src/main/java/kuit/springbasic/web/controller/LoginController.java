package kuit.springbasic.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import kuit.springbasic.web.dao.UserDao;
import kuit.springbasic.web.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.SQLException;

import static kuit.springbasic.config.Constant.USER_SESSION_KEY;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {

    private final UserDao userDao;

    /**
     * TODO: showLoginForm
     */
    @RequestMapping("/user/loginForm")
    public String showLoginForm() {
        log.info("HomeController.showLoginForm");
        return "/user/login";
    }

    /**
     * TODO: showLoginFailed
     */
    @RequestMapping("/user/loginFailed")
    public String showLoginFailed() {
        log.info("HomeController.showLoginFailed");
        return "/user/loginFailed";
    }

    /**
     * TODO: login
     * loginV1 : @RequestParam("")
     * loginV2 : @RequestParam
     * loginV3 : @RequestParam 생략(비추천)
     * loginV4 : @ModelAttribute
     */
    //    @RequestMapping("/user/login")
    public String loginV1(@RequestParam("userId") String userId, @RequestParam("password") String password,
                          HttpServletRequest request) throws SQLException {
        log.info("LoginController.loginV1");

        User loggedInUser = new User(userId, password);
        User user = userDao.findByUserId(userId);

        if (user != null && user.equals(loggedInUser)) {
            HttpSession session = request.getSession();
            session.setAttribute(USER_SESSION_KEY, user);
            return "redirect:/";
        }
        return "redirect:/user/loginFailed";
    }

    //    @RequestMapping("/user/login")
    public String loginV2(@RequestParam String userId, @RequestParam String password,
                          HttpServletRequest request) throws SQLException {
        log.info("LoginController.loginV2");

        User loggedInUser = new User(userId, password);
        User user = userDao.findByUserId(userId);

        if (user != null && user.equals(loggedInUser)) {
            HttpSession session = request.getSession();
            session.setAttribute(USER_SESSION_KEY, user);
            return "redirect:/";
        }
        return "redirect:/user/loginFailed";
    }

    //    @RequestMapping("/user/login")
    public String loginV3(String userId, String password,
                          HttpServletRequest request) throws SQLException {
        log.info("LoginController.loginV3");

        User loggedInUser = new User(userId, password);
        User user = userDao.findByUserId(userId);

        if (user != null && user.equals(loggedInUser)) {
            HttpSession session = request.getSession();
            session.setAttribute(USER_SESSION_KEY, user);
            return "redirect:/";
        }
        return "redirect:/user/loginFailed";
    }

    @RequestMapping("/user/login")
    public String loginV4(@ModelAttribute User loggedInUser,
                          HttpServletRequest request) throws SQLException {
        log.info("LoginController.loginV4");

        User user = userDao.findByUserId(loggedInUser.getUserId());

        if (user != null && user.equals(loggedInUser)) {
            HttpSession session = request.getSession();
            session.setAttribute(USER_SESSION_KEY, user);
            return "redirect:/";
        }
        return "redirect:/user/loginFailed";
    }

    /**
     * TODO: logout
     */
    @RequestMapping("/user/logout")
    public String logout(HttpServletRequest request) {
        log.info("LoginController.logout");

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/";
    }

}
