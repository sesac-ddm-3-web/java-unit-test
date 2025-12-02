package kr.co.calculator;

import kr.co.calculator.domain.Calculator;
import kr.co.calculator.domain.ExpressionTokenizer;
import kr.co.calculator.io.InputHelper;
import kr.co.calculator.io.OutputHelper;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /**
         * 사용자가 입력한 문자열 값에 따라 사칙 연산을 수행할 수 있는 계산기를 구현해야 한다.
         * 문자열 계산기는 사칙 연산의 계산 우선순위가 아닌 입력 값에 따라 계산 순서가 결정된다.
         * 즉, 수학에서는 곱셈, 나눗셈이 덧셈, 뺄셈 보다 먼저 계산해야 하지만 이를 무시한다.
         * 예를 들어 "2 + 3 * 4 / 2"와 같은 문자열을 입력할 경우 2 + 3 * 4 / 2 실행 결과인 10을 출력해야 한다.
         */

        ExpressionTokenizer expressionTokenizer = new ExpressionTokenizer();

        Calculator calculator = new Calculator(expressionTokenizer);

        OutputHelper outputHelper = new OutputHelper();

        try(Scanner scanner = new Scanner(System.in)) {
            InputHelper inputHelper = new InputHelper(scanner);
            
            String expression = inputHelper.inputExpression(); // 입력

            Integer result = calculator.calculate(expression); // 각 토큰 검증 -> 토큰화 완료 -> 계산

            outputHelper.printResult(result); // 출력
        } catch (IllegalArgumentException e) {
            outputHelper.printExceptionMessage(e.getMessage());
        }
    }
}
