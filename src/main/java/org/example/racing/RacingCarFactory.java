package org.example.racing;

import java.util.ArrayList;
import java.util.List;

public class RacingCarFactory {

    private final ValueGenerator valueGenerator;

    public RacingCarFactory(ValueGenerator valueGenerator) {
        this.valueGenerator = valueGenerator;
    }

    public List<RacingCar> createCars(int count) {
        List<RacingCar> cars = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            cars.add(new RacingCar(valueGenerator));
        }
        return cars;
    }
}
