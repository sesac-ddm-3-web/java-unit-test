package com.example.racing_game;

import com.example.racing_game.car.Car;
import com.example.racing_game.car.CarSnapshot;
import com.example.racing_game.game.RaceManager;
import com.example.racing_game.input.InputDto;
import com.example.racing_game.moving_strategy.MovingStrategy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RaceManagerTest {

    @Test
    @DisplayName("carCounts 만큼 Car를 생성하고 attempts를 설정")
    void create_initializes_cars_and_attempts() {
        // given
        InputDto dto = new InputDto(3, 5);
        MovingStrategy strategy = () -> true;

        // when
        RaceManager manager = RaceManager.create(dto, strategy);

        // then
        assertThat(manager.getCars()).hasSize(3);
        assertThat(manager.getAttempts()).isEqualTo(5);

        assertThat(manager.getCars())
                .extracting(Car::getId)
                .containsExactly(1, 2, 3);
    }

    @Test
    @DisplayName("시도 횟수만큼 모든 Car를 이동시키고 history를 남김")
    void racePerAttempt_moves_cars_and_records_history() {
        // given
        int carCounts = 3;
        int attempts = 4;
        InputDto dto = new InputDto(carCounts, attempts);
        MovingStrategy alwaysMove = () -> true;
        RaceManager manager = RaceManager.create(dto, alwaysMove);

        // when
        manager.racePerAttempt();

        // then
        List<List<CarSnapshot>> history = manager.getHistory();
        assertThat(history).hasSize(attempts);
        history.forEach(round ->
                assertThat(round).hasSize(carCounts)
        );
        for (int i = 0; i < attempts; i++) {
            int expectedPosition = i + 1;
            List<CarSnapshot> round = history.get(i);
            assertThat(round)
                    .extracting(CarSnapshot::position)
                    .containsExactly(
                            expectedPosition,
                            expectedPosition,
                            expectedPosition
                    );
        }
    }

    @Test
    @DisplayName("이동하지 않는 전략이면 모든 라운드에서 position은 0")
    void racePerAttempt_with_never_move_strategy_keeps_positions_zero() {
        // given
        int carCounts = 2;
        int attempts = 3;
        InputDto dto = new InputDto(carCounts, attempts);

        MovingStrategy neverMove = () -> false;

        RaceManager manager = RaceManager.create(dto, neverMove);

        // when
        manager.racePerAttempt();

        // then
        List<List<CarSnapshot>> history = manager.getHistory();
        assertThat(history).hasSize(attempts);
        history.forEach(round ->
                round.forEach(snapshot ->
                        assertThat(snapshot.position()).isEqualTo(0)
                )
        );
    }
}