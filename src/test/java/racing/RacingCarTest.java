package racing;

import org.example.racing.RacingCar;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class RacingCarTest {

    @ParameterizedTest
    @DisplayName("0 ~ 3는 움직일 수 없다.")
    @ValueSource(ints = {0, 1, 2, 3})
    void cantMoveTest(int value) {
        //given
        RacingCar racingCar = new RacingCar(() -> value);
        int prevPosition = racingCar.getPosition();

        //when
        racingCar.move();

        //then
        assertThat(racingCar.getPosition()).isEqualTo(prevPosition);
    }

    @ParameterizedTest
    @DisplayName("4 ~ 9는 움직인다.")
    @ValueSource(ints = {4, 5, 6, 7, 8, 9})
    void shouldMoveTest(int value) {
        //given
        RacingCar racingCar = new RacingCar(() -> value);
        int prevPosition = racingCar.getPosition();

        //when
        racingCar.move();

        //then
        assertThat(racingCar.getPosition()).isEqualTo(prevPosition + 1);
    }
}
