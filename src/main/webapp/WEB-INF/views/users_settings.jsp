<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="h" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title>Users settings</title>
</head>
<body>
<h:header/>
<c:if test="${sessionScope.block_result != null}">
    <h5><fmt:message key="${sessionScope.block_result}"/></h5>
</c:if>
<form class="d-flex" id="search_book" method="post" action="account?command=block_user">
    <label>
        <input class="form-control me-2" type="email" name="userEmail" placeholder="<fmt:message key="user_email"/>" />
    </label>
    <button class="btn btn-outline-success" type="submit" name="command" value="block_user"><fmt:message key="block"/></button>
</form>
<h3><fmt:message key="blocked_users"/>:</h3>
<table style="" class="table">
    <thead>
    <tr>
        <th scope="col"><fmt:message key="f_name"/></th>
        <th scope="col"><fmt:message key="l_name"/></th>
        <th scope="col"><fmt:message key="email"/></th>
        <th scope="col"></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${requestScope.blocked_users}">
        <tr>
            <th scope="row">${user.firstName}</th>
            <td>${user.lastName}</td>
            <td>${user.email}</td>
            <td>
                <form action="book_list?command=unblock_user&userId=${user.id}" method="post">
                    <input class="btn btn-outline-secondary" type="submit" value="<fmt:message key="unblock"/>"/>
                </form>
            </td></tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
