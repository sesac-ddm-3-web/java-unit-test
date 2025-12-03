package org.example;

import java.util.Scanner;

public class InputView implements Input {
    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }


    @Override
    public int getCarCount() {
        System.out.println("자동차 대수는 몇 대인가요?");
        int carCount = scanner.nextInt();
        return carCount;
    }

    @Override
    public int getTimes() {
        System.out.println("시도할 횟수는 몇 회인가요?");
        int times = scanner.nextInt();
        return times;
    }
}

