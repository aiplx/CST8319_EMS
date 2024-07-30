<%--
  Created by IntelliJ IDEA.
  User: liangping
  Date: 2024-07-29
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="dto.EmployeeDTO" %>
<%
    List<EmployeeDTO> employees = (List<EmployeeDTO>) request.getAttribute("employees");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Employees</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/loginStyle.css">
</head>
<body>
<%@ include file="header.jsp"%>

<div class="fieldframe">
    <h1>Manage Employees</h1>
    <h2>Add New Employee</h2>
    <form action="manageEmployees" method="post">
        <input type="hidden" name="action" value="add">
        <div class="form-group">
            <label>First Name:</label>
            <input type="text" name="firstName" required>
        </div>
        <div class="form-group">
            <label>Last Name:</label>
            <input type="text" name="lastName" required>
        </div>
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
            <input type="number" name="roleId" required>
        </div>
        <input type="submit" value="Add Employee">
    </form>
    <h2>Employee List</h2>
    <table>
        <tr>
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
            if (employees != null) {
                for (EmployeeDTO employee : employees) {
        %>
        <tr>
            <td><%= employee.getEmployeeId() %></td>
            <td><%= employee.getFirstName() %></td>
            <td><%= employee.getLastName() %></td>
            <td><%= employee.getCity() %></td>
            <td><%= employee.getProvince() %></td>
            <td><%= employee.getUsername() %></td>
            <td><%= employee.getRoleId() %></td>
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
<%@ include file="footer.jsp"%>
</body>
</html>
