package com.example.racing_game.moving_strategy;


import java.util.Random;

public class MovingByRandomNumber implements MovingStrategy{

    private static final Random random = new Random();
    private static final Integer MAX_NUMBER = 10;

    @Override
    public boolean isMovable() {
        return random.nextInt(MAX_NUMBER) >= 4 ;
    }
}
