import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

public class AdminMenuServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        HttpSession session = req.getSession();
        req.getRequestDispatcher("styleSheet.html").include(req, resp);

        out.println("<h1> Welcome," + session.getAttribute("username") + "</h1>");

        req.getRequestDispatcher("adminMenu.html").include(req, resp);


        out.close();

    }
}
