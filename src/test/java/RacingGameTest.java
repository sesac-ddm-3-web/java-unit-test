import org.example.Car;
import org.example.RacingGame;
import org.example.RandomNumberGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RacingGameTest {


    class AlwaysMoveGenerator implements RandomNumberGenerator {
        @Override
        public int getRandomNumber() {
            return 4;
        }
    }

    class NeverMoveGenerator implements RandomNumberGenerator {
        @Override
        public int getRandomNumber() {
            return 3;
        }
    }

    @Test
    @DisplayName("랜덤값이 4 이상이면 자동차가 전진한다")
    public void carMovesWhenRandomNumberIsGreaterThanOrEqual4(){
        RandomNumberGenerator randomNumberGenerator = new AlwaysMoveGenerator();
        RacingGame racingGame = new RacingGame(3, 5, randomNumberGenerator);

        List<Car> cars = racingGame.play();

        assertThat(cars.get(0).getPosition()).isEqualTo(1);
    }

    @Test
    @DisplayName("랜덤값이 4 미만이면 자동차가 안움직인다")
    public void carMovesWhenRandomNumberIsLessThanOrEqual4(){
        RandomNumberGenerator randomNumberGenerator = new NeverMoveGenerator();
        RacingGame racingGame = new RacingGame(3, 5, randomNumberGenerator);

        List<Car> cars = racingGame.play();

        assertThat(cars.get(0).getPosition()).isEqualTo(0);
    }
}
