/**
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.codeup.db;

import java.util.ArrayList;
import java.util.List;

public class PrimaryKey implements HasSQLRepresentation {
    private List<Column> columns = new ArrayList<Column>();

    public PrimaryKey(Column column) {
        columns.add(column);
    }

    @Override
    public String toSQL() {
        return String.format("PRIMARY KEY (%s)", columnNames());
    }

    private String columnNames() {
        StringBuilder names = new StringBuilder();
        for (Column column : columns) {
            names.append(column.name()).append(", ");
        }
        return names.toString().replaceAll(", $", "");
    }
}
