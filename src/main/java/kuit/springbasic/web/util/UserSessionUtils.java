package kuit.springbasic.web.util;

import jakarta.servlet.http.HttpSession;
import kuit.springbasic.web.domain.User;

import static kuit.springbasic.config.Constant.USER_SESSION_KEY;

public class UserSessionUtils {

    public static User getUserFromSession(HttpSession session) {
        Object user = session.getAttribute(USER_SESSION_KEY);
        if (user == null) {
            return null;
        }
        return (User) user;
    }

    public static boolean isLoggedIn(HttpSession session) {
        return getUserFromSession(session) != null;
    }

}
