package org.example.racing;

import java.util.Random;

public class RandomValueGenerator implements ValueGenerator {

    private final Random random = new Random();
    private static final int RANDOM_NUMBER_RANGE = 10;

    @Override
    public int generate() {
        return random.nextInt(RANDOM_NUMBER_RANGE);
    }
}
