package com.codeup.movies;

import com.codeup.db.MySQLConnection;
import java.sql.Connection;

public class MoviesSetupApplication {
    public static void main(String[] args) {
        MySQLConnection databaseConnection = new MySQLConnection("root", "codeup");

        try {
            Connection connection = databaseConnection.connect();
            MoviesDatabase database = new MoviesDatabase(connection);
            System.out.println("Creating movies database...");
            database.create("movies_db");
            System.out.println("Creating tables...");
            MoviesMigration migration = new MoviesMigration(connection);
            System.out.println("Done!");
            migration.up();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            databaseConnection.close();
        }
    }
}
