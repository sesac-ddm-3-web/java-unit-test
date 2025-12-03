package com.example.racing_game.input;


import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);
    private static final String CAR_COUNT_INPUT_MESSAGE = "자동차는 총 몇대인가요? : ";
    private static final String RACE_ATTEMPTS_INPUT_MESSAGE = "시도할 횟수는 몇 회인가요? : ";

    public static InputDto input(){
        return new InputDto(
                inputCarCounts(),
                inputRaceAttempts()
        );
    }

    public static Integer inputCarCounts() {
        System.out.println(CAR_COUNT_INPUT_MESSAGE);
        return scanner.nextInt();
    }

    public static Integer inputRaceAttempts() {
        System.out.println(RACE_ATTEMPTS_INPUT_MESSAGE);
        return scanner.nextInt();
    }
}
