/**
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.codeup.movies.actions;

import com.codeup.db.MySQLConnection;
import com.codeup.movies.JdbcMovies;
import com.codeup.movies.Movie;
import com.codeup.movies.Movies;

public class RateMovie {
    public void rate(int id, int rate) {
        MySQLConnection connection = new MySQLConnection("root", "Codeup1!", "movies_db");

        try {
            Movies movies = new JdbcMovies(connection.connect());
            Movie movie = movies.with(id);
            movie.rate(rate);
            movies.update(movie);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
}
