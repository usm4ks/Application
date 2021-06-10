<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="h" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Book list</title>
</head>
<body>
<h:header/>
<c:if test="${sessionScope.user.role.roleName =='admin'}">
    <h4><button class="form-control me-2" type="submit"  name="command" value="add_new_book"><a style="text-decoration: none;color: #000 !important" href="book_list?command=add_new_book_form"><fmt:message key="add_new_book"/></a></button></h4>
</c:if>
<div class="dropdown">
    <button class="form-control me-2" type="button" id="dropdownMenuButton1" data-bs-toggle="dropdown" aria-expanded="false">
        <fmt:message key="sort_by"/>
    </button>
    <ul class="dropdown-menu" aria-labelledby="dropdownMenuButton1">
        <li><a class="dropdown-item" href="book_list?command=show_sorted_books&sort_by=title"><fmt:message key="title"/></a></li>
        <li><a class="dropdown-item" href="book_list?command=show_sorted_books&sort_by=author"><fmt:message key="author"/></a></li>
        <li><a class="dropdown-item" href="book_list?command=show_sorted_books&sort_by=publishing_house"><fmt:message key="publishing_house"/></a></li>
        <li><a class="dropdown-item" href="book_list?command=show_sorted_books&sort_by=year"><fmt:message key="year"/></a></li>
    </ul>
</div>
<c:if test="${requestScope.search_result != null}">
    <h5><fmt:message key="${requestScope.search_result}"/></h5>
    <c:remove var="search_result" scope="session" />
</c:if>
<c:if test="${sessionScope.order_result != null}">
    <h5><fmt:message key="${sessionScope.order_result}"/></h5>
    <c:remove var="order_result" scope="session" />
</c:if>
<c:if test="${sessionScope.edit_result != null}">
    <h5><fmt:message key="${sessionScope.edit_result}"/></h5>
    <c:remove var="edit_result" scope="session" />
</c:if>
<c:if test="${sessionScope.add_result != null}">
    <h5><fmt:message key="${sessionScope.add_result}"/></h5>
    <c:remove var="add_result" scope="session" />
</c:if>
<c:if test="${sessionScope.delete_result != null}">
    <h5><fmt:message key="${sessionScope.delete_result}"/></h5>
    <c:remove var="delete_result" scope="session" />
</c:if>
<ul class="list-group list-group-flush">
        <c:forEach var="book" items="${requestScope.book_list}">
            <li class="list-group-item">
                <ul>
                    <li><fmt:message key="title"/>: ${book.title}</li>
                    <li><fmt:message key="author"/>: ${book.author}</li>
                    <li><fmt:message key="publishing_house"/>: ${book.publishingHouse}</li>
                    <li><fmt:message key="year"/>: ${book.year}</li>
                    <li><fmt:message key="amount"/>: ${book.amount}</li>
                    <c:choose>
                        <c:when test="${sessionScope.user.role.roleName == 'user' && sessionScope.user.blocked == false}">
                            <form action="book_list?command=order_book&bookId=${book.id}&type=on_ticket" method="post">
                                <button class="form-control me-2" type="submit"><fmt:message key="order_on_ticket"/></button>
                            </form>
                            <form action="book_list?command=order_book&bookId=${book.id}&type=in_hall" method="post">
                                <button class="form-control me-2" type="submit"><fmt:message key="order_in_hall"/></button>
                            </form>
                        </c:when>
                        <c:when test="${sessionScope.user.role.roleName == 'admin'}">
                            <h4>
                                <button class="form-control me-2" type="submit"  name="command" value="add_new_book">
                                    <a style="text-decoration: none;color: #000 !important" href="book_list?command=edit_book_form&bookId=${book.id}"><fmt:message key="edit"/>
                                    </a>
                                </button>
                            </h4>
                            <form action="book_list?command=delete_book&bookId=${book.id}" method="post">
                                <button class="form-control me-2" type="submit"><fmt:message key="delete"/></button>
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
            <li class="page-item"><a style="text-decoration: none;color: #000 !important" href="${requestScope.previous}">&lt;<fmt:message key="previous"/>...</a></li>
        </c:if>
        <c:if test="${requestScope.next != null}">
            <li class="page-item"><a style="text-decoration: none;color: #000 !important" href="${requestScope.next}">...<fmt:message key="next"/></a>></li>
        </c:if>
    </ul>
</nav>
</body>
</html>