<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="h" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title>Order list</title>
</head>
<body>
<h:header/>
<h3><fmt:message key="orders"/>:</h3>

<table style="" class="table">
    <thead>
    <tr>
        <th scope="col"><fmt:message key="user_email"/> </th>
        <th scope="col"><fmt:message key="book"/></th>
        <th scope="col"><fmt:message key="order_type"/></th>
        <th scope="col"><fmt:message key="accept"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="order" items="${requestScope.order_list}">
        <tr>
            <th scope="row">${order.user.email}</th>
            <td>${order.book.title}</td>
            <td><fmt:message key="${order.type.type}"/></td>
            <td>
                <c:choose>
                    <c:when test="${order.type.type == 'on_ticket'}">
                        <form class="d-flex" action="book_list?command=accept_order_book&bookId=${order.book.id}&userId=${order.user.id}&type=${order.type.type}" method="post">
                            <input id="start" name="until_date" type="date">
                            <button class="btn btn-outline-secondary" type="submit"><fmt:message key="accept"/></button>
                        </form>
                    </c:when>
                    <c:when test="${order.type.type == 'in_hall'}">
                        <form  action="book_list?command=accept_order_book&bookId=${order.book.id}&userId=${order.user.id}&type=${order.type.type}" method="post">
                            <input class="btn btn-outline-secondary" type="submit" value="<fmt:message key="accept"/>"/>
                        </form>
                    </c:when>
                </c:choose>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
