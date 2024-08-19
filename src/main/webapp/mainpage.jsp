<%--
  Created by IntelliJ IDEA.
  User: liangping
  Date: 2024-07-28
  Time: 23:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dto.EmployeeDTO" %>
<%@ page import="java.util.List" %>
<%@ include file="/header.jsp"%>
<%
    dto.EmployeeDTO employee = (dto.EmployeeDTO) session.getAttribute("employee");
    if (employee == null) {
        response.sendRedirect("login.jsp");
        return;
    }
// Assuming employee list is already retrieved and set in the request
    List<EmployeeDTO> employees = (List<EmployeeDTO>) request.getAttribute("employees");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Employee Management System</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/loginStyle.css">
</head>
<body>

<div class="fieldframe">
    <h1>Welcome, <%= employee.getFirstName() %> <%= employee.getLastName() %></h1>
    <%
        if (employee.getRoleId() == 1) {  // 1 is the role ID for Manager
    %>
    <h2>Manager Dashboard</h2>
    <ul class="mainpage">
        <li><a href="manageEmployees?action=show">Show Employees</a></li>
        <li><a href="manageEmployees?action=add">Add Employee</a></li>
        <li><a href="manageSchedules.jsp">Manage Schedules</a></li>

    </ul>
    <%
    } else {
    %>
    <h2>Employee Dashboard</h2>
    <ul class="mainpage">
        <li><a href="viewSchedule.jsp">View Schedule</a></li>
        <li><a href="sendRequest.jsp">Send Request</a></li>
        <li><a href="sendMessages.jsp">Send Messages</a></li>
    </ul>
    <%
        }
    %>
    <form action="LogoutServlet" method="post">
        <input type="submit" value="Logout">
    </form>
</div>
<%@ include file="/footer.jsp"%>
</body>
</html>
