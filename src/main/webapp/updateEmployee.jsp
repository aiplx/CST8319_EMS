<%--
  Created by IntelliJ IDEA.
  User: liangping
  Date: 2024-07-29
  Time: 23:37
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dto.EmployeeDTO" %>
<%@ include file="navbar-header.jsp"%>
<%
    EmployeeDTO employeeToUpdate = (EmployeeDTO) request.getAttribute("employeeToUpdate");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Update Employee</title>
    <link rel="stylesheet" href="css/my-style.css">

    <style>
        .form-container {
            width: 50%;
            margin: auto;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 10px;
            background-color: #f9f9f9;
        }

        .form-container h1 {
            text-align: center;
        }

        .form-group {
            margin-bottom: 15px;
        }

        .form-group label {
            display: block;
            margin-bottom: 5px;
        }

        .form-group input {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }

        .form-group input[type="submit"] {
            width: auto;
            background-color: #4CAF50;
            color: white;
            border: none;
            padding: 10px 20px;
            cursor: pointer;
            border-radius: 5px;
        }

        .form-group input[type="submit"]:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<div class="form-container">
    <h1>Update Employee</h1>
    <form action="manageEmployees?action=fetchEmployee" method="post">
        <div class="form-group">
            <label for="employeeId">Enter Employee ID:</label>
            <input type="number" name="employeeId" id="employeeId" required>
        </div>
        <div class="form-group">
            <input type="submit" value="Fetch Employee Details">
        </div>
    </form>

    <%
        if (employeeToUpdate != null) {
    %>
    <h2>Employee Details</h2>
    <form action="manageEmployees?action=update" method="post">
        <input type="hidden" name="employeeId" value="<%= employeeToUpdate.getEmployeeId() %>">

        <div class="form-group">
            <label for="firstName">First Name:</label>
            <input type="text" id="firstName" name="firstName" value="<%= employeeToUpdate.getFirstName() %>" required>
        </div>

        <div class="form-group">
            <label for="lastName">Last Name:</label>
            <input type="text" id="lastName" name="lastName" value="<%= employeeToUpdate.getLastName() %>" required>
        </div>

        <div class="form-group">
            <label for="city">City:</label>
            <input type="text" id="city" name="city" value="<%= employeeToUpdate.getCity() %>" required>
        </div>

        <div class="form-group">
            <label for="province">Province:</label>
            <input type="text" id="province" name="province" value="<%= employeeToUpdate.getProvince() %>" required>
        </div>

        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email"  id="email" name="email" value="<%= employeeToUpdate.getEmail() %>" required>
        </div>

        <div class="form-group">
            <label for="phone">Phone Number:</label>
            <input type="tel" id="phone" name="phone" value="<%= employeeToUpdate.getPhone() %>" required>
        </div>

        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" value="<%= employeeToUpdate.getUsername() %>" required>
        </div>

        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" placeholder="Enter new password" required>
        </div>

        <div class="form-group">
            <label for="roleId">Role ID:</label>
            <input type="number" id="roleId" name="roleId" value="<%= employeeToUpdate.getRoleId() %>" required>
        </div>

        <div class="form-group">
            <input type="submit" value="Update Employee">
        </div>
    </form>
    <% } %>
</div>
</body>
</html>
<%@ include file="myfooter.jsp"%>
