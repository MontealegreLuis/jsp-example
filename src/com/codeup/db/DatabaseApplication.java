/**
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.codeup.db;

import java.sql.Connection;

public class DatabaseApplication {
    public static void main(String[] args) {
        MySQLConnection databaseConnection = new MySQLConnection("root", "codeup");

        try {
            Connection connection = databaseConnection.connect();
            Database database = new Database(connection);
            String name = "northwind";

            database.drop(name);
            System.out.println("Creating Northwind database...");
            database.create(name);
            System.out.println("Creating tables..");
            database.importFile("database/northwind.sql");
            database.use(name);
            System.out.println("Populating database...");
            database.importFile("database/northwind-data.sql");
            System.out.println("Done!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            databaseConnection.close();
        }
    }
}
