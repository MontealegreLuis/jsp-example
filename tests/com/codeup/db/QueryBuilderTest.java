/**
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.codeup.db;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class QueryBuilderTest {

    private QueryBuilder builder;

    @Before
    public void setUp() throws Exception {
        builder = new QueryBuilder();
    }

    @Test
    public void it_sets_default_value_for_select_clause() {
        builder.from("users");
        assertEquals("SELECT * FROM users", builder.toSQL());
    }

    @Test(expected = IllegalStateException.class)
    public void it_errors_out_if_no_from_clause_is_specified() {
        builder.toSQL();
    }

    @Test
    public void it_selects_specific_columns() {
        builder.select("username", "password").from("users");
        assertEquals("SELECT username, password FROM users", builder.toSQL());
    }
}
