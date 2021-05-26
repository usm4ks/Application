
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Registration</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
</head>
<body>
<h2 style="text-decoration-style: inherit">Registration form:</h2>
<c:if test="${sessionScope.registration_result != null}">
    <h5 style="color: red">${sessionScope.registration_result}</h5>
</c:if>
<form class="row g-3 needs-validation" method="post" action="registration?command=registration">
    <div class="col-md-4">
        <label for="validationCustom01" class="form-label">First name</label>
        <input type="text" name="firstName" class="form-control" id="validationCustom01" placeholder="First name" required>
        <div class="valid-feedback">
        </div>
    </div>
    <div class="col-md-4">
        <label for="validationCustom02" class="form-label">Last name</label>
        <input type="text" name="lastName" class="form-control" id="validationCustom02" placeholder="Last name" required>
        <div class="valid-feedback">
        </div>
    </div>
    <div class="col-md-4">
        <label for="validationCustom01" class="form-label">Email</label>
        <input type="email" name="email" class="form-control" id="validationCustom03" placeholder="Email" required>
        <div class="valid-feedback">
        </div>
    </div>
    <br/>
    <div class="col-md-4">
        <label for="validationCustom02" class="form-label">Password</label>
        <input type="password" name="password" class="form-control" id="validationCustom04" placeholder="Password" required>
        <div class="valid-feedback">
        </div>
    </div>
    <div class="col-12">
        <button class="btn btn-primary" type="submit">Submit</button>
    </div>
</form>
<a class="btn btn-outline-secondary" style="text-decoration: none;color: #000 !important" href="index.jsp" role="button">Back to log in</a>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
</body>
</html>
