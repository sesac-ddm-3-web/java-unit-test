# 자동차 경주 게임

##  코드 리뷰 시 봐주세요

### 객체지향 설계
- [ ] 클래스가 **단일 책임 원칙(SRP)**을 지키고 있는가?
- [ ] 핵심 로직(`Car`, `RacingGame`)과 UI 로직(`InputView`, `ResultView`)이 분리되어 있는가?
- [ ] 구체 클래스가 아닌 **인터페이스에 의존**하고 있는가? (DIP)

### 테스트 용이성
- [ ] **의존성 주입(DI)**을 통해 테스트 시 가짜 객체를 주입할 수 있는가?
- [ ] 랜덤, 입출력 등 **외부 의존성이 인터페이스로 분리**되어 있는가?
- [ ] 핵심 비즈니스 로직에 대한 **단위 테스트**가 작성되어 있는가?



---

## 📁 프로젝트 구조

```
src/
├── main/java/org/example/
│   ├── Main.java                      # 애플리케이션 진입점
│   ├── Car.java                       # 자동차 도메인
│   ├── RacingGame.java                # 게임 로직
│   ├── RacingGameRunner.java          # 게임 실행 흐름 관리
│   ├── Input.java                     # 입력 인터페이스
│   ├── InputView.java                 # 입력 UI (콘솔)
│   ├── Output.java                    # 출력 인터페이스
│   ├── ResultView.java                # 출력 UI (콘솔)
│   ├── RandomNumberGenerator.java     # 랜덤 숫자 생성 인터페이스
│   └── ActualRandomNumberGenerator.java # 랜덤 숫자 생성 구현체
│
└── test/java/
    ├── CarTest.java                   # Car 단위 테스트
    ├── RacingGameTest.java            # RacingGame 단위 테스트
    └── RacingGameRunnerTest.java      # RacingGameRunner 단위 테스트
```

---

## 🎯 핵심 로직

### 자동차 전진 조건
- 랜덤 숫자가 **4 이상**이면 전진
- 랜덤 숫자가 **4 미만**이면 정지

```java
// RacingGame.java
if(random.getRandomNumber() >= 4){
    car.move();
}
```



---



## 📝 클래스 다이어그램

```
┌─────────────────┐
│      Main       │
└────────┬────────┘
         │
         ▼
┌─────────────────┐     ┌─────────┐     ┌────────┐
│ RacingGameRunner│────▶│  Input  │◀────│InputView│
└────────┬────────┘     └─────────┘     └────────┘
         │
         │              ┌─────────┐     ┌──────────┐
         ├─────────────▶│ Output  │◀────│ResultView│
         │              └─────────┘     └──────────┘
         │
         │              ┌─────────────────────┐     ┌─────────────────────────┐
         └─────────────▶│RandomNumberGenerator│◀────│ActualRandomNumberGenerator│
                        └─────────────────────┘     └─────────────────────────┘
         │
         ▼
┌─────────────────┐
│   RacingGame    │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│       Car       │
└─────────────────┘
```

