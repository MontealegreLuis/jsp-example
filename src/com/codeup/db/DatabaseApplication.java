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
                        migrateMoviesExercise(schema);
                }
                System.out.println("Do you want to continue?");
            } while ("y".equalsIgnoreCase(scanner.next().trim()));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            close(connection);
        }
    }

    private static void migrateMoviesExercise(DatabaseSchema schema) {
        Table movies = schema.table("movies");
        movies.increments("id");
        movies.string("title", 300).makeRequired();
        movies.integer("rating").defaultTo("0");

        Table categories = schema.table("categories");
        categories.increments("id");
        categories.string("name").makeRequired();

        Table moviesCategories = schema.table("movies_categories");
        IntColumn movieId = (IntColumn) moviesCategories.integer("movie_id").unsigned().makeRequired();
        IntColumn categoryId = (IntColumn) moviesCategories.integer("category_id").unsigned().makeRequired();
        moviesCategories.foreign(movieId).references("id").on("movies");
        moviesCategories.foreign(categoryId).references("id").on("categories");
        moviesCategories.primary(movieId, categoryId);

        System.out.println(movies.toSQL());
        System.out.println(categories.toSQL());
        System.out.println(moviesCategories.toSQL());
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
