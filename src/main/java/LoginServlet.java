import com.revature.EmployeeDAO;
import com.revature.EmployeeDAOFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        req.getRequestDispatcher("styleSheet.html").include(req, resp);

        String username=req.getParameter("user_name");
        String password=req.getParameter("user_pass");

        EmployeeDAO dao = EmployeeDAOFactory.getEmployeeDAO();

        HttpSession session = req.getSession();
        session.setAttribute("username","");
        session.setAttribute("admin", false);


        //Is it a valid employee?
        if(!dao.isEmployee(username, password)) {
            //If no, Show login page with "Invalid Credentials" at the top
            out.println("<h1>Invalid Credentials</h1>");
            req.getRequestDispatcher("index.jsp").include(req, resp);
        } else{
            //Is it an admin?
            if(dao.isAdmin(username)){
                //If yes, go to admin menu
                session.setAttribute("username",username);
                session.setAttribute("admin", true);
                req.getRequestDispatcher("adminMenu").forward(req, resp);


            }
            else{
                //else, go to employee menu
                session.setAttribute("username",username);
                session.setAttribute("admin", false);
                req.getRequestDispatcher("employeeMenu").forward(req, resp);
                out.println("Not An Admin");

            }
        }





        out.close();
    }
}
