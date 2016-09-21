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
    public MoviesInformation view(String category) {
        MySQLConnection connection = new MySQLConnection("root", "Codeup1!", "movies_db");
        try {
            Movies movies = new JdbcMovies(connection.connect());
            JdbcCategories categories = new JdbcCategories(connection.connect());

            return new MoviesInformation(categories.all(), getMovies(movies, category));
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
