package kr.co.racing;

import kr.co.racing.io.InputHelper;
import kr.co.racing.io.OutputHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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

        Scanner scanner = new Scanner(System.in);

        System.out.println("자동차 대수는 몇 대인가요?");
        Integer numberOfCars = scanner.nextInt();

        System.out.println("시도할 횟수는 몇 회인가요?");
        Integer tryCount = scanner.nextInt();

        List<String> racingRecords = new ArrayList<>();
        
        for (int i = 0; i < numberOfCars; i++) {
            racingRecords.add(""); // 경주로 초기화
        }

        Random random = new Random();

        System.out.println("실행결과");

        for (int i = 0; i < tryCount; i++) {
            // 각 자동차에 대해 0 ~ 9 사이의 숫자를 뽑고, 4 이상이면 한 칸 전진
            for (int j = 0; j < numberOfCars; j++) {
                int randomValue = random.nextInt(10); // 0 ~ 9 뽑기

                if (randomValue >= 4) {
                    String before = racingRecords.get(j);
                    racingRecords.set(j, before + "-");
                }
            }

            // 현재 시점의 각 자동차 위치 출력
            for (int j = 0; j < numberOfCars; j++) {
                System.out.println(racingRecords.get(j));
            }

            System.out.println();
        }
    }
}
