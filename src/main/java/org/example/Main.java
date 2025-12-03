package org.example;

import org.example.racing.RacingCarFactory;
import org.example.racing.RandomValueGenerator;
import org.example.view.InputView;
import org.example.view.ResultView;

public class Main {

    public static void main(String[] args) {
        InputView inputView = new InputView();
        ResultView resultView = new ResultView();
        RacingCarFactory racingCarFactory = new RacingCarFactory(new RandomValueGenerator());
        RacingGame game = new RacingGame(inputView, resultView, racingCarFactory);

        game.run();
    }
}
