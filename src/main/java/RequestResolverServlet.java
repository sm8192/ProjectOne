import com.revature.Request;
import com.revature.RequestDAO;
import com.revature.RequestDAOFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class RequestResolverServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        req.getRequestDispatcher("styleSheet.html").include(req, resp);

        RequestDAO dao = RequestDAOFactory.getRequestDAO();
        List list = dao.listAllPendingRequests();
        Request request = new Request();


        out.println("<div><table>" +
                "<tr>" +
                "<th>id</th>" +
                "<th>Amount</th>" +
                "<th>Employee</th>" +
                "<th>Note</th>" +
                "<th>Accept?</th>" +
                "<th>Deny?</th>" +
                "</tr>");

        for (Object object: list) {
            request = (Request) object;
            out.println("<tr>" +
                    "<td>" +
                    request.getId() +
                    "</td>" +
                    "<td>" +
                    request.getAmount() +
                    "</td>" +
                    "<td>" +
                    request.getEmployee() +
                    "</td>" +
                    "<td>" +
                    request.getNote() +
                    "</td>" +
                    "<td>" +
                    "<form action='requestResolver' method='post'>" +
                    "<input type='hidden' name='id' value='"+request.getId()+"'>" +
                    "<input type='hidden' name='resolution' value='accepted'>" +
                    "<input type='submit' value='Accept'>" +
                    "</form>" +
                    "</td>" +
                    "<td>" +
                    "<form action='requestResolver' method='post'>" +
                    "<input type='hidden' name='id' value='"+request.getId()+"'>" +
                    "<input type='hidden' name='resolution' value='denied'>" +
                    "<input type='submit' value='Deny'>" +
                    "</form>" +
                    "</td></tr>");

        }

        out.println("</table></div>");

        req.getRequestDispatcher("backToAdmMenu.html").include(req,resp);


        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        req.getRequestDispatcher("styleSheet.html").include(req, resp);

        RequestDAO dao = RequestDAOFactory.getRequestDAO();

        int id = Integer.parseInt(req.getParameter("id"));
        String resolution = req.getParameter("resolution");
        Request request = dao.getRequest(id);

        request.setStatus(resolution);

        dao.resolveRequest(request);

        out.println("<h1>Request Updated</h1>");

        req.getRequestDispatcher("backToAdmMenu.html").include(req,resp);

        out.close();

    }
}
