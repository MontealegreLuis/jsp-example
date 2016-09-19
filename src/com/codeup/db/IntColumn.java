/**
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.codeup.db;

class IntColumn extends Column{
    private boolean autoIncrements = false;
    private boolean unsigned = false;

    IntColumn(String name) {
        super(name);
    }

    IntColumn autoIncrement() {
        autoIncrements = true;
        return this;
    }

    IntColumn unsigned() {
        unsigned = true;
        return this;
    }

    private boolean isUnsigned() {
        return unsigned;
    }

    private boolean isAutoIncrementing()
    {
        return autoIncrements;
    }

    @Override
    public String toSQL() {
        return String.format(
            "%s INT %s %s %s %s",
            name(),
            isRequired() ? "NOT NULL" : "",
            isUnsigned() ? "UNSIGNED" : "",
            hasDefaultValue() ? String.format("DEFAULT '%s'", defaultValue()) : "",
            isAutoIncrementing() ? "AUTO_INCREMENTS" : ""
        ).trim();
    }
}
