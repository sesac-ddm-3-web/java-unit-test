import org.example.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RacingGameRunnerTest {


    class TestInput implements Input {
        private int carCount;
        private int times;

        public TestInput(int carCount, int times) {
            this.carCount = carCount;
            this.times = times;
        }

        @Override
        public int getCarCount() {
            return carCount;
        }

        @Override
        public int getTimes() {
            return times;
        }
    }


    class TestOutput implements Output {
        private int printRoundCallCount = 0;
        private List<Integer> roundNumbers = new ArrayList<>();

        @Override
        public void printRound(List<Car> cars, int round) {
            printRoundCallCount++;
            roundNumbers.add(round);
        }

        public int getPrintRoundCallCount() {
            return printRoundCallCount;
        }

        public List<Integer> getRoundNumbers() {
            return roundNumbers;
        }
    }


    class AlwaysMoveGenerator implements RandomNumberGenerator {
        @Override
        public int getRandomNumber() {
            return 4;
        }
    }

    @Test
    @DisplayName("run 호출 시 라운드 횟수만큼 printRound가 호출된다")
    void runCallsPrintRoundForEachRound() {
        int carCount = 3;
        int round = 5;
        TestInput input = new TestInput(carCount, round);
        TestOutput output = new TestOutput();
        RandomNumberGenerator randomNumberGenerator = new AlwaysMoveGenerator();

        RacingGameRunner runner = new RacingGameRunner(input, output, randomNumberGenerator);


        runner.run();


        assertThat(output.getPrintRoundCallCount()).isEqualTo(round);
    }


}

