<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="h" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Users settings</title>
</head>
<body>
<h:header/>
<c:if test="${sessionScope.block_result != null}">
    <h5>${sessionScope.block_result}</h5>
</c:if>
<form class="d-flex" id="search_book" method="post" action="account?command=block_user">
    <label>
        <input class="form-control me-2" type="email" name="userEmail" placeholder="User email" />
    </label>
    <button class="btn btn-outline-success" type="submit" name="command" value="block_user">block</button>
</form>
<h3>Blocked users:</h3>
<table style="" class="table">
    <thead>
    <tr>
        <th scope="col">Email</th>
        <th scope="col">First name</th>
        <th scope="col">Last name</th>
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
                    <input class="btn btn-outline-secondary" type="submit" value="Unblock"/>
                </form>
            </td></tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
