<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="h" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>E-Library</title>
</head>
<body>
<h:header/>

<h2 class="html-editor-align-center"><fmt:message key="welcome"/></h2>
<c:if test="${sessionScope.log_in_result != null}">
    <h5 style="color: red">${sessionScope.log_in_result}</h5>
    <c:remove var="log_in_result" scope="session" />
</c:if>
<form action="log_in?command=log_in" method="post" class="row g-3 needs-validation" >
    <div class="col-md-4">
        <label for="validationCustom01" class="form-label"><fmt:message key="email"/></label>
        <input type="email" name="email" class="form-control" id="validationCustom01" placeholder="<fmt:message key="email"/>" required>
        <div class="valid-feedback">
        </div>
    </div>
    <br/>
    <div class="col-md-4">
        <label for="validationCustom02" class="form-label"><fmt:message key="password"/></label>
        <input type="password" name="password" class="form-control" id="validationCustom02" placeholder="<fmt:message key="password"/>" required>
        <div class="valid-feedback">
        </div>
    </div>
    <div class="col-12">
        <button class="btn btn-primary" type="submit"><fmt:message key="submit"/></button>
    </div>
</form>
<a class="btn btn-outline-secondary" style="text-decoration: none;color: #000 !important" href="book_list?command=show_all_books" role="button"><fmt:message key="show_all_books"/></a>
<a class="btn btn-outline-secondary" style="text-decoration: none;color: #000 !important" href="registration" role="button"><fmt:message key="registration"/></a>
</body>
</html>
