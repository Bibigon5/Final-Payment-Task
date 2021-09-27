package servlet.adminUserList;

import entity.Account;
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

@WebServlet(urlPatterns = { "/getUsersCards" })
public class GetUserCardsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public GetUserCardsServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Connection conn = MyUtils.getStoredConnection(request);

        String userName =  request.getParameter("userName");

        String errorString = null;
        List<Account> list = null;

        try {
            list = DBUtils.queryCard(conn, userName);
        } catch (SQLException e) {
            e.printStackTrace();
            errorString = e.getMessage();
        }

        // Store info to the request attribute before forwarding.
        request.setAttribute("errorString", errorString);
        request.setAttribute("cardList", list);

        // If the user has logged in, then forward to the page
        // /WEB-INF/views/userInfoView.jsp
        RequestDispatcher dispatcher //
                = this.getServletContext().getRequestDispatcher("/admin/userCardsView.jsp");
        dispatcher.forward(request, response);
        //сделать циклом поиск в базе карт юзера по его имени

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}