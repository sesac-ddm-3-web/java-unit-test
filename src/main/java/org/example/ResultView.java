package org.example;

import java.util.List;

public class ResultView implements Output {

    @Override
    public void printRound(List<Car> cars, int round) {
        System.out.println("Round " + round);
        for (Car car : cars) {
            String position = "-".repeat(car.getPosition());
            System.out.println(position);
        }
    }

}

