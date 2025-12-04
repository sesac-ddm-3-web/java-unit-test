package SeSAC.RacingGame_TestCode_exmaple;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        RacingRule racingRule = new RandomMoveRule();

        System.out.print("자동차 대수는 몇 대인가요?");
        long totalParticipantNumber = Integer.parseInt(br.readLine());
        System.out.print("시도할 횟수는 몇 회인가요?");
        long totalTryNumber = Integer.parseInt(br.readLine());

        RacingGame racingGame = new RacingGame(
                totalParticipantNumber,
                totalTryNumber,
                racingRule
        );

        racingGame.startRace();
    }
}
