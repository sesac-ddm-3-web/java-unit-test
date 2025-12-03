package com.example.racing_game.game;

import lombok.Getter;


@Getter
public class RacingGame{

    private RaceManager raceManager;

    public RacingGame(RaceManager raceManager) {
        this.raceManager = raceManager;
    }

    public void raceStart() {
        raceManager.racePerAttempt();
    }

    public static RacingGame create(RaceManager raceManager){
        return new RacingGame(raceManager);
    }
}
