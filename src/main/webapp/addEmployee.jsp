<%--
  Created by IntelliJ IDEA.
  User: liangping
  Date: 2024-08-17
  Time: 14:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Employee</title>
    <link rel="stylesheet" href="css/my-style.css">
</head>
<body>
<%@ include file="navbar-header.jsp"%>
<div class="fieldframe">
    <h1>Add New Employee</h1>
    <form action="manageEmployees" method="post">
        <input type="hidden" name="action" value="add">

        <div class="form-group">
            <label>First Name:</label><input type="text" name="firstName" required><br>
        </div>

        <div class="form-group">
            <label>Last Name:</label><input type="text" name="lastName" required><br>
        </div>

        <div class="form-group">
            <label>City:</label><input type="text" name="city" required><br>
        </div>

        <div class="form-group">
            <label>Province:</label><input type="text" name="province" required><br>
        </div>

        <div class="form-group">
            <label>Email:</label><input type="email" name="email" required><br>
        </div>

        <div class="form-group">
            <label>Phone Number:</label><input type="tel" name="phone" required><br>
        </div>

        <div class="form-group">
            <label>Username:</label><input type="text" name="username" required><br>
        </div>

        <div class="form-group">
            <label>Password:</label><input type="password" name="password" required><br>
        </div>

        <div class="form-group">
            <label>Role ID:</label><input type="number" name="roleId" required><br>
        </div>

        <input type="submit" value="Add Employee">
    </form>
</div>
<%@ include file="myfooter.jsp"%>
</body>
</html>
