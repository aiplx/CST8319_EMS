<%--
  Created by IntelliJ IDEA.
  User: liangping
  Date: 2024-07-29
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="dto.EmployeeDTO" %>
<%
    EmployeeDTO currentManager = (EmployeeDTO) session.getAttribute("employee");
    if (currentManager == null || currentManager.getRoleId() != 1) {
        response.sendRedirect("login.jsp");
    }

%>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Employees</title>
    <link rel="stylesheet" href="css/my-style.css">

</head>
<body>
<%@ include file="navbar-header.jsp" %>

<div class="mainContainer" id="management-container">

    <div class="collapsable">

        <h2 class="page-heading">Add New Employee</h2>
        <form class="add-employee-form" action="manageEmployees" method="post">
            <input type="hidden" name="action" value="add">
            <span>
                <div class="form-group">
                <label>First Name:</label>
                <input type="text" name="firstName" required>
                </div>

                <div class="form-group">
                <label>Last Name:</label>
                <input type="text" name="lastName" required>
                </div>
            </span>
            <div class="form-group">
                <label>City:</label>
                <input type="text" name="city" required>
            </div>
            <div class="form-group">
                <label>Province:</label>
                <input type="text" name="province" required>
            </div>
            <div class="form-group">
                <label>Username:</label>
                <input type="text" name="username" required>
            </div>
            <div class="form-group">
                <label>Password:</label>
                <input type="password" name="password" required>
            </div>
            <div class="form-group">
                <label>Role ID:</label>
                <label>
                    <input type="number" name="roleId" required>
                </label>
            </div>
            <input type="submit" value="Add Employee">
        </form>
    </div>


    <div class="employee-list">
        <h2 class="page-heading">Employee List</h2>
        <table>
            <tr class="employee-list-headings">
                <th>ID</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>City</th>
                <th>Province</th>
                <th>Username</th>
                <th>Role ID</th>
                <th>Actions</th>
            </tr>
            <%
                List<EmployeeDTO> employees = (List<EmployeeDTO>) request.getAttribute("employees");
                if (employees != null) {
                    for (EmployeeDTO employee : employees) {
            %>
            <tr class="employee-list-row">
                <td><%= employee.getEmployeeId() %>
                </td>
                <td><%= employee.getFirstName() %>
                </td>
                <td><%= employee.getLastName() %>
                </td>
                <td><%= employee.getCity() %>
                </td>
                <td><%= employee.getProvince() %>
                </td>
                <td><%= employee.getUsername() %>
                </td>
                <td><%= employee.getRoleId() %>
                </td>
                <td>
                    <form action="manageEmployees" method="post" style="display:inline;">
                        <input type="hidden" name="action" value="delete">
                        <input type="hidden" name="employeeId" value="<%= employee.getEmployeeId() %>">
                        <input type="submit" value="Delete">
                    </form>
                    <form action="updateEmployee.jsp" method="get" style="display:inline;">
                        <input type="hidden" name="employeeId" value="<%= employee.getEmployeeId() %>">
                        <input type="submit" value="Update">
                    </form>
                </td>
            </tr>
            <%
                    }
                }
            %>
        </table>
    </div>

</div>


<%@ include file="myfooter.jsp" %>
</body>
</html>
