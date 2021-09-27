package servlet;


import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entity.UserInfo;
import utils.DBUtils;
import utils.MyUtils;

@WebServlet(urlPatterns = { "/register" })
public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RegistrationServlet() {
        super();
    }

    // Show Registration page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Forward to /loginView.jsp
        // (Users can not access directly into JSP pages placed in WEB-INF)
        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/registrationView.jsp");

        dispatcher.forward(request, response);

    }

    // When the user enters userName & password, and click Submit.
    // This method will be executed.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userName = request.getParameter("userName");
        String gender = request.getParameter("gender");
        String password = request.getParameter("password");

        UserInfo user;
        boolean userdb;
        boolean hasError = false;
        String errorString = null;

        if (userName == null || password == null || userName.length() == 0 || password.length() == 0) {
            hasError = true;
            errorString = "Required username and password!";
        } else {
            Connection conn = MyUtils.getStoredConnection(request);
            try {
                // Find the user in the DB.
                userdb = DBUtils.findUserByNickname(conn, userName);

                if (userdb) {
                    hasError = true;
                    errorString = "User name is already taken";
                }


            } catch (SQLException e) {
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
        }
        // If error, forward to /registrationView.jsp
        if (hasError) {
            user = new UserInfo();
            user.setGender(userName);
            user.setPassword(password);

            // Store information in request attribute, before forward.
            request.setAttribute("errorString", errorString);
            request.setAttribute("user", user);

            // Forward to /WEB-INF/views/registrationView.jsp
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/registrationView.jsp");

            dispatcher.forward(request, response);
        }
        // If no error
        // Store user information in DB
        // And redirect to login page.
        else {
            Connection conn = MyUtils.getStoredConnection(request);

            user = new UserInfo();
            user.setUserName(userName);
            user.setGender(gender);
            user.setPassword(password);

            try {
                DBUtils.registerUser(conn, user);
            } catch (SQLException e) {
                e.printStackTrace();
            }


            // Redirect to login page.
            response.sendRedirect(request.getContextPath() + "/login");
        }
    }

}

