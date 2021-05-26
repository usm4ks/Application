<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Add new book</title>
</head>
<h1>Registration Form</h1>
<div id="wrapper">
    <form id="addNewBook" method="post" action="book_list?command=add_new_book">
        <input type="text" name="title" placeholder="Title">
        <br/>
        <input type="text" name="author" placeholder="Author" />
        <br/>
        <input type="text" name="publishing_house" placeholder="Publishing house" />
        <br/>
        <input type="number" name="year" placeholder="Year" />
        <br/>
        <input type="number" name="amount" placeholder="Amount" />
        <br/>
        <button type="submit">SUBMIT!</button>
    </form>
</div>
</html>