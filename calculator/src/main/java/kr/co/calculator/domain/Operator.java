package kr.co.calculator.domain;

import java.util.Arrays;
import java.util.function.IntBinaryOperator;

public enum Operator {
    PLUS("+", (left, right) -> left + right),
    MINUS("-", (left, right) -> left - right),
    MULTIPLY("*", (left, right) -> left * right),
    DIVIDE("/", (left, right) -> {
        if (right == 0) {
            throw new ArithmeticException("0으로 나눌 수 없습니다.");
        }

        return left / right;
    });

    private final String symbol;
    private final IntBinaryOperator operation; // 함수형 인터페이스 사용

    Operator(String symbol, IntBinaryOperator operation) {
        this.symbol = symbol;
        this.operation = operation;
    }

    public int apply(int left, int right) {
        return operation.applyAsInt(left, right);
    }

    public static Operator from(String symbol) {
        return Arrays.stream(values()) // PLUS, MINUS, MULTIPLY, DIVIDE
                .filter(op -> op.symbol.equals(symbol))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 연산자입니다: " + symbol));
    }
}
