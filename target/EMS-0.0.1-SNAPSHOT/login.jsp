<%--
  Created by IntelliJ IDEA.
  User: liangping
  Date: 2024-07-28
--%>
<%@ page contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="css/loginStyle.css">

</head>
<body>
<%@ include file="header.jsp"%>
<div class="content">
    <div class="login-container">
        <h2>Login</h2>
        <a href="register.jsp">Register with us!</a>
        <%
            String loginError = (String) session.getAttribute("loginError");
            if (loginError != null) {
                out.println("<p style='color:red;'>" + loginError + "</p>");
                session.removeAttribute("loginError");
            }
        %>
        <!-- Login Form -->
        <form action="LoginServlet" method="post">
            <div>
                <label for="username">Username:</label> <input type="text" id="username" name="username" required>
            </div>
            <div>
                <label for="password">Password:</label> <input type="password" id="password" name="password" required>
            </div>
            <div>
                <input type="submit" value="Login">
            </div>
        </form>
    </div>
</div>
<%@ include file="footer.jsp"%>
</body>
</html>