package racing.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.List;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class CarsTest {

    @Test
    void 레이스_참가자_수_만큼의_Car를_가진_Cars를_초기화_한다() {
        // when
        Cars actual = Cars.readyToRace(3);

        // then
        assertAll(
                () -> assertThat(actual.getParticipants()).hasSize(3),
                () -> assertThat(actual.getParticipants().get(0).getPosition()).isOne(),
                () -> assertThat(actual.getParticipants().get(1).getPosition()).isOne(),
                () -> assertThat(actual.getParticipants().get(2).getPosition()).isOne()
        );
    }

    @ParameterizedTest(name = "참가자 수가 {0}이라면 초기화 할 수 없다")
    @ValueSource(ints = {1, 11})
    void 유효하지_않은_레이스_참가자_수라면_Cars를_초기화할_수_없다(int participantCount) {
        // when & then
        assertThatThrownBy(() -> Cars.readyToRace(participantCount))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유효한 참가자 수가 아닙니다.");
    }

    @Test
    void 한_번의_라운드를_진행한다() {
        // given
        Cars cars = Cars.readyToRace(3);

        // when
        Cars actual = cars.playRound(() -> 4, position -> position + 1);

        // then
        assertAll(
                () -> assertThat(actual.getParticipants()).hasSize(3),
                () -> assertThat(actual.getParticipants().get(0).getPosition()).isEqualTo(2),
                () -> assertThat(actual.getParticipants().get(1).getPosition()).isEqualTo(2),
                () -> assertThat(actual.getParticipants().get(2).getPosition()).isEqualTo(2)
        );
    }

    @Test
    void 참가자를_조회하면_변경할_수_없는_List로_조회한다() {
        // given
        Cars cars = Cars.readyToRace(3);

        // when
        List<Car> actual = cars.getParticipants();

        // then
        assertThatThrownBy(() -> actual.add(Car.atStartLine()))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
