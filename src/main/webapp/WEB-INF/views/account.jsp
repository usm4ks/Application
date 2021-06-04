<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="h" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/mytags.tld" prefix="m" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Personal account</title>
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
        <td>${sessionScope.user.email}</td>
    </tr>
    <tr>
        <th scope="row"><fmt:message key="f_name"/>:</th>
        <td>${sessionScope.user.firstName}</td>
    </tr>
    <tr>
        <th scope="row"><fmt:message key="l_name"/>:</th>
        <td>${sessionScope.user.lastName}</td>
    </tr>
    <c:if test="${sessionScope.user.role.roleName == 'user'}">
        <tr>
            <th scope="row"><fmt:message key="blocked"/>:</th>
            <td><fmt:message key="${sessionScope.user.blocked}"/></td>
        </tr>
    </c:if>
    <tr>
        <th scope="row"><fmt:message key="role"/>:</th>
        <td><fmt:message key="${sessionScope.user.role.roleName}"/></td>
    </tr>
    </tbody>
</table>
<c:choose>
    <c:when test="${sessionScope.user.role.roleName == 'user'}">
        <h3><fmt:message key="ordered_books"/>:</h3>
        <div class="card" style="width: 18rem;">
            <ul class="list-group list-group-flush">
                <c:forEach var="order" items="${sessionScope.ordered_books}">
                    <li class="list-group-item">&laquo;${order.book.title}&raquo; ${order.book.author}.(<fmt:message key="ordered"/>: <fmt:message key="${order.type.type}"/>)</li>
                </c:forEach>
            </ul>
        </div>
        <hr/>
        <h3><fmt:message key="books_on_ticket"/>:</h3>
        <div class="card" style="width: 18rem;">
            <ul class="list-group list-group-flush">
                <c:forEach var="bookOnTicket" items="${sessionScope.books_on_ticket}">
                    <li class="list-group-item">&laquo;${bookOnTicket.book.title}&raquo; ${bookOnTicket.book.author}.(<fmt:message key="until"/>: ${bookOnTicket.untilDate})<br/><c:if test="${bookOnTicket.fine>0}"><p style="color: red"><fmt:message key="fine"/>: ${bookOnTicket.fine}</p> </c:if> </li>
                </c:forEach>
            </ul>
        </div>
        <hr/>
        <h3><fmt:message key="books_in_hall"/>:</h3>
        <div class="card" style="width: 18rem;">
            <ul class="list-group list-group-flush">
                <c:forEach var="bookInHall" items="${sessionScope.books_in_hall}">
                    <li class="list-group-item">&laquo;${bookInHall.book.title}&raquo; ${bookInHall.book.author}.</li>
                </c:forEach>
            </ul>
        </div>
    </c:when>
    <c:when test="${sessionScope.user.role.roleName == 'librarian'}">
        <a class="btn btn-outline-secondary" style="text-decoration: none;color: #000 !important" href="account?command=show_all_orders" role="button"><fmt:message key="show_all_orders"/></a>
        <a class="btn btn-outline-secondary" style="text-decoration: none;color: #000 !important" href="account?command=show_all_users" role="button"><fmt:message key="show_all_users"/></a>
    </c:when>
    <c:when test="${sessionScope.user.role.roleName == 'admin'}">
        <a class="btn btn-outline-secondary" style="text-decoration: none;color: #000 !important" href="account?command=librarians_settings" role="button"><fmt:message key="librarian_settings"/></a>
        <a class="btn btn-outline-secondary" style="text-decoration: none;color: #000 !important" href="account?command=users_settings" role="button"><fmt:message key="user_settings"/></a>
    </c:when>
</c:choose>


</body>
</html>
