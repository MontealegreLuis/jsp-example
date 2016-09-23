/**
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.codeup.movies.di;

import com.codeup.db.MySQLConnection;
import com.codeup.movies.Categories;
import com.codeup.movies.JdbcCategories;
import com.codeup.movies.JdbcMovies;
import com.codeup.movies.Movies;
import com.codeup.movies.actions.AddMovie;
import com.codeup.movies.actions.RateMovie;
import com.codeup.movies.actions.ViewMovie;
import com.codeup.movies.actions.ViewMovies;

import java.sql.SQLException;

public class Container {
    private static AddMovie addMovie;
    private static RateMovie rateMovie;
    private static ViewMovie viewMovie;
    private static ViewMovies viewMovies;
    private static MySQLConnection connection;

    public static AddMovie addMovie() throws SQLException, ClassNotFoundException {
        if (addMovie == null) {
            addMovie = new AddMovie(categories(), movies());
        }
        return addMovie;
    }

    public static RateMovie rateMovie() throws SQLException, ClassNotFoundException {
        if (rateMovie == null) {
            rateMovie = new RateMovie(movies());
        }
        return rateMovie;
    }


    public static ViewMovie viewMovie() throws SQLException, ClassNotFoundException {
        if (viewMovie == null) {
            viewMovie = new ViewMovie(movies());
        }
        return viewMovie;
    }

    public static ViewMovies viewMovies() throws SQLException, ClassNotFoundException {
        if (viewMovies == null) {
            viewMovies = new ViewMovies(movies(), categories());
        }
        return viewMovies;
    }

    private static Movies movies() throws SQLException, ClassNotFoundException {
        return new JdbcMovies(connection().connect());
    }

    private static Categories categories()
        throws SQLException, ClassNotFoundException {
        return new JdbcCategories(connection().connect());
    }

    public static MySQLConnection connection() {
        if (connection == null) {
            connection = new MySQLConnection("root", "Codeup1!", "movies_db");
        }
        return connection;
    }
}
