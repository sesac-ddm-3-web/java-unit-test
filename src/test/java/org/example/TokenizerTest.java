package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class TokenizerTest {
    private final Tokenizer tokenizer = new Tokenizer();

    @Nested
    @DisplayName("성공 케이스")
    class SuccessCases {
        @Test
        @DisplayName("사칙연산 수식을 정확히 분리한다")
        void split(){
            String expression = "1 + 2";
            List<String> tokens = tokenizer.tokenize(expression);
            assertThat(tokens).containsExactly("1", "+", "2");
        }

        @Test
        @DisplayName("수식 앞뒤나 중간에 공백이 있어도 정상적으로 분리한다")
        void trim_split(){
            String expression = "      1 + 23  *  4   ";
            List<String> tokens = tokenizer.tokenize(expression);
            assertThat(tokens).containsExactly("1", "+", "23", "*", "4");
        }

        @Test
        @DisplayName("음수도 숫자로 인식한다")
        void negative_number(){
            String expression = "-1 - -3";
            List<String> tokens = tokenizer.tokenize(expression);
            assertThat(tokens).containsExactly("-1", "-", "-3");
        }
    }

    @Nested
    @DisplayName("예외 케이스")
    class FailCases {
        @ParameterizedTest
        @NullAndEmptySource
        @ValueSource(strings = {" ", "\t", "\n"})
        @DisplayName("입력값이 null이거나 공백이면 예외가 발생한다")
        void null_or_blank(String expression){
            assertThatThrownBy(() -> tokenizer.tokenize(expression))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("입력값이 null 또는 비어 있음");
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "1 +",
                "1 + 7 *",
                "+ 2",
                "-3"
        })
        @DisplayName("수식의 개수가 짝수(완성되지 않은 수식)면 예외가 발생한다")
        void invalid_size(String expression) {
            assertThatThrownBy(() -> tokenizer.tokenize(expression))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("수식의 개수가 맞지 않습니다");
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "a + 1",
                "1.5 + 1",
                "! + 2"
        })
        @DisplayName("숫자 자리에 정수가 아닌 값이 오면 예외가 발생한다")
        void invalid_number_format(String expression) {
            assertThatThrownBy(() -> tokenizer.tokenize(expression))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("유효하지 않은 숫자입니다");
        }

        @ParameterizedTest
        @ValueSource(strings = {
                "1 & 2",
                "1 2 3",
                "1 ++ 2"
        })
        @DisplayName("연산자 자리에 올바르지 않은 기호가 오면 예외가 발생한다")
        void invalid_operator_format(String input) {
            // Operator.validate()에서 던지는 예외 확인
            assertThatThrownBy(() -> tokenizer.tokenize(input))
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessageContaining("유효하지 않은 연산자입니다");
        }
    }

}