package racing.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputView {

    private final Scanner sc;

    public InputView(Scanner sc) {
        this.sc = sc;
    }

    public int inputParticipantCount() {
        System.out.println("자동차 대수는 몇 대인가요?");

        return inputNumber();
    }

    public int inputRoundCount() {
        System.out.println("시도할 횟수는 몇 회인가요?");

        return inputNumber();
    }

    private int inputNumber() {
        try {
            return sc.nextInt();
        } catch (InputMismatchException ex) {
            throw new IllegalArgumentException("숫자 형식이 아닙니다.", ex);
        }
    }
}
