package kr.co.calculator.domain;

public class Calculator {
    public Integer calculate(String normalized) {
        String trimmed = normalized.trim().replaceAll("\\s+", " ");
        String[] tokens = trimmed.split(" ");

        int result = Integer.parseInt(tokens[0]);

        for (int i = 1; i < tokens.length; i += 2) {
            String op = tokens[i];
            int value = Integer.parseInt(tokens[i + 1]);

            switch (op) {
                case "+" -> result += value;
                case "-" -> result -= value;
                case "*" -> result *= value;
                case "/" -> {
                    if (value == 0) {
                        throw new ArithmeticException("0으로 나눌 수 없습니다.");
                    }

                    result /= value;
                }
                default -> throw new IllegalArgumentException("지원하지 않는 연산자입니다: " + op);
            }
        }

        return result;
    }
}
