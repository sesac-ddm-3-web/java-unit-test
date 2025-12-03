package org.example;

public class Main {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        ResultView resultView = new ResultView();
        NumberGenerator numberGenerator = new RandomNumberGenerator();

        int carNumber = inputView.getCarCount();
        int tryCount = inputView.getTryCount();

        Cars cars = new Cars(carNumber);
        CarRacingGame carRacingGame = new CarRacingGame(numberGenerator, resultView);
        carRacingGame.run(cars, tryCount);
    }
}
