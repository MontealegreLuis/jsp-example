/**
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.codeup.movies;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcMovies implements Movies {
    private Connection connection;

    public JdbcMovies(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Movie with(int id) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
            "SELECT * FROM movies WHERE id = ?"
        );
        statement.setInt(1, id);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return new Movie(
            resultSet.getInt("id"),
            resultSet.getString("title"),
            resultSet.getInt("rating")
        );
    }

    @Override
    public void update(Movie movie) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
            "UPDATE movies SET title = ?, rating = ? WHERE id = ?"
        );
        statement.setString(1, movie.title());
        statement.setInt(2, movie.rating());
        statement.setInt(3, movie.id());
        statement.executeUpdate();
    }

    @Override
    public List<Movie> all() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM movies");
        return populateMovies(resultSet);
    }

    @Override
    public List<Movie> inCategory(String category) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
            "SELECT " +
                "m.* " +
            "FROM movies m " +
            "INNER JOIN movies_categories mc ON mc.movie_id = m.id " +
            "INNER JOIN categories c ON c.id = mc.category_id " +
            "WHERE c.id = ?"
        );
        statement.setString(1, category);

        return populateMovies(statement.executeQuery());
    }

    private ArrayList<Movie> populateMovies(ResultSet resultSet) throws SQLException {
        ArrayList<Movie> movies = new ArrayList<>();
        while (resultSet.next()) {
            movies.add(new Movie(
                resultSet.getInt("id"),
                resultSet.getString("title"),
                resultSet.getInt("rating")
            ));
        }
        return movies;
    }
}
