/**
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.codeup.movies;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcCategories implements Categories {
    private final Connection connection;

    public JdbcCategories(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Category> all() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM categories");
        return populateCategories(resultSet);
    }

    private ArrayList<Category> populateCategories(
        ResultSet resultSet
    ) throws SQLException {
        ArrayList<Category> categories = new ArrayList<>();
        while (resultSet.next()) {
            categories.add(new Category(
                resultSet.getInt("id"),
                resultSet.getString("name")
            ));
        }
        return categories;
    }
}
