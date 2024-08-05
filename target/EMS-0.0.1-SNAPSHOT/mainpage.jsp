<%--
  Created by IntelliJ IDEA.
  User: liangping
  Date: 2024-07-28
  Time: 23:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    dto.EmployeeDTO employee = (dto.EmployeeDTO) session.getAttribute("employee");
    if (employee == null) {
        response.sendRedirect("login.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Employee Management System</title>
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="css/my-style.css">
    <link rel="stylesheet" href="css/loginStyle.css">
</head>
<body>
<%@ include file="navbar-header.jsp"%>
<div class="fieldframe">
    <h1>Welcome, <%= employee.getFirstName() %> <%= employee.getLastName() %></h1>
    <%
        if (employee.getRoleId() == 1) {  // 1 is the role ID for Manager
    %>
    <h2>Manager Dashboard</h2>
    <ul class="mainpage">
        <li><a href="<%= request.getContextPath() %>/manageEmployees">Manage Employees</a></li>
        <li><a href="manageSchedules.jsp">Manage Schedules</a></li>
        <li><a href="managePayroll.jsp">Manage Payroll</a></li>
        <li><a href="viewRequests.jsp">View Employee Requests</a></li>
        <li><a href="<%= request.getContextPath() %>/MessagesServlet">Check Messages</a></li>
        <li><a href="viewReports.jsp">View Reports</a></li>
    </ul>
    <%
    } else {
    %>
    <h2>Employee Dashboard</h2>
    <ul class="mainpage">
        <li><a href="<%= request.getContextPath() %>/schedule">View Schedule</a></li>
        <li><a href="clockInOut.jsp">Clock In/Out</a></li>
        <li><a href="viewPayHistory.jsp">View Pay History</a></li>
        <li><a href="sendRequest.jsp">Send Request</a></li>
        <li><a href="<%= request.getContextPath() %>/MessagesServlet">Check Messages</a></li>
    </ul>
    <%
        }
    %>

</div>
<%@ include file="footer.jsp"%>
</body>
</html>
