package utils;

import java.sql.Connection;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Payments;
import entity.UserInfo;

public class MyUtils {

    public static final String ATT_NAME_CONNECTION = "ATTRIBUTE_FOR_CONNECTION";

    private static final String ATT_NAME_USER_NAME = "ATTRIBUTE_FOR_STORE_USER_NAME_IN_COOKIE";

    // Store Connection in request attribute.
    // (Information stored only exist during requests)
    public static void storeConnection(ServletRequest request, Connection conn) {
        request.setAttribute(ATT_NAME_CONNECTION, conn);
    }

    // Get the Connection object has been stored in attribute of the request.
    public static Connection getStoredConnection(ServletRequest request) {
        return (Connection) request.getAttribute(ATT_NAME_CONNECTION);
    }

    // Store user info in Session.
    public static void storeLoginedUser(HttpSession session, UserInfo loginedUser) {
        // On the JSP can access via ${loginedUser}
        session.setAttribute("loginedUser", loginedUser);
    }

    // Get the user information stored in the session.
    public static UserInfo getLoginedUser(HttpSession session) {
        return (UserInfo) session.getAttribute("loginedUser");
    }

    // Store user name in Session.
    public static void storeLoginedUserName(HttpSession session, String loginedUserName) {
        // On the JSP can access via ${loginedUserName}
        session.setAttribute("loginedUserName", loginedUserName);
    }

    // Get the user name stored in the session.
    public static String getLoginedUserName(HttpSession session) {
        return (String) session.getAttribute("loginedUserName");
    }

    // Store payment information in Session.
    public static void storeMobileTopUp(HttpSession session, Payments payment) {
        // On the JSP can access via ${loginedUserName}
        session.setAttribute("payment", payment);
    }

    // Get the payment information stored in the session.
    public static Payments getMobileTopUpPayment(HttpSession session) {
        // On the JSP can access via ${loginedUserName}
        return (Payments) session.getAttribute("payment");
    }

    // Store info in Cookie
    public static void storeUserCookie(HttpServletResponse response, UserInfo user) {
        System.out.println("Store user cookie");
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, user.getUserName());
        // 1 day (Converted to seconds)
        cookieUserName.setMaxAge(24 * 60 * 60);
        response.addCookie(cookieUserName);
    }

    public static String getUserNameInCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (ATT_NAME_USER_NAME.equals(cookie.getName())) {
                    return cookie.getValue();
                }
            }
        }
        return null;
    }

    // Delete cookie.
    public static void deleteUserCookie(HttpServletResponse response) {
        Cookie cookieUserName = new Cookie(ATT_NAME_USER_NAME, null);
        // 0 seconds (This cookie will expire immediately)
        cookieUserName.setMaxAge(0);
        response.addCookie(cookieUserName);
    }

}
