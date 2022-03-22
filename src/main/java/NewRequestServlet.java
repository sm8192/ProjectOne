import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

public class NewRequestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        req.getRequestDispatcher("styleSheet.html").include(req, resp);

        HttpSession session = req.getSession();
        String username = (String) session.getAttribute("username");

        out.println("<form action='requestAdder' method='post'>" +
                "<input type='hidden' value='" + username + "'>");
        req.getRequestDispatcher("requestForm.html").include(req, resp);
        out.println("<input type='submit' value='Submit Request'>" +
                "</form>");

        req.getRequestDispatcher("backToEmpMenu.html").include(req, resp);

        out.close();

    }
}
