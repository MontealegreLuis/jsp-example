/**
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.codeup.movies.actions;

import com.codeup.db.MySQLConnection;
import com.codeup.movies.JdbcMovies;
import com.codeup.movies.Movie;

public class ViewMovie {
    private final MySQLConnection connection;

    public ViewMovie(MySQLConnection connection) {
        this.connection = connection;
    }

    public Movie view(int id) {
        try {
            return new JdbcMovies(connection.connect()).with(id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return null;
    }
}
