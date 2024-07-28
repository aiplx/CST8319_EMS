<%--
  Created by IntelliJ IDEA.
  User: liangping
  Date: 2024-07-28
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register</title>
</head>
<body>
<h2>Register</h2>
<form action="RegistrationServlet" method="post">
    <label for="firstName">First Name:</label>
    <input type="text" id="firstName" name="firstName" required><br><br>
    <label for="lastName">Last Name:</label>
    <input type="text" id="lastName" name="lastName" required><br><br>
    <label for="city">City:</label>
    <input type="text" id="city" name="city" required><br><br>
    <label for="province">Province:</label>
    <input type="text" id="province" name="province" required><br><br>
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" required><br><br>
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" required><br><br>
    <label for="roleId">Role ID:</label>
    <input type="text" id="roleId" name="roleId" required><br><br>
    <input type="submit" value="Register">
</form>
<p><a href="login.jsp">Already have an account? Login here</a></p>
</body>
</html>
