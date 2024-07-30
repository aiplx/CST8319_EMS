<%--
  Created by IntelliJ IDEA.
  User: liangping
  Date: 2024-07-29
  Time: 23:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="dto.EmployeeDTO" %>
<%
    int employeeId = Integer.parseInt(request.getParameter("employeeId"));
    EmployeeDTO employee = null;
    for (EmployeeDTO e : (List<EmployeeDTO>) request.getAttribute("employees")) {
        if (e.getEmployeeId() == employeeId) {
            employee = e;
            break;
        }
    }
    if (employee == null) {
        response.sendRedirect("manageEmployees.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Update Employee</title>
</head>
<body>
<h1>Update Employee</h1>
<form action="manageEmployees" method="post">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="employeeId" value="<%= employee.getEmployeeId() %>">
    <label>First Name:</label><input type="text" name="firstName" value="<%= employee.getFirstName() %>" required><br>
    <label>Last Name:</label><input type="text" name="lastName" value="<%= employee.getLastName() %>" required><br>
    <label>City:</label><input type="text" name="city" value="<%= employee.getCity() %>" required><br>
    <label>Province:</label><input type="text" name="province" value="<%= employee.getProvince() %>" required><br>
    <label>Username:</label><input type="text" name="username" value="<%= employee.getUsername() %>" required><br>
    <label>Password:</label><input type="password" name="password" value="<%= employee.getPassword() %>" required><br>
    <label>Role ID:</label><input type="number" name="roleId" value="<%= employee.getRoleId() %>" required><br>
    <input type="submit" value="Update Employee">
</form>
</body>
</html>
