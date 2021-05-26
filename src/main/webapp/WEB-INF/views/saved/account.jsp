<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Personal account</title>
</head>
<body>
<form action="book_list?command=log_out" method="post">
    <button type="submit">Log out</button>
</form>
 <h3>Email: ${sessionScope.user.email}</h3>
 <h3>First Name: ${sessionScope.user.firstName}</h3>
 <h3>Last Name: ${sessionScope.user.lastName}</h3>
 <c:if test="${sessionScope.user.role.roleName == 'user'}">
     <h3>Blocked: ${sessionScope.user.blocked}</h3>
 </c:if>
 <h3>Role: ${sessionScope.user.role.roleName}</h3>
<c:if test="${sessionScope.user.role.roleName == 'librarian'}">

</c:if>
<c:choose>
    <c:when test="${sessionScope.user.role.roleName == 'user'}">
        <h1>Ordered books: <br/>
            <c:forEach var="order" items="${sessionScope.ordered_books}">
                <h3>
                    ${order.book.title} <br/>
                    ${order.book.author}<br/>
                    ${order.book.year}<br/>
                    ${order.type.type}<br/>
                </h3>
            </c:forEach>
        </h1>
        <hr/>
        <h1>Books on ticket<br/>
            <c:forEach var="bookOnTicket" items="${sessionScope.books_on_ticket}">
                <h3>
                        ${bookOnTicket.book.title} <br/>
                        ${bookOnTicket.book.author}<br/>
                        ${bookOnTicket.book.year}<br/>
                        ${bookOnTicket.untilDate}
                </h3>
            </c:forEach>
        </h1> <hr/>
        <h1>Books in hall<br/>
            <c:forEach var="bookInHall" items="${sessionScope.books_in_hall}">
                <h3>
                        ${bookInHall.book.title} <br/>
                        ${bookInHall.book.author}<br/>
                        ${bookInHall.book.year}
                </h3>
            </c:forEach>
        </h1>

    </c:when>
    <c:when test="${sessionScope.user.role.roleName == 'librarian'}">
        <a href="account?command=show_all_orders">Show all orders</a>
        <a href="account?command=show_all_users">Show all users</a>
    </c:when>
    <c:when test="${sessionScope.user.role.roleName == 'admin'}">
        <a href="account?command=librarians_settings">Add/delete librarian</a>
        <a href="account?command=users_settings">Block/unblock user</a>
    </c:when>

</c:choose>
</body>
</html>