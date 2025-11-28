package SeSAC.calculator_test_code_example_;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class PostFixExpressionConverter {
    public List<String> convert(List<String> tokens) {
        //parser에서 올바른 표현식만 넘어왔다고 가정
        Stack<String> operatorstack = new Stack<>();
        List<String> resultPostFixExpression = new ArrayList<>();

        tokens.forEach(token -> {
            if (token.matches("\\d+")) {
                //토큰이 숫자인 경우
                resultPostFixExpression.add(token);
            }
            else{
                //토큰이 연산자인 경우
                if(!operatorstack.isEmpty()){
                    //스택이 비어있지 않다면 pop하여 가져와서 후위연산식에 추가
                    resultPostFixExpression.add(operatorstack.pop());
                }
                //현재 연산자는 stack에 Push 후 다음 연산자를 기다림
                operatorstack.push(token);
            }
        });
        //stack에 남은 마지막 연산자 pop
        resultPostFixExpression.add(operatorstack.pop());
        return resultPostFixExpression;
    }

}
