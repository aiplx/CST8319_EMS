<%--
  Created by IntelliJ IDEA.
  User: M
  Date: 7/31/2024
  Time: 2:31 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String redirectURL = request.getContextPath() + "/mainpage.jsp";
    response.sendRedirect(redirectURL);
%>

