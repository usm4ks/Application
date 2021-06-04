<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="h" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>New book form</title>
</head>
<body>
<h:header/>
<h3><fmt:message key="add_new_book"/></h3>
<form class="row g-3 needs-validation" method="post" action="book_list?command=add_new_book">
    <div class="col-md-4">
        <label for="validationCustom01" class="form-label"><fmt:message key="title"/></label>
        <input type="text" name="title" class="form-control" id="validationCustom01" placeholder="<fmt:message key="title"/>" required>
        <div class="valid-feedback">
        </div>
    </div>
    <div class="col-md-4">
        <label for="validationCustom02" class="form-label"><fmt:message key="author"/></label>
        <input type="text" name="author" class="form-control" id="validationCustom02" placeholder="<fmt:message key="author"/>" required>
        <div class="valid-feedback">
        </div>
    </div>
    <div class="col-md-4">
        <label for="validationCustom01" class="form-label"><fmt:message key="publishing_house"/></label>
        <input type="text" name="publishing_house" class="form-control" id="validationCustom03" placeholder="<fmt:message key="publishing_house"/>" required>
        <div class="valid-feedback">
        </div>
    </div>
    <div class="col-md-4">
        <label for="validationCustom02" class="form-label"><fmt:message key="year"/></label>
        <input type="number" name="year" class="form-control" id="validationCustom04" placeholder="<fmt:message key="year"/>" required>
        <div class="valid-feedback">
        </div>
    </div>
    <div class="col-md-4">
        <label for="validationCustom02" class="form-label"><fmt:message key="amount"/></label>
        <input type="number" name="amount" class="form-control" id="validationCustom05" placeholder="<fmt:message key="amount"/>" required>
        <div class="valid-feedback">
        </div>
    </div>
    <div class="col-12">
        <button class="btn btn-primary" type="submit"><fmt:message key="submit"/></button>
    </div>
</form>
</body>
</html>
