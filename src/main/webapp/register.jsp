<%--
  Created by IntelliJ IDEA.
  User: liangping
  Date: 2024-07-28
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
    <link rel="stylesheet" href="css/my-style.css">
</head>
<body>
<%@ include file="navbar-header.jsp"%>

<div class="fieldframe">
    <h2>Register</h2>

    <!-- Display error message if registration fails -->
    <%
        String errorMessage = (String) request.getAttribute("error");
        if (errorMessage != null) {
    %>
    <div class="error-message">
        <p style="color:red;"><%= errorMessage %></p>
    </div>
    <%
        }
    %>

    <form action="RegistrationServlet" method="post">
        <div class="form-group">
            <label for="firstName">First Name:</label>
            <input type="text" id="firstName" name="firstName" required>
        </div>
        <div class="form-group">
            <label for="lastName">Last Name:</label>
            <input type="text" id="lastName" name="lastName" required>
        </div>
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" id="email" name="email" required>
        </div>
        <div class="form-group">
            <label for="phone">Phone Number:</label>
            <input type="tel" id="phone" name="phone" required>
        </div>
        <div class="form-group">
            <label for="city">City:</label>
            <input type="text" id="city" name="city" required>
        </div>
        <div class="form-group">
            <label for="province">Province:</label>
            <input type="text" id="province" name="province" required>
        </div>
        <div class="form-group">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
        </div>
        <div class="form-group">
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <div class="form-group">
            <label for="roleId">Role ID:</label>
            <input type="text" id="roleId" name="roleId" required>
        </div>
        <input type="submit" value="Register">
    </form>

    <p><a href="login.jsp">Already have an account? Login here</a></p>
</div>

<%@ include file="myfooter.jsp"%>
</body>
</html>
