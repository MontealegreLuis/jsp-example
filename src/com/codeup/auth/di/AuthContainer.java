/**
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.codeup.auth.di;

import com.codeup.auth.JdbcUsers;
import com.codeup.auth.Users;
import com.codeup.auth.actions.AuthenticateUser;
import com.codeup.movies.di.MoviesContainer;

import java.sql.SQLException;

public class AuthContainer {
    private static AuthenticateUser authenticateUser;

    public static AuthenticateUser authenticateUser() throws SQLException, ClassNotFoundException {
        if (authenticateUser == null) {
            authenticateUser = new AuthenticateUser(users());
        }
        return authenticateUser;
    }

    private static Users users() throws SQLException, ClassNotFoundException {
        return new JdbcUsers(MoviesContainer.connection().connect());
    }
}
