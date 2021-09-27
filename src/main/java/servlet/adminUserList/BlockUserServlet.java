package servlet.adminUserList;

import entity.UserInfo;
import utils.DBUtils;
import utils.MyUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

@WebServlet(urlPatterns = { "/blockUser" })
public class BlockUserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public BlockUserServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);

        String userName = request.getParameter("userName");

        String errorString = null;
        String notification = null;

        UserInfo user = new UserInfo();
        user.setUserName(userName);

        try {
            if (Objects.equals(DBUtils.getUserStatus(conn, userName), "UNBLOCKED")) {
                try {
                    DBUtils.userBlock(conn, user);
                    notification = "User was successfully blocked";
                } catch (SQLException e) {
                    e.printStackTrace();
                    errorString = e.getMessage();
                }
            } else {
                try {
                    DBUtils.userUnblock(conn, user);
                    notification = "User was successfully unblocked";
                } catch (SQLException e) {
                    e.printStackTrace();
                    errorString = e.getMessage();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }


        // If has an error, redirect to the error page.
        if (errorString != null) {
            // Store the information in the request attribute, before forward to views.
            request.setAttribute("errorString", errorString);
            //
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/admin/adminUserListView.jsp");
            dispatcher.forward(request, response);
        }
        // If everything nice.
        // Redirect to the product listing page.
        else {
            request.setAttribute("notification", notification);
            response.sendRedirect(request.getContextPath() + "/userList");
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
