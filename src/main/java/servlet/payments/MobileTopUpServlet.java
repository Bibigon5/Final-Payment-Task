package servlet.payments;

import logic1.Account;
import logic1.Product;
import logic1.UserInfo;
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
import java.nio.channels.AcceptPendingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = { "/topUp" })
public class MobileTopUpServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MobileTopUpServlet() {
        super();
    }

    // Show payments page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        Connection conn = MyUtils.getStoredConnection(request);
        String errorString = null;
        List<Account> list = null;
        String user = MyUtils.getLoginedUserName(session);
        //Добавить ошибку если ниче не найдено
        try {
            list = DBUtils.queryCard(conn, user);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        // Store info in request attribute, before forward to views
        request.setAttribute("errorString", errorString);
        request.setAttribute("cardList", list);

        Account card = new Account();




        RequestDispatcher dispatcher = this.getServletContext().
                getRequestDispatcher("/mobileTopUpView.jsp");

        dispatcher.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);

    }

}

