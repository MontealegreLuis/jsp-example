/**
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.codeup.movies;

import java.sql.SQLException;
import java.util.List;

public interface Movies {
    Movie with(int id) throws SQLException;
    List<Movie> all() throws SQLException;
    List<Movie> inCategory(String category) throws SQLException;
    void update(Movie movie) throws SQLException;
    void add(Movie movie) throws SQLException;
}
