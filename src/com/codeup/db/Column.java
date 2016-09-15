/**
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.codeup.db;

public abstract class Column implements HasSQLRepresentation {
    private final String name;
    private boolean required = false;
    private String value;

    public Column(String name) {
        this.name = name;
    }

    public String name() {
        return name;
    }

    public Column makeRequired() {
        required = true;
        return this;
    }

    public Column defaultTo(String value) {
        this.value = value;
        return this;
    }

    public String defaultValue() {
        return value;
    }

    public boolean isRequired() {
        return required;
    }

    public boolean hasDefaultValue() {
        return value != null;
    }
}
