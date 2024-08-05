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

@WebServlet("/manageEmployees")
public class ManageEmployeesServlet extends HttpServlet {
    private static final Logger logger = LogManager.getLogger(ManageEmployeesServlet.class);

    private EmployeeDAO employeeDAO = new EmployeeDAOImpl();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<EmployeeDTO> employees = employeeDAO.getAllEmployees();
        request.setAttribute("employees", employees);
        request.getRequestDispatcher("manageEmployees.jsp").forward(request, response);
        logger.info("Number of employees retrieved: " + employeeDAO.getAllEmployees().size());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ("delete".equals(action)) {
            int employeeId = Integer.parseInt(request.getParameter("employeeId"));
            employeeDAO.deleteEmployee(employeeId);
        } else if ("update".equals(action)) {
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
        } else if ("add".equals(action)) {
            EmployeeDTO employee = new EmployeeDTO();
            employee.setRoleId(Integer.parseInt(request.getParameter("roleId")));
            employee.setFirstName(request.getParameter("firstName"));
            employee.setLastName(request.getParameter("lastName"));
            employee.setCity(request.getParameter("city"));
            employee.setProvince(request.getParameter("province"));
            employee.setUsername(request.getParameter("username"));
            employee.setPassword(request.getParameter("password"));
            employeeDAO.addEmployee(employee);
        }
        response.sendRedirect("manageEmployees");
    }
}
