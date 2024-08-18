package controller;

import dao.EmployeeDAO;
import dao.EmployeeDAOImpl;
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

@WebServlet("/manageEmployees")
public class ManageEmployeesServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(ManageEmployeesServlet.class);
    private final EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        switch (action) {
            case "show" -> {
                // Show all employees
                List<EmployeeDTO> employees = employeeDAO.getAllEmployees();
                request.setAttribute("employees", employees);
                request.getRequestDispatcher("showEmployees.jsp").forward(request, response);
            }
            case "add" -> {
                // Display the add employee form
                request.getRequestDispatcher("addEmployee.jsp").forward(request, response);
            }
            default -> {
                // Default action: redirect to the main page
                response.sendRedirect("dashboard.jsp");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "fetchEmployee" -> {
                    String employeeIdParam = request.getParameter("employeeId");
                    int employeeId;
                    try {
                        employeeId = Integer.parseInt(employeeIdParam);
                    } catch (NumberFormatException e) {
                        logger.error("Invalid employee ID format: {}", employeeIdParam, e);
                        response.sendRedirect("updateEmployee.jsp");
                        return;
                    }

                    EmployeeDTO employee = employeeDAO.getEmployeeById(employeeId);
                    if (employee != null) {
                        request.setAttribute("employeeToUpdate", employee);
                        request.getRequestDispatcher("updateEmployee.jsp").forward(request, response);
                    } else {
                        logger.warn("Employee not found for ID: {}", employeeId);
                        response.sendRedirect("updateEmployee.jsp");
                    }
                }
                case "update" -> {
                    EmployeeDTO employee = new EmployeeDTO();
                    employee.setEmployeeId(Integer.parseInt(request.getParameter("employeeId")));
                    employee.setRoleId(Integer.parseInt(request.getParameter("roleId")));
                    employee.setFirstName(request.getParameter("firstName"));
                    employee.setLastName(request.getParameter("lastName"));
                    employee.setCity(request.getParameter("city"));
                    employee.setProvince(request.getParameter("province"));
                    employee.setUsername(request.getParameter("username"));
                    employee.setPassword(request.getParameter("password"));
                    employeeDAO.updateEmployee(employee);
                    response.sendRedirect("manageEmployees?action=show");
                }
                case "add" -> {
                    EmployeeDTO employee = new EmployeeDTO();
                    employee.setRoleId(Integer.parseInt(request.getParameter("roleId")));
                    employee.setFirstName(request.getParameter("firstName"));
                    employee.setLastName(request.getParameter("lastName"));
                    employee.setCity(request.getParameter("city"));
                    employee.setProvince(request.getParameter("province"));
                    employee.setUsername(request.getParameter("username"));
                    employee.setPassword(request.getParameter("password"));
                    employeeDAO.addEmployee(employee);
                    response.sendRedirect("manageEmployees?action=show");
                }
                case "delete" -> {
                    int employeeId = Integer.parseInt(request.getParameter("employeeId"));
                    employeeDAO.deleteEmployee(employeeId);
                    logger.info("Employee with ID {} deleted successfully.", employeeId);
                    // Ensure proper redirection after deletion
                    response.sendRedirect("manageEmployees?action=show");
                }
                default -> response.sendRedirect("mainpage.jsp");
            }
        } catch (Exception e) {
            logger.error("Error processing request", e);
            response.sendRedirect("error.jsp");  // Redirect to an error page if something goes wrong
        }
    }
}
