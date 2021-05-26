<%@taglib prefix="h" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Librarians settings</title>
</head>
<body>
<h:header/>
<c:if test="${sessionScope.add_result != null}">
    <h5>${sessionScope.add_result}</h5>
</c:if>
<form class="d-flex" id="search_book" method="post" action="account?command=add_librarian">
    <label>
        <input class="form-control me-2" type="email" name="librarianEmail" placeholder="Librarian email" />
    </label>
    <button class="btn btn-outline-success" type="submit" name="command" value="add_librarian">add</button>
</form>
<h3>Librarians:</h3>
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
    <c:forEach var="librarian" items="${requestScope.librarians}">
        <tr>
            <th scope="row">${librarian.firstName}</th>
            <td>${librarian.lastName}</td>
            <td>${librarian.email}</td>
            <td>
                <form action="book_list?command=delete_librarian&userId=${librarian.id}" method="post">
                    <input class="btn btn-outline-secondary" type="submit" value="Delete"/>
                </form>
            </td></tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
