package racing;

import org.example.racing.RacingCar;
import org.example.racing.RacingCarFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import racing.mock.MockValueGenerator;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class RacingCarFactoryTest {

    @ParameterizedTest
    @DisplayName("자동차를 10개 생성한다.")
    @ValueSource(ints = {10})
    void createCarsTest(int count){
        //given
        RacingCarFactory factory = new RacingCarFactory(new MockValueGenerator());

        //when
        List<RacingCar> cars = factory.createCars(count);

        //then
        assertThat(cars).hasSize(10);
    }
}
