/**
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.codeup.db;

import java.util.ArrayList;
import java.util.List;

public class QueryBuilder implements HasSQLRepresentation {
    private List<String> select = null;
    private String table;

    public QueryBuilder() {
        select = new ArrayList<>();
    }

    @Override
    public String toSQL() {
        if (table == null) {
            throw new IllegalStateException("No table to select from was specified");
        }
        return String.format(
            "SELECT %s FROM %s",
            selectToString(),
            table
        );
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
}
