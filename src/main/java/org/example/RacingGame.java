package org.example;

import org.example.racing.RacingCarFactory;
import org.example.racing.RacingParticipants;
import org.example.view.InputView;
import org.example.view.ResultView;

public class RacingGame {
    private final InputView inputView;
    private final ResultView resultView;
    private final RacingCarFactory racingCarFactory;

    public RacingGame(InputView inputView, ResultView resultView, RacingCarFactory racingCarFactory) {
        this.inputView = inputView;
        this.resultView = resultView;
        this.racingCarFactory = racingCarFactory;
    }

    public void run() {
        int participantCount = inputView.inputCarCount();
        int tryCount = inputView.inputTryCount();
        RacingParticipants participants = new RacingParticipants(racingCarFactory.createCars(participantCount));

        for(int i = 0; i < tryCount; i++) {
            participants.playRound();
            resultView.print(participants);
        }
    }
}
