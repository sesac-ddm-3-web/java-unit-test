package kr.co.racing;

import kr.co.racing.domain.InitRacing;
import kr.co.racing.domain.numbergenerator.NumberGenerator;
import kr.co.racing.domain.RaceRunner;
import kr.co.racing.domain.numbergenerator.RandomNumberGenerator;
import kr.co.racing.io.InputHelper;
import kr.co.racing.io.OutputHelper;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        /**
         * 초간단 자동차 경주 게임을 구현한다.
         * 주어진 횟수 동안 n대의 자동차는 전진 또는 멈출 수 있다.
         * 사용자는 몇 대의 자동차로 몇 번의 이동을 할 것인지 입력할 수 있어야 한다.
         * 전진하는 조건은 0에서 9사이에서 무작위 값을 구한 후 무작위 값이 4 이상일 경우이다.
         * 자동차의 상태를 화면에 출력한다. 어느 시점에 출력할 것인지에 대한 제약은 없다.
         */

        InitRacing initRacing = new InitRacing();
        NumberGenerator numberGenerator = new RandomNumberGenerator();
        OutputHelper outputHelper = new OutputHelper();

        try (Scanner scanner = new Scanner(System.in)) {
            InputHelper inputHelper = new InputHelper(scanner);

            Integer numberOfCars = inputHelper.inputNumberOfCars();
            Integer tryCount = inputHelper.inputTryCount();

            List<String> racingRoads = initRacing.initRacingRoads(numberOfCars);

            RaceRunner raceRunner = new RaceRunner(racingRoads, tryCount, numberGenerator);

            String result = raceRunner.run();

            outputHelper.printResult(result);

        } catch (IllegalArgumentException e) {
            outputHelper.printExceptionMessage(e.getMessage());
        }
    }
}
