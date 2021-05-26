<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>E-Library</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
</head>
<body>
<header>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
        <div class="container-fluid">
            <a class="navbar-brand" href="book_list?command=show_all_books&page=1">E-Library</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li >
                        <a class="form-control me-2" style="text-decoration: none;color: #000 !important" href="book_list?command=show_all_books" role="button">Home</a>
                    </li>
                    <c:choose>
                        <c:when test="${sessionScope.user != null}">
                    <li>
                        <a class="form-control me-2" style="text-decoration: none;color: #000 !important" href="account?command=account" role="button">Settings</a>
                    </li>
                    <li>
                        <form class="d-flex" action="book_list?command=log_out" method="post">
                            <button class="form-control me-2" type="submit">Log out</button>
                        </form>
                    </li>
                        </c:when>
                        <c:when test="${sessionScope.user == null}">
                            <a class="form-control me-2" style="text-decoration: none;color: #000 !important" href="log_in" role="button">Login</a>
                        </c:when>
                    </c:choose>
                </ul>
                <form class="d-flex" id="search_book" method="get" action="book_list">
                    <label>
                        <input class="form-control me-2" type="search" name="search" placeholder="search book..." />
                    </label>
                    <button class="btn btn-outline-success" type="submit" name="command" value="search_book">SEARCH</button>
                </form>
            </div>
        </div>
    </nav>
</header>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
</body>
</html>