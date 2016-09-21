<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="/WEB-INF/includes/head.jsp">
        <jsp:param name="title" value="Search your favorite movies"/>
    </jsp:include>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-8">
            <header class="page-header">
                <h1>${requestScope.movie.title()}</h1>
            </header>
            <p class="lead">Rating: <strong>${requestScope.movie.rating()}</strong></p>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/includes/scripts.jsp"/>
</body>
</html>