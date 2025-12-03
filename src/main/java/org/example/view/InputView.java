package org.example.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputView {
    private Scanner scanner = new Scanner(System.in);

    public void requestCarCount() {
        System.out.print("자동차 대수는 몇 대인가요? ");
    }

    public void requestTryCount() {
        System.out.print("시도할 횟수는 몇 회인가요? ");
    }

    public int inputIntData() {
        int data = scanner.nextInt();

        if (data < 1 || data > 9) {
            throw new InputMismatchException("----유효하지 않은 입력입니다. 1-9만 입력 가능---");
        }
        return data;
    }
}
