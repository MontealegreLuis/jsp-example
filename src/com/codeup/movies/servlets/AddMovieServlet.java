/**
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.codeup.movies.servlets;

import com.codeup.db.MySQLConnection;
import com.codeup.movies.JdbcCategories;
import com.codeup.movies.JdbcMovies;
import com.codeup.movies.Movie;
import com.codeup.movies.actions.AddMovie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AddMovieServlet", urlPatterns = {"/movies/new"})
public class AddMovieServlet extends HttpServlet {
    protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        AddMovie addMovie = new AddMovie(new MySQLConnection(
            "root", "Codeup1!", "movies_db"
        ));

        addMovie.add(
            request.getParameter("title"),
            Integer.parseInt(request.getParameter("rating")),
            request.getParameterValues("category[]")
        );

        response.sendRedirect("/movies");
    }

    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        AddMovie addMovie = new AddMovie(new MySQLConnection(
            "root", "Codeup1!", "movies_db"
        ));

        request.setAttribute(
            "categories",
            addMovie.categories()
        );

        request
            .getRequestDispatcher("/WEB-INF/movies/new.jsp")
            .forward(request, response)
        ;
    }
}
