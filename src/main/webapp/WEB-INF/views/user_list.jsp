<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="h" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Title</title>
</head>
<body>
<h:header/>
<h3><fmt:message key="users"/>:</h3>
<table style="" class="table">
    <thead>
    <tr>
        <th scope="col"><fmt:message key="email"/></th>
        <th scope="col"><fmt:message key="f_name"/></th>
        <th scope="col"><fmt:message key="l_name"/></th>
        <th scope="col"><fmt:message key="info"/></th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="user" items="${requestScope.user_list}">
        <tr>
            <th scope="row">${user.email}</th>
            <td>${user.firstName}</td>
            <td>${user.lastName}</td>
            <td><a class="btn btn-outline-secondary" style="text-decoration: none;color: #000 !important" href="account?command=show_user_info&userId=${user.id}" role="button"><fmt:message key="show_more_information"/></a></td></tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
