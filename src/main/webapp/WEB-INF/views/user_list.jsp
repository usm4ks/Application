<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="h" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h:header/>
<h3>Users:</h3>
<table style="" class="table">
    <thead>
    <tr>
        <th scope="col">Email</th>
        <th scope="col">First name</th>
        <th scope="col">Last name</th>
        <th scope="col">Info</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${requestScope.user_list}">
        <tr>
            <th scope="row">${user.email}</th>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td><a class="btn btn-outline-secondary" style="text-decoration: none;color: #000 !important" href="account?command=show_user_info&userId=${user.id}" role="button">Show more information</a></td></tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
