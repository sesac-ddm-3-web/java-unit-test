package org.example.ui;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public int getCarCount(){
        System.out.println("자동차 대수는 몇 대인가요?");
        return scanner.nextInt();

    }

    public int getTryCount(){
        System.out.println("시도할 횟수는 몇 회인가요?");
        int tryCount = scanner.nextInt();

        if (tryCount < 1) {
            throw new IllegalArgumentException("시도 횟수는 1회 이상이어야 합니다.");
        }

        return tryCount;
    }

}
