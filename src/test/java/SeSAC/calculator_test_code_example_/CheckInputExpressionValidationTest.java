package SeSAC.calculator_test_code_example_;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import static org.assertj.core.api.Assertions.assertThat;

class CheckInputExpressionValidationTest {
    @ParameterizedTest //347+2*5/
    @ValueSource(strings = {"3+47*2/5", "3 + 47 * 2 / 5", "3+ 47 * 2/5"}) //공백 x, 공백 o, 부분 공백 o
    @DisplayName("올바른 수식이 들어갔을 때 파싱 성공")
    public void expressionParsingSuccessTest(String expression) {
        //given
        ExpressionParser expressionParser = new ExpressionParser();
        List<String> convertResult;
        List<String> correctResult= List.of("3","+","47","*","2","/","5");

        //when
        convertResult = expressionParser.checkInputExpressionValidation(expression);

        //then
        assertThat(convertResult).isEqualTo(correctResult);
    }

    @ParameterizedTest //347+2*5/
    @ValueSource(strings = {"3+a47*2/5", "3&47*2/5", "4+2-A&B"}) //알파벳을 포함, 사칙연산 외 기호 포함
    @DisplayName("불순물이 포함되어 예외 발생")
    public void expressionParsingInvalidFailTest(String expression) {
        //given
        ExpressionParser expressionParser = new ExpressionParser();
        List<String> convertResult;

        //when&then
        Assertions.assertThrows(
                IllegalArgumentException.class,
                () -> {
                    expressionParser.checkInputExpressionValidation(expression);;
                }
        );
    }
    @ParameterizedTest //347+2*5/
    @ValueSource(strings = {"", "    "}) //빈 문자열, 공백 포함 문자열
    @DisplayName("표현식이 빈 문자열 혹은 공백으로 이뤄진 경우 예외 발생")
    public void expressionParsingEmptyFailTest(String expression) {
        //given
        ExpressionParser expressionParser = new ExpressionParser();

        //when&then
        Assertions.assertThrows(
                EmptyExpressionException.class,
                () -> {
                    expressionParser.checkInputExpressionValidation(expression);;
                }
        );
    }

    @ParameterizedTest //347+2*5/
    @ValueSource(strings = {"+3*4", "2+3*", "+3*4/", "2*/34"}) // 연산자로 시작, 연산자로 끝, 시가과 끝이 연산자, 연속된 연산자
    @DisplayName("수식의 문법이 잘못된 경우 예외 발생")
    public void expressionParsingInvalidGramerTest(String expression) {
        //given
        ExpressionParser expressionParser = new ExpressionParser();

        //when&then
        Assertions.assertThrows(
                InvalidGramerException.class,
                () -> {
                    expressionParser.checkInputExpressionValidation(expression);
                    ;
                }
        );
    }

}
