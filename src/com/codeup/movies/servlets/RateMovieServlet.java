/**
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.codeup.movies.servlets;

import com.codeup.db.MySQLConnection;
import com.codeup.movies.actions.RateMovie;

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
        RateMovie rateMovie = new RateMovie(
            new MySQLConnection("root", "Codeup1!", "movies_db")
        );

        rateMovie.rate(
            Integer.parseInt(request.getParameter("id")),
            Integer.parseInt(request.getParameter("rating"))
        );

        response.sendRedirect("/movies");
    }
}
