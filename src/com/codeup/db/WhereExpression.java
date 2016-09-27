/**
 * This source file is subject to the license that is bundled with this package in the file LICENSE.
 */
package com.codeup.db;

public class WhereExpression implements HasSQLRepresentation {

    private final String expression;
    private final Operator operator;
    private enum Operator {AND, OR};

    public static WhereExpression first(String expression) {
        return new WhereExpression(expression, null);
    }

    private WhereExpression(String expression, Operator operator) {
        this.expression = expression;
        this.operator = operator;
    }

    public static WhereExpression or(String expression) {
        return new WhereExpression(expression, Operator.OR);
    }

    public static WhereExpression and(String expression) {
        return new WhereExpression(expression, Operator.AND);
    }

    @Override
    public String toSQL() {
        return operator() + expression;
    }

    private String operator() {
        return operator != null ? operator.toString() + " " : "";
    }
}
