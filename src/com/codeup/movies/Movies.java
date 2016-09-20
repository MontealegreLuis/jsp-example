/**
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.codeup.movies;

import java.sql.SQLException;
import java.util.List;

public interface Movies {
    void add(Movie movie) throws SQLException;
    List<Movie> all() throws SQLException;
    List<Movie> inCategory(String category) throws SQLException;
}
