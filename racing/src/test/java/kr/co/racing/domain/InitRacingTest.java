package kr.co.racing.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class InitRacingTest {
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 3, 5})
    void initRacingRoads_자동차_대수만큼_비어있는_도로를_만든다(int numberOfCars) {
        // given
        InitRacing initRacing = new InitRacing();

        // when
        List<String> roads = initRacing.initRacingRoads(numberOfCars);

        // then
        assertThat(roads).hasSize(numberOfCars);
        assertThat(roads).allSatisfy(road -> assertThat(road).isEmpty());
    }
}
