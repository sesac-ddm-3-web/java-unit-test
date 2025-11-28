클래스마다 기능과 세부 설명 작성했습니다.
클래스와 예외 변수 작명이 아직 느낌이 없는 것 같은 느낌이 있습니다..
많은 훈수 부탁드립니다.

# 프로젝트 주제 및 요구사항
### 주제

연산자 우선 순위를 배제한 계산기 구현하기

### 요구사항
1. 사용자가 입력한 문자열 값에 따라 사칙 연산을 수행할 수 있는 계산기를 구현해야 한다.
2. 문자열 계산기는 사칙 연산의 계산 우선순위가 아닌 입력 값에 따라 계산 순서가 결정된다. 
3. 즉, 수학에서는 곱셈, 나눗셈이 덧셈, 뺄셈 보다 먼저 계산해야 하지만 이를 무시한다.

(예를 들어 "2 + 3 * 4 / 2"와 같은 문자열을 입력할 경우 2 + 3 * 4 / 2 실행 결과인 10을 출력해야 한다.)

# 클래스 구조
**1. Calculator**
- 기능
매개변수로 전달받은 수식의 연산을 수행하고 결과 반환
- 작동 과정


**2. ExpressionParser**
(Parser 설명)

**3. PostFixExpressionConverter**
(converter 설명)

**4. Operator**
(operator 설명)

**5. EmptyExpressionException**

**6. InvalidGramerException**

**7. CalculatorTest**

**8. CheckInputExpressionValidationTest**

**9. PostFixExpressionConverterTest**
