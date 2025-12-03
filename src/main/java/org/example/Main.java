package org.example;

public class Main {
    public static void main(String[] args) {
        Input input = new InputView();
        Output output = new ResultView();
        RandomNumberGenerator randomNumberGenerator = new ActualRandomNumberGenerator();

        RacingGameRunner racingGameRunner = new RacingGameRunner(input, output, randomNumberGenerator);

        racingGameRunner.run();
    }

}