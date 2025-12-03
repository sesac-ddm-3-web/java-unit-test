package org.example.view;

import java.util.Scanner;

public class GameSettingInputView {
    private static final Scanner in = new Scanner(System.in);

    public static int readCarCount() {
        System.out.print("자동차 대수: ");
        return in.nextInt();
    }

    public static int readTryCount() {
        System.out.print("시도할 횟수: ");
        return in.nextInt();
    }
}
