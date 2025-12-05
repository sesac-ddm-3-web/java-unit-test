package kr.co.racing.domain;

import java.util.ArrayList;
import java.util.List;

public class InitRacing {
    public List<String> initRacingRoads(Integer numberOfCars) {
        List<String> racingRoads = new ArrayList<>();

        for (int i = 0; i < numberOfCars; i++) {
            racingRoads.add("");
        }

        return racingRoads;
    }
}
