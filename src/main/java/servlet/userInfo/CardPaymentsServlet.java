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
import java.util.List;

@WebServlet(urlPatterns = { "/cardPayments" })
public class CardPaymentsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public CardPaymentsServlet() {
        super();
    }

    // Show mobile top-up page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);

        String cardNumber =  request.getParameter("cardNumber");

        String errorString = null;
        List<Payments> list = null;
        Double cardBalance = null;

        try {
            list = DBUtils.queryPayments(conn, cardNumber);
            cardBalance = DBUtils.getCardBalance(conn, cardNumber);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }

        // Store info to the request attribute before forwarding.
        request.setAttribute("errorString", errorString);
        request.setAttribute("paymentsList", list);
        request.setAttribute("cardBalance", cardBalance);

        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/userInfo/cardPaymentsView.jsp");
        dispatcher.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
