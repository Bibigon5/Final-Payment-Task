package logic;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "InsertServlet", value = "/registration")
public class InsertServlet extends HttpServlet {

    InsertDao insertDao = new InsertDao();

    public InsertServlet() {
        super();
    }
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at:").append(request.getContextPath());

        RequestDispatcher dispatcher = request.getRequestDispatcher("/insertRegister.jsp");
        dispatcher.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String quantity = request.getParameter("quantity");

        Insert insert = new Insert();
        insert.setTitle(title);
        insert.setAuthor(author);
        insert.setQuantity(quantity);

        try {
            insertDao.registerEmployee(insert);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        RequestDispatcher dispatcher = request.getRequestDispatcher("/insertDetails.jsp");
        dispatcher.forward(request,response);
    }
}
