/**
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.codeup.db;

import java.io.*;
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

    public void useDatabase(String databaseName) throws SQLException {
        Statement statement = connection.createStatement();
        statement.executeUpdate(String.format("USE %s", databaseName));
        statement.close();
    }

    public void importFile(String sqlFile) throws IOException, SQLException {
        String line;
        StringBuilder queries = new StringBuilder();

        BufferedReader reader = new BufferedReader(new FileReader(new File(sqlFile)));
        while ((line = reader.readLine()) != null) {
            queries.append(line).append(" ");
        }
        reader.close();

        Statement statement = connection.createStatement();

        String[] ddlStatements = queries.toString().split(";");
        for (String query : ddlStatements) {
            if (!query.trim().isEmpty()) {
                statement.executeUpdate(query);
            }
        }
        statement.close();
    }
}
