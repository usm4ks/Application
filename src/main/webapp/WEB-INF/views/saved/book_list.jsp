<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
    <title>JSP - Hello World</title>
</head>
<body>
<c:if test="${sessionScope.user != null}">
    <h1>${sessionScope.user.firstName}</h1>
    <form action="book_list?command=log_out" method="post">
        <button type="submit">Log out</button>
    </form>
    <a href="account?command=account">My account</a>
    <c:if test="${sessionScope.user.role.roleName =='admin'}">
        <h4><button type="submit"  name="command" value="add_new_book"><a href="book_list?command=add_new_book_form">Add new book</a></button></h4>
    </c:if>
</c:if>
<form id="search_book" method="get" action="book_list">
    <label>
        <input type="search" name="search" placeholder="search..." />
    </label>
    <button type="submit" name="command" value="search_book">SEARCH</button>
</form>
<h4><button type="submit"  name="command" value="sort_by_title"><a href="book_list?command=show_sorted_books&sort_by=title&page=1">Sort by title</a></button></h4>
<h4><button type="submit" name="command" value="sort_by_author"><a href="book_list?command=show_sorted_books&sort_by=author&page=1">Sort by author</a></button></h4>
<h4><button type="submit"  name="command" value="sort_by_title"><a href="book_list?command=show_sorted_books&sort_by=publishing_house&page=1">Sort by publishing house</a></button></h4>
<h4><button type="submit" name="command" value="sort_by_author"><a href="book_list?command=show_sorted_books&sort_by=year&page=1">Sort by year</a></button></h4>
<c:if test="${requestScope.not_found} != null}">
    <h5 class="navbar-toggler">${requestScope.not_found}</h5>
</c:if>
<c:if test="${sessionScope.order_result != null}">
    <h5 class="navbar-toggler">${sessionScope.order_result}</h5>
</c:if>
<c:forEach var="book" items="${requestScope.book_list}">
    <ul>
        <li><h3>Title: ${book.title}</h3></li>
        <li><h3>Author: ${book.author}</h3></li>
        <li><h3>Publishing house: ${book.publishingHouse}</h3></li>
        <li><h3>Year: ${book.year}</h3></li>
        <li><h3>Amount: ${book.amount}</h3></li>
        <c:if test="${sessionScope.user.role.roleName == 'user' && sessionScope.user.blocked == false}">
        <form action="book_list?command=order_book&bookId=${book.id}&type=on_ticket" method="post">
            <button type="submit">Order on ticket</button>
        </form>
        <form action="book_list?command=order_book&bookId=${book.id}&type=in_hall" method="post">
            <button type="submit">Order in hall</button>
        </form>
        </c:if>
        <c:if test="${sessionScope.user.role.roleName == 'admin'}">
            <form  method="get">
                <button type="submit"><a href="book_list?command=edit_book_form&bookId=${book.id}">Edit</a></button>
            </form>
            <form action="book_list?command=delete_book&bookId=${book.id}" method="post">
                <button type="submit">Delete</button>
            </form>
        </c:if>
    </ul>
    <hr/>
</c:forEach>
<nav aria-label="Page navigation example">
    <ul class="pagination">
        <c:if test="${requestScope.previous != null}">
            <li class="page-item"><a style="text-decoration: none;color: #000 !important" href="${requestScope.previous}">Previous</a></li>
        </c:if>
        <c:if test="${requestScope.next != null}">
        <li class="page-item"><a style="text-decoration: none;color: #000 !important" href="${requestScope.next}">Next</a>></li>
        </c:if>
    </ul>
</nav>
</body>
</html>