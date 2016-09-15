/**
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.codeup.db;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class DatabaseApplication {
    public static void main(String[] args) {
        Connection connection = null;
        Scanner scanner = new Scanner(System.in);

        try {
            connection = connection();
            DatabaseSchema schema = new DatabaseSchema(connection);
            do {
                System.out.println("1. Import Northwind");
                System.out.println("2. Create table");
                int action = scanner.nextInt();
                switch (action) {
                    case 1:
                        importNorthwindDatabase(schema);
                        break;
                    default:
                        Table table = schema.table("movies");
                        table.increments("id");
                        table.string("title", 300).makeRequired();
                        table.integer("rating").defaultTo("0");
                        System.out.println(table.toSQL());
                }
                System.out.println("Do you want to continue?");
            } while ("y".equalsIgnoreCase(scanner.next().trim()));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(connection);
        }
    }

    private static void close(Connection connection) {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void importNorthwindDatabase(
        DatabaseSchema schema
    ) throws SQLException, IOException {

        String databaseName = "northwind";
        schema.dropDatabase(databaseName);
        schema.createDatabase(databaseName);
        schema.importFile("database/northwind.sql");
        schema.useDatabase(databaseName);
        schema.importFile("database/northwind-data.sql");
    }

    private static Connection connection() throws ClassNotFoundException, SQLException {
        Connection connection;

        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(
            String.format("jdbc:mysql://%s/", "localhost"),
            "root",
            "codeup"
        );
        return connection;
    }
}
