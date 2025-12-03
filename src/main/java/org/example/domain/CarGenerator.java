package org.example.domain;

import java.util.ArrayList;
import java.util.List;

public class CarGenerator {
    public List<Car> generateCar(int countCars) {
        List<Car> cars = new ArrayList<>();

        for (int i = 0; i < countCars; i++) {
            cars.add(new Car());
        }
        return cars;
    }
}
