package org.example.racing;

import java.util.List;

public class RacingParticipants {
    private final List<RacingCar> cars;

    public RacingParticipants(List<RacingCar> cars) {
        this.cars = cars;
    }

    public void playRound() {
        for(RacingCar car : cars) {
            car.move();
        }
    }

    public int size(){
        return cars.size();
    }

    public List<RacingCar> getCars() {
        return cars;
    }
}
