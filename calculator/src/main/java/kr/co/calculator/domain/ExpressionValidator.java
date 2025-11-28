package kr.co.calculator.domain;

public class ExpressionValidator {
    /**
     * (상황 - 전개 - 질문 - 답변)
     *
     * 하나의 메서드는 하나의 기능만 해야 하는 건 알겠는데, 그렇다고 분자, 원자, 쿼크 단위로 쪼개면
     * 가독성이 떨어질 것 같기도 하다(like 가루 삼겹살)
     *
     * 위 문제를 해결하면서 동시에 테스트 코드를 쉽게 작성할 수 있는 방향이 있는가? 있다!
     *
     * 큰 메서드(실제 main 함수에서 호출)가 세부 메서드들을 가지고 있는데, 세부 메서드들은
     * 서두에 던진 조건을 만족하도록 작성한다. 큰 메서드에 대한 테스트는 하지 않는다.
     */

    public String checkNumberAndOper(String expression) {
        // TODO : 입력 값이 null이거나 빈 공백 문자일 경우 exception
        validateNotNull(expression); // null 체크
        validateNotBlank(expression); // 빈 문자열 체크
        validateNoLeadingWhitespace(expression); // 공백으로 시작하는지 체크

        String normalized = normalize(expression); // 공백 정리: "2   +   3" -> "2 + 3"

        // TODO : 사칙연산 기호가 아닌 경우 exception
        validateTokenSequence(normalized); // 숫자, 연산자 위치 및 형태 검사

        return normalized;
    }

    // 입력 값이 null이거나 빈 공백 문자일 경우 exception
    private void validateNotNull(String expression) {
        if (expression == null) {
            throw new IllegalArgumentException("입력 계산식은 null이 될 수 없습니다.");
        }
    }

    private void validateNotBlank(String expression) {
        if (expression.trim().isEmpty()) {
            throw new IllegalArgumentException("입력 계산식이 비어 있습니다.");
        }
    }

    private void validateNoLeadingWhitespace(String expression) {
        if (!expression.isEmpty() && Character.isWhitespace(expression.charAt(0))) {
            throw new IllegalArgumentException("입력 계산식이 잘못되었습니다.(공백이 먼저 왔습니다.)");
        }
    }

    private String normalize(String expression) {
        return expression.trim().replaceAll("\\s+", " ");
    }

    // 사칙연산 기호가 아닌 경우 exception
    private void validateTokenSequence(String normalized) {
        String[] tokens = normalized.split(" ");  // 예: "2 + 3 - 1 * 3"

        ensureTokensNotEmpty(tokens);
        ensureFirstTokenIsValid(tokens);
        ensureTokenCountIsOdd(tokens);
        ensureLastTokenIsValid(tokens);
        ensureTokenAlternationIsValid(tokens);
    }

    private void ensureTokensNotEmpty(String[] tokens) {
        if (tokens.length == 0) {
            throw new IllegalArgumentException("입력 계산식이 비어 있습니다.");
        }
    }

    private void ensureTokenCountIsOdd(String[] tokens) {
        if (tokens.length % 2 == 0) {
            throw new IllegalArgumentException("연산자와 피연산자의 개수가 맞지 않습니다.");
        }
    }

    private void ensureFirstTokenIsValid(String[] tokens) {
        String first = tokens[0];

        if (first.matches("^[+\\-*/]$")) {
            throw new IllegalArgumentException("연산부호가 먼저 올 수 없습니다.");
        }
        if (!first.matches("^\\d+$")) {
            throw new IllegalArgumentException("수식은 정수로 시작해야 합니다: " + first);
        }
    }

    private void ensureLastTokenIsValid(String[] tokens) {
        String last = tokens[tokens.length - 1];

        if (last.matches("^[+\\-*/]$")) {
            throw new IllegalArgumentException("수식은 연산자로 끝날 수 없습니다.");
        }
    }

    private void ensureTokenAlternationIsValid(String[] tokens) {
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];

            if (i % 2 == 0) { // 피연산자
                if (!token.matches("^\\d+$")) {
                    throw new IllegalArgumentException("정수만 사용할 수 있습니다: " + token);
                }
            } else { // 연산자
                if (!token.matches("^[+\\-*/]$")) {
                    throw new IllegalArgumentException("연산자 위치에 잘못된 토큰이 있습니다: " + token);
                }
            }
        }
    }
}
