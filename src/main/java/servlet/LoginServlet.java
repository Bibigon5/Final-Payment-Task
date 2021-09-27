package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.UserInfo;
import utils.DBUtils;
import utils.MyUtils;

@WebServlet(urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    // Show Login page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Forward to /loginView.jsp
        // (Users can not access directly into JSP pages placed in WEB-INF)
        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/loginView.jsp");

        dispatcher.forward(request, response);

    }

    // When the user enters userName & password, and click Submit.
    // This method will be executed.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String rememberMeStr = request.getParameter("rememberMe");
        boolean remember = "Y".equals(rememberMeStr);

        UserInfo user = null;
        boolean hasError = false;
        String errorString = null;

        if (userName == null || password == null || userName.length() == 0 || password.length() == 0) {
            hasError = true;
            errorString = "Required username and password!";
        } else {
            Connection conn = MyUtils.getStoredConnection(request);
            try {
                // Find the user in the DB.
                user = DBUtils.findUser(conn, userName, password);

                if (user == null) {
                    hasError = true;
                    errorString = "User is not found";
                } else if (Objects.equals(DBUtils.getUserStatus(conn, userName), "BLOCKED")) {
                    hasError = true;
                    errorString = "Sorry, but you were blocked in this payment system.";
                }
            } catch (SQLException e) {
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
        }
        // If error, forward to /loginView.jsp
        if (hasError) {
            user = new UserInfo();
            user.setUserName(userName);
            user.setPassword(password);

            // Store information in request attribute, before forward.
            request.setAttribute("errorString", errorString);
            request.setAttribute("user", user);

            // Forward to /WEB-INF/views/login.jsp
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/loginView.jsp");

            dispatcher.forward(request, response);
        }
        // If no error
        // Store user information in Session
        // And redirect to userInfo page.
        else {
            HttpSession session = request.getSession();
            MyUtils.storeLoginedUser(session, user);
            MyUtils.storeLoginedUserName(session, userName);

            // If user checked "Remember me".
            if (remember) {
                MyUtils.storeUserCookie(response, user);
            }
            // Else delete cookie.
            else {
                MyUtils.deleteUserCookie(response);
            }

            // Redirect to userInfo page.
            response.sendRedirect(request.getContextPath() + "/userInfo");
        }
    }

}
