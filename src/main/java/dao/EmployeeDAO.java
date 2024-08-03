package dao;

import dto.EmployeeDTO;
import java.util.List;

public interface EmployeeDAO {
    void addEmployee(EmployeeDTO employee);
    void updateEmployee(EmployeeDTO employee);
    void deleteEmployee(int employeeId);
    EmployeeDTO getEmployeeById(int employeeId);
    String getEmployeeFullNameById(int employeeId);
    String getEmployeePhoneNumber(int employeeId);
    String getEmployeeEmail(int employeeId);
    List<EmployeeDTO> getAllEmployees();
    EmployeeDTO getEmployeeByUsernameAndPassword(String username, String password);
}
