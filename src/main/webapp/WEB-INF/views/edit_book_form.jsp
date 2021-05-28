<%@ taglib prefix="h" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h:header/>
<h3><fmt:message key="edit"/></h3>
<form class="row g-3 needs-validation" method="post" action="book_list?command=edit_book&bookId=${requestScope.book.id}">
    <div class="col-md-4">
        <label for="validationCustom01" class="form-label"><fmt:message key="title"/></label>
        <input type="text" name="title" class="form-control" id="validationCustom01" value="${requestScope.book.title}" required>
    </div>
    <div class="col-md-4">
        <label for="validationCustom02" class="form-label"><fmt:message key="author"/></label>
        <input type="text" name="author" class="form-control" id="validationCustom02" value="${requestScope.book.author}" required>
        <div class="valid-feedback">
        </div>
    </div>
    <div class="col-md-4">
        <label for="validationCustom01" class="form-label"><fmt:message key="publishing_house"/></label>
        <input type="text" name="publishing_house" class="form-control" id="validationCustom03" value="${requestScope.book.publishingHouse}" required>
        <div class="valid-feedback">
        </div>
    </div>
    <div class="col-md-4">
        <label for="validationCustom02" class="form-label"><fmt:message key="year"/></label>
        <input type="number" name="year" class="form-control" id="validationCustom04" value="${requestScope.book.year}" required>
        <div class="valid-feedback">
        </div>
    </div>
    <div class="col-md-4">
        <label for="validationCustom02" class="form-label"><fmt:message key="amount"/></label>
        <input type="number" name="amount" class="form-control" id="validationCustom05" value="${requestScope.book.amount}" required>
        <div class="valid-feedback">
        </div>
    </div>
    <div class="col-12">
        <button class="btn btn-primary" type="submit"><fmt:message key="submit"/></button>
    </div>
</form>
</body>
</html>
