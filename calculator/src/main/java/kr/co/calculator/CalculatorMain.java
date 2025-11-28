package kr.co.calculator;

import kr.co.calculator.domain.Calculator;
import kr.co.calculator.domain.ExpressionValidator;

import java.util.Scanner;

public class CalculatorMain {
    public static void main(String[] args) {
        Calculator calculator = new Calculator();
        ExpressionValidator expressionValidator = new ExpressionValidator();

        // 1. 문자열 입력
        Scanner scanner = new Scanner(System.in);
        String expression = scanner.nextLine();

        while (true) {
            if (expression.equals("/exit")) {
                break;
            }

            System.out.println(validAndCalculate(expression, calculator, expressionValidator));
        }
    }

    private static int validAndCalculate(String expression, Calculator calculator,
                                         ExpressionValidator expressionValidator) {

        //  2. 문자열에 대한 모든 유효성 검사
        String normalized = expressionValidator.checkNumberAndOper(expression);

        // 3. 사칙 연산
        int ret = calculator.calculate(normalized);

        return ret;
    }

    /**
     * 메서드가 너무 많은 일을 하지 않도록 분리한다.
     * 테스트 할 수 있는 단위로 나누어 구현 목록을 만든다.
     * 덧셈, 뺄셈, 곱셈, 나눗셈
     * 입력 값이 null이거나 빈 공백 문자일 경우 exception
     * 사칙연산 기호가 아닌 경우 exception
     * 사칙 연산을 모두 포함하는 기능 구현
     */
}
