<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
    <title>E-Library</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
</head>
<body>
<h2 class="html-editor-align-center">Welcome to E-Library</h2>
<c:if test="${sessionScope.log_in_result != null}">
    <h5 style="color: red">${sessionScope.log_in_result}</h5>
</c:if>
<form action="log_in?command=log_in" method="post" class="row g-3 needs-validation" >
    <div class="col-md-4">
        <label for="validationCustom01" class="form-label">Email</label>
        <input type="email" name="email" class="form-control" id="validationCustom01" placeholder="Email" required>
        <div class="valid-feedback">
        </div>
    </div>
    <br/>
    <div class="col-md-4">
        <label for="validationCustom02" class="form-label">Password</label>
        <input type="password" name="password" class="form-control" id="validationCustom02" placeholder="Password" required>
        <div class="valid-feedback">
        </div>
    </div>
    <div class="col-12">
        <button class="btn btn-primary" type="submit">Submit</button>
    </div>
</form>
<a class="btn btn-outline-secondary" style="text-decoration: none;color: #000 !important" href="book_list?command=show_all_books" role="button">Show all books</a>
<a class="btn btn-outline-secondary" style="text-decoration: none;color: #000 !important" href="registration" role="button">Registration</a>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
</body>
</html>
