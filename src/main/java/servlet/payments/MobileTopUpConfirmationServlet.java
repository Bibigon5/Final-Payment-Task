package servlet.payments;

import entity.Payments;
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
import java.sql.Date;

@WebServlet(urlPatterns = { "/topUpConfirm" })
public class MobileTopUpConfirmationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public MobileTopUpConfirmationServlet() {
        super();
    }

    // Show mobile top-up confirmation page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();


        Payments payment = MyUtils.getMobileTopUpPayment(session);

        // Store info in request attribute, before forward to views
        request.setAttribute("payment", payment);

        RequestDispatcher dispatcher = this.getServletContext().
                getRequestDispatcher("/payments/mobileTopUpViewConfirm.jsp");

        dispatcher.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);
        HttpSession session = request.getSession();

        Payments payment = MyUtils.getMobileTopUpPayment(session);
        UserInfo loginedUser = MyUtils.getLoginedUser(session);
        long millis = System.currentTimeMillis();
        Date date = new Date(millis);

        payment.setPaymentDateAndTime(date);

        try {
            DBUtils.addPayment(conn, payment, loginedUser);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Redirect to payments page.
        response.sendRedirect(request.getContextPath() + "/payments");


    }
}
