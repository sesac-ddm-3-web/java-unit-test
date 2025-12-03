# 자동차 경주 게임

## 기능 요구사항

* 초간단 자동차 경주 게임을 구현한다.
* 주어진 횟수 동안 n대의 자동차는 전진 또는 멈출 수 있다.
* 사용자는 몇 대의 자동차로 몇 번의 이동을 할 것인지 입력할 수 있어야 한다.
* 전진하는 조건은 0에서 9사이에서 무작위 값을 구한 후 무작위 값이 4 이상일 경우이다.
* 자동차의 상태를 화면에 출력한다. 어느 시점에 출력할 것인지에 대한 제약은 없다.
## 클래스 및 기능 설명

#### 1. 도메인 
핵심 비즈니스 로직을 담당하는 클래스들입니다.

- **`Car`**: 개별 자동차 객체입니다.
    - `position`: 자동차의 현재 위치를 저장합니다.
    - `move(int condition)`: 전달받은 조건 값(`condition`)이 4 이상일 경우 위치(`position`)를 1 증가시킵니다.
    - `getPosition()`: 현재 자동차의 위치 값을 반환합니다.


- **`Cars`**: 여러 대의 자동차(`Car`)를 관리하는 일급 컬렉션입니다.
    - `Cars(int carNumber)`: 입력받은 수만큼 자동차 객체를 생성합니다. 자동차가 2대 미만일 경우 예외(`IllegalArgumentException`)를 발생시킵니다.
    - `moveAll(NumberGenerator numberGenerator)`: 리스트에 있는 모든 자동차에게 난수를 전달하여 이동 명령을 내립니다.
    - `getCars()`: 관리하고 있는 자동차 리스트를 반환합니다.

#### 2. UI
사용자 입력과 화면 출력을 담당합니다.

- **`InputView`**: 사용자의 입력을 처리합니다.
    - `getCarCount()`: 사용자로부터 자동차 대수를 입력받아 반환합니다.
    - `getTryCount()`: 시도할 횟수를 입력받습니다. 1회 미만 입력 시 예외(`IllegalArgumentException`)를 발생시킵니다.


- **`ResultView`**: 게임의 실행 결과를 출력합니다.
    - `printBeforeGame()`: "실행 결과" 문구를 출력합니다.
    - `printCarGame(Cars cars)`: 자동차들의 현재 위치만큼 하이픈(`-`)을 반복하여 시각적으로 출력합니다.

#### 3. 유틸리티
랜덤 값 생성을 담당합니다.

- **`NumberGenerator` (Interface)**: 숫자 생성 전략을 정의하는 인터페이스입니다. 테스트 시 고정된 숫자를 반환하기 위해 분리되었습니다.
    - `generateNumber()`: 숫자를 생성하여 반환합니다.


- **`RandomNumberGenerator`**: `NumberGenerator`의 구현체로, 실제 게임 진행 시 사용됩니다.
    - `generateNumber()`: 0에서 9 사이의 무작위 정수(`Random`)를 생성하여 반환합니다.

#### 4. 실행
프로그램의 흐름을 제어합니다.

- **`CarRacingGame`**: 게임의 전체 흐름을 제어하는 컨트롤러입니다.
    - `run(Cars cars, int tryCount)`: 입력받은 시도 횟수만큼 반복문을 돌며, 자동차들을 이동시키고(`moveAll`) 결과를 출력(`printCarGame`)합니다.


- **`Main`**: 프로그램의 진입점입니다.
    - 필요한 객체(`InputView`, `ResultView`, `NumberGenerator`)를 생성하고, `CarRacingGame`을 실행합니다.
## 테스트

- **`CarTest`**: 개별 자동차(`Car`)의 핵심 이동 로직을 검증합니다.
    - `move_if_number_is_4_or_more`: 4 이상의 값이 주어졌을 때 자동차가 정상적으로 1칸 전진하는지 확인합니다.
    - `stop_if_number_is_3_or_less`: 3 이하의 값이 주어졌을 때 자동차가 움직이지 않고 정지해 있는지 확인합니다.


- **`CarsTest`**: 자동차 일급 컬렉션(`Cars`)의 생성 제약 조건과 일괄 이동을 검증합니다.
    - `create_cars_check_size`: 입력한 수만큼 자동차 리스트가 올바르게 생성되는지 확인합니다.
    - `throw_exception_if_less_than_two_cars`: 자동차 대수가 2대 미만일 경우 예외(`IllegalArgumentException`)가 발생하는지 검증합니다.
    - `move_all_cars`: 전진 조건이 충족될 경우, 리스트 내의 **모든** 자동차가 전진하는지 확인합니다.
    - `stop_all_cars`: 전진 조건이 미달될 경우, 리스트 내의 **모든** 자동차가 멈춰있는지 확인합니다.
