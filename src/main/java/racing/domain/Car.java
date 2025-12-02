package racing.domain;

public class Car {

    private static final int START_LINE_POSITION = 1;
    private static final int MOVABLE_NUMBER = 4;

    public static Car atStartLine() {
        return new Car(START_LINE_POSITION);
    }

    private final int position;

    private Car(int position) {
        this.position = position;
    }

    public Car move(MoveValueGenerator moveValueGenerator, MoveAmountCalculator moveAmountCalculator) {
        if (moveValueGenerator.generate() >= MOVABLE_NUMBER) {
            int movingPosition = moveAmountCalculator.calculate(this.position);

            return new Car(movingPosition);
        }

        return this;
    }

    public int getPosition() {
        return position;
    }
}
