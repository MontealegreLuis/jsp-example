/**
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.codeup.db;

import com.codeup.movies.MoviesDatabase;
import com.codeup.movies.MoviesMigration;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class DatabaseApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MySQLConnection databaseConnection = new MySQLConnection("root", "codeup");

        try {
            Connection connection = databaseConnection.connect();
            do {
                System.out.println("1. Import Northwind");
                System.out.println("2. Create table");
                int action = scanner.nextInt();
                switch (action) {
                    case 1:
                        importNorthwindDatabase(connection);
                        break;
                    default:
                        setupMoviesDatabase(connection);
                }
                System.out.println("Do you want to continue?");
            } while ("y".equalsIgnoreCase(scanner.next().trim()));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            databaseConnection.close();
        }
    }

    private static void setupMoviesDatabase(
        Connection connection
    ) throws SQLException, IOException {
        MoviesDatabase moviesDatabase = new MoviesDatabase(connection);
        moviesDatabase.create("movies_db");
        MoviesMigration migration = new MoviesMigration(connection);
        migration.up();
    }

    private static void importNorthwindDatabase(
        Connection connection
    ) throws SQLException, IOException {
        Database database = new Database(connection);
        String name = "northwind";

        database.drop(name);
        database.create(name);
        database.importFile("database/northwind.sql");
        database.use(name);
        database.importFile("database/northwind-data.sql");
    }
}
