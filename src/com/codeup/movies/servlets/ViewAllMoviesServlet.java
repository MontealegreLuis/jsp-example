/**
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.codeup.movies.servlets;

import com.codeup.db.MySQLConnection;
import com.codeup.movies.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ViewAllMoviesServlet", urlPatterns = {"/movies"})
public class ViewAllMoviesServlet extends HttpServlet {
    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        MySQLConnection connection = new MySQLConnection("root", "Codeup1!", "movies_db");
        try {
            Movies movies = new JdbcMovies(connection.connect());
            JdbcCategories categories = new JdbcCategories(connection.connect());
            request.setAttribute("categories", categories.all());
            String category = request.getParameter("category");
            if (category != null && !category.isEmpty()) {
                request.setAttribute("movies", movies.inCategory(category));
            } else {
                request.setAttribute("movies", movies.all());
            }
            request
                .getRequestDispatcher("/WEB-INF/movies/index.jsp")
                .forward(request, response)
            ;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }
}
