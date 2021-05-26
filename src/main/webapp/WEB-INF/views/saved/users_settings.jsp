<html>
<head>
    <%@ page contentType="text/html;charset=UTF-8" %>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    <title>User list</title>
</head>
<body>
<c:if test="${sessionScope.block_result != null}">
    <h5>${sessionScope.block_result}</h5>
</c:if>
<form id="block" method="post" action="account?command=block_user">
    <input type="email" name="userEmail" placeholder="User email" />
    <button type="submit">block</button>
</form>
<h1>Blocked users:</h1>
<c:forEach var="user" items="${requestScope.blocked_users}">
    <h4>${user.firstName} ${user.lastName}</h4>
    <h4>${user.email}</h4>
    <form action="book_list?command=unblock_user&userId=${user.id}" method="post">
        <input type="submit" value="Unblock"/>
    </form>
    <hr/>
</c:forEach>
</body>
</html>