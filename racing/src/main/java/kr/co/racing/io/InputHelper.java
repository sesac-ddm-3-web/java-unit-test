package kr.co.racing.io;

import java.util.Scanner;

public class InputHelper {
    private final Scanner scanner;

    public InputHelper(Scanner scanner) {
        this.scanner = scanner;
    }

    public Integer inputNumberOfCars() {
        System.out.println("자동차 대수는 몇 대인가요?");
        Integer numberOfCars = scanner.nextInt();

        return numberOfCars;
    }

    public Integer inputTryCount() {
        System.out.println("시도할 횟수는 몇 회인가요?");
        Integer tryCount = scanner.nextInt();

        return tryCount;
    }
}
