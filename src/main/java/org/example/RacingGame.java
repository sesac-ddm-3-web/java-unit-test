package org.example;

import java.util.ArrayList;
import java.util.List;

public class RacingGame {
    private RandomNumberGenerator random;
    private List<Car> cars = new ArrayList<>();
    private int round;

    public RacingGame(int carCount, int round, RandomNumberGenerator random) {
        this.cars = carAdd(carCount);
        this.round = round;
        this.random = random;
    }

    private List<Car> carAdd(int carCount) {
        for (int i = 0; i < carCount; i++) {
            cars.add(new Car());
        }
        return cars;
    }

    public List<Car> play() {
        for ( Car car : cars ) {
            if(random.getRandomNumber() >= 4){
                car.move();
            }
        }
        return cars;
    }


}
