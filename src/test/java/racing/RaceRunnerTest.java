package racing;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

@SuppressWarnings("NonAsciiCharacters")
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class RaceRunnerTest {

    @Test
    void 레이스를_진행한다() {
        // given
        StubInputView inputView = new StubInputView(3, 3);
        MockOutputView outputView = new MockOutputView();
        RaceRunner raceRunner = new RaceRunner(inputView, outputView);

        // when
        raceRunner.run();

        // then
        assertAll(
                () -> assertThat(outputView.getCountAnnounceRaceResultCall()).isOne(),
                () -> assertThat(outputView.getCountPrintRoundCall()).isEqualTo(3)
        );
    }
}
