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

public class FullRequestListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        req.getRequestDispatcher("styleSheet.html").include(req, resp);

        RequestDAO dao = RequestDAOFactory.getRequestDAO();
        List list;
        String filter = req.getParameter("filter");

        if(filter != null)
        {
            if(filter.equals("resolved"))
            {
                list=dao.listAllResolvedRequests();
            } else{
                list=dao.listAllPendingRequests();
            }
        }else{
            list= dao.listRequests();
        }

        Request request = new Request();

        out.println("<div><table>" +
                "<tr>" +
                "<th>id</th>" +
                "<th>Amount</th>" +
                "<th>Employee</th>" +
                "<th>Note</th>" +
                "<th>Status</th>" +
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
                    request.getStatus() +
                    "</td>" +
                    "</tr>");

        }

        out.println("</table></div>");


        out.println("<form action='fullRequestList' method='get'>" +
                "<input type='hidden' name='filter' value='pending'>" +
                "<input type='submit' value='See Pending Only'>" +
                "</form>");

        out.println("<form action='fullRequestList' method='get'>" +
                "<input type='hidden' name='filter' value='resolved'>" +
                "<input type='submit' value='See Resolved Only'>" +
                "</form>");


        req.getRequestDispatcher("backToAdmMenu.html").include(req,resp);

        out.close();
    }
}
