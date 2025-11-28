package SeSAC.calculator_test_code_example_;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;


public class PostFixExpressionConverterTest {
    @Test
    @DisplayName("후위표현식 형식에 맞게 변환")
    public void convertPostFixExpressionTest() {
        //given
        PostFixExpressionConverter postFixExpressionConverter = new PostFixExpressionConverter();
        List<String> parsedTokens = List.of("1", "+", "33", "/", "2", "-", "10");

        //when
        List<String> convertResult = postFixExpressionConverter.convert(parsedTokens);

        //then
        Assertions.assertThat(convertResult).containsExactly("1", "33", "+", "2", "/", "10", "-");
    }

}