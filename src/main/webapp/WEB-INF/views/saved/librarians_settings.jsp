<html>
<%@ page contentType="text/html;charset=UTF-8" %>
<head>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    <title>User list</title>
</head>
<body>
<c:if test="${sessionScope.add_result != null}">
    <h5>${sessionScope.add_result}</h5>
</c:if>
<form id="add" method="post" action="account?command=add_librarian">
    <input type="email" name="librarianEmail" placeholder="Librarian email" />
    <button type="submit">add</button>
</form>
<h1>Librarians:</h1>
<c:forEach var="librarian" items="${requestScope.librarians}">
    <h4>${librarian.firstName} ${librarian.lastName}</h4>
    <h4>${librarian.email}</h4>
    <form action="book_list?command=delete_librarian&userId=${librarian.id}" method="post">
        <input type="submit" value="Delete"/>
    </form>
    <hr/>
</c:forEach>
</body>
</html>