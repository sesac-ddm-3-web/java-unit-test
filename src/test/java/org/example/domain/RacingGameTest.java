package org.example.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RacingGameTest {

    private final MoveStrategy moveStrategy = () -> true;

    @DisplayName("[성공] 레이싱 게임 세팅에 성공한다.")
    @Test
    public void success_game_setting() {
        // given, when
        int carCnt = 3;
        int tryCnt = 20;
        RacingGame racingGame = new RacingGame(carCnt, tryCnt, moveStrategy);

        // then
        assertThat(racingGame.getCars()).hasSize(3);
        assertThat(racingGame.getCars().getLast().getId()).isEqualTo(carCnt);
    }

    @DisplayName("[실패] 자동차 대수 입력값이 유효하지 않은 경우 레이싱 게임 세팅에 실패한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, -50})
    public void fail_game_setting_when_invalid_car_cnt(int carCnt) {
        // given
        int tryCnt = 20;

        // when, then
        assertThatThrownBy(() -> new RacingGame(carCnt, tryCnt, moveStrategy))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("자동차 대수는 0 이상이어야 합니다.");
    }

    @DisplayName("[실패] 시도 횟수 입력값이 유효하지 않은 경우 레이싱 게임 세팅에 실패한다.")
    @ParameterizedTest
    @ValueSource(ints = {0, -50})
    public void fail_game_setting_when_invalid_try_cnt(int tryCnt) {
        // given
        int carCnt = 3;

        // when, then
        assertThatThrownBy(() -> new RacingGame(carCnt, tryCnt, moveStrategy))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("시도 횟수는 0 이상이어야 합니다.");
    }

    @DisplayName("[성공] 레이싱 게임 실행에 성공한다.")
    @Test
    public void success_game_play() {
        // given
        int carCnt = 3;
        int tryCnt = 20;
        RacingGame racingGame = new RacingGame(carCnt, tryCnt, moveStrategy);

        // then
        racingGame.play();

        int actual = racingGame.getCars().getFirst().getDistance();
        assertThat(actual).isEqualTo(tryCnt);
    }
}