package racing;

import java.util.Scanner;
import racing.view.InputView;
import racing.view.OutputView;

public class Main {

    public static void main(String[] args) {
        OutputView outputView = new OutputView();

        try (Scanner sc = new Scanner(System.in)) {
            InputView inputView = new InputView(sc);
            RaceRunner raceRunner = new RaceRunner(inputView, outputView);

            raceRunner.run();
        } catch (IllegalArgumentException ex) {
            outputView.printExceptionMessage(ex.getMessage());
        }
    }
}
