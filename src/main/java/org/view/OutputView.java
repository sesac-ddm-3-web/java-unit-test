package org.view;

import org.domain.Car;
import org.domain.RacingCars;

public class OutputView {
    private static final String RACE_RESULT_HEADER = "실행결과";
    private static final String POSITION_MARKER = "-";
    private OutputView () {}

    public static void printResultHeader() {
        System.out.println(RACE_RESULT_HEADER);
    }

    public static void printRaceStatus(RacingCars racingCars) {
        for (Car car : racingCars.getCars()) {
            printCarStatus(car);
        }
        System.out.println();
    }

    private static void printCarStatus(Car car) {
        System.out.println(POSITION_MARKER.repeat(car.getPosition()));
    }
}
