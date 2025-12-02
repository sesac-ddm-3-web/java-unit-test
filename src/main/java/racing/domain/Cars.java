package racing.domain;

import java.util.ArrayList;
import java.util.List;

public class Cars {

    private static final int MIN_PARTICIPANT_COUNT = 2;
    private static final int MAX_PARTICIPANT_COUNT = 10;

    public static Cars readyToRace(int participantCount) {
        validateParticipantCount(participantCount);

        List<Car> cars = new ArrayList<>();

        for (int i = 0; i < participantCount; i++) {
            cars.add(Car.atStartLine());
        }

        return new Cars(cars);
    }

    private static void validateParticipantCount(int participantCount) {
        if (participantCount < MIN_PARTICIPANT_COUNT || participantCount > MAX_PARTICIPANT_COUNT) {
            throw new IllegalArgumentException("유효한 참가자 수가 아닙니다.");
        }
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
