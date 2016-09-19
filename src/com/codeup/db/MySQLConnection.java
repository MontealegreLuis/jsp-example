package com.codeup.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {
    private Connection connection;
    private String username;
    private String password;

    public MySQLConnection(String username, String password) {
        this.username = username;
        this.password = password;
    }

    void close() {
        try {
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    Connection connect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        connection = DriverManager.getConnection(
            String.format("jdbc:mysql://%s/", "localhost"),
            username,
            password
        );
        return connection;
    }
}
