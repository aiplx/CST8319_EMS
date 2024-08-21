<%--
  Created by IntelliJ IDEA.
  User: liangping
  Date: 2024-08-17
  Time: 14:57
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="dto.EmployeeDTO" %>
<%@ include file="navbar-header.jsp"%>
<%
    List<EmployeeDTO> employees = (List<EmployeeDTO>) request.getAttribute("employees");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Show Employees</title>
    <link rel="stylesheet" href="css/my-style.css">

    <script type="text/javascript">
        function confirmDelete(employeeId) {
            return confirm("Are you sure you want to delete the employee with ID " + employeeId + "?");
        }
    </script>
</head>
<body>

<div class="fieldframe">
    <h1>Employee List</h1>
    <table>
        <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
            <th>City</th>
            <th>Province</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Username</th>
            <th>Role ID</th>
            <th>Actions</th>
        </tr>
        <%
            if (employees != null && !employees.isEmpty()) {
                for (EmployeeDTO employee : employees) {
        %>
        <tr>
            <td><%= employee.getEmployeeId() %></td>
            <td><%= employee.getFirstName() %></td>
            <td><%= employee.getLastName() %></td>
            <td><%= employee.getCity() %></td>
            <td><%= employee.getProvince() %></td>
            <td><%= employee.getEmail() %></td>
            <td><%= employee.getPhone() %></td>
            <td><%= employee.getUsername() %></td>
            <td><%= employee.getRoleId() %></td>
            <td>
                <!-- Delete Button with Confirmation -->
                <form action="manageEmployees" method="post" style="display:inline;" onsubmit="return confirmDelete(<%= employee.getEmployeeId() %>);">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="employeeId" value="<%= employee.getEmployeeId() %>">
                    <input type="submit" value="Delete">
                </form>
                <!-- Update Button -->
                <form action="updateEmployee.jsp" method="get" style="display:inline;">
                    <input type="hidden" name="employeeId" value="<%= employee.getEmployeeId() %>">
                    <input type="submit" value="Update">
                </form>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr>
            <td colspan="8">No employees found.</td>
        </tr>
        <%
            }
        %>
    </table>
</div>

<%@ include file="myfooter.jsp"%>
</body>
</html>
