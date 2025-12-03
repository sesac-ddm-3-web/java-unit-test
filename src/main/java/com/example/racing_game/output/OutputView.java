package com.example.racing_game.output;

import com.example.racing_game.car.CarSnapshot;

import java.util.List;

public class OutputView {

    private static final String RESULT_PER_ATTEMPTS_OUTPUT_MESSAGE = "실행 결과";

    public static void printHistory(List<List<CarSnapshot>> history) {
        System.out.println(RESULT_PER_ATTEMPTS_OUTPUT_MESSAGE);
        for (List<CarSnapshot> round : history) {
            System.out.println("================");
            for (CarSnapshot snapshot : round) {
                System.out.println("-".repeat(snapshot.position()));
            }
            System.out.println();
        }
    }
}
