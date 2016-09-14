/**
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.codeup.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseApplication {
    public static void main(String[] args) {
        Connection connection = null;
        String databaseName = "northwind";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(
                String.format("jdbc:mysql://%s/", "localhost"),
                "root",
                "codeup"
            );
            DatabaseSchema schema = new DatabaseSchema(connection);
            schema.dropDatabase(databaseName);
            schema.createDatabase(databaseName);
            schema.importFile("database/northwind.sql");
            schema.useDatabase(databaseName);
            schema.importFile("database/northwind-data.sql");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
