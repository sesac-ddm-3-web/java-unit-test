package SeSAC.calculator_test_code_example_;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
2 + 3 * 4 / 2
=> 23+4*2/ (계산할 때는 push push pop push ...)
 */
public class ExpressionParser {
    //후위 표기법으로 변경하고
    //연산자 문자열을 enum 으로도 바꿔서 처리하려고했는데 또 책임이 많아지는 거 같아서 안깝치는 걸로
    public List<String> checkInputExpressionValidation(String inputExpression) {
        //공백이 포함여부의 가능성이 존재하거나 존재하지 않을 수 있기 때문에 둘 다 처리
        //처음에 split으로 공백을 나누고 결과 리스트를 다시 파싱하려고 했는데 복잡해서 폐기하고
        //정규표현식을 활용하는 것으로 수정
        String spaceCleanedExp = inputExpression.replaceAll("\\s+", ""); // 모든 공백을 제거

        if (spaceCleanedExp.isEmpty()) {
            throw new EmptyExpressionException();
        }

        Pattern pattern = Pattern.compile("(\\d+|[+\\-*/()])"); // 정규 표현식: 숫자(\d+) 또는 연산자/괄호([+\-*/()]) 탐색
        Matcher matcher = pattern.matcher(spaceCleanedExp);

        List<String> tokens = new ArrayList<>();
        int currentPos = 0; // 현재 탐색을 시작할 위치

        //연산식에 불순물(숫자, 연산자가 아닌 다른 문자)이 포함되어있는지 확인
        while (matcher.find(currentPos)) {

            // Matcher가 토큰을 찾았지만, 현재 위치(이전 매칭의 끝)와
            // 새로운 토큰의 시작 위치 사이에 공백(Gap)이 발생한 경우
            if (matcher.start() != currentPos) {
                String remaining = spaceCleanedExp.substring(currentPos, matcher.start());
                throw new IllegalArgumentException("수식에 유효하지 않은 문자 포함");
            }

            tokens.add(matcher.group());
            //currentPos(=탐색할 위치)의 값을 matcher.end()(그 다음 탐색할 위치 값)으로 변경
            currentPos = matcher.end();
        }
        // while 루프 종료 후 추가
        if (currentPos != spaceCleanedExp.length()) { // expression은 전체 연산식 문자열
            throw new IllegalArgumentException("수식 끝에 불순물 포함");
        }

        //숫자와 사칙연산 기호에 대한 검사는 완료
        //수식 오류 예외처리
        String firstToken = tokens.get(0); // 시작 토큰이 숫자가 아니거나 (숫자 정규식 \\d+ 사용)
        String lastToken = tokens.get(tokens.size() - 1);// 끝 토큰이 숫자가 아닌 경우 예외 처리

        if (!firstToken.matches("\\d+") && !firstToken.matches("\\(")) {
            throw new InvalidGramerException("수식의 시작은 숫자여야합니다.");
        }

        if (!lastToken.matches("\\d+") && !lastToken.matches("\\)")) {
            throw new InvalidGramerException("수식의 끝은 숫자여야합니다.");
        }

        // 연산자가 연속으로 나오는 잘못된 경우
        Pattern operatorPattern = Pattern.compile("[+\\-*/]");// 연산자 패턴을 미리 정의

        for (int i = 0; i < tokens.size() - 1; i++) {
            String current = tokens.get(i);
            String next = tokens.get(i + 1);

            // 현재 토큰이 연산자이고, 다음 토큰도 연산자라면 예외
            if (operatorPattern.matcher(current).matches() && operatorPattern.matcher(next).matches()) {
                throw new InvalidGramerException("연산자가 연속될 수 없습니다.");
            }
        }

        return tokens;
    }
}
