클래스마다 기능과 세부 설명 작성했습니다.
클래스와 예외 변수 작명이 아직 느낌이 없는 것 같은 느낌이 있습니다..
많은 훈수와 지적 감사히 받겠습니다.

# 프로젝트 주제 및 요구사항
### 주제

연산자 우선 순위를 배제한 계산기 구현하기

### 요구사항
1. 사용자가 입력한 문자열 값에 따라 사칙 연산을 수행할 수 있는 계산기를 구현해야 한다.
2. 문자열 계산기는 사칙 연산의 계산 우선순위가 아닌 입력 값에 따라 계산 순서가 결정된다. 
3. 즉, 수학에서는 곱셈, 나눗셈이 덧셈, 뺄셈 보다 먼저 계산해야 하지만 이를 무시한다.

(예를 들어 "2 + 3 * 4 / 2"와 같은 문자열을 입력할 경우 2 + 3 * 4 / 2 실행 결과인 10을 출력해야 한다.)

# 클래스 구조
### 1.Calculator
- 역할

  사칙연산 수행 후 결과 반환
- 입력 및 출력

  1. 매개변수로 임의의 String expression을 입력받음
  2. 계산한 결과를 double로 반환
- 메서드 설명
  1. ```public double calculate(String expression)```
     >ExpressionParser 클래스를 사용하여 입력된 문자열을 연산자와 피연산자 단위로 토큰화하여 List로 반환
     >
     >PostFixExpressionConverter 클래스를 사용하여 토큰화된 수식을 후위 표현식으로 변환
     >
     >후위 표현식의 모든 요소를 순회하며 사칙연산 수행
     >
     >토큰이 피연산자인 경우 스택에 push
     >
     >토큰이 연산자인 경우 스택에서 피연산자 두 개를 가져와 현재 처리중인 연산자에 맞는 연산 수행 후 결과를 스택에 push
     >
     >실제 연산은 Operator Enum 클래스에서 구현한 calculate() 메서드의 다형성을 통해 각 연산 종류에 맞게 Operator Enum 클래스가 알아서 연산 수행후 결과 반환(객체지향 적용 시도)
     >
     >최종 연산 후 스택에 남은 값을 반환

### 2.ExpressionParser
- 역할

  입력받은 문자열을 연산자와 피연산자 단위로 토큰화하고 잘못된 값에 대한 예외 처리
- 입력 및 출력

  1. String inputExpression을 입력받음
  2. 파싱한 결과를 List<String>으로 반환
  3. 아래 경우에 대한 예외 처리
      1. 입력 문자열이 빈 값이거나 공백만 입력된 경우
      2. 숫자와 연산기호(+, -, *, /)외 다른 문자열이 포함된 경우
      3. 수식 문법이 맞지 않는 경우
- 메서드 설명
  (문자열을 직접적으로 비교하지 않으려고 노력해봤습니다.)
  1. ```public List<String> checkInputExpressionValidation(String inputExpression)```
     >처음 입력된 String 값의 모든 공백을 제거
     >
     >공백 제거한 문자열이 빈 경우 ```EmptyExpressionException``` 예외 발생
     >
     >정규 표현식을 사용하여 숫자와 사칙 연산 기호를 탐색하여 토큰 단위로 구분
     
     >불순물이 포함되었는지 확인하기 위해 currentPos값과 matcher.start() 두 값을 비교한다.
     >
     >이 과정에서 세 개의 변수가 사용된다.
     >1. currentPos : 문자열에서 현재 쳐다보고있는 문자열의 위치(정규 표현식 일치 여부와 무관)
     >2. matcher.start() : 문자열에서 matcher.find()가 실제로 정규 표현식과 일치하는 토큰을 찾은 위치
     >(matcher.start() 메서드는 matcher.find()에서 토큰을 찾은 경우 해당 토큰의 시작 위치 값을 반환)
     >3. matcher.end() : start()에서 찾은 토큰의 끝 인덱스(다음 탐색할 토큰의 위치 => currentPos에 대입)
     >   
     >while 조건인 ```while(matcher.find(currentPos))```에서 matcher.find(currentPos)는 현재 문자열에서 currentPos위치부터 문자열의 끝까지 살펴보며 정규표현에 일치하는 문자열이 존재하는지 확인한다.
     >(정해진 범위 내에서 일치하는 토큰이 어디든 존재하면 true 반환 -> while문 유지)
     >
     >matcher.find()메서드가 currentPos 위치서부터 주어진 문자열에서 정규 표현식에 일치하는 문자열을 찾은 경우
     >matcher.start()값을 matcher.find()메소드가 찾은 문자열 위치 값으로 설정한다.
     >matcher.start() 값과 currentPos 값을 비교한다.
     >(값이 서로 다른 경우 예외 발생)
     >matcher.end() 메서드는 현재 탐색에 성공한 토큰의 위치 값에 1을 더해 다음 탐색할 위치 값을 반환한다.
     >이 값을 currentPos에 대입하고 다음 while문에서 갱신된 currentPos 위치서부터 다시 matcher.find()를 수행한다.
     >
     >만약 (3+4+A-1)와 같이 중간에 불순물이 포함되어 matcher.find()가 그 불순물을 건너뛰고 그 뒤에 토큰을 찾은 경우
     >matcher.start()는 건너뛴 만큼 더 큰 값을 가질 것이고 currentPos는 그보다 작은 불순물의 위치값을 가지고있을 것이다.
     >따라서 이 때 if 조건문을 실행했을 때 matcher.find()값과 currentPos값이 다르고 불순물 예외가 발생한다.
     >
     >근데 (3+4+A*B)처럼 끝까지 불순물로 이뤄진 경우는 matcher.find()가 실패해서 false를 반환해 if의 예외를 발생시킬 수 없다.
     >그래서 currentPos와 spaceCleanedExp의 길이를 비교하여 matcher.find()가 중간에 불순물이 끼어 탐색이 중단된건지 끝까지 탐색을 완료하고 정상 종료 된 것인지 비교하여 예외를 발생시킨다.
     
     > 정규표현식을 사용하여 시작과 끝이 숫자가 아니거나 연산자가 중복되는 경우에 대한 예외(```InvalidGramerException```)를 발생시킨다.
  


