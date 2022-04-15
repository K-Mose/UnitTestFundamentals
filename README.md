# UnitTestFundamentals

## Introduction of Testing
코드 테스팅은 정말 중요한 작업입니다. 초기에는 이 작업이 불필요하고 코딩 작업을 느리게 하여시간낭비로 느껴지겠지만, 
테스트는 많은 시간을 아끼고 장기간의 고통을 피하게 해줍니다. 
안드로이드의 TDD(Test-Driven Development, 테스트 주도 개발)를 하기 위해서 테스트 케이스를 작성해야 합니다. 
그리고 테스트를 수항할 코드를 작성합니다. 

새로운 기능들을 설계할 때 발생하는 units of responsibility를 고려하는 것은 중요합니다. 우리는 보통 각각의 단위(unit)에서 각 단위에 상응하는 유닛테스를 작성합니다. 
우리의 유닛테스트는 기본적인 상호작용부터 입력의 invalid 와 리소스가 어디에서 사용되지 않는지 등을 총망라 할 수 있어야 합니다. 

### Android Testing Pyramid
<img src="https://user-images.githubusercontent.com/55622345/163204977-3f2213cc-9fed-4b08-9ca8-e3dcf0c38c98.png" width="600px"/>

- Unit Tests <br>
유닛테스트는 아주 작은 테스트로 여겨집니다. 유닛테스트는 앱의 행위를 Unit by Uniy(=Class by Class)으로 입증합니다. 
통상, 70% 정도의 테스트를유닛테스트를 통해 수행합니다. 
우리가 코드를 작성할 때 클래스 별로 테스트를 하고 우리가 의도한 단위마다 잘 작동하는지 확인하기 위해 유닛테스트를 합니다. 
- Integration Tests <br>
확실하게 코드가 작됭이 된다면, 
유닛테스트를 시스텡으로 통합합니다. 그리고 각각의 단위들이 서로 잘 작동하는지 확인하기 위해 통합테스트를 수항햅니다. 
약 20%의 테스트를 통합테스트로 진행합니다. 
- UI Tests <br>
마지막으로 UI 테스트를 통해 인터페이스를 확인합니다. 

## Source Sets
<img src="https://user-images.githubusercontent.com/55622345/163396609-d33ce740-237b-4bd9-bfeb-448a7a86ce29.png" width="400px"/> <br>
- com : App의 soucre code를 포함하는 Main source set 입니다. 
- com (androidTest) / com (test) : 테스트를 위한 source sets 입니다. 
  - com (test) : 개발도구에서 실행하는 로컬 테스트를 위한 source set 입니다. 실제 구동하기 위한 device나 emulator가 필요 없습니다. 로컬테스트를 위한 클래스들을 포함합니다. 
  - com (androidTest) : 실제 device나 emulator에서 계측 테스트를 위해 사용합니다. Android Framework library들을 사용하며 로컬테스트보다 속도가 느립니다. 하지만 실제로 어떤 일이 일어나는지 반영합니다. 계측 테스트를 위한 클래스들을 포함합니다. 
### Test Implementation 
***app level build.gradle*** <br>
<img src="https://user-images.githubusercontent.com/55622345/163408382-758a0447-cbb4-478b-9ca0-6f03e314a6ef.png" width="600px"/> <br>
- testImplementation : test source set 에서만 사용 가능한 implementation 입니다.
- androidTestImplementation : androidTest source set 에서만 사용 가능한 implementation 입니다.

*APK 파일을 만들 때는 APK의 용량을 줄이기 위해 test 라이브러리들은 포함되지 않고, Implementation으로 명명된 라이브러리들만 포함됩니다.*

**junit** - 가장 유명한 테스트 프레임워크입니다. <br>
**espresso** - 안드로이드를 위한 UI 테스트 프레임워크입니다. <br>
**core-testing** - ***ViewModel & LiveData*** 테스트하기 위한 라이브러리 입니다. <br>
<a href="https://truth.dev/">**truth**</a> - assertion을 수행하기 위한 라이브러리 입니다. 

