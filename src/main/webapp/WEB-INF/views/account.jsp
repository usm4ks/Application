<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="h" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Personal account</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
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
        <td>${sessionScope.user.email}</td>
    </tr>
    <tr>
        <th scope="row">First name:</th>
        <td>${sessionScope.user.firstName}</td>
    </tr>
    <tr>
        <th scope="row">Last name:</th>
        <td>${sessionScope.user.lastName}</td>
    </tr>
    <c:if test="${sessionScope.user.role.roleName == 'user'}">
        <tr>
            <th scope="row">Blocked:</th>
            <td>${sessionScope.user.blocked}</td>
        </tr>
    </c:if>
    <tr>
        <th scope="row">Role:</th>
        <td>${sessionScope.user.role.roleName}</td>
    </tr>
    </tbody>
</table>
<c:choose>
    <c:when test="${sessionScope.user.role.roleName == 'user'}">
        <h3>Ordered books:</h3>
        <div class="card" style="width: 18rem;">
            <ul class="list-group list-group-flush">
                <c:forEach var="order" items="${sessionScope.ordered_books}">
                    <li class="list-group-item">&laquo;${order.book.title}&raquo; ${order.book.author}.(ordered: ${order.type.toString()})</li>
                </c:forEach>
            </ul>
        </div>
        <hr/>
        <h3>Books on ticket:</h3>
        <div class="card" style="width: 18rem;">
            <ul class="list-group list-group-flush">
                <c:forEach var="bookOnTicket" items="${sessionScope.books_on_ticket}">
                    <li class="list-group-item">&laquo;${bookOnTicket.book.title}&raquo; ${bookOnTicket.book.author}.(until: ${bookOnTicket.untilDate})<br/><c:if test="${bookOnTicket.fine>0}"><p style="color: red">FINE: ${bookOnTicket.fine}</p> </c:if> </li>
                </c:forEach>
            </ul>
        </div>
        <hr/>
        <h3>Books in hall:</h3>
        <div class="card" style="width: 18rem;">
            <ul class="list-group list-group-flush">
                <c:forEach var="bookInHall" items="${sessionScope.books_in_hall}">
                    <li class="list-group-item">&laquo;${bookInHall.book.title}&raquo; ${bookInHall.book.author}.</li>
                </c:forEach>
            </ul>
        </div>
    </c:when>
    <c:when test="${sessionScope.user.role.roleName == 'librarian'}">
        <a class="btn btn-outline-secondary" style="text-decoration: none;color: #000 !important" href="account?command=show_all_orders" role="button">Show all orders</a>
        <a class="btn btn-outline-secondary" style="text-decoration: none;color: #000 !important" href="account?command=show_all_users" role="button">Show all users</a>
    </c:when>
    <c:when test="${sessionScope.user.role.roleName == 'admin'}">
        <a class="btn btn-outline-secondary" style="text-decoration: none;color: #000 !important" href="account?command=librarians_settings" role="button">Add/delete librarian</a>
        <a class="btn btn-outline-secondary" style="text-decoration: none;color: #000 !important" href="account?command=users_settings" role="button">Block/unblock user</a>
    </c:when>
</c:choose>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
</body>
</html>
