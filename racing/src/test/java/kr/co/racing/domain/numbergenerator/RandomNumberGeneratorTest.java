package kr.co.racing.domain.numbergenerator;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RandomNumberGeneratorTest {
    @Test
    void next_항상_0이상_10미만의_숫자를_반환한다() {
        RandomNumberGenerator generator = new RandomNumberGenerator();

        for (int i = 0; i < 100; i++) {
            int value = generator.next();
            assertThat(value)
                    .isGreaterThanOrEqualTo(0)
                    .isLessThan(10);
        }
    }
}
