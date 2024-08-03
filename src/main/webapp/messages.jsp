<%--
  Created by IntelliJ IDEA.
  User: M
  Date: 7/30/2024
  Time: 1:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="dto.Message" %>
<%@ page import="dto.EmployeeDTO" %>
<%@ page import="java.util.Collections" %>
<html>

<head>
    <title>Your Messages</title>
    <link rel="stylesheet" href="css/my-style.css">
</head>
<body>
<%@ include file="navbar-header.jsp" %>
<div class="mainContainer">
    <%
        EmployeeDTO employee = (EmployeeDTO) session.getAttribute("employee");
        if (employee == null) {
            response.sendRedirect("login.jsp");
        }
        int senderID = employee.getEmployeeId();

    %>
    <div class="messagesContainer">
        <h1 class="message-page-title"> Messages </h1>

        <%

            List<Message> messages = (List<Message>) request.getAttribute("messages");
            Collections.reverse(messages);
            for (Message message : messages) {
        %>

        <div class="message-card">
            <div class="message-header" onclick="expandCard(this)">

                <span> <% if(!message.getRead() ) {%> <b>**** Unread Message ****</b> <br/> <%}%>
                    <b>Sender:</b> <%=message.getSenderName()%> <br><b>Topic</b> - <%=message.getTitle()%> <br/>  <%=message.getFormattedTime()%></span>
            </div>
            <div class="message-body">
                <i> Sender ID:  <%=message.getSenderId()%>  MessageID: <%=message.getMessageId()%><br/> </i>

                <%=message.getMessageContent()%>

                <% if (!message.isRead()) { %>
                <form action="MessagesServlet" method="post">
                    <input type="hidden" name="action" value="markMessage">
                    <input type="hidden" name="messageID" value="<%= message.getMessageId() %>">
                    <input type="submit" value="Mark as Read">
                </form>
                <% } %>
            </div>
        </div>

        <%
            }

        %>

    </div>
    <div class="create-message">
        <h1 class="message-page-title">Send a New Message</h1>
        <div class="message-form">
            <form action="MessagesServlet" method="post">
                <input type="hidden" name="senderId" value="<%= senderID%>">
                <label for="receiverId">Receiver ID:</label>
                <input type="number" id="receiverId" name="receiverId" required><br><br>
                <label for="title">Title:</label>
                <input type="text" id="title" name="title" required><br><br>
                <label for="content">Content:</label><br>
                <textarea id="content" name="content" rows="4" cols="50" required></textarea><br><br>
                <input type="submit" value="Send Message">
            </form>
        </div>
    </div>

</div>
<%@ include file="myfooter.jsp" %>
<script>
    function expandCard(card) {
        const messageCard = card.closest(".message-card");
        const messageContent = messageCard.querySelector('.message-body');
        if (messageContent.style.display === "none" || messageContent.style.display === "") {
            messageContent.style.display = "block";
        } else {
            messageContent.style.display = "none";
        }
    }
</script>

</body>
</html>
