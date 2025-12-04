package SeSAC.RacingGame_TestCode_exmaple;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicLong;

public class ParticipantList {
    private static HashMap<Long, Car> participantList = new HashMap<>();
    private static AtomicLong carIdGenerator = new AtomicLong(0);

    public ParticipantList(long participantNumber) {
        if (participantNumber < 2) {
            throw new IllegalArgumentException("Participant number must be greater than 1");
        }
        for(long i = 0; i < participantNumber; i++) {
            long carId = carIdGenerator.incrementAndGet();
            Car car = new Car(carId);
            participantList.put(carId, car);
        }
    }

    public void doRace(RacingRule racingRule) {
        participantList.forEach(
                (id, car) -> {
                    car.drive(racingRule.raceProcess());
                }
        );
    }

    public long getParticipantNumber() {
        return participantList.size();
    }

    public void printRaceState() {
        participantList.forEach(
                (id, car) -> {
                    System.out.print(id + " : ");
                    System.out.println(car.raceState());
                }
        );
    }
}