### 3.PostFixExpressionConverter
- 역할

  중위 표현식으로 정렬된 토큰을 후위 표현식으로 재정렬하여 반환한다.
- 입력 및 출력

  ExpressionParser에서 처리한 토큰 리스트 입력
  후위 표현식으로 재정렬한 토큰 리스트를 반환
- 메서드 설명

  
  1. ```public List<String> convert(List<String> tokens```
     >입력받은 tokens 리스트를 순회하며 토큰이 피연산자인 경우 그대로 후위 표현식 토큰 리스트에 담는다.
     >
     >토큰이 연산자일 때 operatorStack이 비어있지 않은 경우 스택에서 값을 pop하여 후위 표현식 토큰 리스트에 담는다.
     >이후 현재 처리중인 토큰은 스택에 push 한다.
     >순회가 끝나면 operatorStack에 남아있는 연산자를 마지막으로 후위 표현식 토큰 리스트에 담는다.
     
     >올바른 표현식이 넘어왔다고 가정하여 예외는 발생시키지 않는다.

### 4.Operator
- 역할

  각 연산자 종류별로 calculate 메서드 연산 과정을 다르게 오버라이딩하여 다형성 구현
- 메서드 설명

  1. ```public double calculate(double opd1, double opd2)```
     >enum 클래스의 연산자 종류에 맞게 사칙 연산을 수행하고 double형 결과를 반환한다.
     >DIVIDE는 0으로 나누는 경우 예외 처리
  2. ```public static Operator findBySymbol(String token)```
     >입력된 연산자 문자열 값을 비교하여 Operator 객체를 반환
     >사용처에서는 operator.calculate(opd1,opd2) 형식으로 각 연산자 종류를 구체적으로 지정하지 않고 사칙 연산 수행

### 5.EmptyExpressionException
- 역할

  빈 문자열 혹은 공백으로만 이뤄진 문자열에 대한 예외
### 6.InvalidGramerException

- 역할

  문법에 맞지 않는 수식에 대한 예외
### 7.CalculatorTest

1. 올바른 수식을 입력하여 실제 값과 비교하여 제대로된 사칙 연산 결과를 반환하는지 테스트
2. 0으로 나누는 경우 예외 발생 테스트
### 8.CheckInputExpressionValidationTest

1. 올바른 수식에 대한 파싱 성공 테스트
```@ValueSource(strings = {"3+47*2/5", "3 + 47 * 2 / 5", "3+ 47 * 2/5"})```

2. 불순물이 포함된 수식에 대한 예외 발생 테스트
```@ValueSource(strings = {"3+a47*2/5", "3&47*2/5", "4+2-A&B"})```

3. 표현식이 빈 문자열 혹은 공백으로 이뤄진 경우 예외 발생 테스트
```@ValueSource(strings = {"", "    "}```

4. 수식의 문법이 잘못된 경우 예외 발생 테스트
```@ValueSource(strings = {"+3*4", "2+3*", "+3*4/", "2*/34"})```

### 9.PostFixExpressionConverterTest

1. 후위 표현식 정상 변환 성공 테스트
