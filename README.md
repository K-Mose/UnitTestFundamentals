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
