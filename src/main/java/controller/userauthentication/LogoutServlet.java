package controller.userauthentication;
import dto.EmployeeDTO;


import javax.servlet.ServletException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/LogoutServlet")  // Servlet annotation to handle requests at /LogoutServlet
public class LogoutServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(LogoutServlet.class);

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Handles POST requests to process logout
        HttpSession session = request.getSession();
        if (session != null && session.getAttribute("employee") != null) {
            // Check if a session exists and if it contains an employee attribute
            String username = ((EmployeeDTO) session.getAttribute("employee")).getUsername();
            session.invalidate();
            logger.info("Employee logged out: {}", username);
        }
        // Redirect to the login page after logout
        response.sendRedirect("login.jsp");
    }
}