package org.example;

import org.example.domain.Cars;
import org.example.ui.ResultView;
import org.example.utils.NumberGenerator;

public class CarRacingGame {
    private final NumberGenerator numberGenerator;
    private final ResultView resultView;

    public CarRacingGame(NumberGenerator numberGenerator, ResultView resultView) {
        this.numberGenerator = numberGenerator;
        this.resultView = resultView;
    }

    public void run(Cars cars, int tryCount){
        resultView.printBeforeGame();

        for (int i = 0; i < tryCount; i++) {
            cars.moveAll(numberGenerator);
            resultView.printCarGame(cars);
            resultView.printNewLine();
        }
    }
}
