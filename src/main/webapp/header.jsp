<%--
  Created by IntelliJ IDEA.
  User: liangping
  Date: 2024-07-28
--%>

<div id="navbar">
    <img src="images/logo.png" alt="Logo" class="navbar-logo">
    <div class="nav-items">
        <ul>
            <li><a href="mainpage.jsp">Home</a></li>
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
    </div>
</div>

