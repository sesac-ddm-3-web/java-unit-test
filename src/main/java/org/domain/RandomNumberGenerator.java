package org.domain;

import java.util.concurrent.ThreadLocalRandom;

public class RandomNumberGenerator implements NumberGenerator{
    private static final int NUMBER_RANGE = 10;

    @Override
    public int generate() {
        return ThreadLocalRandom.current().nextInt(NUMBER_RANGE);
    }
}
