<%@ page import="com.codeup.db.MySQLConnection" %>
<%@ page import="com.codeup.movies.Movies" %>
<%@ page import="com.codeup.movies.JdbcMovies" %>
<%@ page import="com.codeup.movies.Movie" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int rate = Integer.parseInt(request.getParameter("rating"));
    int id = Integer.parseInt(request.getParameter("id"));
    MySQLConnection connection = new MySQLConnection("root", "Codeup1!", "movies_db");

    try {
        Movies movies = new JdbcMovies(connection.connect());
        Movie movie = movies.with(id);
        movie.rate(rate);
        movies.update(movie);
        response.sendRedirect("/movies/index.jsp");
    } catch (Exception e) {
        e.printStackTrace();
    } finally {
        connection.close();
    }
%>
