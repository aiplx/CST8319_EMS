<%--
  Created by IntelliJ IDEA.
  User: liangping
  Date: 2024-07-29
  Time: 23:37
  To change this template use File | Settings | File Templates.
--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<%@ page import="dto.EmployeeDTO" %>--%>
<%--<%@ include file="/header.jsp"%>--%>
<%--<%--%>
<%--    // Retrieve the employee information from the session--%>
<%--    EmployeeDTO employee = (EmployeeDTO) session.getAttribute("employeeToUpdate");--%>

<%--    // Check if the employee exists--%>
<%--    if (employee == null) {--%>
<%--        response.sendRedirect("manageEmployees?action=show");--%>
<%--        return;--%>
<%--    }--%>
<%--%>--%>
<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <title>Update Employee</title>--%>
<%--    <link rel="stylesheet" href="css/loginStyle.css"> <!-- Link to your stylesheet -->--%>
<%--    <style>--%>
<%--        .form-container {--%>
<%--            width: 50%;--%>
<%--            margin: auto;--%>
<%--            padding: 20px;--%>
<%--            border: 1px solid #ddd;--%>
<%--            border-radius: 10px;--%>
<%--            background-color: #f9f9f9;--%>
<%--        }--%>

<%--        .form-container h1 {--%>
<%--            text-align: center;--%>
<%--        }--%>

<%--        .form-group {--%>
<%--            margin-bottom: 15px;--%>
<%--        }--%>

<%--        .form-group label {--%>
<%--            display: block;--%>
<%--            margin-bottom: 5px;--%>
<%--        }--%>

<%--        .form-group input {--%>
<%--            width: 100%;--%>
<%--            padding: 8px;--%>
<%--            box-sizing: border-box;--%>
<%--        }--%>

<%--        .form-group input[type="submit"] {--%>
<%--            width: auto;--%>
<%--            background-color: #4CAF50;--%>
<%--            color: white;--%>
<%--            border: none;--%>
<%--            padding: 10px 20px;--%>
<%--            cursor: pointer;--%>
<%--            border-radius: 5px;--%>
<%--        }--%>

<%--        .form-group input[type="submit"]:hover {--%>
<%--            background-color: #45a049;--%>
<%--        }--%>
<%--    </style>--%>
<%--</head>--%>
<%--<body>--%>
<%--<div class="form-container">--%>
<%--    <h1>Update Employee</h1>--%>
<%--    <form action="manageEmployees" method="post">--%>
<%--        <input type="hidden" name="action" value="update">--%>
<%--        <input type="hidden" name="employeeId" value="<%= employee.getEmployeeId() %>">--%>

<%--        <div class="form-group">--%>
<%--            <label>First Name:</label>--%>
<%--            <input type="text" name="firstName" value="<%= employee.getFirstName() %>" required>--%>
<%--        </div>--%>

<%--        <div class="form-group">--%>
<%--            <label>Last Name:</label>--%>
<%--            <input type="text" name="lastName" value="<%= employee.getLastName() %>" required>--%>
<%--        </div>--%>

<%--        <div class="form-group">--%>
<%--            <label>City:</label>--%>
<%--            <input type="text" name="city" value="<%= employee.getCity() %>" required>--%>
<%--        </div>--%>

<%--        <div class="form-group">--%>
<%--            <label>Province:</label>--%>
<%--            <input type="text" name="province" value="<%= employee.getProvince() %>" required>--%>
<%--        </div>--%>

<%--        <div class="form-group">--%>
<%--            <label>Username:</label>--%>
<%--            <input type="text" name="username" value="<%= employee.getUsername() %>" required>--%>
<%--        </div>--%>

<%--        <div class="form-group">--%>
<%--            <label>Password:</label>--%>
<%--            <input type="password" name="password" placeholder="Enter new password" required>--%>
<%--        </div>--%>

<%--        <div class="form-group">--%>
<%--            <label>Role ID:</label>--%>
<%--            <input type="number" name="roleId" value="<%= employee.getRoleId() %>" required>--%>
<%--        </div>--%>

<%--        <div class="form-group">--%>
<%--            <input type="submit" value="Update Employee">--%>
<%--        </div>--%>
<%--    </form>--%>
<%--</div>--%>
<%--</body>--%>
<%--</html>--%>
<%--<%@ include file="/footer.jsp"%>--%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dto.EmployeeDTO" %>
<%@ include file="/header.jsp"%>
<%
    EmployeeDTO employeeToUpdate = (EmployeeDTO) request.getAttribute("employeeToUpdate");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Update Employee</title>
    <link rel="stylesheet" href="css/style.css">
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
            <label>First Name:</label>
            <input type="text" name="firstName" value="<%= employeeToUpdate.getFirstName() %>" required>
        </div>

        <div class="form-group">
            <label>Last Name:</label>
            <input type="text" name="lastName" value="<%= employeeToUpdate.getLastName() %>" required>
        </div>

        <div class="form-group">
            <label>City:</label>
            <input type="text" name="city" value="<%= employeeToUpdate.getCity() %>" required>
        </div>

        <div class="form-group">
            <label>Province:</label>
            <input type="text" name="province" value="<%= employeeToUpdate.getProvince() %>" required>
        </div>

        <div class="form-group">
            <label>Username:</label>
            <input type="text" name="username" value="<%= employeeToUpdate.getUsername() %>" required>
        </div>

        <div class="form-group">
            <label>Password:</label>
            <input type="password" name="password" placeholder="Enter new password" required>
        </div>

        <div class="form-group">
            <label>Role ID:</label>
            <input type="number" name="roleId" value="<%= employeeToUpdate.getRoleId() %>" required>
        </div>

        <div class="form-group">
            <input type="submit" value="Update Employee">
        </div>
    </form>
    <% } %>
</div>
</body>
</html>
<%@ include file="/footer.jsp"%>
