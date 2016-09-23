/**
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.codeup.movies.servlets;

import com.codeup.db.MySQLConnection;
import com.codeup.movies.JdbcCategories;
import com.codeup.movies.JdbcMovies;
import com.codeup.movies.Movie;

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
        MySQLConnection connection = new MySQLConnection(
            "root", "Codeup1!", "movies_db"
        );
        try {
            JdbcCategories categories = new JdbcCategories(connection.connect());
            Movie movie = new Movie(
                request.getParameter("title"),
                Integer.parseInt(request.getParameter("rating")),
                categories.with(request.getParameterValues("category[]"))
            );
            new JdbcMovies(connection.connect()).add(movie);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        response.sendRedirect("/movies");
    }

    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        MySQLConnection connection = new MySQLConnection(
            "root", "Codeup1!", "movies_db"
        );
        try {
            request.setAttribute(
                "categories",
                new JdbcCategories(connection.connect()).all()
            );
            request
                .getRequestDispatcher("/WEB-INF/movies/new.jsp")
                .forward(request, response)
            ;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
}
