<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="h" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Error</title>
</head>
<body>
<h:header/>
<h1 class="display-6"><fmt:message key="smth_went_wrong"/></h1>
<br/>
<p class="lead">${requestScope.error_message}</p>
</body>
</html>
