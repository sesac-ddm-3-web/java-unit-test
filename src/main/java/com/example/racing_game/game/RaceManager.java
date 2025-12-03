package com.example.racing_game.game;

import com.example.racing_game.car.Car;
import com.example.racing_game.car.CarSnapshot;
import com.example.racing_game.input.InputDto;
import com.example.racing_game.moving_strategy.MovingStrategy;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

@Getter
public class RaceManager {

    private final List<Car> cars;
    private final Integer attempts;
    private final List<List<CarSnapshot>> history = new ArrayList<>();

    private RaceManager(List<Car> cars, int attempts) {
        this.cars = cars;
        this.attempts = attempts;
    }

    public void racePerAttempt() {
        for (int i = 0; i < attempts; i++) {
            moveOrStop();
            saveSnapshot();
        }
    }

    private void moveOrStop() {
        cars.forEach(Car::move);
    }

    private void saveSnapshot() {
        history.add(
                cars.stream()
                        .map(CarSnapshot::from)
                        .toList()
        );
    }

    public static RaceManager create(InputDto dto,
                                     MovingStrategy strategy) {
        List<Car> cars = IntStream
                .rangeClosed(1, dto.carCounts())
                .mapToObj(i -> new Car(i, strategy))
                .toList();
        return new RaceManager(cars, dto.attempts());
    }
}
