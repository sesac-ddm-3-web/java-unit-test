package org.example.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ParserTest {

    private Parser parser;

    @BeforeEach
    void setUp() {
        Validator validator = input -> {};
        parser = new Parser(validator);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "'3', '3'",
            "'3 + 4', '3,+,4'",
            "'3+4', '3,+,4'",
            "'3 * 5 + 8', '3,*,5,+,8'",
            "'3*5+8', '3,*,5,+,8'",
            "'10 / 2 - 3', '10,/,2,-,3'"
    })
    void 수식을_파싱하면_토큰_리스트를_반환한다(String input, String expected) {
        List<String> result = parser.parse(input);
        List<String> expectedTokens = List.of(expected.split(","));

        assertThat(result).isEqualTo(expectedTokens);
    }

    @Test
    void 단일_숫자도_파싱할_수_있다() {
        List<String> result = parser.parse("42");

        assertThat(result).containsExactly("42");
    }

    @Test
    void 공백이_여러개_있어도_파싱할_수_있다() {
        List<String> result = parser.parse("3  +  4   *   5");

        assertThat(result).containsExactly("3", "+", "4", "*", "5");
    }
}