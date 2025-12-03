package racing;

import org.example.racing.RacingCar;
import org.example.racing.RacingParticipants;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RacingParticipantsTest {

    @ParameterizedTest
    @DisplayName("playRound를 호출하면 모든 자동차가 전략에 따라 움직인다")
    @CsvSource({
            "5, 4, 1",
            "2, 3, 0"
    })
    void playRound_MoveAll(int generatedValue1, int generatedValue2, int expectedValue) {
        // given
        RacingCar car1 = new RacingCar(() -> generatedValue1);
        RacingCar car2 = new RacingCar(() -> generatedValue2);
        RacingParticipants participants = new RacingParticipants(List.of(car1, car2));

        // when
        participants.playRound();

        // then
        assertThat(car1.getPosition()).isEqualTo(expectedValue);
        assertThat(car2.getPosition()).isEqualTo(expectedValue);
    }

    @Test
    @DisplayName("참가자 수를 정확히 반환한다")
    void sizeTest() {
        // given
        List<RacingCar> cars = List.of(
                new RacingCar(() -> 1),
                new RacingCar(() -> 1),
                new RacingCar(() -> 1)
        );
        RacingParticipants participants = new RacingParticipants(cars);

        // when & then
        assertThat(participants.size()).isEqualTo(3);
    }
}
