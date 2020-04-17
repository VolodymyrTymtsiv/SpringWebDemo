<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
<head>
    <title>Lesson-18</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
    <script
            src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script
            src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>

<nav class="navbar navbar-default">
    <div class="container-fluid">
        <ul class="nav navbar-nav">
            <li class="active"><a href="/ILibrary/books/">All books</a></li>
            <li><a href="/ILibrary/books/new">new Book</a></li>
        </ul>
    </div>
</nav>


<div class="container">
    <c:choose>
        <c:when test="${mode == 'BOOK_VIEW'}">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Book Name</th>
                    <th>Author</th>
                    <th>Cover</th>
                    <th>Edit</th>
                    <th>Delete</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="book" items="${books}">
                    <tr>
                        <td>${book.id}</td>
                        <td>${book.name}</td>
                        <td>${book.author}</td>
                        <td>
                            <img src="/ILibrary/book-cover-files/download/${book.coverId}" width="50px">
                        </td>
                        <td><a href="/ILibrary/books/update?id=${book.id}">edit</a></td>
                        <td><a href="/ILibrary/books/delete?id=${book.id}">delete</a></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>

        <c:when test="${mode == 'BOOK_EDIT' || mode == 'BOOK_CREATE'}">
            <form action="${mode == 'BOOK_EDIT' ? "/ILibrary/books/edit": "/ILibrary/books/create"}" method="POST">
                <c:choose>
                    <c:when test="${mode == 'BOOK_EDIT'}">
                        <img src="/ILibrary/book-cover-files/download/${book.coverId}" width="200px" id="book-cover">
                        <input type="hidden" value="${book.id}" class="form-control" id="id" name="id">
                    </c:when>

                    <c:when test="${mode == 'BOOK_CREATE'}">
                        <img src="https://omegamma.com.au/wp-content/uploads/2017/04/default-image.jpg" id="book-cover"
                             width="200px">
                    </c:when>
                </c:choose>

                <div class="form-group">
                    <label for="cover-file">Book cover</label>
                    <input id="cover-file" type="file" />
                </div>

                <input type="hidden" value=""
                       class="form-control"
                       id="cover-id" name="coverId">

                <label for="bookName">Book Name:</label> <input type="text" class="form-control" id="name" name="name"
                                                                value="${book.name}">

                <div class="form-group">
                    <label for="author">Author:</label> <input type="text"
                                                               class="form-control" id="author" name="author"
                                                               value="${book.author}">
                </div>
                <button type="submit" class="btn btn-default">Submit</button>
            </form>
        </c:when>

    </c:choose>
    <script src="${pageContext.request.contextPath}/js/book.js"></script>
</div>
</body>
</html>









