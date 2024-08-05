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
            logger.error("Error adding employee", e);
        }
    }

    @Override
    public void updateEmployee(EmployeeDTO employee) {
        String sql = "UPDATE employee SET role_id = ?, first_name = ?, last_name = ?, city = ?, province = ?, username = ?, password = ? WHERE employee_id = ?";
        try (Connection conn = DatabaseUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employee.getRoleId());
            stmt.setString(2, employee.getFirstName());
            stmt.setString(3, employee.getLastName());
            stmt.setString(4, employee.getCity());
            stmt.setString(5, employee.getProvince());
            stmt.setString(6, employee.getUsername());
            stmt.setString(7, employee.getPassword());
            stmt.setInt(8, employee.getEmployeeId());
            stmt.executeUpdate();
            logger.info("Employee updated: {}", employee);
        } catch (SQLException e) {
            logger.error("Error updating employee", e);
        }
    }

    @Override
    public void deleteEmployee(int employeeId) {
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
                logger.info("Employee retrieved: {}", employee);
            }
        } catch (SQLException e) {
            logger.error("Error retrieving employee", e);
        }
        return employee;
    }

    @Override
    public String getEmployeeFullNameById(int employeeId) {
        String query = "SELECT first_name, last_name  FROM employee WHERE employee_id = ?";
        String fullName = "";
        try(Connection conn = DatabaseUtil.getInstance().getConnection(); PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1, employeeId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                fullName = rs.getString("first_name") + " " + rs.getString("last_name");
            }
        }catch(Exception e){
            logger.error("Error retrieving Employee Full Name:{}", e.getMessage());
        }
        return fullName;
    }

    @Override
    public String getEmployeePhoneNumber(int employeeId) {
        String query = "SELECT * FROM employee WHERE employee_id = ?";
        String phone_number = "";
        try(Connection conn = DatabaseUtil.getInstance().getConnection(); PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1,employeeId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
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
        String sql = "SELECT * FROM employee";
        List<EmployeeDTO> employees = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
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
        String sql = "SELECT * FROM employee WHERE username = ? AND password = ?";
        EmployeeDTO employee = null;
        try (Connection conn = DatabaseUtil.getInstance().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
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
                logger.info("Employee retrieved: {}", employee);
            }
        } catch (SQLException e) {
            logger.error("Error retrieving employee by username and password", e);
        }
        return employee;
    }
}