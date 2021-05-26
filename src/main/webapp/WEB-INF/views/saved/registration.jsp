<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Registration</title>
</head>
<h1>Registration Form</h1>
<c:if test="${sessionScope.result != null}">
    <h3>${sessionScope.result}</h3>
</c:if>
<div id="wrapper">
    <form id="registration" method="post" action="registration?command=registration">
        <input type="text" name="firstName" placeholder="First Name">
        <br/>
        <input type="text" name="lastName" placeholder="Last Name" />
        <br/>
        <input type="email" name="email" placeholder="Email" />
        <br/>
        <input type="password" name="password" placeholder="Password" />
        <br/>
        <button type="submit">SUBMIT!</button>
    </form>
</div>
</html>