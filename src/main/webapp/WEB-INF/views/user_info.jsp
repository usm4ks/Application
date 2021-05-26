<%@ taglib prefix="h" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h:header/>
<table class="table">
    <thead>
    <tr>
        <th scope="col"> </th>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <th scope="row">Email:</th>
        <td>${requestScope.user_info.email}</td>
    </tr>
    <tr>
        <th scope="row">First name:</th>
        <td>${requestScope.user_info.firstName}</td>
    </tr>
    <tr>
        <th scope="row">Last name:</th>
        <td>${requestScope.user_info.lastName}</td>
    </tr>
    <tr>
        <th scope="row">Blocked:</th>
        <td>${requestScope.user_info.blocked}</td>
    </tr>
    </tbody>
</table>
<h3>Books on ticket:</h3>
<div class="card" style="width: 18rem;">
    <ul class="list-group list-group-flush">
        <c:forEach var="bookOnTicket" items="${requestScope.user_ticket_info}">
            <li class="list-group-item">&laquo;${bookOnTicket.book.title}&raquo; ${bookOnTicket.book.author}.(until: ${bookOnTicket.untilDate})<c:if test="${bookOnTicket.fine>0}"><p style="color: red">FINE: ${bookOnTicket.fine}</p> </c:if></li>
        </c:forEach>
    </ul>
</div>
<hr/>
<h3>Books in hall:</h3>
<div class="card" style="width: 18rem;">
    <ul class="list-group list-group-flush">
        <c:forEach var="bookInHall" items="${requestScope.user_in_hall_info}">
            <li class="list-group-item">&laquo;${bookInHall.book.title}&raquo; ${bookInHall.book.author}.</li>
        </c:forEach>
    </ul>
</div>
</body>
</html>
