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
import java.util.List;

public class MyRequestListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        req.getRequestDispatcher("styleSheet.html").include(req, resp);

        HttpSession session = req.getSession();
        RequestDAO dao = RequestDAOFactory.getRequestDAO();

        String username;
        Request request;

        boolean admin = (boolean) session.getAttribute("admin");
        if(admin){
            username = req.getParameter("user_name");
        } else{
            username = (String) session.getAttribute("username");
        }

        List list = dao.listMyPendingRequests(username);

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

        list = dao.listMyResolvedRequests(username);

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

        if(admin){req.getRequestDispatcher("backToAdmMenu.html").include(req, resp);}
        else {req.getRequestDispatcher("backToEmpMenu.html").include(req, resp);}

        out.close();
    }
}
