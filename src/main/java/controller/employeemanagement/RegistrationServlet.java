package controller.employeemanagement;

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
import java.io.IOException;

@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
    private EmployeeDAO employeeDAO = new EmployeeDAOImpl();
    private static final Logger logger = LogManager.getLogger(RegistrationServlet.class);


    @Override
    public void init() throws ServletException {
        super.init();
        logger.info("RegistrationServlet initialized");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String city = request.getParameter("city");
        String province = request.getParameter("province");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        int roleId = Integer.parseInt(request.getParameter("roleId"));

        EmployeeDTO employee = new EmployeeDTO();
        employee.setFirstName(firstName);
        employee.setLastName(lastName);
        employee.setCity(city);
        employee.setProvince(province);
        employee.setUsername(username);
        employee.setPassword(password);
        employee.setRoleId(roleId);
        employee.setEmail(email);
        employee.setPhone(phone);

        try {
            employeeDAO.addEmployee(employee);
            logger.info("Employee registered: {}", employee.getUsername());
            response.sendRedirect("login.jsp");
        } catch (Exception e) {
            logger.error("Error during registration: {}", e.getMessage());
            request.setAttribute("error", "Registration failed. Please try again.");
            request.getRequestDispatcher("register.jsp").forward(request, response);
        }
    }
}