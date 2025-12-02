package kr.co.calculator;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /**
         * 사용자가 입력한 문자열 값에 따라 사칙 연산을 수행할 수 있는 계산기를 구현해야 한다.
         * 문자열 계산기는 사칙 연산의 계산 우선순위가 아닌 입력 값에 따라 계산 순서가 결정된다.
         * 즉, 수학에서는 곱셈, 나눗셈이 덧셈, 뺄셈 보다 먼저 계산해야 하지만 이를 무시한다.
         * 예를 들어 "2 + 3 * 4 / 2"와 같은 문자열을 입력할 경우 2 + 3 * 4 / 2 실행 결과인 10을 출력해야 한다.
         */

        Scanner scanner = new Scanner(System.in);

        String expression = scanner.nextLine();

        expression = expression.trim();
        expression = expression.replaceAll(" ", "");

        Integer ret = expression.charAt(0) - '0';

        for (int i = 1; i < expression.length() - 1; i += 2) {
            char oper = expression.charAt(i);
            int val = expression.charAt(i + 1) - '0';

            switch (oper) {
                case '+':
                    ret += val;
                    break;
                case '-':
                    ret -= val;
                    break;
                case '*':
                    ret *= val;
                    break;
                case '/':
                    if (val == 0) {
                        throw new ArithmeticException("0으로 나눌 수 없습니다.");
                    }

                    ret /= val;
                    break;
                default:
                    throw new RuntimeException("잘못된 수식입니다");
            }
        }

        System.out.println(ret);
    }
}
