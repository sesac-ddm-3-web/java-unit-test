package org.example.calculator;

import java.util.Arrays;

public enum Operator {
    PLUS("+"),
    MINUS("-"),
    MULTIPLY("*"),
    DIVIDE("/");

    private final String op;

    Operator(String op) {
        this.op = op;
    }

    public String getOp() {
        return op;
    }

    public static Operator fromSymbol(String symbol) {
        return Arrays.stream(values())
                .filter(op -> op.getOp().equals(symbol))
                .findFirst()
                .orElseThrow(() -> new RuntimeException());
    }
}
