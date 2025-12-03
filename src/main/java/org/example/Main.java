package org.example;

import org.example.domain.RacingController;
import org.example.view.InputView;
import org.example.view.ResultView;

import java.util.InputMismatchException;

public class Main {
    public static void main(String[] args) {
        RacingController racingController = new RacingController();
        InputView inputView = new InputView();
        ResultView resultView = new ResultView();

        inputView.requestCarCount();
        int countCar = inputView.inputIntData();

        inputView.requestTryCount();
        int countPlay = inputView.inputIntData();

        resultView.resultComment();

        try {
            racingController.play(countCar, countPlay);
        } catch (InputMismatchException e) {
            System.out.println(e.getMessage());
        }
    }
}