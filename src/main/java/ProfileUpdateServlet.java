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

public class ProfileUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        req.getRequestDispatcher("styleSheet.html").include(req, resp);

        out.println("<form action='profileUpdate' method='post'>");
        req.getRequestDispatcher("profileForm.html").include(req, resp);
        out.println("<input type='submit' value='update'>");
        out.println("</form>");


        req.getRequestDispatcher("backToEmpMenu.html").include(req, resp);

        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        req.getRequestDispatcher("styleSheet.html").include(req, resp);

        HttpSession session = req.getSession();
        EmployeeDAO dao = EmployeeDAOFactory.getEmployeeDAO();

        String oldName = (String) session.getAttribute("username");
        Employee employee = dao.getEmployee(oldName);

        employee.setName(req.getParameter("user_name"));
        employee.setEmail(req.getParameter("user_email"));
        dao.updateProfile(employee);

        out.println("Changes saved");

        req.getRequestDispatcher("backToEmpMenu.html").include(req, resp);

        out.close();
    }
}
