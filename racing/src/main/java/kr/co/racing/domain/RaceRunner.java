package kr.co.racing.domain;

import kr.co.racing.domain.numbergenerator.NumberGenerator;

import java.util.List;

public class RaceRunner {
    private final List<String> racingRoads;
    private final Integer tryCount;
    private final NumberGenerator numberGenerator;

    public RaceRunner(List<String> racingRoads, Integer tryCount,
                      NumberGenerator numberGenerator) {
        this.racingRoads = racingRoads;
        this.tryCount = tryCount;
        this.numberGenerator = numberGenerator;
    }

    public String run() {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < tryCount; i++) {
            assignRandomNumber();

            result.append(recordMoveOrNot());
            result.append("\n");
        }

        return result.toString();
    }

    private void assignRandomNumber() {
        for (int i = 0; i < racingRoads.size(); i++) {
            Integer randomValue = numberGenerator.next();

            decideMoveOrNot(randomValue, i);
        }
    }

    private void decideMoveOrNot(Integer randomValue, Integer index) {
        if (randomValue >= 4) {
            String before = racingRoads.get(index);
            racingRoads.set(index, before + "-");
        }
    }

    private String recordMoveOrNot() {
        StringBuilder temp = new StringBuilder();

        for (int i = 0; i < racingRoads.size(); i++) {
            temp.append(racingRoads.get(i))
                    .append("\n");
        }

        return temp.toString();
    }
}
