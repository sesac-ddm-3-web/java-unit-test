package org.example.domain;

public class RandomMoveStrategy implements MoveStrategy {

    public static final int DEFAULT_BOUND = 10;
    public static final int DEFAULT_MOVE_THRESHOLD = 4;

    private final int bound;
    private final int moveThreshold;
    private final NumberGenerator numberGenerator;

    public RandomMoveStrategy(int bound, int threshold, NumberGenerator numberGenerator) {
        this.bound = bound;
        this.moveThreshold = threshold;
        this.numberGenerator = numberGenerator;
    }

    public static RandomMoveStrategy defaultStrategy(NumberGenerator numberGenerator) {
        return new RandomMoveStrategy(DEFAULT_BOUND, DEFAULT_MOVE_THRESHOLD, numberGenerator);
    }

    @Override
    public boolean canMove() {
        int number = numberGenerator.generate(this.bound);
        return number >= this.moveThreshold;
    }
}
