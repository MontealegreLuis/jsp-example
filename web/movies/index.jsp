<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.codeup.db.MySQLConnection" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.codeup.movies.*" %>
<%
    String searchedCategory = "";
    List<Movie> allMovies = new ArrayList<>();
    List<Category> allCategories = new ArrayList<>();
    MySQLConnection connection = new MySQLConnection("root", "Codeup1!", "movies_db");
    try {
        Movies movies = new JdbcMovies(connection.connect());
        JdbcCategories categories = new JdbcCategories(connection.connect());
        allCategories = categories.all();
        searchedCategory = request.getParameter("category");
        if (searchedCategory != null && !searchedCategory.isEmpty()) {
            allMovies = movies.inCategory(searchedCategory);
        } else {
            allMovies = movies.all();
        }
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
                    <h1>Search your favorite movies</h1>
                </header>
            </div>
            <div class="col-md-4 search">
                <form class="form-inline">
                    <div class="form-group">
                        <label for="category">Category</label>
                        <select name="category" id="category" class="form-control">
                            <option value="">Choose one</option>
                            <% for (Category category : allCategories) { %>
                                <option value="<%= category.id() %>">
                                    <%= category.name() %>
                                </option>
                            <% } %>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-default">
                        <span class="glyphicon glyphicon-search"></span>
                        Search
                    </button>
                </form>
            </div>
        </div>
        <table class="table table-striped table-bordered table-hover">
            <thead>
                <tr>
                    <th>Title</th>
                    <th>Rating</th>
                    <th>Rate!</th>
                </tr>
            </thead>
            <tbody>
            <% for (Movie movie : allMovies) { %>
            <tr>
                <td>
                    <a href="show.jsp?id=<%= movie.id() %>">
                        <%= movie.title() %>
                    </a>
                </td>
                <td><%= movie.rating() %></td>
                <td>
                    <form action="rate.jsp" method="post">
                        <input type="hidden" name="id" value="<%= movie.id() %>">
                        <label class="radio-inline">
                            <input type="radio" name="rating" value="1"> 1
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="rating" value="2"> 2
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="rating" value="3"> 3
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="rating" value="4"> 4
                        </label>
                        <label class="radio-inline">
                            <input type="radio" name="rating" value="5"> 5
                        </label>
                        &nbsp;&nbsp;
                        <button type="submit" class="btn btn-primary">
                            <span class="glyphicon glyphicon-ok"></span>
                            Rate
                        </button>
                    </form>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
    <jsp:include page="/includes/scripts.jsp"/>
</body>
</html>
