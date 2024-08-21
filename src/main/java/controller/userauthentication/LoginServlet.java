package controller.userauthentication;

import dao.employee.EmployeeDAO;
import dao.employee.EmployeeDAOImpl;
import dto.EmployeeDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LoginServlet")  // Servlet annotation to handle requests at /LoginServlet
public class LoginServlet extends HttpServlet {
    private EmployeeDAO employeeDAO = new EmployeeDAOImpl();
    private static final Logger logger = LogManager.getLogger(LoginServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handles GET requests to display the login form
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handles POST requests to process login
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            // Attempt to retrieve the employee with the provided username and password
            EmployeeDTO employee = employeeDAO.getEmployeeByUsernameAndPassword(username, password);
            if (employee != null) {
                // If employee exists, create a session and set the employee attribute
                HttpSession session = request.getSession();
                session.setAttribute("employee", employee);
                logger.info("Employee logged in: {}", username);
                response.sendRedirect("mainpage.jsp");
            } else {
                // If no employee found, log the invalid attempt and show error message
                logger.warn("Invalid login attempt: {}", username);
                request.setAttribute("error", "Invalid username or password");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (Exception e) {
            // Handle any exceptions that occur during login processing
            logger.error("Error during login: {}", e.getMessage());
            request.setAttribute("error", "Login failed. Please try again.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}