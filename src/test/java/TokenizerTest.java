import org.example.Tokenizer;
import org.example.Tokens;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TokenizerTest {

    Tokenizer tokenizer = new Tokenizer();

    @Test
    @DisplayName("정상적인 표현식은 숫자와 연산자로 분리되어야 한다")
    void 정상표현식은_토큰화된다() {
        Tokens tokens = tokenizer.tokenize("10 + 20");

        assertEquals(2, tokens.getNumbers().size());
        assertEquals(10.0, tokens.getNumbers().get(0));
        assertEquals(20.0, tokens.getNumbers().get(1));

        assertEquals(1, tokens.getOperators().size());
        assertEquals("+", tokens.getOperators().get(0));
    }

    @Test
    @DisplayName("여러 연산자가 포함된 표현식도 순서대로 토큰화되어야 한다")
    void 여러연산자를_포함한_표현식도_토큰화된다() {
        Tokens tokens = tokenizer.tokenize("4 + 2 * 3");

        assertEquals(3, tokens.getNumbers().size());
        assertEquals(4.0, tokens.getNumbers().get(0));
        assertEquals(2.0, tokens.getNumbers().get(1));
        assertEquals(3.0, tokens.getNumbers().get(2));

        assertEquals(2, tokens.getOperators().size());
        assertEquals("+", tokens.getOperators().get(0));
        assertEquals("*", tokens.getOperators().get(1));
    }
}
