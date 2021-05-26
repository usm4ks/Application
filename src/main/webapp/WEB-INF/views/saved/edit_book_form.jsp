<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Edit book</title>
</head>
<h1>Edit book</h1>
<div id="wrapper">
    <form id="addNewBook" method="post" action="book_list?command=edit_book&bookId=${requestScope.book.id}">
        <input type="text" name="title" value="${requestScope.book.title}">
        <br/>
        <input type="text" name="author" value="${requestScope.book.author}" />
        <br/>
        <input type="text" name="publishing_house" value="${requestScope.book.publishingHouse}" />
        <br/>
        <input type="number" name="year" value="${requestScope.book.year}" />
        <br/>
        <input type="number" name="amount" value="${requestScope.book.amount}" />
        <br/>
        <button type="submit">SUBMIT!</button>
    </form>
</div>
</html>