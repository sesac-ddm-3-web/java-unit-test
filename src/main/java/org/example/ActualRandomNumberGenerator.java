package org.example;

import java.util.Random;

public class ActualRandomNumberGenerator implements RandomNumberGenerator {
    private final Random random = new Random();

    @Override
    public int getRandomNumber() {

        return random.nextInt(10);
    }
}
