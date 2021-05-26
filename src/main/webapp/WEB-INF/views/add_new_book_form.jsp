<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="h" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <title>New book form</title>
</head>
<body>
<h:header/>
<h3>Add new book form</h3>
<form class="row g-3 needs-validation" method="post" action="book_list?command=add_new_book">
    <div class="col-md-4">
        <label for="validationCustom01" class="form-label">Title</label>
        <input type="text" name="title" class="form-control" id="validationCustom01" placeholder="Title" required>
        <div class="valid-feedback">
        </div>
    </div>
    <div class="col-md-4">
        <label for="validationCustom02" class="form-label">Author</label>
        <input type="text" name="author" class="form-control" id="validationCustom02" placeholder="Author name" required>
        <div class="valid-feedback">
        </div>
    </div>
    <div class="col-md-4">
        <label for="validationCustom01" class="form-label">Publishing house</label>
        <input type="text" name="publishing_house" class="form-control" id="validationCustom03" placeholder="Publishing house" required>
        <div class="valid-feedback">
        </div>
    </div>
    <div class="col-md-4">
        <label for="validationCustom02" class="form-label">Year</label>
        <input type="number" name="year" class="form-control" id="validationCustom04" placeholder="Year" required>
        <div class="valid-feedback">
        </div>
    </div>
    <div class="col-md-4">
        <label for="validationCustom02" class="form-label">Amount</label>
        <input type="number" name="amount" class="form-control" id="validationCustom05" placeholder="Amount" required>
        <div class="valid-feedback">
        </div>
    </div>
    <div class="col-12">
        <button class="btn btn-primary" type="submit">Submit</button>
    </div>
</form>
</body>
</html>
