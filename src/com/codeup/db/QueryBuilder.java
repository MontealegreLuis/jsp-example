/**
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.codeup.db;

import java.util.ArrayList;
import java.util.List;

public class QueryBuilder implements HasSQLRepresentation {
    private List<String> select = null;
    private String table;
    private List<WhereExpression> whereExpressions;

    public QueryBuilder() {
        select = new ArrayList<>();
        whereExpressions = new ArrayList<>();
    }

    @Override
    public String toSQL() {
        if (table == null) {
            throw new IllegalStateException("No table to select from was specified");
        }
        return String.format(
            "SELECT %s FROM %s%s",
            selectToString(),
            table,
            whereToString()
        );
    }

    private String whereToString() {
        StringBuilder where = new StringBuilder();
        whereExpressions
            .forEach(expression -> where.append(expression.toSQL()).append(" "))
        ;
        return (whereExpressions.size() > 0 ? " WHERE " : "")
            + where.toString().replaceAll(" $", "")
        ;
    }

    private String selectToString() {
        StringBuilder columns = new StringBuilder();
        if (select.isEmpty()) {
            select.add("*");
        }
        select.forEach(column -> columns.append(column).append(", "));
        return columns.toString().replaceAll(", $", "");
    }

    public QueryBuilder from(String table) {
        this.table = table;
        return this;
    }

    public QueryBuilder select(String ...columns) {
        for (String column : columns) {
            this.select.add(column);
        }
        return this;
    }

    public QueryBuilder where(String expression) {
        if (whereExpressions.size() == 0) {
            whereExpressions.add(WhereExpression.first(expression));
        } else {
            whereExpressions.add(WhereExpression.and(expression));
        }
        return this;
    }

    public QueryBuilder orWhere(String expression) {
        if (whereExpressions.size() == 0) {
            whereExpressions.add(WhereExpression.first(expression));
        } else {
            whereExpressions.add(WhereExpression.or(expression));
        }
        return this;
    }
}
