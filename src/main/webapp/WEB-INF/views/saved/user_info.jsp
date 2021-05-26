<html>
<head>
    <%@ page contentType="text/html;charset=UTF-8" %>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    <title>User info</title>
</head>
<body>
<h4>Email: ${requestScope.user_info.email}</h4>
<h4>First name: ${requestScope.user_info.firstName}</h4>
<h4>Last name: ${requestScope.user_info.lastName}</h4>
<h4>Blocked: ${requestScope.user_info.blocked}</h4>

<h2>Books on ticket: </h2>
<c:forEach var="bookOnTicket" items="${requestScope.user_ticket_info}">
    <h4>Title: ${bookOnTicket.book.title} </h4>
    <h4>Author: ${bookOnTicket.book.author}</h4>
    <h4>Year: ${bookOnTicket.book.year}</h4>
    <h4>Until date: ${bookOnTicket.untilDate}</h4>
    <c:if test="${bookOnTicket.fine > 0}">
        <h4>Fine: ${bookOnTicket.fine}</h4>
    </c:if>

</c:forEach>
<h2>Books in hall</h2>
<c:forEach var="bookInHall" items="${requestScope.user_in_hall_info}">
    <h4>Title: ${bookInHall.book.title} </h4>
    <h4>Author: ${bookInHall.book.author}</h4>
    <h4>Year: ${bookInHall.book.year}</h4>
</c:forEach>
</body>
</html>