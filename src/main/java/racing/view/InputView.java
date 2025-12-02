package racing.view;

import java.util.InputMismatchException;
import java.util.Scanner;

public class InputView {

    private static final int MIN_PARTICIPANT_COUNT = 2;
    private static final int MAX_PARTICIPANT_COUNT = 10;
    private static final int MIN_ROUND_COUNT = 1;
    private static final int MAX_ROUND_COUNT = 5;

    private final Scanner sc;

    public InputView(Scanner sc) {
        this.sc = sc;
    }

    public int inputParticipantCount() {
        System.out.println("자동차 대수는 몇 대인가요?");
        int participantCount = inputNumber();

        validateParticipantCount(participantCount);

        return participantCount;
    }

    public int inputRoundCount() {
        System.out.println("시도할 횟수는 몇 회인가요?");

        int roundCount = inputNumber();

        validateRoundCount(roundCount);

        return roundCount;
    }

    private void validateParticipantCount(int participantCount) {
        if (participantCount < MIN_PARTICIPANT_COUNT || participantCount > MAX_PARTICIPANT_COUNT) {
            throw new IllegalArgumentException("유효한 참가자 수가 아닙니다.");
        }
    }

    private void validateRoundCount(int roundCount) {
        if (roundCount < MIN_ROUND_COUNT || roundCount > MAX_ROUND_COUNT) {
            throw new IllegalArgumentException("유효한 참가자 수가 아닙니다.");
        }
    }

    private int inputNumber() {
        try {
            return sc.nextInt();
        } catch (InputMismatchException ex) {
            throw new IllegalArgumentException("숫자 형식이 아닙니다.", ex);
        }
    }
}
