package org;

import org.domain.RandomNumberGenerator;
import org.view.InputView;
import org.view.OutputView;

public class Main {
    public static void main(String[] args) {
        try {
            String inputCarCount = InputView.inputCarCount();
            String inputTryCount = InputView.inputTryCount();

            int carCount = Integer.parseInt(inputCarCount);
            int tryCount = Integer.parseInt(inputTryCount);

            RacingManager manager = RacingManager.create(
                    carCount,
                    tryCount,
                    new RandomNumberGenerator()
            );
            OutputView.printResultHeader();
            manager.race();

        } catch (NumberFormatException e) {
            System.out.println("[ERROR] " + e.getMessage());
        }

    }
}
