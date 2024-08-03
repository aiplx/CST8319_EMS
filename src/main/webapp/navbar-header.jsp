<%--
  Created by IntelliJ IDEA.
  User: M
  Date: 7/31/2024
  Time: 4:42 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="my-nav">
    <img src="images/Logo.png" alt="Logo" class="my-navbar-logo">
    <ul class="nav-links">
        <li>
            <a href="mainpage.jsp"> Home </a>
                <%
            if (session != null && session.getAttribute("employee") != null) {
                Integer unreadMessages = (Integer) session.getAttribute("unreadMessages");
                if (unreadMessages == null) unreadMessages = 0;
        %>
        <li>
            <a href="<%= request.getContextPath() %>/MessagesServlet">
                Messages <span class="notification-icon" style="display: none;"><%= unreadMessages %></span>
            </a>
        </li>
        <%
        } else {
        %>
        <li><a href="login.jsp">Login</a></li>
        <li><a href="register.jsp">Register</a></li>
        <%
            }
        %>
    </ul>
    <li class="logout">
        <form action="<%= request.getContextPath() %>/LogoutServlet" method="post">
            <input type="submit" value="Logout">
        </form>
    </li>
</nav>
<script>
    function fetchUnreadMessagesCount() {
        fetch('<%= request.getContextPath() %>/Unread', {
            credentials: 'include'
        })
            .then(response => {
                if (response.ok) {
                    return response.json()
                } else if (response.status === 401) {
                    console.error('Unauthorized access. Please log in.')
                } else {
                    throw new Error('Failed to fetch unread messages count')
                }
            })
            .then(data => {
                if (data && typeof data.unreadMessages === 'number') {
                   const notificationIcon =  document.querySelector('.notification-icon')
                    if(data.unreadMessages > 0){
                        notificationIcon.textContent = data.unreadMessages
                        notificationIcon.style.display = "inline-block"
                    }else{
                        notificationIcon.textContent = ''
                        notificationIcon.style.display = 'none'
                    }
                }
            })
            .catch(error => console.error('Error:', error))
    }


    document.addEventListener('DOMContentLoaded', event =>{
        fetchUnreadMessagesCount();

        setInterval(fetchUnreadMessagesCount, 10000);
    })

</script>