package kr.co.racing.domain.numbergenerator;

import java.util.Random;

public class RandomNumberGenerator implements NumberGenerator {
    Random random = new Random();

    @Override
    public int next() {
        return random.nextInt(10);
    }
}
