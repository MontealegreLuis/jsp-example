/**
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.codeup.movies.servlets;

import com.codeup.db.MySQLConnection;
import com.codeup.movies.actions.ViewMovie;

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
        ViewMovie viewMovie = new ViewMovie(
            new MySQLConnection("root", "Codeup1!", "movies_db")
        );

        request.setAttribute(
            "movie",
            viewMovie.view(Integer.parseInt(request.getParameter("id")))
        );

        request
            .getRequestDispatcher("/WEB-INF/movies/show.jsp")
            .forward(request, response)
        ;
    }
}
