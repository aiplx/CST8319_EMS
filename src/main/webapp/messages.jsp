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
<html>

<head>
    <title>Your Messages</title>
    <link rel="stylesheet" href="css/messagestyle.css">


</head>
<body>
<%@ include file="navbar-header.jsp" %>
<div class="mainContainer">
    <%
        EmployeeDTO employee = (EmployeeDTO) session.getAttribute("employee");
        if (employee == null) {
            response.sendRedirect("login.jsp");
        }

    %>
    <div class="messagesContainer">
        <h1 class="message-page-title"> Messages </h1>

        <div class="message-card">
            <div class="message-header" onclick="expandCard(this)">
                <span> <b>Sender</b>: Sender Name <b>Topic</b> - Topic title <b>@</b> 1/1/1 00:00pm</span>
            </div>
            <div class="message-body">
                <i>Sender ID: <br/> </i>
                Message Body is held here!
                The content of this message is very long.The content of this message is very
                long.The content of this message is very long.The content of this message is very long.The content of
                this message is very long.The content of this message is very long.The content of this message is very
                long.The content of this message is very long.The content of this message is very long.
            </div>
        </div>
        <div class="message-card">
            <div class="message-header" onclick="expandCard(this)">
                <span> <b>Sender</b>: Sender Name <b>Topic</b> - Topic title <b>@</b> 1/1/1 00:00pm</span>
            </div>
            <div class="message-body">
                <i>Sender ID: <br/> </i>
                Message Body is held here!
            </div>
        </div>
        <div class="message-card">
            <div class="message-header" onclick="expandCard(this)">
                <span> <b>Sender</b>: Sender Name <b>Topic</b> - Topic title <b>@</b> 1/1/1 00:00pm</span>
            </div>
            <div class="message-body">
                <i>Sender ID: <br/> </i>
                Message Body is held here!
            </div>
        </div>
        <div class="message-card">
            <div class="message-header" onclick="expandCard(this)">
                <span>Sender: Sender Name Topic - Topic name @ 1/1/1 00:00pm</span>
            </div>
            <div class="message-body">
                <i>Sender ID: <br/> </i>
                Message Body.
                The content of this message is very long.The content of this message is very
                long.The content of this message is very long.The content of this message is very long.The content of
                this message is very long.The content of this message is very long.The content of this message is very
                long.The content of this message is very long.The content of this message is very long.
            </div>
        </div>
        <div class="message-card">
            <div class="message-header" onclick="expandCard(this)">
                Sender: Sender Name Topic - Topic name @ 1/1/1 00:00pm
            </div>
            <div class="message-body">
                Message Body
            </div>
        </div>

        <%

            List<Message> messages = (List<Message>) request.getAttribute("messages");
            if (messages != null) {
                for (Message message : messages) {
        %>

        <div class="message-card">
            <div class="message-header" onclick="expandCard(this)">
                <span> <b>Sender:</b> <%=message.getSenderName()%> <b>Topic</b> - <%=message.getTitle()%> <b>@</b> <%=message.getTimestamp()%></span>
            </div>
            <div class="message-body">
                <i> Sender ID:  <%=message.getSenderId()%> <br/> </i>

                <%=message.getMessageContent()%>
            </div>
        </div>

        <%
                }
            }

        %>

    </div>
    <div class="create-message">
        <h1 class="message-page-title">Send a New Message</h1>
        <div class="message-form">
            <form action="Messages" method="post">
                <input type="hidden" name="senderId" value="<%=session.getAttribute("employeeID")%>">
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
        var messageCard = card.closest(".message-card");
        var messageContent = messageCard.querySelector('.message-body');
        if (messageContent.style.display === "none" || messageContent.style.display === "") {
            messageContent.style.display = "block";
        } else {
            messageContent.style.display = "none";
        }
    }
</script>

</body>
</html>
