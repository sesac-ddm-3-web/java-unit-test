package org.domain;

import java.util.ArrayList;
import java.util.List;

public class RacingCars {
    private final List<Car> cars;

    private RacingCars(List<Car> cars) {
        this.cars = List.copyOf(cars);
    }

    public static RacingCars create(int participants, NumberGenerator generator) {
        List<Car> cars = new ArrayList<>();
        for (int i = 0; i < participants; i++) {
            cars.add(new Car(generator));
        }
        return new RacingCars(cars);
    }

    public void moveAll() {
        for (Car car : cars) {
            car.move();
        }
    }

    public List<Car> getCars() {
        return List.copyOf(cars);
    }
}
