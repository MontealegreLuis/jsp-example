/**
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.codeup.db;

import java.util.ArrayList;
import java.util.List;

public class Table implements HasSQLRepresentation {
    private final String name;
    private List<Column> columns;
    private PrimaryKey primaryKey;

    Table(String name) {
        this.name = name;
        columns = new ArrayList<Column>();
    }

    Column string(String name) {
        return string(name, 256);
    }

    Column string(String name, int length) {
        StringColumn column = new StringColumn(name, length);
        columns.add(column);
        return column;
    }

    Column integer(String name) {
        IntColumn column = new IntColumn(name);
        columns.add(column);
        return column;
    }

    Column increments(String name) {
        Column id = new IntColumn(name).autoIncrement().makeRequired();
        primaryKey = new PrimaryKey(id);
        columns.add(id);
        return id;
    }

    @Override
    public String toSQL() {
        return String.format(
            "CREATE TABLE %s (%s %s) ENGINE = InnoDB DEFAULT CHARACTER SET = utf8;",
            name,
            columnDefinitions(),
            primaryKey.toSQL()
        );
    }

    private String columnDefinitions() {
        StringBuilder definition = new StringBuilder();
        for (Column column : columns) {
            definition.append(column.toSQL()).append(", ");
        }
        return definition.toString().trim();
    }
}
