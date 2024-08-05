<%--
  Created by IntelliJ IDEA.
  User: liangping
  Date: 2024-07-30
  Time: 00:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="dto.ScheduleDTO" %>
<%
    int scheduleId = Integer.parseInt(request.getParameter("scheduleId"));
    ScheduleDTO schedule = null;
    for (ScheduleDTO s : (List<ScheduleDTO>) request.getAttribute("schedules")) {
        if (s.getScheduleId() == scheduleId) {
            schedule = s;
            break;
        }
    }
    if (schedule == null) {
        response.sendRedirect("manageSchedules");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Update Schedule</title>
</head>
<body>
<h1>Update Schedule</h1>
<form action="manageSchedules" method="post">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="scheduleId" value="<%= schedule.getScheduleId() %>">
    <label>Employee ID:</label><input type="number" name="employeeId" value="<%= schedule.getEmployeeId() %>" required><br>
    <label>Date:</label><input type="date" name="date" value="<%= schedule.getDate() %>" required><br>
    <label>Start Time (HH:MM):</label><input type="time" name="startTime" value="<%= schedule.getStartTime() %>" required><br>
    <label>End Time (HH:MM):</label><input type="time" name="endTime" value="<%= schedule.getEndTime() %>" required><br>
    <input type="submit" value="Update Schedule">
</form>
</body>
</html>