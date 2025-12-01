package org.example;

import java.util.Arrays;
import java.util.function.BinaryOperator;

public enum Operator {
    PLUS("+", (a,b) -> a + b),
    MINUS("-", (a,b) -> a - b),
    MULTIPLY("*", (a,b) -> a * b),
    DIVIDE("/", (a,b) -> {
        if(b == 0){
            throw new ArithmeticException("0으로 나눌 수 없습니다.");
        }
        return a / b;
    });

    private final String operator;
    private final BinaryOperator<Integer> binaryOperator;

    Operator(String operator,  BinaryOperator<Integer> binaryOperator) {
        this.operator = operator;
        this.binaryOperator = binaryOperator;
    }

    public int calculate(int a, int b) {
        return binaryOperator.apply(a, b);
    }

    public static Operator of(String symbol) {
        return Arrays.stream(Operator.values())
                .filter(op -> op.operator.equals(symbol))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 연산자입니다. " + symbol));
    }
}
