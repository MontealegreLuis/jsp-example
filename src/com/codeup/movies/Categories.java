/**
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.codeup.movies;

import java.sql.SQLException;
import java.util.List;

public interface Categories {
    List<Category> all() throws SQLException;
    Category named(String name) throws SQLException;
    void add(Category category) throws SQLException;
}
