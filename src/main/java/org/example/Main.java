package org.example;

import org.example.domain.MoveStrategy;
import org.example.domain.NumberGenerator;
import org.example.domain.RacingGame;
import org.example.domain.RandomMoveStrategy;
import org.example.domain.RandomNumberGenerator;
import org.example.view.GameResultView;
import org.example.view.GameSettingInputView;

public class Main {
    public static void main(String[] args) {
        int carCnt = GameSettingInputView.readCarCount();
        int tryCnt = GameSettingInputView.readTryCount();

        NumberGenerator numberGenerator = new RandomNumberGenerator();
        MoveStrategy moveStrategy = RandomMoveStrategy.defaultStrategy(numberGenerator);
        RacingGame racingGame = new RacingGame(carCnt, tryCnt, moveStrategy);

        racingGame.play();

        GameResultView.printResult(racingGame.getCars());
    }
}