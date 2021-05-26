<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>E-Library</title>
</head>
<body>
<h1><%= "Welcome to E-Library" %>
</h1>
<br/>
<c:if test="${sessionScope.result != null}">
    <h5>${sessionScope.result}</h5>
</c:if>
<br/>
<form id="signin" method="post" action="log_in?command=log_in">
    <input type="email" name="email" placeholder="Email" />
    <br/>
    <input type="password" name="password" placeholder="Password" />
    <br/>
    <button type="submit">LOG IN!</button>
</form>
<br/>
<a href="book_list?command=show_all_books&page=1">Show all books</a>
<br/>
<a href="registration">Registration</a>
</body>
</html>