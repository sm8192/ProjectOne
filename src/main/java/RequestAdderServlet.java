import com.revature.Request;
import com.revature.RequestDAO;
import com.revature.RequestDAOFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

public class RequestAdderServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        req.getRequestDispatcher("styleSheet.html").include(req, resp);

        HttpSession session = req.getSession();
        RequestDAO dao = RequestDAOFactory.getRequestDAO();

        float amount = Float.parseFloat(req.getParameter("user_amount"));
        String employee = (String) session.getAttribute("username");
        String note = req.getParameter("user_note");

        Request request = new Request(amount, employee, note);

        dao.newRequest(request);

        out.println("<h1>Request sent</h1>");

        req.getRequestDispatcher("backToEmpMenu.html").include(req, resp);

        out.close();
    }
}
