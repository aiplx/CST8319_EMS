<%--
  Created by IntelliJ IDEA.
  User: M
  Date: 7/31/2024
  Time: 4:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="my-nav">
    <img src="images/logo.png" alt="Logo" class="my-navbar-logo">
    <ul>
        <li>
            <a href="mainpage.jsp"> Home </a>
        </li>
        <%
            if (session != null && session.getAttribute("currentUser") != null) {
                out.println("<li><a href='LogoutServlet'>Logout</a></li>");
                out.println("<li><a href='MessageServlet'>My Messages</a></li>");
            } else {
                out.println("<li><a href='login.jsp'>Login</a></li>");
                out.println("<li><a href='register.jsp'>Register</a></li>");
            }
        %>
    </ul>
</nav>
