/**
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.codeup.movies;

import com.codeup.db.Database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class MoviesDatabase {
    private final Connection connection;

    public MoviesDatabase(Connection connection) {
        this.connection = connection;
    }

    public void create(String name) throws SQLException, IOException {
        Database database = new Database(connection);
        database.drop(name);
        database.create(name);
        database.use(name);
    }
}
