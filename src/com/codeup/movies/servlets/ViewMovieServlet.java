/**
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.codeup.movies.servlets;

import com.codeup.db.MySQLConnection;
import com.codeup.movies.JdbcMovies;
import com.codeup.movies.Movies;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ViewMovieServlet", urlPatterns = { "/movies/show" })
public class ViewMovieServlet extends HttpServlet {
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        MySQLConnection connection = new MySQLConnection("root", "Codeup1!", "movies_db");
        try {
            Movies movies = new JdbcMovies(connection.connect());
            request.setAttribute(
                "movie",
                movies.with(Integer.parseInt(request.getParameter("id")))
            );
            request
                .getRequestDispatcher("/WEB-INF/movies/show.jsp")
                .forward(request, response)
            ;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
}
