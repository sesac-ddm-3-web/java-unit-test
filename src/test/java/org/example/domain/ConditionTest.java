package org.example.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class ConditionTest {

    @ParameterizedTest
    @ValueSource(ints = {4, 6, 9})
    @DisplayName("4이상이면 true 반환")
    void CanGoTrueTeest(int number) {
        Condition condition = new Condition();

        boolean result = condition.canGo(number);

        assertThat(result).isEqualTo(true);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    @DisplayName("3이하면 false 반환")
    void CanGoFalseTeest(int number) {
        Condition condition = new Condition();

        boolean result = condition.canGo(number);

        assertThat(result).isEqualTo(false);
    }
}