<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.codeup.db.MySQLConnection" %>
<%@ page import="com.codeup.movies.Movies" %>
<%@ page import="com.codeup.movies.JdbcMovies" %>
<%@ page import="com.codeup.movies.Movie" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%
  List<Movie> allMovies = new ArrayList<>();
  MySQLConnection connection = new MySQLConnection("root", "Codeup1!", "movies_db");
  try {
    Movies movies = new JdbcMovies(connection.connect());
    allMovies = movies.all();
  } catch (Exception e) {
    e.printStackTrace();
  } finally {
    connection.close();
  }
%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Movies</title>
    <link
        rel="stylesheet"
        href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
        integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
        crossorigin="anonymous"
    >
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
    <div class="container">
        <ul>
        <% for(Movie movie: allMovies) { %>
          <li><%= movie.title() %></li>
        <% } %>
        </ul>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script
        src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
        integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
        crossorigin="anonymous"
    ></script>
  </body>
</html>
