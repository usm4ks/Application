<%@taglib prefix="h" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title>Librarians settings</title>
</head>
<body>
<h:header/>
<c:if test="${sessionScope.add_result != null}">
    <h5><fmt:message key="${sessionScope.add_result}"/></h5>
</c:if>
<form class="d-flex" id="search_book" method="post" action="account?command=add_librarian">
    <label>
        <input class="form-control me-2" type="email" name="librarianEmail" placeholder="<fmt:message key="librarian_email"/>" />
    </label>
    <button class="btn btn-outline-success" type="submit" name="command" value="add_librarian"><fmt:message key="add"/></button>
</form>
<h3><fmt:message key="librarians"/>:</h3>
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
    <c:forEach var="librarian" items="${requestScope.librarians}">
        <tr>
            <th scope="row">${librarian.firstName}</th>
            <td>${librarian.lastName}</td>
            <td>${librarian.email}</td>
            <td>
                <form action="book_list?command=delete_librarian&userId=${librarian.id}" method="post">
                    <input class="btn btn-outline-secondary" type="submit" value="<fmt:message key="delete"/>">
                </form>
            </td></tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
