package racing.domain;

@FunctionalInterface
public interface MoveAmountCalculator {

    int calculate(int position);
}
