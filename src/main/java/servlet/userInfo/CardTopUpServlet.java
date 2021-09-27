package servlet.userInfo;

import entity.Account;
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

@WebServlet(urlPatterns = { "/cardTopUp" })
public class CardTopUpServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CardTopUpServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Connection conn = MyUtils.getStoredConnection(request);

        String cardNumber =  request.getParameter("cardNumber");

        Account card = null;

        String errorString = null;

        try {
            card = DBUtils.findCard(conn, cardNumber);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }

        // If no error.
        // The product does not exist to edit.
        // Redirect to productList page.
        if (errorString != null && card == null) {
            response.sendRedirect(request.getServletPath() + "/productList");
            return;
        }

        // Store errorString in request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("card", card);


        RequestDispatcher dispatcher = request.getServletContext()
                .getRequestDispatcher("/userInfo/cardTopUpView.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        Connection conn = MyUtils.getStoredConnection(request);

        String cardNumber = request.getParameter("cardNumber");
        String cardBalanceTopUpStr = request.getParameter("cardBalanceTopUp");
        double cardBalanceTopUp = 0.0;

        try {
            cardBalanceTopUp = Double.parseDouble(cardBalanceTopUpStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        Account card = new Account(cardNumber, cardBalanceTopUp);

        String errorString = null;

        try {
            DBUtils.cardTopUp(conn, card);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }
        // Store infomation to request attribute, before forward to views.
        request.setAttribute("errorString", errorString);
        request.setAttribute("card", card);

        // If error, forward to Edit page.
        if (errorString != null) {
            RequestDispatcher dispatcher = request.getServletContext()
                    .getRequestDispatcher("/userInfo/userInfoView.jsp");
            dispatcher.forward(request, response);
        }
        // If everything is nice.
        // Redirect to the product listing page.
        else {
            response.sendRedirect(request.getContextPath() + "/userInfo");
        }


    }

}
