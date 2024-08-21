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
import java.util.List;

@WebServlet("/manageEmployees")  // Servlet annotation mapping to handle requests at /manageEmployees
public class ManageEmployeesServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(ManageEmployeesServlet.class);  // Logger instance for logging events
    private final EmployeeDAO employeeDAO = new EmployeeDAOImpl();  // Data Access Object (DAO) for Employee operations

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");  // Retrieve the action parameter from the request

        switch (action) {
            case "show" -> {
                // Case "show": Retrieve and display all employees
                List<EmployeeDTO> employees = employeeDAO.getAllEmployees();
                request.setAttribute("employees", employees);
                request.getRequestDispatcher("showEmployees.jsp").forward(request, response);  // Forward to the JSP page to display employees
            }
            case "add" -> {
                // Case "add": Display the form to add a new employee
                request.getRequestDispatcher("addEmployee.jsp").forward(request, response);  // Forward to the JSP page to display the add employee form
            }
            default -> {
                // Default case: Redirect to the main page if action is unknown
                response.sendRedirect("mainpage.jsp");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");  // Retrieve the action parameter from the request

        try {
            switch (action) {
                case "fetchEmployee" -> {
                    // Case "fetchEmployee": Fetch employee details based on ID for updating
                    String employeeIdParam = request.getParameter("employeeId");
                    int employeeId;
                    try {
                        employeeId = Integer.parseInt(employeeIdParam);  // Parse the employee ID from the request parameter
                    } catch (NumberFormatException e) {
                        // Handle invalid employee ID format
                        logger.error("Invalid employee ID format: {}", employeeIdParam, e);
                        response.sendRedirect("updateEmployee.jsp");  // Redirect to the update form in case of error
                        return;
                    }

                    EmployeeDTO employee = employeeDAO.getEmployeeById(employeeId);  // Retrieve employee details from the database
                    if (employee != null) {
                        // If employee exists, set attribute and forward to update form
                        request.setAttribute("employeeToUpdate", employee);
                        request.getRequestDispatcher("updateEmployee.jsp").forward(request, response);
                    } else {
                        // If employee not found, log a warning and redirect to update form
                        logger.warn("Employee not found for ID: {}", employeeId);
                        response.sendRedirect("updateEmployee.jsp");
                    }
                }
                case "update" -> {
                    // Case "update": Update existing employee details
                    EmployeeDTO employee = new EmployeeDTO();
                    employee.setEmployeeId(Integer.parseInt(request.getParameter("employeeId")));  // Set employee ID
                    employee.setRoleId(Integer.parseInt(request.getParameter("roleId")));  // Set role ID
                    employee.setFirstName(request.getParameter("firstName"));  // Set first name
                    employee.setLastName(request.getParameter("lastName"));  // Set last name
                    employee.setCity(request.getParameter("city"));  // Set city
                    employee.setProvince(request.getParameter("province"));  // Set province
                    employee.setUsername(request.getParameter("username"));  // Set username
                    employee.setPassword(request.getParameter("password"));  // Set password (consider hashing for security)
                    employee.setEmail(request.getParameter("email"));  // Set email
                    employee.setPhone(request.getParameter("phone"));  // Set phone number
                    employeeDAO.updateEmployee(employee);  // Update the employee in the database
                    response.sendRedirect("manageEmployees?action=show");  // Redirect to the employee list page
                }
                case "add" -> {
                    // Case "add": Add a new employee
                    EmployeeDTO employee = new EmployeeDTO();
                    employee.setRoleId(Integer.parseInt(request.getParameter("roleId")));  // Set role ID
                    employee.setFirstName(request.getParameter("firstName"));  // Set first name
                    employee.setLastName(request.getParameter("lastName"));  // Set last name
                    employee.setCity(request.getParameter("city"));  // Set city
                    employee.setProvince(request.getParameter("province"));  // Set province
                    employee.setUsername(request.getParameter("username"));  // Set username
                    employee.setPassword(request.getParameter("password"));  // Set password (consider hashing for security)
                    employee.setEmail(request.getParameter("email"));  // Set email
                    employee.setPhone(request.getParameter("phone"));  // Set phone number
                    employeeDAO.addEmployee(employee);  // Add the new employee to the database
                    logger.info("New employee added with username: {}", employee.getUsername());  // Log the addition of a new employee
                    response.sendRedirect("manageEmployees?action=show");  // Redirect to the employee list page
                }
                case "delete" -> {
                    // Case "delete": Delete an employee based on ID
                    int employeeId = Integer.parseInt(request.getParameter("employeeId"));  // Parse employee ID from the request parameter
                    employeeDAO.deleteEmployee(employeeId);  // Delete the employee from the database
                    logger.info("Employee with ID {} deleted successfully.", employeeId);  // Log the deletion of the employee
                    response.sendRedirect("manageEmployees?action=show");  // Redirect to the employee list page after deletion
                }
                default -> response.sendRedirect("mainpage.jsp");  // Default case: Redirect to the main page if action is unknown
            }
        } catch (Exception e) {
            // Catch any exceptions, log the error, and redirect to the main page
            logger.error("Error processing request", e);
            response.sendRedirect("mainpage.jsp");
        }
    }
}
