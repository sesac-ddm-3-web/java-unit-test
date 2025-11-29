# 문자열 계산기 

## 요구 사항

사용자가 입력한 문자열 값에 따라 사칙 연산을 수행할 수 있는 계산기를 구현해야 한다.
문자열 계산기는 사칙 연산의 계산 우선순위가 아닌 입력 값에 따라 계산 순서가 결정된다.
즉, 수학에서는 곱셈, 나눗셈이 덧셈, 뺄셈 보다 먼저 계산해야 하지만 이를 무시한다.
예를 들어 "2 + 3 * 4 / 2"와 같은 문자열을 입력할 경우 2 + 3 * 4 / 2 실행 결과인 10을 출력해야 한다.

## 특이 사항

- 문자열로 입력한 숫자는 오로지 정수라고 가정
- 구분자는 반드시 공백 한 칸(` `)이라고 가정
- - 수식 시작 전, 종료 후 공백은 길이 무관하게 허용 (`  1 + 2  ` 등)
- 나눗셈의 경우 소수점 세 자리를 기준으로 반올림해서 반환
- 발생하는 모든 예외는 IllegalArgumentException으로 통일

## 실행 방법 

- calculator.CalculatorMain 실행

## 컴포넌트 

### Operator

- 설명 
  - 사칙연산 기호와 그에 따른 연산 로직을 관리하는 enum
- 기능
  - `public static Operator find(String targetName)`
    - 사칙연산 Operator를 조회하는 static 메서드 
    - String targetName : 찾고자 하는 사칙연산 기호
  - `public BigDecimal calculate(BigDecimal a, BigDecimal b)`
    - 해당하는 사칙연산을 수행하는 메서드 
    - BigDecimal a : 좌측 피연산자 
    - BigDecimal b : 우측 피연산자
- 예외 메시지 
  - `연산자를 찾을 수 없습니다.`
    - 사칙연산이 아닌 기호로 조회한 경우 
  - `0으로 나눌 수 없습니다.`
    - 나눗셈 연산 시 0으로 나눈 경우 (`1 / 0`)

### ExpressionTokenizer

- 설명
  - 주어진 수식을 토큰화하는 class
- 기능
  - `public String[] tokenize(String expression)`
    - 문자열로 주어진 수식의 연산자와 피연산자를 문자열로 토큰화하는 메서드
    - String expression : 문자열 수식
- 예외 메시지
  - `수식은 정수, 사칙연산 기호와 구분자로 공백 한 칸만을 허용합니다.`
    - 정수, 사칙연산 기호, 공백 한 칸을 제외한 나머지 문자를 입력한 경우 (`1.2 + 3`, `a + 5` 등)
  - `적어도 세 개 이상의 토큰으로 된 수식을 입력해야 합니다.`
    - 수식에 단 하나의 정수만 입력한 경우 (`123`, `123 + ` 등)

### TokenValidator

- 설명
  - 주어진 토큰을 검증하는 enum
- 기능
  - `public static TokenValidator find(int index)`
    - 토큰 인덱스에 따른 검증기를 조회하는 static 메서드 
    - int index : 해당 토큰의 인덱스
  - `public void validate(String token, int index, int tokenLength)`
    - 문자열 토큰을 검증하는 메서드 
    - String token : 문자열 토큰 
    - int index : 해당 토큰의 인덱스 
    - int tokenLength : 전체 토큰 길이
- 예외 메시지
  - `유효한 인덱스가 아닙니다.`
    - 검증기 조회 시 토큰의 인덱스가 유효하지 않은 경우 (`-1` 등)
  - `첫 문자는 정수여야 합니다.`
    - 수식의 첫 번째 문자가 정수가 아닌 경우 (`+ 2 + 3`, `- 2 + 3 * 2` 등)
  - `마지막 문자는 정수여야 합니다.`
    - 수식의 마지막 문자가 정수가 아닌 경우 (`2 + 3 +`, `2 + 1 + - ` 등)
  - `수식은 피연산자와 연산자가 번갈아 나와야 합니다.`
    - 연산자/피연산자 순서가 어긋난 경우 (`3 3 + 1`, `1 + + 2` 등)

### Calculator

- 설명
  - 사용자가 입력한 문자열 값에 따라 사칙 연산을 수행하는 class
- 기능
  - `public BigDecimal calculate(String expression)`
    - 문자열로 주어진 수식을 계산하는 메서드 
    - String expression : 문자열 수식
