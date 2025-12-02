package kr.co.calculator.io;

import java.util.Scanner;

public class InputHelper {
    private final Scanner scanner;

    public InputHelper(Scanner scanner) {
        this.scanner = scanner;
    }

    public String inputExpression() {
        System.out.print("계산식을 입력하세요: ");
        String expression = scanner.nextLine();

        return expression;
    }
}
