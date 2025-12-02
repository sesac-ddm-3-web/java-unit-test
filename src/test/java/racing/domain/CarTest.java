package racing.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CarTest {

    @Test
    void 출발_지점에_선_Car를_초기화_한다() {
        // when
        Car actual = Car.atStartLine();

        // then
        assertThat(actual.getPosition()).isOne();
    }

    @Test
    void 이동을_위한_값이_4_이상으로_나오면_전진한다() {
        // given
        Car car = Car.atStartLine();

        // when
        Car actual = car.move(() -> 4, position -> position + 1);

        // then
        assertThat(actual.getPosition()).isEqualTo(2);
    }

    @Test
    void 이동을_위한_값이_3_이하로_나오면_전진하지_못한다() {
        // given
        Car car = Car.atStartLine();

        // when
        Car actual = car.move(() -> 3, position -> position + 1);

        // then
        assertThat(actual.getPosition()).isOne();
    }

    @Test
    void 이동하는_경우_기존_위치에서_이동_거리_전략에_따라_전진한다() {
        // given
        Car car = Car.atStartLine();

        // when
        Car actual = car.move(() -> 4, position -> position + 7);

        // then
        assertThat(actual.getPosition()).isEqualTo(8);
    }
}
