package servlet.payments;

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
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(urlPatterns = { "/payments" })
public class PaymentsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PaymentsServlet() {
        super();
    }

    // Show payments page.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();

        // Check User has logged on
        UserInfo loginedUser = MyUtils.getLoginedUser(session);

        // Not logged in
        if (loginedUser == null) {
            // Redirect to login page.
            response.sendRedirect(request.getContextPath() + "/login");
            return;
        }
        // Store info to the request attribute before forwarding.
        request.setAttribute("user", loginedUser);
        RequestDispatcher dispatcher = this.getServletContext().
                getRequestDispatcher("/paymentsView.jsp");

        dispatcher.forward(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
