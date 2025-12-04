package SeSAC.RacingGame_TestCode_exmaple;

import java.util.Random;

public class RandomMoveRule implements RacingRule {

    private static final Random random = new Random();
    private final int baseNumber = 4;

    @Override
    public int raceProcess(){
        return (random.nextInt(10) > 3) ? 1 : 0;
    }
}
