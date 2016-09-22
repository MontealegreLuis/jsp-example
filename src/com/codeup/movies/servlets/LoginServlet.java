/**
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.codeup.movies.servlets;

import com.codeup.auth.JdbcUsers;
import com.codeup.auth.Password;
import com.codeup.auth.User;
import com.codeup.db.MySQLConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
    protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        MySQLConnection connection = new MySQLConnection(
            "root", "Codeup1!", "movies_db"
        );
        try {
            JdbcUsers users = new JdbcUsers(connection.connect());
            User user = users.identifiedBy(request.getParameter("username"));
            if (user == null) {
                invalidCredentials(request, response);
                return;
            }
            if (!Password.verify(request.getParameter("password"), user.password())) {
                invalidCredentials(request, response);
                return;
            }
            request.getSession().setAttribute("user", user);
            response.sendRedirect("/movies");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
    }

    protected void doGet(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        request
            .getRequestDispatcher("/WEB-INF/auth/login.jsp")
            .forward(request, response)
        ;
    }

    private void invalidCredentials(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        request.setAttribute(
            "error",
            "Either your username or password is incorrect."
        );
        request
            .getRequestDispatcher("/WEB-INF/auth/login.jsp")
            .forward(request, response)
        ;
    }
}
