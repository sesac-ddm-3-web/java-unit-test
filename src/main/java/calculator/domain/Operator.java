package calculator.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.function.BinaryOperator;

public enum Operator {

    PLUS(
            "+",
            (a, b) -> a.add(b)
    ),
    MINUS(
            "-",
            (a, b) -> a.subtract(b)
    ),
    MULTIPLE(
            "*",
            (a, b) -> a.multiply(b)
    ),
    DIVIDE(
            "/",
            (a, b) -> {
                try {
                    return a.divide(b, 3, RoundingMode.HALF_UP);
                } catch (ArithmeticException ex) {
                    throw new IllegalArgumentException("0으로 나눌 수 없습니다.", ex);
                }
            }
    );

    private final String name;
    private final BinaryOperator<BigDecimal> calculator;

    Operator(String name, BinaryOperator<BigDecimal> calculator) {
        this.name = name;
        this.calculator = calculator;
    }

    public static boolean isOperator(String targetName) {
        return Arrays.stream(Operator.values())
                     .anyMatch(operator -> operator.name.equals(targetName));
    }

    public static Operator find(String targetName) {
        return Arrays.stream(Operator.values())
                     .filter(operator -> operator.name.equals(targetName))
                     .findAny()
                     .orElseThrow(() -> new IllegalArgumentException("연산자를 찾을 수 없습니다."));
    }

    public BigDecimal calculate(BigDecimal a, BigDecimal b) {
        return this.calculator.apply(a, b);
    }
}
