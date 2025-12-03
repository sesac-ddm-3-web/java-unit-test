package org.example.domain;

import org.example.utils.NumberGenerator;

import java.util.ArrayList;
import java.util.List;

public class Cars {
    private final List<Car> cars;

    public Cars(int carNumber){
        if (carNumber < 2) {
            throw new IllegalArgumentException("자동차는 2대 이상이어야 합니다.");
        }
        cars = new ArrayList<>();
        for(int i = 0; i < carNumber; i++){
            cars.add(new Car());
        }
    }

    public void moveAll(NumberGenerator numberGenerator){
        for (Car car : cars) {
            int condition = numberGenerator.generateNumber();
            car.move(condition);
        }
    }

    public List<Car> getCars(){
        return cars;
    }
}
