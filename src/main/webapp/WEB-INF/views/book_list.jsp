<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="h" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Book list</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
</head>
<body>
<h:header/>
<c:if test="${sessionScope.user.role.roleName =='admin'}">
    <h4><button class="form-control me-2" type="submit"  name="command" value="add_new_book"><a style="text-decoration: none;color: #000 !important" href="book_list?command=add_new_book_form">Add new book</a></button></h4>
</c:if>
<div class="dropdown">
    <button class="form-control me-2" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
        Sort by
    </button>
    <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
        <li><a class="dropdown-item" href="book_list?command=show_sorted_books&sort_by=title">title</a></li>
        <li><a class="dropdown-item" href="book_list?command=show_sorted_books&sort_by=author">author</a></li>
        <li><a class="dropdown-item" href="book_list?command=show_sorted_books&sort_by=publishing_house">publishing house</a></li>
        <li><a class="dropdown-item" href="book_list?command=show_sorted_books&sort_by=year">year</a></li>
    </ul>
</div>
<c:if test="${requestScope.search_result != null}">
    <h5>${requestScope.search_result}</h5>
</c:if>
<c:if test="${sessionScope.order_result != null}">
    <h5>${sessionScope.order_result}</h5>
</c:if>
<ul class="list-group list-group-flush">
        <c:forEach var="book" items="${requestScope.book_list}">
            <li class="list-group-item">
                <ul>
                    <li>Title: ${book.title}</li>
                    <li>Author: ${book.author}</li>
                    <li>Publishing house: ${book.publishingHouse}</li>
                    <li>Year: ${book.year}</li>
                    <li>Amount: ${book.amount}</li>
                    <c:choose>
                        <c:when test="${sessionScope.user.role.roleName == 'user' && sessionScope.user.blocked == false}">
                            <form action="book_list?command=order_book&bookId=${book.id}&type=on_ticket" method="post">
                                <button class="form-control me-2" type="submit">Order on ticket</button>
                            </form>
                            <form action="book_list?command=order_book&bookId=${book.id}&type=in_hall" method="post">
                                <button class="form-control me-2" type="submit">Order in hall</button>
                            </form>
                        </c:when>
                        <c:when test="${sessionScope.user.role.roleName == 'admin'}">
                            <h4><button class="form-control me-2" type="submit"  name="command" value="add_new_book"><a style="text-decoration: none;color: #000 !important" href="book_list?command=edit_book_form&bookId=${book.id}">Edit</a></button></h4>
                            <form action="book_list?command=delete_book&bookId=${book.id}" method="post">
                                <button class="form-control me-2" type="submit">Delete</button>
                            </form>
                        </c:when>
                    </c:choose>
                </ul>
            </li>
        </c:forEach>
</ul>
<nav aria-label="Page navigation example">
    <ul class="pagination justify-content-center">
        <c:if test="${requestScope.previous != null}">
            <li class="page-item"><a style="text-decoration: none;color: #000 !important" href="${requestScope.previous}">&lt;Previous...</a></li>
        </c:if>
        <c:if test="${requestScope.next != null}">
            <li class="page-item"><a style="text-decoration: none;color: #000 !important" href="${requestScope.next}">...Next</a>></li>
        </c:if>
    </ul>
</nav>
</body>
</html>