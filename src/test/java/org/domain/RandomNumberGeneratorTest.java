package org.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RandomNumberGeneratorTest {

    private NumberGenerator numberGenerator = new RandomNumberGenerator();

    @Test
    void 생성되는_난수의_범위는_0부터_9까지다() {
        for(int i = 0; i < 1000; i++) {
            assertThat(numberGenerator.generate()).isBetween(0,9);
        }
    }
}