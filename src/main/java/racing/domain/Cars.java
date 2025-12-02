package racing.domain;

import java.util.ArrayList;
import java.util.List;

public class Cars {

    public static Cars readyToRace(int participantCount) {
        List<Car> cars = new ArrayList<>();

        for (int i = 0; i < participantCount; i++) {
            cars.add(Car.atStartLine());
        }

        return new Cars(cars);
    }

    private final List<Car> participants;

    private Cars(List<Car> participants) {
        this.participants = participants;
    }

    public Cars playRound(MoveValueGenerator moveValueGenerator, MoveAmountCalculator moveAmountCalculator) {
        List<Car> movingCars = this.participants.stream()
                                                .map(car -> car.move(moveValueGenerator, moveAmountCalculator))
                                                .toList();

        return new Cars(movingCars);
    }

    public List<Car> getParticipants() {
        return List.copyOf(this.participants);
    }
}
