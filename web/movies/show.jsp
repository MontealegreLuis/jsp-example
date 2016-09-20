<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.codeup.db.MySQLConnection" %>
<%@ page import="com.codeup.movies.*" %>
<%
    MySQLConnection connection = new MySQLConnection("root", "Codeup1!", "movies_db");
    Movie movie = null;
    try {
        Movies movies = new JdbcMovies(connection.connect());
        movie = movies.with(Integer.parseInt(request.getParameter("id")));
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        connection.close();
    }
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="/includes/head.jsp">
        <jsp:param name="title" value="Search your favorite movies"/>
    </jsp:include>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-8">
            <header class="page-header">
                <h1><%= movie.title() %></h1>
            </header>
            <p class="lead">Rating: <strong><%= movie.rating() %></strong></p>
        </div>
    </div>
</div>
<jsp:include page="/includes/scripts.jsp"/>
</body>
</html>
