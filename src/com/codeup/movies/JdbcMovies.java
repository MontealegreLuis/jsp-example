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
    public void add(Movie movie) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO movies (title, rating) VALUES (?, ?)",
                Statement.RETURN_GENERATED_KEYS
            );
            statement.setString(1, movie.title());
            statement.setInt(2, movie.rating());
            statement.executeUpdate();
            ResultSet key = statement.getGeneratedKeys();
            key.next();
            movie.setId(key.getInt(1));
            statement.close();
            if (movie.category() != null) {
                categorize(movie);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Cannot add movie", e);
        }
    }

    private void categorize(Movie movie) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
            "INSERT INTO movies_categories (movie_id, category_id) VALUES (?, ?)"
        );
        statement.setInt(1, movie.id());
        statement.setInt(2, movie.category().id());
        statement.executeUpdate();
        statement.close();
    }

    @Override
    public Movie with(int id) {
        try {
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
        } catch (SQLException e) {
            throw new RuntimeException("Cannot find movie", e);
        }
    }

    @Override
    public void update(Movie movie) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                "UPDATE movies SET title = ?, rating = ? WHERE id = ?"
            );
            statement.setString(1, movie.title());
            statement.setInt(2, movie.rating());
            statement.setInt(3, movie.id());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Cannot update movie", e);
        }
    }

    @Override
    public List<Movie> all() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM movies");
            return populateMovies(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Cannot retrieve the movies", e);
        }
    }

    @Override
    public List<Movie> inCategory(String category) {
        try {
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
        } catch (SQLException e) {
            throw new RuntimeException("Cannot filter movies by category", e);
        }
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
