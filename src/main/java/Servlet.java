import logic.Cart;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/servlet")
public class Servlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession session = req.getSession();

        String user = (String) session.getAttribute("current_user");

        if (user == null) {
            //response for anonymous user
        } else {
            //response for authorized user
        }

        /*Cart cart = (Cart) session.getAttribute("cart");

        String name = req.getParameter("name");
        int quantity = Integer.parseInt(req.getParameter("quantity"));

        if(cart == null) {
            cart = new Cart();
            cart.setName(name);
            cart.setQuintity(quantity);
        }

        session.setAttribute("cart", cart);
        getServletContext().getRequestDispatcher("/showCart.jsp").forward(req, resp);*/

        /*PrintWriter pw = resp.getWriter();
        pw.println("<html>");
        pw.println("<h1> Your count is: " + count + " <h1>");
        pw.println("<html>");*/


        //RequestDispatcher dispather = getServletContext().getRequestDispatcher("/newfile.jsp");
     //dispather.forward(req, resp);
    }
}
