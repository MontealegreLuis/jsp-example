/**
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.codeup.auth.servlets;

import com.codeup.auth.actions.AuthenticateUser;
import com.codeup.auth.di.AuthContainer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
    private AuthenticateUser action;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            action = AuthContainer.authenticateUser();
        } catch (Exception e) {
            throw new RuntimeException("Cannot initialize AuthenticateUser action", e);
        }
    }

    protected void doPost(
        HttpServletRequest request,
        HttpServletResponse response
    ) throws ServletException, IOException {
        if (!action.attemptLogin(
            request.getParameter("username"),
            request.getParameter("password")
        )) {
            invalidCredentials(request, response);
            return;
        }

        request.getSession().setAttribute("user", action.user());
        response.sendRedirect("/movies");
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
