/**
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.codeup.movies.servlets;

import com.codeup.db.MySQLConnection;
import com.codeup.movies.actions.MoviesInformation;
import com.codeup.movies.actions.ViewMovies;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ViewMoviesServlet", urlPatterns = {"/movies"})
public class ViewMoviesServlet extends HttpServlet {
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        ViewMovies viewMovies = new ViewMovies(
            new MySQLConnection("root", "Codeup1!", "movies_db")
        );

        MoviesInformation information = viewMovies.view(
            request.getParameter("category")
        );

        request.setAttribute("categories", information.categories);
        request.setAttribute("movies", information.movies);
        request
            .getRequestDispatcher("/WEB-INF/movies/index.jsp")
            .forward(request, response)
        ;
    }
}
