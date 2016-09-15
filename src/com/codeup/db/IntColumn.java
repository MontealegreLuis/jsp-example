/**
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.codeup.db;

public class IntColumn extends Column{
    boolean autoIncrements = false;

    public IntColumn(String name) {
        super(name);
    }

    public IntColumn autoIncrement()
    {
        autoIncrements = true;
        return this;
    }

    public boolean isAutoIncrementing()
    {
        return autoIncrements;
    }

    @Override
    public String toSQL() {
        return String.format(
            "%s INT %s %s %s",
            name(),
            isRequired() ? "NOT NULL" : "",
            hasDefaultValue() ? String.format("DEFAULT '%s'", defaultValue()) : "",
            isAutoIncrementing() ? "AUTO_INCREMENTS" : ""
        ).trim();
    }
}
