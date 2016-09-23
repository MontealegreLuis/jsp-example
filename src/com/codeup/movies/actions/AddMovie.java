/**
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.codeup.movies.actions;

import com.codeup.db.MySQLConnection;
import com.codeup.movies.Category;
import com.codeup.movies.JdbcCategories;
import com.codeup.movies.JdbcMovies;
import com.codeup.movies.Movie;

import java.sql.SQLException;
import java.util.List;

public class AddMovie {
    private final MySQLConnection connection;

    public AddMovie(MySQLConnection connection) {
        this.connection = connection;
    }

    public void add(String title, int rating, String[] categoriesIds) {
        try {
            JdbcCategories categories = new JdbcCategories(connection.connect());
            Movie movie = new Movie(
                title,
                rating,
                categories.with(categoriesIds)
            );
            new JdbcMovies(connection.connect()).add(movie);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    public List<Category> categories() {
        try {
            return new JdbcCategories(connection.connect()).all();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
