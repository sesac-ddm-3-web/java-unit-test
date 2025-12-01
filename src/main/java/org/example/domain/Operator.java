package org.example.domain;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum Operator {
    PLUS("+", (a, b) -> a + b) {
        @Override
        public double operate(double a, double b) {
            return this.operation.apply(a, b);
        }
    },

    MINUS("-", (a, b) -> a - b) {
        @Override
        public double operate(double a, double b) {
            return this.operation.apply(a, b);
        }
    },

    MULTIPLY("*", (a, b) -> a * b) {
        @Override
        public double operate(double a, double b) {
            return this.operation.apply(a, b);
        }
    },

    DIVIDE("/", (a, b) -> a / b) {
        @Override
        public double operate(double a, double b) {
            if (b == 0) {
                throw new IllegalArgumentException("나누는 수가 0일 수 없습니다.");
            }
            return this.operation.apply(a, b);
        }
    };

    private final String symbol;
    final BiFunction<Double, Double, Double> operation;

    Operator(String symbol, BiFunction<Double, Double, Double> operation) {
        this.symbol = symbol;
        this.operation = operation;
    }

    public static Operator getBySymbol(String symbol) {
        return Arrays.stream(Operator.values())
            .filter(op -> op.symbol.equals(symbol))
            .findAny()
            .orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 연산자 입니다.")
            );
    }

    public abstract double operate(double a, double b);
}
