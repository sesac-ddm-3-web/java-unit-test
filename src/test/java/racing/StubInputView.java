package racing;

import racing.view.InputView;

public class StubInputView extends InputView {

    private final int participantCount;
    private final int roundInput;

    public StubInputView(int participantCount, int roundInput) {
        super(null);

        this.participantCount = participantCount;
        this.roundInput = roundInput;
    }

    @Override
    public int inputParticipantCount() {
        return participantCount;
    }

    @Override
    public int inputRoundCount() {
        return roundInput;
    }
}
