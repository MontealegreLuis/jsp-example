/**
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.codeup.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSchema {
    private final Connection connection;

    public DatabaseSchema(Connection connection) {
        this.connection = connection;
    }

    public void createDatabase(String databaseName) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(
            String.format("CREATE DATABASE IF NOT EXISTS %s", databaseName)
        );
        statement.close();
    }

    public void dropDatabase(String databaseName) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(
            String.format("DROP DATABASE IF EXISTS %s", databaseName)
        );
        statement.close();
    }
}