## Test Scenario
<img src="https://user-images.githubusercontent.com/55622345/163511246-2756290e-4abc-46a7-92e5-cc992d0420ea.png" width="400px"/> <br>
지름을 입력하면 원의 넓이와 둘레를 구하는 계산기입니다. 이 계산기에서 사용되는 함수 가 정상적으로 작동이 되는지 궁금하여 테스트해보려 합니다. 

계산에 사용되는 클래스는 아래와 같습니다. 원의 둘레와 넓이를 구하는 각각의 메소드가 정의되어있습니다.
```kotlin
class MyCalc : Calculations{
    private val pi = 3.14

    override fun calculateCircumference(radius: Double): Double =  2 * pi * radius

    override fun calculateArea(radius: Double): Double = pi * radius * radius
}
```
MyCalc 클래스의 메소드들이 원의 둘레와 넓이를 구하는 식으로 잘 작성이 되어있지만 실제로 우리가 예상히는 값이 나오는지 테스트를 해봅시다. 

### Local Unit Test
위의 클래스로 Test 클래스를 만들어보겠습니다. 

1. 우선 클래스명을 우클릭하여 Generate를 선택합니다. <br>
<img src="https://user-images.githubusercontent.com/55622345/163511694-c4d13068-1916-4b14-9f3d-b00b1ce9bb34.png" width="300px"/> <br>

2. 그리고 test클래스를 만들기 위해 Test.. 를 클릭합니다. <br>
<img src="https://user-images.githubusercontent.com/55622345/163511841-c1ef56cf-6cef-4033-b360-048b68067941.png" width="300px"/> <br>
3. testing library를 선택하고 Class name을 지정 후 OK 버튼을 누릅니다. <br>
<img src="https://user-images.githubusercontent.com/55622345/163513072-dca78372-cd7b-4786-9119-a4079bf654e0.png" width="300px"/> <br>
4. 테스트 클래스를 생성한 폴더를 선택합니다. 지금은 local 테스트만 하므로 test폴더를 선택합니다.  <br>
<img src="https://user-images.githubusercontent.com/55622345/163513113-283d76df-bd70-45bc-9443-b6716f7f037b.png" width="400px"/> <br>

<img src="https://user-images.githubusercontent.com/55622345/163523299-63a7a6e2-1f48-4a2d-bb8b-be4304f42175.png" width="400px"/> <br>
이제 테스트 클래스가 생성되었습니다. 처음 생성시에 아무 메소드도 선택하지 않았기 때문에 클래스 내에 아무 코드도 존재하지 않습니다. 

테스트 클래스를 작성해 봅시다. 

```kotlin
class MyCalcTest {
    private lateinit var myCalc: MyCalc

    @Before
    fun setUp() {
        myCalc = MyCalc()
    }

    @Test
    fun  calculateCircumference_radiusGiven_returnsCorrectResult() {
        val result = myCalc.calculateCircumference(2.1)
        Truth.assertThat(result).isEqualTo(13.188)
    }
}
```
우선 테스트에서 사용 할 `MyCalc` 객체를 선언합니다. `MyCalc` 객체가 여러 함수에서 사용될 때 함수 호출 시마다 객체도 생성되지 않도록 
Generate - SetUp Function으로 `@Before` 어노테이션이 붙은 `setUp()`함수를 만들고 객체를 초기화 합니다. 
`@Before`는 테스트를 실행하기 전에 객체를 생성시킵니다. 

그리고 테스트에 사용할 함수를 작성한 후 테스트 케이스로 사용하기 위해서 `@Test` 어노테이션을 붙여줍니다. 
함수 내에서 `MyCalc`객체의 원의 둘레를 구하는 함수를 실행 한 후 결과 값을 assort 함수에 넘겨 값을 평가합니다.

이제 테스트를 실행해 보겠습니다. 테스트가 성공하면 아래와 같이 실행됩니다. 
<img src="https://user-images.githubusercontent.com/55622345/163525320-84e696be-6050-4914-89d2-abdf5309982d.png" width="500px"/>

테스트가 실패하면 아래와 같이 예상 값과 실제 출력 값들을 비교하여 어느 코드에서 실패했는지 나타납니다. 
<img src="https://user-images.githubusercontent.com/55622345/163525509-a983f948-0792-4ab7-8eb3-c7edd2bde20a.png" width="700px"/>
