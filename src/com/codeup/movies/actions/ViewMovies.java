/**
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.codeup.movies.actions;

import com.codeup.db.MySQLConnection;
import com.codeup.movies.JdbcCategories;
import com.codeup.movies.JdbcMovies;
import com.codeup.movies.Movie;
import com.codeup.movies.Movies;

import java.sql.SQLException;
import java.util.List;

public class ViewMovies {
    private final MySQLConnection connection;

    public ViewMovies(MySQLConnection connection) {
        this.connection = connection;
    }

    public MoviesInformation view(String category) {
        try {
            Movies movies = new JdbcMovies(connection.connect());

            return new MoviesInformation(
                new JdbcCategories(connection.connect()).all(),
                getMovies(movies, category)
            );
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return null;
    }

    private List<Movie> getMovies(
        Movies movies,
        String category
    ) throws SQLException {
        if (category != null && !category.isEmpty()) {
            return movies.inCategory(category);
        } else {
            return movies.all();
        }
    }
}
