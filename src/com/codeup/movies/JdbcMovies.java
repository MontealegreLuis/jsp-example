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
    public void add(Movie movie) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
            "INSERT INTO movies(title, rating) VALUES(?, 0)"
        );
        statement.setString(1, movie.title());
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
