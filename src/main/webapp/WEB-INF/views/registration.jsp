<%@taglib prefix="h" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Registration</title>
</head>
<body>
<h:header/>
<h2 style="text-decoration-style: inherit"><fmt:message key="registration_form"/>:</h2>
<c:if test="${sessionScope.registration_result != null}">
    <h5 style="color: red">${sessionScope.registration_result}</h5>
    <c:remove var="registration_result" scope="session" />
</c:if>
<form class="row g-3 needs-validation" method="post" action="registration?command=registration">
    <div class="col-md-4">
        <label for="validationCustom01" class="form-label"><fmt:message key="f_name"/></label>
        <input type="text" name="firstName" class="form-control" id="validationCustom01" placeholder="<fmt:message key="f_name"/>" required>
        <div class="valid-feedback">
        </div>
    </div>
    <div class="col-md-4">
        <label for="validationCustom02" class="form-label"><fmt:message key="l_name"/></label>
        <input type="text" name="lastName" class="form-control" id="validationCustom02" placeholder="<fmt:message key="l_name"/>" required>
        <div class="valid-feedback">
        </div>
    </div>
    <div class="col-md-4">
        <label for="validationCustom01" class="form-label"><fmt:message key="email"/></label>
        <input type="email" name="email" class="form-control" id="validationCustom03" placeholder="<fmt:message key="email"/>" required>
        <div class="valid-feedback">
        </div>
    </div>
    <br/>
    <div class="col-md-4">
        <label for="validationCustom02" class="form-label"><fmt:message key="password"/></label>
        <input type="password" name="password" class="form-control" id="validationCustom04" placeholder="<fmt:message key="password"/>" required>
        <div class="valid-feedback">
        </div>
    </div>
    <div class="col-12">
        <button class="btn btn-primary" type="submit"><fmt:message key="submit"/></button>
    </div>
</form>
<a class="btn btn-outline-secondary" style="text-decoration: none;color: #000 !important" href="index.jsp" role="button"><fmt:message key="back_to_log_in"/></a>
</body>
</html>
