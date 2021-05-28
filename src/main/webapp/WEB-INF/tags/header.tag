<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
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
            <a class="navbar-brand" href="book_list?command=show_all_books">E-Library</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                    <li >
                        <a class="form-control me-2" style="text-decoration: none;color: #000 !important" href="book_list?command=show_all_books" role="button"><fmt:message key="header.home"/></a>
                    </li>
                    <c:choose>
                        <c:when test="${sessionScope.user != null}">
                            <li>
                                <a class="form-control me-2" style="text-decoration: none;color: #000 !important" href="account?command=account" role="button"><fmt:message key="header.settings"/></a>
                            </li>
                            <li>
                                <form class="d-flex" action="book_list?command=log_out" method="post">
                                    <button class="form-control me-2" type="submit"><fmt:message key="header.log_out"/></button>
                                </form>
                            </li>
                        </c:when>
                        <c:when test="${sessionScope.user == null}">
                            <li>
                                <a class="form-control me-2" style="text-decoration: none;color: #000 !important" href="index.jsp" role="button"><fmt:message key="header.log_in"/></a>
                            </li>
                        </c:when>
                    </c:choose>
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            <fmt:message key="header.language"/>
                        </a>
                        <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                            <li>
                                <form class="dropdown-item" action="account?command=set_lang&lang=en" method="post">
                                    <button class="form-control me-2" type="submit"><fmt:message key="header.english"/></button>
                                </form>
                            </li>
                            <li>
                                <form class="dropdown-item" action="account?command=set_lang&lang=ru" method="post">
                                    <button class="form-control me-2" type="submit"><fmt:message key="header.russian"/></button>
                                </form>
                            </li>
                        </ul>
                    </li>
                </ul>
                <form class="d-flex" id="search_book" method="get" action="book_list">
                    <label>
                        <input class="form-control me-2" type="search" name="search" placeholder="..." />
                    </label>
                    <button class="btn btn-outline-success" type="submit" name="command" value="search_book"><fmt:message key="header.search"/></button>
                </form>
            </div>
        </div>
    </nav>
</header>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
</body>
</html>