package kr.co.calculator.domain;

public class Calculator {

    /**
     *
     * 이 메서드는 이미 checkNumberAndOper를 통과한 식만 받는다 << 라는 보장이 있을까? 사용자는 이를 모른다.
     *
     * 메서드가 정해진 순서대로 호출되도록 하고 싶다. 방법이 있나?
     *
     * valid + calculate -> validAndCalculate 메서드 만들기..? 더 세련된 방법 찾아보면 좋을 것 같다.
     */

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
