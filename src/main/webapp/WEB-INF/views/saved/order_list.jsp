<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Order list</title>
</head>
<body>
<h1>Orders:</h1>
<c:forEach var="order" items="${requestScope.order_list}">
    <h4>User email: ${order.user.email}</h4>
    <h4>Book: ${order.book.title}</h4>
    <h4>Order type: ${order.type.type}</h4>
    <c:choose>
        <c:when test="${order.type.type == 'on_ticket'}">
            <form action="book_list?command=accept_order_book&bookId=${order.book.id}&userId=${order.user.id}&type=${order.type.type}" method="post">
                <input id="start" name="until_date" type="date">
                <button type="submit">Accept</button>
            </form>
        </c:when>
        <c:when test="${order.type.type == 'in_hall'}">
            <form action="book_list?command=accept_order_book&bookId=${order.book.id}&userId=${order.user.id}&type=${order.type.type}" method="post">
                <input type="submit" value="Accept"/>
            </form>
        </c:when>
    </c:choose>

    <hr/>
</c:forEach>
</body>
</html>