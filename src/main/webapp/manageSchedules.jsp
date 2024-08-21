<%--
  Created by IntelliJ IDEA.
  User: liangping
  Date: 2024-07-30
  Time: 00:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="dto.ScheduleDTO" %>
<%
    List<ScheduleDTO> schedules = (List<ScheduleDTO>) request.getAttribute("schedules");
    String error = (String) request.getAttribute("error");
%>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Schedules</title>
    <link rel="stylesheet" href="css/my-style.css">
</head>
<body>
<%@ include file="navbar-header.jsp"%>
<div class="fieldframe">
    <h1>Manage Schedules</h1>
    <%
        if (error != null) {
    %>
    <p style="color: red;"><%= error %></p>
    <%
        }
    %>
    <h2>Add New Schedule</h2>
    <form action="manageSchedules" method="post">
        <input type="hidden" name="action" value="add">
        <div class="form-group">
            <label>Employee ID:</label>
            <input type="number" name="employeeId" required>
        </div>
        <div class="form-group">
            <label>Date:</label>
            <input type="date" name="date" required>
        </div>
        <div class="form-group">
            <label>Start Time (HH:MM):</label>
            <input type="time" name="startTime" required>
        </div>
        <div class="form-group">
            <label>End Time (HH:MM):</label>
            <input type="time" name="endTime" required>
        </div>
        <input type="submit" value="Add Schedule">
    </form>
    <h2>Schedule List</h2>
    <table>
        <tr>
            <th>ID</th>
            <th>Employee ID</th>
            <th>Date</th>
            <th>Start Time</th>
            <th>End Time</th>
            <th>Actions</th>
        </tr>
        <%
            if (schedules != null) {
                for (ScheduleDTO schedule : schedules) {
        %>
        <tr>
            <td><%= schedule.getScheduleId() %></td>
            <td><%= schedule.getEmployeeId() %></td>
            <td><%= schedule.getDate() %></td>
            <td><%= schedule.getStartTime() %></td>
            <td><%= schedule.getEndTime() %></td>
            <td>
                <form action="manageSchedules" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="scheduleId" value="<%= schedule.getScheduleId() %>">
                    <input type="submit" value="Delete">
                </form>
                <form action="updateSchedule.jsp" method="get" style="display:inline;">
                    <input type="hidden" name="scheduleId" value="<%= schedule.getScheduleId() %>">
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
<%@ include file="myfooter.jsp"%>
</body>
</html>
