import com.revature.Employee;
import com.revature.EmployeeDAO;
import com.revature.EmployeeDAOFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

public class ProfileServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        req.getRequestDispatcher("styleSheet.html").include(req, resp);

        HttpSession session= req.getSession();
        EmployeeDAO dao = EmployeeDAOFactory.getEmployeeDAO();

        String name = (String) session.getAttribute("username");

        Employee employee = dao.getEmployee(name);


        out.println("<h2>Username: "+employee.getUsername()+"</h2>");
        out.println("<h2>Name: "+employee.getName()+"</h2>");
        out.println("<h2>Email: "+employee.getEmail()+"</h2>");

        out.println("<br>" +
                "<form action='profileUpdate' method='get'>" +
                "<input type='submit' value='update'>" +
                "</form>");


        req.getRequestDispatcher("backToEmpMenu.html").include(req, resp);

        out.close();
    }
}
