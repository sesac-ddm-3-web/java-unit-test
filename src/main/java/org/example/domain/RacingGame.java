package org.example.domain;

import java.util.List;
import java.util.stream.IntStream;

public class RacingGame {

    private final List<Car> cars;
    private final int tryCnt;
    private final MoveStrategy moveStrategy;

    public RacingGame(int carCnt, int tryCnt, MoveStrategy moveStrategy) {
        validateGameSettings(carCnt, tryCnt);

        this.cars = IntStream.rangeClosed(1, carCnt)
            .mapToObj(Car::new)
            .toList();
        this.tryCnt = tryCnt;
        this.moveStrategy = moveStrategy;
    }

    private void validateGameSettings(int carCnt, int tryCnt) {
        if (carCnt <= 0) {
            throw new IllegalArgumentException("자동차 대수는 0 이상이어야 합니다.");
        }

        if (tryCnt <= 0) {
            throw new IllegalArgumentException("시도 횟수는 0 이상이어야 합니다.");
        }
    }

    public void play() {
        for (int i = 0; i < tryCnt; i++) {
            moveCarOnce();
        }
    }

    private void moveCarOnce() {
        for (Car car : cars) {
            car.move(moveStrategy);
        }
    }

    public List<Car> getCars() {
        return List.copyOf(cars);
    }
}
