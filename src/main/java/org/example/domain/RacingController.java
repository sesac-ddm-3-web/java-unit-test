package org.example.domain;

import org.example.view.ResultView;

import java.util.ArrayList;
import java.util.List;

public class RacingController {

    private ResultView resultView = new ResultView();
    private CarGenerator carGenerator = new CarGenerator();
    private Condition condition = new Condition();

    private int play = 0;

    public void play(int countCar, int countPlay) {
        List<Car> cars = carGenerator.generateCar(countCar);

        while (play < countPlay) {
            List<Integer> carResult = new ArrayList<>();

            for (Car car : cars) {
                int randomNum = condition.generateRandomNum();
                boolean goAllowed = condition.canGo(randomNum);
                car.move(goAllowed);
                carResult.add(car.getPosition());
            }
            resultView.resultView(carResult);

            play++;
        }
    }
}
