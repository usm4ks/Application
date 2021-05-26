<html>
<head>
    <%@ page contentType="text/html;charset=UTF-8" %>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    <title>User list</title>
</head>
<body>
<h1>Users:</h1>
<c:forEach var="user" items="${requestScope.user_list}">
    <h4>${user.firstName} ${user.lastName}</h4>
    <h4>${user.email}</h4>
    <button type="submit"><a href="account?command=show_user_info&userId=${user.id}">Show more information</a></button>
    <hr/>
</c:forEach>
</body>
</html>