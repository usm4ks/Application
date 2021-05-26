<%@taglib prefix="h" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>Error</title>
</head>
<body>
<h:header/>
<h1 class="display-6">oooops, something went wrong....</h1>
<br/>
<p class="lead">${requestScope.error_message}</p>
</body>
</html>
