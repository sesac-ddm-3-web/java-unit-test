package org.example.StringCalculator;

import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        StringCalculator calculator = new StringCalculator(new Tokenizer(), new Evaluator());
        Scanner sc = new Scanner(System.in);

        System.out.println("계산할 수식을 입력하세요 (종료하려면 0 입력):");

        while (true) {
            System.out.print("> ");
            String input = sc.nextLine();

            if ("0".equals(input.trim())) {
                System.out.println("프로그램을 종료합니다.");
                break;
            }

            try {
                int result = calculator.calculate(input);
                System.out.println("결과: " + result);
            } catch (IllegalArgumentException | ArithmeticException e) {
                System.out.println("오류: " + e.getMessage());
            }
        }

        sc.close();
    }
}
