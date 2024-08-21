package dao.employee;

import dto.EmployeeDTO;
import java.util.List;

public interface EmployeeDAO {
    /**
     * Adds a new employee to the database.
     *
     * @param employee The employee data to be added.
     */
    void addEmployee(EmployeeDTO employee);

    /**
     * Updates an existing employee's information in the database.
     *
     * @param employee The employee data with updated information.
     */
    void updateEmployee(EmployeeDTO employee);

    /**
     * Deletes an employee from the database based on their ID.
     *
     * @param employeeId The ID of the employee to be deleted.
     */
    void deleteEmployee(int employeeId);

    /**
     * Retrieves an employee's details from the database based on their ID.
     *
     * @param employeeId The ID of the employee to be retrieved.
     * @return The employee data, or null if no employee is found with the given ID.
     */
    EmployeeDTO getEmployeeById(int employeeId);

    /**
     * Retrieves an employee's full name based on their ID.
     *
     * @param employeeId The ID of the employee.
     * @return The full name of the employee, or null if no employee is found.
     */
    String getEmployeeFullNameById(int employeeId);

    /**
     * Retrieves an employee's phone number based on their ID.
     *
     * @param employeeId The ID of the employee.
     * @return The phone number of the employee, or null if no employee is found.
     */
    String getEmployeePhoneNumber(int employeeId);

    /**
     * Retrieves an employee's email address based on their ID.
     *
     * @param employeeId The ID of the employee.
     * @return The email address of the employee, or null if no employee is found.
     */
    String getEmployeeEmail(int employeeId);

    /**
     * Retrieves a list of all employees from the database.
     *
     * @return A list of all employee data.
     */
    List<EmployeeDTO> getAllEmployees();

    /**
     * Retrieves an employee's details based on their username and password.
     *
     * @param username The username of the employee.
     * @param password The password of the employee.
     * @return The employee data if a match is found, or null if no match is found.
     */
    EmployeeDTO getEmployeeByUsernameAndPassword(String username, String password);
}
