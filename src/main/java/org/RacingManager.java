package org;

import org.domain.NumberGenerator;
import org.domain.RacingCars;
import org.view.OutputView;

public class RacingManager {
    private final RacingCars racingCars;
    private final int rounds;

    private RacingManager(RacingCars racingCars, int rounds) {
        this.racingCars = racingCars;
        this.rounds = rounds;
    }

    public static RacingManager create(int participants, int rounds, NumberGenerator generator) {
        RacingCars racingCars = RacingCars.create(participants, generator);
        return new RacingManager(racingCars, rounds);
    }

    public void race() {
        for (int i = 0; i < rounds; i++) {
            racingCars.moveAll();
            OutputView.printRaceStatus(racingCars);
        }
    }
}
