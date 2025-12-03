package org.example.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class RandomNumberGeneratorTest {

    private NumberGenerator numberGenerator;

    @BeforeEach
    public void init() {
        numberGenerator = new RandomNumberGenerator();
    }

    @DisplayName("[성공] 지정된 범위 내의 숫자만 생성한다.")
    @ParameterizedTest
    @ValueSource(ints = {10, 100, 500})
    public void success_number_in_range(int bound) {
        // when, then
        for (int i = 0; i < 1000; i++) {
            int number = numberGenerator.generate(bound);

            assertThat(number)
                .isGreaterThanOrEqualTo(0)
                .isLessThan(bound);
        }
    }
}