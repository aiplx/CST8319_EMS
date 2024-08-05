<%@ page import="dto.EmployeeDTO" %>
<%@ page import="dto.ScheduleDTO" %>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.util.*" %><%--
  Created by IntelliJ IDEA.
  User: M
  Date: 8/4/2024
  Time: 12:48 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<%
    EmployeeDTO employee = (EmployeeDTO) session.getAttribute("employee");
    if (employee == null) {
        response.sendRedirect("login.jsp");
    }
    List<ScheduleDTO> schedules = (List<ScheduleDTO>) request.getAttribute("schedules");
    if (schedules == null) {
        schedules = new ArrayList<ScheduleDTO>();
    }
    LocalDate today = LocalDate.now();
    LocalDate nextWeek = today.plusWeeks(1);
    List<ScheduleDTO> previousSchedules = new ArrayList<>();
    List<ScheduleDTO> nextSchedules = new ArrayList<>();
    List<ScheduleDTO> currentSchedules = new ArrayList<>();
    for(ScheduleDTO schedule : schedules) {
        if(schedule.getDate().toLocalDate().isBefore(today)) {
            previousSchedules.add(schedule);
        }else if((schedule.getDate().toLocalDate().isAfter(today) || schedule.getDate().toLocalDate().isEqual(today)) && schedule.getDate().toLocalDate().isBefore(nextWeek)) {
            currentSchedules.add(schedule);
        }else{
            nextSchedules.add(schedule);
        }
    }
    Comparator comparator = new Comparator<ScheduleDTO>() {public int compare(ScheduleDTO schedule1, ScheduleDTO schedule2) {
        return schedule1.getDate().compareTo(schedule2.getDate());
    }};
    currentSchedules.sort(comparator);
    nextSchedules.sort(comparator);
    previousSchedules.sort(comparator);
    previousSchedules.reversed();


%>
<%@include file="navbar-header.jsp"%>
<head>
    <title>Your Schedule</title>
    <link rel="stylesheet" href="css/my-style.css">

</head>
<body>

<div class="scheduleContainer" id="schedule">

    <div class="scheduleTable">
        <h1> Previous Shifts</h1>
        <table >
            <thead>
            <tr>
                <th> Date </th>
                <th> Start Time </th>
                <th> End Time </th>
            </tr>
            </thead>
            <tbody>
            <%
                for (ScheduleDTO schedule : previousSchedules){
            %>

            <tr>

                <td><%=schedule.getDate()%></td>
                <td><%=schedule.getStartTime()%></td>
                <td><%=schedule.getEndTime()%></td>
            </tr>
            <%}%>
            </tbody>
        </table>
    </div>

    <div class="scheduleTable">
        <h1> This Week's Shifts</h1>
        <table >
            <thead>
            <tr>
                <th> Date </th>
                <th> Start Time </th>
                <th> End Time </th>
            </tr>
            </thead>
            <tbody>
            <%
                for (ScheduleDTO schedule : currentSchedules){
            %>

            <tr>

                <td><%=schedule.getDate()%></td>
                <td><%=schedule.getStartTime()%></td>
                <td><%=schedule.getEndTime()%></td>
            </tr>
            <%}%>
            </tbody>
        </table>
    </div>
    <div class="scheduleTable">
        <h1> Future Shifts</h1>
        <table >
            <thead>
            <tr>
                <th> Date </th>
                <th> Start Time </th>
                <th> End Time </th>
            </tr>
            </thead>
            <tbody>
            <%
                for (ScheduleDTO schedule : nextSchedules){
            %>

            <tr>

                <td><%=schedule.getDate()%></td>
                <td><%=schedule.getStartTime()%></td>
                <td><%=schedule.getEndTime()%></td>
            </tr>
            <%}%>
            </tbody>
        </table>
    </div>


</div>


</body>
<%@include file="myfooter.jsp"%>
</html>
