package servlet.userInfo;

import entity.Payments;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@WebServlet(urlPatterns = { "/paymentCommit" })
public class PaymentCommitServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PaymentCommitServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);

        String paymentNumberStr = request.getParameter("paymentNumber");
        Integer paymentNumber = Integer.parseInt(paymentNumberStr);

        Payments payment = null;
        String errorString = null;
        String notification = null;
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");


        try {
            payment = DBUtils.getPayment(conn, paymentNumber);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        payment.setPaymentDateAndTime(formatForDateNow.format(dateNow));

        String number = payment.getCardNumber();

        try {
            if (Objects.equals(DBUtils.getPaymentStatus(conn, paymentNumber), "Prepared")) {
                try {
                    DBUtils.paymentBalanceDecrease(conn, payment);
                    DBUtils.commitPayment(conn, payment);

                    notification = "Payment was successfully dispatched";
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
                    .getRequestDispatcher("/userInfo/cardPaymentsView.jsp");
            dispatcher.forward(request, response);
        }
        // If everything nice.
        // Redirect to the payments listing page.
        else {
            request.setAttribute("notification", notification);
            response.sendRedirect(request.getContextPath() + "/cardPayments?cardNumber=" + number);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
