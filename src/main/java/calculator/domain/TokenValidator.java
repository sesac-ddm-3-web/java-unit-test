package calculator.domain;

import java.util.Arrays;
import java.util.function.Predicate;

public enum TokenValidator {

    NUMBER("^-?[0-9]+$", index -> index % 2 == 0),
    OPERATOR("^[+\\-*/]$", index -> index % 2 == 1);

    private final String regex;
    private final Predicate<Integer> positionMatcher;

    TokenValidator(String regex, Predicate<Integer> positionMatcher) {
        this.regex = regex;
        this.positionMatcher = positionMatcher;
    }

    public static TokenValidator findValidator(int index) {
        validateIndex(index);

        return Arrays.stream(TokenValidator.values())
                     .filter(tokenValidator -> tokenValidator.positionMatcher.test(index))
                     .findFirst()
                     .orElseThrow(() -> new IllegalArgumentException("유효한 인덱스가 아닙니다."));
    }

    private static void validateIndex(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("유효한 인덱스가 아닙니다.");
        }
    }

    public void validate(String token, int index, int tokenLength) {
        if (index == 0 && !token.matches(NUMBER.regex)) {
            throw new IllegalArgumentException("첫 문자는 정수여야 합니다.");
        }

        if (index == tokenLength - 1 && !token.matches(NUMBER.regex)) {
            throw new IllegalArgumentException("마지막 문자는 정수여야 합니다.");
        }

        if (token.matches(regex)) {
            return;
        }

        throw new IllegalArgumentException("수식은 피연산자와 연산자가 번갈아 나와야 합니다.");
    }
}
