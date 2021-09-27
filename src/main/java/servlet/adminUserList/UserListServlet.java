package servlet.adminUserList;

import entity.Account;
import entity.UserInfo;
import utils.DBUtils;
import utils.MyUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = { "/userList" })
public class UserListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UserListServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Connection conn = MyUtils.getStoredConnection(request);// into try sec

        // Check User has logged on
        UserInfo loginedUser = MyUtils.getLoginedUser(session);

        // Not logged in
        if (loginedUser == null) {
            // Redirect to login page.
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }

        String errorString = null;
        List<UserInfo> userList = null;
        List<Account> requestList = null;

        try {
            userList = DBUtils.queryUser(conn);
            requestList = DBUtils.queryRequest(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }

        // Store info to the request attribute before forwarding.
        request.setAttribute("errorString", errorString);
        request.setAttribute("userList", userList);
        request.setAttribute("requestList", requestList);
        request.setAttribute("user", loginedUser);

        // If the user has logged in, then forward to the page
        // /WEB-INF/views/userInfoView.jsp
        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/admin/adminUserListView.jsp");
        dispatcher.forward(request, response);
        //сделать циклом поиск в базе карт юзера по его имени

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        String cardName = request.getParameter("cardName");
        String cardNumber = request.getParameter("cardNumber");


        Account card;
        UserInfo user;
        String user1;

        boolean hasError = false;
        String errorString = null;
        boolean carddb;


        if (cardName == null || cardNumber == null || cardName.length() == 0 || cardNumber.length() == 0) {
            hasError = true;
            errorString = "Required card name and card number!";
        } else {
            Connection conn = MyUtils.getStoredConnection(request);
            try {
                // Find the user in the DB.
                carddb = DBUtils.findCardByNumber(conn, cardNumber);

                if (carddb) {
                    hasError = true;
                    errorString = "Card number is already taken";
                }

            } catch (SQLException e) {
                e.printStackTrace();
                hasError = true;
                errorString = e.getMessage();
            }
        }
        // If error, forward to /registrationView.jsp
        if (hasError) {
            card = new Account();
            card.setCardName(cardName);

            // Store information in request attribute, before forward.
            request.setAttribute("errorString", errorString);
            request.setAttribute("user", card);

            // Forward to /WEB-INF/views/registrationView.jsp
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/userInfoView.jsp");

            dispatcher.forward(request, response);
        }
        // If no error
        // Store card information in DB
        // And redirect to login page.
        else {
            Connection conn = MyUtils.getStoredConnection(request);

            card = new Account();
            user = new UserInfo();
            user1 = MyUtils.getLoginedUserName(session);


            user.setUserName(user1);
            card.setCardName(cardName);
            card.setCardNumber(cardNumber);

            try {
                DBUtils.addCard(conn, card, user);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            // Redirect to login page.
            response.sendRedirect(request.getContextPath() + "/userInfo");
        }
    }

}
