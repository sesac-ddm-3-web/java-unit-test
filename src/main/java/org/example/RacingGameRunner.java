package org.example;

import java.util.List;

public class RacingGameRunner {
    Input input;
    Output output;
    RandomNumberGenerator randomNumberGenerator;

    public RacingGameRunner(Input input, Output output, RandomNumberGenerator randomNumberGenerator) {
        this.input = input;
        this.output = output;
        this.randomNumberGenerator = randomNumberGenerator;
    }

    public void run() {
        int carCount = input.getCarCount();
        int round = input.getTimes();

        RacingGame game = new RacingGame(carCount, round,  randomNumberGenerator);

        for (int i = 0; i < round; i++) {
            List<Car> cars = game.play();
            output.printRound(cars , i);
        }


    }
}
