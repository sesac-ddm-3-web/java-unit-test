package kr.co.racing.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RaceRunnerTest {
    @DisplayName("decideMoveOrNot 역할: 랜덤 값이 4 미만이면 자동차는 이동하지 않는다")
    @Test
    void carDoesNotMoveWhenRandomValueIsLessThan4() {
        List<String> roads = new ArrayList<>();

        roads.add("");

        // given
        int tryCount = 1;

        RaceRunner runner = new RaceRunner(roads, tryCount, () -> 3);

        // when
        String result = runner.run();

        // then : 자동차는 이동하지 않음
        assertThat(roads.get(0)).isEmpty();
    }

    @DisplayName("decideMoveOrNot 역할: 랜덤 값이 4 이상이면 자동차는 한 칸(-) 이동한다")
    @Test
    void carMovesWhenRandomValueIsGreaterOrEqualTo4() {
        List<String> roads = new ArrayList<>();

        roads.add("");

        // given
        int tryCount = 1;

        RaceRunner runner = new RaceRunner(roads, tryCount, () -> 5);

        // when
        String result = runner.run();

        // then
        assertThat(roads.get(0)).isEqualTo("-");
    }

    @DisplayName("assignRandomNumber 역할: 여러 대의 자동차에 대해 각각 랜덤 값에 따라 이동 여부를 적용한다")
    @Test
    void assignRandomNumber_appliesRandomValueToEachCar() {
        // given
        List<String> roads = new ArrayList<>();

        roads.add("");
        roads.add("");
        roads.add("");

        int tryCount = 1;

        int[] values = {4, 3, 7};
        int[] index = {0};

        RaceRunner runner = new RaceRunner(roads, tryCount, () -> {
            int v = values[index[0]];

            index[0]++;

            return v;
        });

        // when
        String result = runner.run();

        // then : 각 자동차에 랜덤 값이 각각 적용되었는지 확인
        assertThat(roads).containsExactly("-", "", "-");

        // recordMoveOrNot 포맷까지 함께 검증
        assertThat(result).isEqualTo(
                "-\n" +
                        "\n" +
                        "-\n" +
                        "\n"
        );
    }

    @DisplayName("recordMoveOrNot 역할: 현재 레이싱 도로 상태를 자동차별로 줄마다 기록한다")
    @Test
    void recordMoveOrNot_formatsRoadsPerLine() {
        // given
        List<String> roads = new ArrayList<>();

        roads.add("");
        roads.add("");

        int tryCount = 1;

        RaceRunner runner = new RaceRunner(roads, tryCount, () -> 4);

        // when
        String result = runner.run();

        // then
        // assignRandomNumber에 의해 두 자동차 모두 한 칸 이동 → "-" 상태
        assertThat(roads).containsExactly("-", "-");

        // recordMoveOrNot는 각 자동차 상태 뒤에 줄바꿈을 붙여 문자열을 만든다
        // run은 그 뒤에 한 줄 더 개행을 붙이므로 최종 출력은 아래와 같다
        assertThat(result).isEqualTo(
                "-\n" +
                        "-\n" +
                        "\n"
        );
    }

    @DisplayName("run 역할: 시도 횟수만큼 랜덤 값 할당과 기록을 반복하며 이동이 누적된다")
    @Test
    void run_repeatsAssignAndRecordByTryCount() {
        // given
        List<String> roads = new ArrayList<>();

        roads.add("");

        int tryCount = 3;

        RaceRunner runner = new RaceRunner(roads, tryCount, () -> 4);

        // when
        String result = runner.run();

        // then
        // 3번 모두 이동했으므로 "---" 가 되어야 함
        assertThat(roads.get(0)).isEqualTo("---");

        // 매 시도마다 recordMoveOrNot 결과가 누적된다:
        // 1회차: "-\n"
        // 2회차: "--\n"
        // 3회차: "---\n"
        // 그리고 각 회차 뒤에 run에서 "\n"을 한 번 더 추가
        assertThat(result).isEqualTo(
                "-\n\n" +
                        "--\n\n" +
                        "---\n\n"
        );
    }
}
