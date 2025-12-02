# 자동차 경주

## 요구사항

초간단 자동차 경주 게임을 구현한다.
- 주어진 횟수 동안 n대의 자동차는 전진 또는 멈출 수 있다.
- 사용자는 몇 대의 자동차로 몇 번의 이동을 할 것인지 입력할 수 있어야 한다.
- 전진하는 조건은 0에서 9사이에서 무작위 값을 구한 후 무작위 값이 4 이상일 경우이다.
- 자동차의 상태를 화면에 출력한다. 어느 시점에 출력할 것인지에 대한 제약은 없다.

## 출력

```text
자동차 대수는 몇 대인가요?
3
시도할 횟수는 몇 회인가요?
3

실행 결과
-
--
--

--
--
---

--
---
----
```

### 특이 사항
- 발생하는 예외는 IllegalArgumentException으로 통일 
- 전진할 수 있는지 여부, 얼마나 전진할 수 있는지를 함수형 인터페이스로 표현
- 출력 가독성을 위해 자동차의 최초 위치는 `1`이라고 가정
- 최소 참여자 수는 2명, 최대 참여자 수는 10명
- 최소 1 라운드, 최대 5 라운드

## 컴포넌트

### Car

- 설명
  - 자동차 경주 참가자의 자동차를 의미하는 객체 
- 기능
  - `public static Car atStartLine()`
    - 출발 지점에 있는 자동차를 세팅하는 정적 팩토리 메서드 
    - 자동차 위치를 최초 위치인 `1`로 초기화 
  - `public Car move(MoveValueGenerator moveValueGenerator, MoveAmountCalculator moveAmountCalculator)`
    - 자동차를 전진시키는 메서드 
    - `MoveValueGenerator`를 기준으로 전진 여부 판단 
    - `MoveAmountCalculator`를 통해 전진할 거리 계산 
  - `public int getPosition()`
    - view 조회용 getter

### MoveValueGenerator

- 설명
  - 자동차가 움직일 수 있는지 판단하기 위한 숫자(Value)를 반환하는 함수형 인터페이스 
- 기능
  - `int generate()`
    - 숫자 생성 메서드 

### MoveAmountCalculator

- 설명
  - 자동차가 얼마나 움직일 수 있는지를 게산하기 위한 함수형 인터페이스
- 기능
  - `int calculate(int position)`
    - 어디까지 전진했는지에 대한 position을 계산하는 메서드

### Cars

- 설명
  - 경주에 참여하는 모든 참가자의 자동차를 표현하기 위한 일급 컬렉션 
- 기능
  - `public static Cars readyToRace(int participantCount)`
    - 경주를 시작하기 위해 모든 참여자의 자동차를 최초 위치인 `1`로 초기화하는 메서드 
    - `participantCount` 만큼 Car를 초기화
  - `public Cars playRound(MoveValueGenerator moveValueGenerator, MoveAmountCalculator moveAmountCalculator)`
    - 자동차 경주 1라운드를 진행하는 메서드 
  - `public List<Car> getParticipants()`
    - view 조회용 getter

### RaceRunner

- 설명
  - 자동차 경주를 진행하기 위한 컴포넌트 
  - `Car`, `Cars`를 초기화하고 주어진 라운드만큼 수행한 뒤 그 결과를 출력하는 컴포넌트 
- 기능
  - `public void run()`
    - 자동차 경주 실행 

### Main

- 설명
  - 필요한 객체들 간의 협력 관계를 정의하고 자동차 경주를 트리거하는 컴포넌트
  - 일종의 `@Configuration`
- 기능
  - `public static void main(String[] args)`
    - 애플리케이션 진입점
