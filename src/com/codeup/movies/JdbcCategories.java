/**
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.codeup.movies;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class JdbcCategories implements Categories {
    private final Connection connection;

    public JdbcCategories(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Category named(String name) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM categories WHERE name = ?"
            );
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return new Category(
                resultSet.getInt("id"),
                resultSet.getString("name")
            );
        } catch (SQLException e) {
            throw new RuntimeException("Cannot retrieve category...", e);
        }
    }

    @Override
    public void add(Category category) {
        try {
            PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO categories (name) VALUES (?)"
            );
            statement.setString(1, category.name());
            statement.executeUpdate();
            statement.close();
        } catch (SQLException e) {
            throw new RuntimeException("Cannot add category", e);
        }
    }

    @Override
    public List<Category> all() {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM categories");
            return populateCategories(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException("Cannot retrieve the categories", e);
        }
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
