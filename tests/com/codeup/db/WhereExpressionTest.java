/**
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.codeup.db;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class WhereExpressionTest {
    @Test
    public void it_converts_to_sql_an_and_expression() {
        WhereExpression where = WhereExpression.and("u.username = ?");
        assertEquals("AND u.username = ?", where.toSQL());
    }

    @Test
    public void it_converts_to_sql_an_or_expression() {
        WhereExpression where = WhereExpression.or("u.password = ?");
        assertEquals("OR u.password = ?", where.toSQL());
    }

    @Test
    public void it_ignores_boolean_operator_from_first_expression() {
        WhereExpression where = WhereExpression.first("u.name LIKE ?");
        assertEquals("u.name LIKE ?", where.toSQL());
    }
}
