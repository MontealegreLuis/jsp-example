/**
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.codeup.movies.servlets;

import com.codeup.db.MySQLConnection;
import com.codeup.movies.JdbcMovies;
import com.codeup.movies.Movie;
import com.codeup.movies.Movies;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RateMovieServlet", urlPatterns = {"/movies/rate"})
public class RateMovieServlet extends HttpServlet {
    protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        int rate = Integer.parseInt(request.getParameter("rating"));
        int id = Integer.parseInt(request.getParameter("id"));
        MySQLConnection connection = new MySQLConnection("root", "Codeup1!", "movies_db");

        try {
            Movies movies = new JdbcMovies(connection.connect());
            Movie movie = movies.with(id);
            movie.rate(rate);
            movies.update(movie);
            response.sendRedirect("/movies");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
}
