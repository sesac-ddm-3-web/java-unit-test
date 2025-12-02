
import Calculator.Tokenizer;
import org.assertj.core.api.Assertions;
import org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Queue;

public class TokenizerTest {

    @Test
    @DisplayName("토큰나이저가 토큰을 제대로 했는지 검증한다.")
    void tokenize_correct(){
        // Given
        Tokenizer tokenizer = new Tokenizer();
        String input = "1+2*3";

        // When
        Queue<String> result = tokenizer.tokenize(input);

        // Then

        Assertions.assertThat(result)
                .hasSize(5)
                .containsExactly("1", "+", "2", "*","3");
    }

}
