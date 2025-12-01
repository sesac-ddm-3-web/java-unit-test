package org.example;

import java.util.Scanner;

public class InputView {

    private final Scanner sc;

    public InputView(Scanner sc) {
        this.sc = sc;
    }

    public String inputExpression() {

        System.out.println("계산식을 입력하세요: ");

        return sc.nextLine();
    }
}
