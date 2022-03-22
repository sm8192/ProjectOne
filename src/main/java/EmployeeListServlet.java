import com.revature.Employee;
import com.revature.EmployeeDAO;
import com.revature.EmployeeDAOFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class EmployeeListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        req.getRequestDispatcher("styleSheet.html").include(req, resp);

        EmployeeDAO dao = EmployeeDAOFactory.getEmployeeDAO();

        List list = dao.listAllEmployees();
        Employee employee;
        String admin;

        out.println("<div><table>" +
                "<tr>" +
                "<th>id</th>" +
                "<th>username</th>" +
                "<th>name</th>" +
                "<th>email</th>" +
                "<th>Administrator?</th>" +
                "</tr>");

        for (Object object : list) {
            employee = (Employee) object;
            if (employee.isAdministrator()) {
                admin = "yes";
            } else {
                admin = "no";
            }

            out.println("<tr>");
            out.println("<td>" +
                    employee.getId() +
                    "</td>" +
                    "<td>" +
                    employee.getUsername() +
                    "</td>" +
                    "<td>" +
                    employee.getName() +
                    "</td>" +
                    "<td>" +
                    employee.getEmail() +
                    "</td>" +
                    "<td>" +
                    admin +
                    "</td>" +
                    "<td>" +
                    "<form action='myRequestList' method='get'>" +
                    "<input type='hidden' name='user_name' value='" +employee.getUsername()+ "'>" +
                    "<input type='submit' value='See Requests'>" +
                    "</form>" +
                    "</td></tr>");

        }


            out.println("</table></div>");




        req.getRequestDispatcher("backToAdmMenu.html").include(req,resp);
        out.close();
    }
}
