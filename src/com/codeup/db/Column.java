/**
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.codeup.db;

abstract class Column implements HasSQLRepresentation {
    private final String name;
    private boolean required = false;
    private String value;

    Column(String name) {
        this.name = name;
    }

    String name() {
        return name;
    }

    Column makeRequired() {
        required = true;
        return this;
    }

    Column defaultTo(String value) {
        this.value = value;
        return this;
    }

    String defaultValue() {
        return value;
    }

    boolean isRequired() {
        return required;
    }

    boolean hasDefaultValue() {
        return value != null;
    }
}
