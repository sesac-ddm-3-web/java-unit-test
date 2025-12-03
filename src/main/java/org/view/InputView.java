package org.view;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String INPUT_CAR_COUNT_MESSAGE = "자동차 대수는 몇 대인가요?";
    private static final String INPUT_TRY_COUNT_MESSAGE = "시도할 횟수는 몇 회인가요?";

    private InputView() {
    }

    public static String inputCarCount() {
        System.out.println(INPUT_CAR_COUNT_MESSAGE);
        return SCANNER.nextLine();
    }

    public static String inputTryCount() {
        System.out.println(INPUT_TRY_COUNT_MESSAGE);
        return SCANNER.nextLine();
    }
}
