<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="h" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<html>
<head>
    <title>Page not found</title>
</head>
<body>
<h:header/>
<h1 class="display-6"><fmt:message key="page_not_found"/></h1>
<br/>
<p class="lead">Error 404</p>
</body>
</html>