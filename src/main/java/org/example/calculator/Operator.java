package org.example.calculator;

import java.util.Arrays;
import java.util.function.BiFunction;

public enum Operator {
    PLUS("+", (a,b) -> a + b),
    MINUS("-" , (a,b) -> a - b),
    MULTIPLY("*", (a,b) -> a * b),
    DIVIDE("/", (a,b) ->{
        if(b == 0) {
            throw new IllegalArgumentException("0으로 나눌 수 없습니다.");
        }
        return a / b;
    });

    private final String op;
    private final BiFunction<Integer,Integer, Integer> operation;

    Operator(String op, BiFunction<Integer, Integer, Integer> operation) {
        this.op = op;
        this.operation = operation;
    }

    public int calculate(int a, int b) {
        return operation.apply(a, b);
    }

    public String getOp() {
        return op;
    }

    public static Operator fromSymbol(String symbol) {
        return Arrays.stream(values())
                .filter(op -> op.getOp().equals(symbol))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 형식의 연산자입니다."));
    }
}
