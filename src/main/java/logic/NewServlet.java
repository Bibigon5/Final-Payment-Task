package logic;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "NewServlet", value = "/NewServlet")
public class NewServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter pw = response.getWriter();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            Connection conn = DriverManager.getConnection
                    ("jdbc:mysql://localhost:3306/java_ee_db",
                            "root", "321bazed789!");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT title FROM books");

            String INSERT_USERS_SQL = "INSERT INTO books" + "(title, author, quantity) VALUES" +
                    "(?, ?, ?);";

            PreparedStatement statement = conn.prepareStatement(INSERT_USERS_SQL);

            while (rs.next()) {
                pw.println(rs.getString("title"));
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
