package org.example.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RandomMoveStrategyTest {

    @DisplayName("[성공] 랜덤 값이 기준 이상이면 이동한다.")
    @Test
    public void success_when_number_equal_or_greater_than_threshold() {
        int default_bound = RandomMoveStrategy.DEFAULT_BOUND;
        int default_threshold = RandomMoveStrategy.DEFAULT_MOVE_THRESHOLD;

        for (int i = default_threshold; i < default_bound; i++) {
            int value = i;

            // given
            NumberGenerator numberGenerator = bound -> value;
            MoveStrategy moveStrategy = RandomMoveStrategy.defaultStrategy(numberGenerator);

            // when, then
            assertThat(moveStrategy.canMove()).isTrue();
        }
    }

    @DisplayName("[성공] 랜덤 값이 기준 미만이면 이동하지 않는다.")
    @Test
    public void success_when_number_less_than_threshold() {
        int default_threshold = RandomMoveStrategy.DEFAULT_MOVE_THRESHOLD;

        for (int i = 0; i < default_threshold; i++) {
            int value = i;

            // given
            NumberGenerator numberGenerator = bound -> value;
            MoveStrategy moveStrategy = RandomMoveStrategy.defaultStrategy(numberGenerator);

            // when, then
            assertThat(moveStrategy.canMove()).isFalse();
        }
    }
}