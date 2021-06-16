<%@ taglib prefix="h" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/mytags.tld" prefix="m" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<!DOCTYPE html>
<html lang="en">
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
        <th scope="row"><fmt:message key="email"/>:</th>
        <td>${requestScope.user_info.email}</td>
    </tr>
    <tr>
        <th scope="row"><fmt:message key="f_name"/>:</th>
        <td>${requestScope.user_info.firstName}</td>
    </tr>
    <tr>
        <th scope="row"><fmt:message key="l_name"/>:</th>
        <td>${requestScope.user_info.lastName}</td>
    </tr>
    <tr>
        <th scope="row"><fmt:message key="blocked"/>:</th>
        <td><fmt:message key="${requestScope.user_info.blocked}"/></td>
    </tr>
    </tbody>
</table>
<h3><fmt:message key="books_on_ticket"/>:</h3>
<div class="card" style="width: 18rem;">
    <ul class="list-group list-group-flush">
        <c:forEach var="bookOnTicket" items="${requestScope.user_ticket_info}">
            <li class="list-group-item">&laquo;${bookOnTicket.book.title}&raquo; ${bookOnTicket.book.author}.(<fmt:message key="until"/>: ${bookOnTicket.untilDate})<c:if test="${bookOnTicket.fine>0}"><p style="color: red"><fmt:message key="fine"/>: ${bookOnTicket.fine}</p> </c:if></li>
        </c:forEach>
    </ul>
</div>
<hr/>
<h3><fmt:message key="books_in_hall"/>:</h3>
<div class="card" style="width: 18rem;">
    <ul class="list-group list-group-flush">
        <c:forEach var="bookInHall" items="${requestScope.user_in_hall_info}">
            <li class="list-group-item">&laquo;${bookInHall.book.title}&raquo; ${bookInHall.book.author}.</li>
        </c:forEach>
    </ul>
</div>
</body>
</html>
