<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<html>
<head>
    <title>Manager Dashboard</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        .search-container {
            margin-bottom: 20px;
        }
        .search-container input, .search-container button {
            padding: 10px;
            margin-right: 10px;
        }
        table {
            width: 100%;
            border-collapse: collapse;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        .action-buttons button {
            margin-right: 5px;
        }
    </style>
</head>
<link rel="stylesheet" href="css/style.css">
<body>
<h1>Employee Requests</h1>

<div class="search-container">
    <input type="text" id="ticketNoSearch" placeholder="Search by Ticket No here...">
    <input type="text" id="titleSearch" placeholder="Search by Title here...">
    <input type="text" id="categorySearch" placeholder="Search by Name here...">
    <button onclick="search()">Search</button>
    <button onclick="reset()">Reset</button>
</div>

<table>
    <thead>
    <tr>
        <th><input type="checkbox" onclick="selectAll(this)"></th>
        <th>Ticket Number</th>
        <th>Request Type</th>
        <th>Title</th>
        <th>Submitted by</th>
        <th>Date</th>
        <th>Status</th>
        <th>Comments</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="ticket" items="${tickets}">
        <tr>
            <td><input type="checkbox" name="selectTicket" value="${ticket.id}"></td>
            <td>${ticket.ticketNo}</td>
            <td>${ticket.categoryName}</td>
            <td>${ticket.title}</td>
            <td>${ticket.raisedBy}</td>
            <td>${ticket.date}</td>
            <td>${ticket.status}</td>
            <td>${ticket.description}</td>
            <td class="action-buttons">
                <button>Approved</button>
                <button>Denied</button>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
<script>
    function search() {

    }

    function reset() {
        document.getElementById('ticketNoSearch').value = '';
        document.getElementById('titleSearch').value = '';
        document.getElementById('categorySearch').value = '';
        // Implement reset functionality to reload the table
    }

    function selectAll(source) {
        checkboxes = document.getElementsByName('selectTicket');
        for (var i = 0, n = checkboxes.length; i < n; i++) {
            checkboxes[i].checked = source.checked;
        }
    }
</script>
</html>>