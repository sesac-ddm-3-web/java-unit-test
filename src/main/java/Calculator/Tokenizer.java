package Calculator;

import java.util.LinkedList;
import java.util.Queue;

public class Tokenizer {

    public Queue<String> tokenize(String input) {
       String[] tokens = input.split("");
       Queue<String> tokenList = new LinkedList<>();


       // 왼쪽부터 오른쪽으로 토큰을 큐에 넣어준다.
       for(String token : tokens){
           tokenList.add(token);
       }

       return tokenList;
    }

}
