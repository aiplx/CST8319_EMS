package dao.employee;

import dto.EmployeeDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    private static final Logger logger = LogManager.getLogger(EmployeeDAOImpl.class);

    @Override
    public void addEmployee(EmployeeDTO employee) {
        // SQL query to insert a new employee into the database
        String sql = "INSERT INTO employee (role_id, first_name, last_name, city, province, username, password, phone_number, email) VALUES (?, ?, ?, ?, ?, ?, ?,?,?)";
        try (Connection conn = DatabaseUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employee.getRoleId());
            stmt.setString(2, employee.getFirstName());
            stmt.setString(3, employee.getLastName());
            stmt.setString(4, employee.getCity());
            stmt.setString(5, employee.getProvince());
            stmt.setString(6, employee.getUsername());
            stmt.setString(7, employee.getPassword());
            stmt.setString(8,employee.getPhone());
            stmt.setString(9,employee.getEmail());
            stmt.executeUpdate();
            logger.info("Employee added: {}", employee);
        } catch (SQLException e) {
            logger.error("Error adding employee", e);// Log any errors that occur during the process
        }
    }

    @Override
    public void updateEmployee(EmployeeDTO employee) {
        // SQL query to update an existing employee in the database
        String sql = "UPDATE employee SET role_id = ?, first_name = ?, last_name = ?, city = ?, province = ?, username = ?, password = ?, phone_number = ?, email = ? WHERE employee_id = ?";
        try (Connection conn = DatabaseUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            // Set the parameters for the SQL query
            stmt.setInt(1, employee.getRoleId());
            stmt.setString(2, employee.getFirstName());
            stmt.setString(3, employee.getLastName());
            stmt.setString(4, employee.getCity());
            stmt.setString(5, employee.getProvince());
            stmt.setString(6, employee.getUsername());
            stmt.setString(7, employee.getPassword());
            stmt.setString(8,employee.getPhone());
            stmt.setString(9,employee.getEmail());
            stmt.setInt(10, employee.getEmployeeId());
            stmt.executeUpdate();
            logger.info("Employee updated: {}", employee);
        } catch (SQLException e) {
            logger.error("Error updating employee", e);
        }
    }

    @Override
    public void deleteEmployee(int employeeId) {
        // SQL query to delete an employee from the database based on their ID
        String sql = "DELETE FROM employee WHERE employee_id = ?";
        try (Connection conn = DatabaseUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            stmt.executeUpdate();
            logger.info("Employee deleted: {}", employeeId);
        } catch (SQLException e) {
            logger.error("Error deleting employee", e);
        }
    }

    @Override
    public EmployeeDTO getEmployeeById(int employeeId) {
        // SQL query to retrieve an employee's details based on their ID
        String sql = "SELECT * FROM employee WHERE employee_id = ?";
        EmployeeDTO employee = null;
        try (Connection conn = DatabaseUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employeeId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                employee = new EmployeeDTO();
                employee.setEmployeeId(rs.getInt("employee_id"));
                employee.setRoleId(rs.getInt("role_id"));
                employee.setFirstName(rs.getString("first_name"));
                employee.setLastName(rs.getString("last_name"));
                employee.setCity(rs.getString("city"));
                employee.setProvince(rs.getString("province"));
                employee.setUsername(rs.getString("username"));
                employee.setPassword(rs.getString("password"));
                employee.setPhone(rs.getString("phone_number"));
                employee.setEmail(rs.getString("email"));
                logger.info("Employee retrieved: {}", employee);
            }
        } catch (SQLException e) {
            logger.error("Error retrieving employee", e);
        }
        return employee;
    }

    @Override
    public String getEmployeeFullNameById(int employeeId) {
        // SQL query to retrieve the full name of an employee based on their ID
        String query = "SELECT first_name, last_name  FROM employee WHERE employee_id = ?";
        String fullName = "";
        try(Connection conn = DatabaseUtil.getInstance().getConnection(); PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, employeeId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Concatenate the first and last names to get the full name
                fullName = rs.getString("first_name") + " " + rs.getString("last_name");
            }
        }catch(Exception e){
            logger.error("Error retrieving Employee Full Name:{}", e.getMessage());
        }
        return fullName;
    }

    @Override
    public String getEmployeePhoneNumber(int employeeId) {
        // SQL query to retrieve the phone number of an employee based on their ID
        String query = "SELECT * FROM employee WHERE employee_id = ?";
        String phone_number = "";
        try(Connection conn = DatabaseUtil.getInstance().getConnection(); PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1,employeeId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                // Retrieve the phone number
                phone_number = rs.getString("phone_number");
                logger.info("Phone number retrieved for Employee #" + employeeId + " : {}", phone_number);
            }

        }catch(Exception e){
            logger.error("Error retrieving phone number: {}", e.getMessage());
        }
        return phone_number;
    }

    @Override
    public String getEmployeeEmail(int employeeId) {
        // SQL query to retrieve the email of an employee based on their ID
        String query = "SELECT * FROM employee WHERE employee_id = ?";
        String email = "";
        try(Connection conn = DatabaseUtil.getInstance().getConnection(); PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1,employeeId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                email = rs.getString("email");
                logger.info("Email retrieved for Employee #" + employeeId + " : {}", email);
            }
        }catch(Exception e){
            logger.error("Error retrieving email: {}", e.getMessage());
        }
        return email;
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        // SQL query to retrieve all employees from the database
        String sql = "SELECT * FROM employee";
        List<EmployeeDTO> employees = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            // Iterate through the result set and populate the list of employees
            while (rs.next()) {
                EmployeeDTO employee = new EmployeeDTO();
                employee.setEmployeeId(rs.getInt("employee_id"));
                employee.setRoleId(rs.getInt("role_id"));
                employee.setFirstName(rs.getString("first_name"));
                employee.setLastName(rs.getString("last_name"));
                employee.setCity(rs.getString("city"));
                employee.setProvince(rs.getString("province"));
                employee.setUsername(rs.getString("username"));
                employee.setPassword(rs.getString("password"));
                employee.setPhone(rs.getString("phone_number"));
                employee.setEmail(rs.getString("email"));
                employees.add(employee);
                logger.info("Employee added to list: {}", employee);
            }
        } catch (SQLException e) {
            logger.error("Error retrieving all employees", e);
        }
        return employees;
    }

    @Override
    public EmployeeDTO getEmployeeByUsernameAndPassword(String username, String password) {
        // SQL query to retrieve an employee based on their username and password
        String sql = "SELECT * FROM employee WHERE username = ? AND password = ?";
        EmployeeDTO employee = null;
        try (Connection conn = DatabaseUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                // Populate the EmployeeDTO object with the retrieved data
                employee = new EmployeeDTO();
                employee.setEmployeeId(rs.getInt("employee_id"));
                employee.setRoleId(rs.getInt("role_id"));
                employee.setFirstName(rs.getString("first_name"));
                employee.setLastName(rs.getString("last_name"));
                employee.setCity(rs.getString("city"));
                employee.setProvince(rs.getString("province"));
                employee.setUsername(rs.getString("username"));
                employee.setPassword(rs.getString("password"));
                employee.setPhone(rs.getString("phone_number"));
                employee.setEmail(rs.getString("email"));
                logger.info("Employee retrieved: {}", employee);
            }
        } catch (SQLException e) {
            logger.error("Error retrieving employee by username and password", e);
        }
        return employee;
    }
}