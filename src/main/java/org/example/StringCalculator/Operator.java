package org.example.StringCalculator;

import org.example.StringCalculator.token.OperatorToken;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Operator {
    PLUS("+") {
        @Override
        public double apply(double a, double b) {
            return a + b;
        }
    },
    MINUS("-") {
        @Override
        public double apply(double a, double b) {
            return a - b;
        }
    },
    MULTIPLY("*") {
        @Override
        public double apply(double a, double b) {
            return a * b;
        }
    },
    DIVIDE("/") {
        @Override
        public double apply(double a, double b) {
            if (b == 0) {
                throw new ArithmeticException("0으로 나눌 수 없습니다.");
            }
            return a / b;
        }
    };

    private final String symbol;
    private final OperatorToken token;

    private static final Map<String, Operator> OPERATOR_MAP =
            Arrays.stream(values()).collect(Collectors.toMap(op -> op.symbol, Function.identity()));

    Operator(String symbol) {
        this.symbol = symbol;
        this.token = new OperatorToken(this);
    }

    public OperatorToken getToken() {
        return token;
    }

    public static boolean isOperator(String symbol) {
        return OPERATOR_MAP.containsKey(symbol);
    }

    public static Operator from(String symbol) {
        if (!OPERATOR_MAP.containsKey(symbol)) {
            throw new IllegalArgumentException("지원하지 않는 연산자: " + symbol);
        }
        return OPERATOR_MAP.get(symbol);
    }

    public abstract double apply(double a, double b);
}
