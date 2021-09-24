package servlet.payments;

import entity.Account;
import entity.Payments;
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

@WebServlet(urlPatterns = { "/topUp" })
public class MobileTopUpServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MobileTopUpServlet() {
        super();
    }

    // Show mobile top-up page.
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
            list = DBUtils.queryCardUnblocked(conn, user);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }

        if (list == null) {
            errorString = "You haven't any available cards. Try adding one in \"User Info\" section";
        }

        // Store info in request attribute, before forward to views
        request.setAttribute("errorString", errorString);
        request.setAttribute("cardList", list);


        RequestDispatcher dispatcher = this.getServletContext().
                getRequestDispatcher("/mobileTopUpView.jsp");

        dispatcher.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);

        String card = request.getParameter("card");
        String telephone = request.getParameter("telephone");
        String amountStr = request.getParameter("amount");
        String purpose = request.getParameter("purpose");
        double amount = 0.0;

        Payments payment = new Payments();
        Account acc = new Account();

        boolean hasError = false;
        String errorString = null;
        Double cardBalance;

        try {
            amount = Double.parseDouble(amountStr);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //ввести некие проверки
        if ( amount > 100000.00) {
            errorString = "This payment system supports payments only up to 100.000 UAH";
            hasError = true;
        } else {
            if (amount < 10) {
                errorString = "Minimum top-up amount is 10.00 UAH";
                hasError = true;
            } else {
                try {
                    //Check card balance
                    if (amount > DBUtils.getCardBalance(conn, card)) {
                        errorString = "Looks like you don't have enough funds on this card. Choose another one or try to top it up on \"User Info\" section";
                        hasError = true;
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    hasError = true;
                    errorString = e.getMessage();
                }
            }
        }

        // If error, forward to /loginView.jsp
        if (hasError) {

            // Store information in request attribute, before forward.
            request.setAttribute("errorString", errorString);

            // Forward to /WEB-INF/views/login.jsp
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/mobileTopUpView.jsp");

            dispatcher.forward(request, response);
        } else {
            HttpSession session = request.getSession();

            payment.setCardNumber(card);
            payment.setPaymentTelephone(telephone);
            payment.setPaymentAmount(amount);
            payment.setPaymentPurpose(purpose);

            MyUtils.storeMobileTopUp(session, payment);

            // Redirect to confirmation page.
            response.sendRedirect(request.getContextPath() + "/topUpConfirm");
        }

    }

}

