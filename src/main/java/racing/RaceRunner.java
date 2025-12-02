package racing;

import java.util.List;
import racing.domain.Car;
import racing.domain.Cars;
import racing.view.InputView;
import racing.view.OutputView;

public class RaceRunner {

    private static final int RANDOM_BOUND = 10;

    private final InputView inputView;
    private final OutputView outputView;

    public RaceRunner(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void run() {
        int participantCount = inputView.inputParticipantCount();
        int roundCount = inputView.inputRoundCount();
        Cars cars = Cars.readyToRace(participantCount);

        race(roundCount, cars);
    }

    private void race(int roundCount, Cars cars) {
        outputView.announceRaceResult();

        for (int i = 0; i < roundCount; i++) {
            cars = cars.playRound(
                    () -> (int) (Math.random() * RANDOM_BOUND),
                    position -> position + 1
            );

            List<Car> participants = cars.getParticipants();

            outputView.printRound(participants);
        }
    }
}
