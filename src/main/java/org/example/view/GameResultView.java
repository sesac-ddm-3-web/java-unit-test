package org.example.view;

import java.util.List;
import java.util.stream.Collectors;

import org.example.domain.Car;

public class GameResultView {

    public static void printResult(List<Car> cars) {
        System.out.println("=== 레이싱 게임 결과 ===");
        String result = cars.stream()
            .map(car -> car.getId() + ": " + "-".repeat(car.getDistance()))
            .collect(Collectors.joining("\n"));

        System.out.println(result);
    }
}
