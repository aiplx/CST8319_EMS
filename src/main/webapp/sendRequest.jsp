<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<%@include file="navbar-header.jsp"%>
<head>
    <title>Employee Request Form</title>
    <link rel="stylesheet" href="css/my-style.css">

</head>
<body>
<h1>Employee Request Form</h1>
<form action="sendRequest.jsp" method="post">
    <fieldset>
        <legend>Employee</legend>
        <label for="employee">Employee Name</label>
        <input type="text" id="employee" name="emplyee" required>
    </fieldset>
    <fieldset>
        <legend>Manager</legend>
        <label for="manager">Assigned Manager</label>
        <input type="text" id="manager" name="manager" required>
    </fieldset>

    <fieldset>
        <legend>Type of request</legend>
        <input type="radio" id="vacation" name="requestType" value="Vacation Request">
        <label for="vacation">Vacation Request</label>
        <input type="radio" id="timeoff" name="requestType" value="Time Off with No Pay">
        <label for="timeoff">Time Off with No Pay</label>
        <input type="radio" id="swap" name="requestType" value="Swap Request">
        <label for="swap">Swap Request</label>
        <input type="radio" id="timeowed" name="requestType" value="Time Owed">
        <label for="timeowed">Time Owed</label>
    </fieldset>

    <fieldset>
        <legend>Additional Comments</legend>
        <label for="comments">Comments:</label>
        <input type="text" id="comments" name="comments">
    </fieldset>

    <input type="submit" value="Submit">
</form>
<%@include file="myfooter.jsp"%>
</body>
</html>
