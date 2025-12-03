package org.example.view;

import org.example.racing.RacingCar;
import org.example.racing.RacingParticipants;

public class ResultView {
    public void print(RacingParticipants participants) {
        for(RacingCar car : participants.getCars()){
            System.out.println("-".repeat(car.getPosition()));
        }
        System.out.println();
    }
}
