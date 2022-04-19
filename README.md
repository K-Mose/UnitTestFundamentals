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


## Test Double 
아래의 `CalcViewModel`클래스로 테스트 클래스를 만든다고 생각해 봅시다. 
```kotlin 
class CalcViewModel(
    private val calculations: Calculations
) : ViewModel() {
  …
}
```
`CalcViewModel`클래스는 `Calculations`인터페이스에 의존성을 가지므로 테스트 케이스에서 `CalcViewModel`의 객체를 생성할 때 `Calculations`인터페이스의 객체를 같이 생성해 줘야 합니다. 하지만 위에 
[Local Unit Test](#local-unit-test)에서 생성한 테스트 케이스의 `MyCalc`은 `Calculations`인터페이스를 상속하므로 이 테스트 케이스에서 테스트를 하면 될 것같아 보이지만 이런 방식은 *Unit Test*가 아니게 되고 third-party-library에서는 사용하기 어렵습니다. 

이러한 상황들에서 사용하는 것이 **Test Double** 입니다. 테스트 유닛이 다른 테스트 유닛에게 디펜던시를 제공하기 위해 사용합니다. 

아래는 자주 사용되는 <a href="https://developer.android.com/training/testing/fundamentals/test-doubles#types">test double의 타입</a>입니다. <br>
- Fakes : light weight implementation class of the infercae, usually we hand code fake classes.
- Stubs : an object that provides predefined answers to method calls.
- Mocks : similar to stub, but they allows  tester to set answers to method calls when writing the test case.

이제 `CalcVIewMOdel`의 테스트 클래스를 `Mock`을 사용해 테스트 해보겠습니다. 

우선 app level gradle에 Mockito를 추가합니다. 
```
dependencies {
    def mockitoVersion = "4.4.0"
    testImplementation "org.mockito:mockito-core:$mockitoVersion"
}
```

그리고 [Local Unit Test](#local-unit-test)에서 만든 것과 같이 test source set에 CalcViewModelTest 클래스를 만듭니다. 
```kotlin 
    private lateinit var calcViewModel: CalcViewModel
    private lateinit var calculations: Calculations
```
`CalcViewModel`클래스와 `Calculations`인터페이스를 전역변수로 생성해 줍니다. 

```kotlin 
@Before
    fun setUp() {
        // create Calculations instance using mockito
        calculations = Mockito.mock(Calculations::class.java)
        Mockito
            .`when`(calculations.calculateArea(2.1))
            .thenReturn(13.8474)
        Mockito
            .`when`(calculations.calculateCircumference(5.0))
            .thenReturn(31.4)
        calcViewModel = CalcViewModel(calculations)
    }
```
`setUp()`함수에서 Mockito 객체를 만들고 `when`과 `thenReturn`으로 인터페이스가 구현되어 작동 될 때 나올 값들을 설정해 줍니다. 

```kotlin 
    @Test
    fun calculateArea_radiusSent_updateLiveData() {
        calcViewModel.calculateArea(2.1)
        val result = calcViewModel.areaValue.value
        assertThat(result).isEqualTo("13.8474")
    }

    @Test
    fun calculateCircumference_radiusSent_updateLiveData() {
        calcViewModel.calculateCircumference(5.0)
        val result = calcViewModel.circumferenceValue.value
        assertThat(result).isEqualTo("31.4")
    }
```
마지막으로 테스트 케이스들을 작성합니다. 

이제 독립적으로 의존성을 주입할 수 있습니다. 
<details>
  <summary><b>Full-Code</b></summary>
  
```kotlin
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class CalcViewModelTest {

    private lateinit var calcViewModel: CalcViewModel
    private lateinit var calculations: Calculations

    // Instant Task Execute Rule - test case를 같은 스레드 내에서 실행시킴
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        // create Calculations instance using mockito
        calculations = Mockito.mock(Calculations::class.java)
        Mockito
            .`when`(calculations.calculateArea(2.1))
            .thenReturn(13.8474)
        Mockito
            .`when`(calculations.calculateCircumference(5.0))
            .thenReturn(31.4)
        calcViewModel = CalcViewModel(calculations)
    }

    @Test
    fun calculateArea_radiusSent_updateLiveData() {
        calcViewModel.calculateArea(2.1)
        val result = calcViewModel.areaValue.value
        assertThat(result).isEqualTo("13.8474")
    }

    @Test
    fun calculateCircumference_radiusSent_updateLiveData() {
        calcViewModel.calculateCircumference(5.0)
        val result = calcViewModel.circumferenceValue.value
        assertThat(result).isEqualTo("31.4")
    }
}
```
</details>

## Test Room 
Room의 dao interface는 어떻게 테스트하는지 확인하기 위해 
<a href="https://github.com/K-Mose/TMDBClient---MVVM-with-Clean-Architecture">TMDBClient</a>프로젝트를 이용해서 Room Database의 테스트 케이스를 만들어보겠습니다. 

영화 정보를 저장하고 삭제하는 테스트 케이스를 작성하기 위해서 `MovieDao`의 테스트 클래스를 ***androidTest*** source set에 생성합니다. <br>
![image](https://user-images.githubusercontent.com/55622345/163544889-6a7fafa7-aa8b-4f36-9196-0903d42210c6.png)

Dao 객체와 Database 객체를 생성합니다. 
``` kotlin 
    private lateinit var dao: MovieDao
    private lateinit var database: TMDBDatabase
```

`setUp()`함수에서 위의 객체들을 생성하는데, Room에서 테스트를 위해서 제공하는 inMemoryDatabase로 database 객체를 생성합니다. 
```kotlin
    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TMDBDatabase::class.java,
        ).build()
        dao = database.movieDao()
    }
```
inMemoryDatabase는 임시적으로 생성되는 데이터베이스이므로 테스트가 끝나면 저장되었던 데이터는 삭제됩니다. 

이제 테스트 케이스를 작성합니다. 
``` kotlin 
    @Test
    fun saveMoviesTest() = runBlocking {
        val movies = listOf(
            Movie(1, "overView1", "posterPath1", "date1", "title1"),
            Movie(2, "overView2", "posterPath2", "date2", "title2"),
            Movie(3, "overView3", "posterPath3", "date3", "title3"),
        )
        dao.saveMovies(movies)
        val allMovie = dao.getMovies()
        assertThat(allMovie).isEqualTo(movies)
    }
    
    @Test
    fun deleteAllMoviesTest() = runBlocking {
        val movies = listOf(
            Movie(1, "overView1", "posterPath1", "date1", "title1"),
            Movie(2, "overView2", "posterPath2", "date2", "title2"),
            Movie(3, "overView3", "posterPath3", "date3", "title3"),
        )
        dao.saveMovies(movies)
        dao.deleteAllMovies()
        assertThat(dao.getMovies()).isEmpty()
    }    
```

테스트가 완료되었다면 `tearDown()`함수를 호출시켜 database와의 연결을 종료시킵니다. 
``` kotlin 
    @After
    fun tearDown() {
        database.close()
    }
```
<details>
  <summary><b>Full-Code</b></summary>
  
```kotlin 
import com.google.common.truth.Truth.assertThat
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.core.provider.SelfDestructiveThread
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.kmose.tmdbclient.model.movie.Movie
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var dao: MovieDao
    private lateinit var database: TMDBDatabase

    @Before
    fun setUp() {
        // inMemoryDatabaseBuilder - 테스트에서 사용할 수 있도록 임시적인 데이터 저장공간을 제공한다. 데이터가 지속적이지 않아서 테스트가 끝나면 데이터는 제거된다.
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            TMDBDatabase::class.java,
        ).build()
        dao = database.movieDao()
    }

    @Test
    fun saveMoviesTest() = runBlocking {
        val movies = listOf(
            Movie(1, "overView1", "posterPath1", "date1", "title1"),
            Movie(2, "overView2", "posterPath2", "date2", "title2"),
            Movie(3, "overView3", "posterPath3", "date3", "title3"),
        )
        dao.saveMovies(movies)
        val allMovie = dao.getMovies()
        assertThat(allMovie).isEqualTo(movies)
    }

    @Test
    fun deleteAllMoviesTest() = runBlocking {
        val movies = listOf(
            Movie(1, "overView1", "posterPath1", "date1", "title1"),
            Movie(2, "overView2", "posterPath2", "date2", "title2"),
            Movie(3, "overView3", "posterPath3", "date3", "title3"),
        )
        dao.saveMovies(movies)
        dao.deleteAllMovies()
        assertThat(dao.getMovies()).isEmpty()
    }

    @After
    fun tearDown() {
        database.close()
    }
}
```
</details>

## Test LiveData
다시 한번 <a href="https://github.com/K-Mose/TMDBClient---MVVM-with-Clean-Architecture">TMDBClient</a>프로젝트의 LiveData를 이용합니다. 
<details>
  <summary>테스트에 사용할 코드 구조</summary>

    
```kotlin
class MovieViewModel(
    private val getMoviesUseCase: GetMoviesUseCase,
    private val updateMoviesUseCase: UpdateMoviesUsecase
) : ViewModel() {
    fun getMovies() = liveData {
        val movieList = getMoviesUseCase.execute()
        emit(movieList)
    }

    fun updateMovies() = liveData {
        val movieList = updateMoviesUseCase.execute()
        emit(movieList)
    }
}
```
`MovieViewModel` 클래스 내에는 2개의 UseCase 의존성을 가지고 있습니다. 그리고 두 객체를 가지고 영화 정보들을 가지고 오고 업데이트 합니다. 

```kotlin
class GetMoviesUseCase(private val movieRepository: MovieRepository) {
    suspend fun execute():List<Movie>? = movieRepository.getMovies()
}

class UpdateMoviesUseCase(private val movieRepository: MovieRepository)  {
    suspend fun execute():List<Movie>? = movieRepository.updateMovies()
}
```
두 클래스 `GetMoviesUseCase`와 `UpdateMoviesUseCase`는 `MovieRepository`인터페이스에 정의된 각각의 함수를 가져옵니다.
MovieRepository`인터페이스는 아래와 같이 정의되어 있습니다. 
```kotlin
interface MovieRepository {
    suspend fun getMovies(): List<Movie>?
    suspend fun updateMovies(): List<Movie>?
}

class MovieRepositoryImpl(
    private val movieRemoteDatasource: MovieRemoteDatasource,
    private val movieLocalDataSource: MovieLocalDataSource,
    private val movieCacheDataSource: MovieCacheDataSource
) : MovieRepository {
    override suspend fun getMovies(): List<Movie>? {
        return getMoviesFromCache()
    }

    override suspend fun updateMovies(): List<Movie>? {
        val newListOfMovies = getMoviesFromAPI()
        movieLocalDataSource.clearAll() 
        movieLocalDataSource.saveMovieToDB(newListOfMovies) 
        movieCacheDataSource.saveMovieToCache(newListOfMovies)
        return newListOfMovies
    }

    suspend fun getMoviesFromAPI(): List<Movie> {
        lateinit var movieList:List<Movie>
        try {
            val response = movieRemoteDatasource.getMovies()
            val body = response.body()
            if (body != null) {
                movieList = body.movies
            }
        } catch (e: Exception) {
            Log.i("MyTag", e.message.toString())
        }
        return movieList
    }

    suspend fun getMoviesFromDB(): List<Movie> {
        lateinit var movieList:List<Movie>
        try {
            movieList = movieLocalDataSource.getMovieFromDB()
        } catch (e: Exception) {
            Log.i("MyTag", e.message.toString())
        }
        if(movieList.isNotEmpty()) {
            return movieList
        } else {
            movieList = getMoviesFromAPI()
            movieLocalDataSource.saveMovieToDB(movieList)
        }
        return movieList
    }

    suspend fun getMoviesFromCache(): List<Movie> {
        lateinit var movieList:List<Movie>
        try {
            movieList = movieCacheDataSource.getMovieFromCache()
        } catch (e: Exception) {
            Log.i("MyTag", e.message.toString())
        }
        if(movieList.isNotEmpty()) {
            return movieList
        } else {
            movieList = getMoviesFromDB()
            movieCacheDataSource.saveMovieToCache(movieList)
        }
        return movieList
    }
}
```
그리고 MovieRepository`인터페이스를 구현하는 MovieRepositoryImpl`클래스는 각각 Cache, DB, API에서 데이터를 가져오는 함수를 정의합니다. 
</details>

지금 테스트에서는 `MoviewViewModel`을 사용해서 Fake로 테스트하는 법을 알아보겠습니다. 

우선 test source set에서 테스트를 할 수 있도록 app level의 build.gradle을 수정합니다. 
```kotlin
    testImplementation 'junit:junit:4.+'
    testImplementation 'androidx.test.ext:junit:1.1.3'
    testImplementation "androidx.arch.core:core-testing:$arch_version"
    testImplementation 'org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.1'
    testImplementation 'com.google.truth:truth:1.1.3'
    testImplementation "androidx.test.ext:junit:1.1.3"
    testImplementation "org.robolectric:robolectric:4.7.3"
```
*robolectric은 가상의 안드로이드 환경을 JVM에 제공하여 테스트 할 수 있도록 합니다.*
 
그리고 test set source에 실제 경로와 같은 package를 생성하고 사용할 테스트 클래스를 생성합니다. <br>
  <img src="https://user-images.githubusercontent.com/55622345/163786211-abe35f57-1a13-454c-84c4-0dfebce234a6.png" width="350px"/> <br>
`MovieViewModel`클래스는 `GetMoviesUseCase`와 `UpdateMoviesUseCase`에 의존성을 갖고 각각 UseCase는 `MovieRepository`인터페이스 구현한 객체에 의존성을 갖기 때문에 `MovieRepository`인터페이스를 구현하는 **Fake** 클래스로 테스트를 수행합니다.  
  
`FakeMovieRepository`에서 테스트를 위한 get과 update를 구현합니다.   
```kotlin
class FakeMovieRepository : MovieRepository {
    private val movies = mutableListOf<Movie>()

    init {
        movies.addAll(listOf(
            Movie(1, "overView1", "posterPath1", "date1", "title1"),
            Movie(2, "overView2", "posterPath2", "date2", "title2"),
            Movie(3, "overView3", "posterPath3", "date3", "title3"),
        ))
    }

    override suspend fun getMovies(): List<Movie>? {
        return movies
    }

    override suspend fun updateMovies(): List<Movie>? {
        movies.clear()
        movies.addAll(listOf(
            Movie(4, "overView4", "posterPath4", "date4", "title4"),
            Movie(5, "overView5", "posterPath5", "date5", "title5"),
            Movie(6, "overView6", "posterPath6", "date6", "title6"),
        ))
        return movies
    }
```

그리고 `MovieViewModelTest`에 작성한 Fake객체를 이용하여 ViewModel 객체를 생성합니다. 
```kotlin
private lateinit var viewModel: MovieViewModel

@Before
fun setUp() {
    val fakeMovieRepository = FakeMovieRepository()
    val getMovieUseCase = GetMoviesUseCase(fakeMovieRepository)
    val updateMovieUseCase = UpdateMoviesUseCase(fakeMovieRepository)
    viewModel = MovieViewModel(getMovieUseCase, updateMovieUseCase)
}
```

마지막으로 테스트 케이스를 작성한 후 테스트를 실행합니다. 
```kotlin
@Test
fun getMovies_returnsCurrentList() {
    val movies = mutableListOf<Movie>()
    movies.addAll(listOf(
        Movie(1, "overView1", "posterPath1", "date1", "title1"),
        Movie(2, "overView2", "posterPath2", "date2", "title2"),
        Movie(3, "overView3", "posterPath3", "date3", "title3"),
    ))

    val currentList  = viewModel.getMovies().getOrAwaitValue()
    assertThat(currentList).isEqualTo(movies)
}

@Test
fun updateMovies_returnsCurrentList() {
    val movies = mutableListOf<Movie>()
    movies.addAll(listOf(
        Movie(4, "overView4", "posterPath4", "date4", "title4"),
        Movie(5, "overView5", "posterPath5", "date5", "title5"),
        Movie(6, "overView6", "posterPath6", "date6", "title6"),
    ))
    val updateList  = viewModel.updateMovies().getOrAwaitValue()
    assertThat(updateList).isEqualTo(movies)
}
```
<details>
  <summary>Full-Code</summary>
  
FakeMovieRepository - 
```kotlin
package com.kmose.tmdbclient.data.repository.movie

import com.kmose.tmdbclient.domain.repository.MovieRepository
import com.kmose.tmdbclient.model.movie.Movie

class FakeMovieRepository : MovieRepository {
    private val movies = mutableListOf<Movie>()

    init {
        movies.addAll(listOf(
            Movie(1, "overView1", "posterPath1", "date1", "title1"),
            Movie(2, "overView2", "posterPath2", "date2", "title2"),
            Movie(3, "overView3", "posterPath3", "date3", "title3"),
        ))
    }

    override suspend fun getMovies(): List<Movie>? {
        return movies
    }

    override suspend fun updateMovies(): List<Movie>? {
        movies.clear()
        movies.addAll(listOf(
            Movie(4, "overView4", "posterPath4", "date4", "title4"),
            Movie(5, "overView5", "posterPath5", "date5", "title5"),
            Movie(6, "overView6", "posterPath6", "date6", "title6"),
        ))
        return movies
    }
}
```
  MovieViewModelTest -
```kotlin
package com.kmose.tmdbclient.presentation.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.kmose.tmdbclient.data.repository.movie.FakeMovieRepository
import com.kmose.tmdbclient.domain.usecase.GetMoviesUseCase
import com.kmose.tmdbclient.domain.usecase.UpdateMoviesUseCase
import com.kmose.tmdbclient.getOrAwaitValue
import com.kmose.tmdbclient.model.movie.Movie
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MovieViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MovieViewModel

    @Before
    fun setUp() {
        val fakeMovieRepository = FakeMovieRepository()
        val getMovieUseCase = GetMoviesUseCase(fakeMovieRepository)
        val updateMovieUseCase = UpdateMoviesUseCase(fakeMovieRepository)
        viewModel = MovieViewModel(getMovieUseCase, updateMovieUseCase)
    }

    @Test
    fun getMovies_returnsCurrentList() {
        val movies = mutableListOf<Movie>()
        movies.addAll(listOf(
            Movie(1, "overView1", "posterPath1", "date1", "title1"),
            Movie(2, "overView2", "posterPath2", "date2", "title2"),
            Movie(3, "overView3", "posterPath3", "date3", "title3"),
        ))

        val currentList  = viewModel.getMovies().getOrAwaitValue()
        assertThat(currentList).isEqualTo(movies)
    }

    @Test
    fun updateMovies_returnsUpdateList() {
        val movies = mutableListOf<Movie>()
        movies.addAll(listOf(
            Movie(4, "overView4", "posterPath4", "date4", "title4"),
            Movie(5, "overView5", "posterPath5", "date5", "title5"),
            Movie(6, "overView6", "posterPath6", "date6", "title6"),
        ))
        val updateList  = viewModel.updateMovies().getOrAwaitValue()
        assertThat(updateList).isEqualTo(movies)
    }
}
```  
</details>
  
### <a href="https://medium.com/androiddevelopers/unit-testing-livedata-and-other-common-observability-problems-bb477262eb04">※※ Unit-testing LiveData and other common observability problems ※※</a>
위 테스트 코드의 `getMovies_returnsCurrentList()`와 `updateMovies_returnsUpdateList()`에서 LiveDate의 값을 가져오기 위해 `getOrAwaitValue()`를 사용하고 있습니다. 위 코드에서 `viewModel.updateMovies().value`로 값을 가져오게 된다면 `null`이 출력됩니다. <br>
<img src="https://user-images.githubusercontent.com/55622345/163937669-3d016a45-c610-49aa-92ae-130a7ec32198.png" width="800px"/>


LiveData는 데이터의 변화가 감지되어야합니다. 그렇지 않으면 값이 평가될일이 없습니다. 
위 테스트 코드에서는 LiveData의 데이터 변화를 감지할 수 있는 것들이 없습니다. 그렇기 때문에 `val currentList  = viewModel.getMovies().value`나 `val updateList  = viewModel.updateMovies().value`를 집어 넣어도 항상 값이 `null`이 나오게 됩니다. 
 
 JetPack에서 LiveData 테스트를 위한 해결법을 직접적으로 제공하고있지 않아 아래와 같이 `observeForever{}` 내장 함수를 사용함으로 임시적으로 해결 가능합니다. 
```kotlin
val updateList  = viewModel.updateMovies()
updateList.observeForever{}
assertThat(updateList.value).isEqualTo(movies)
```
 
하지만 여러개의 LiveData를 테스트 코드에서 감지하기 위해서는 아래와 같은 확장함수를 추가하여 해결 가능합니다. 
```kotlin
/* Copyright 2019 Google LLC.	
   SPDX-License-Identifier: Apache-2.0 */
fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 2,
    timeUnit: TimeUnit = TimeUnit.SECONDS
): T {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : Observer<T> {
        override fun onChanged(o: T?) {
            data = o
            latch.countDown()
            this@getOrAwaitValue.removeObserver(this)
        }
    }

    this.observeForever(observer)

    // Don't wait indefinitely if the LiveData is not set.
    if (!latch.await(time, timeUnit)) {
        throw TimeoutException("LiveData value was never set.")
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}
```
LiveData에 Observer를 추가하고 값의 변화가 감지된다면 `removeObserver`로 Observer를 제거합니다. 그리고 변화된 데이터를 타입에 맞게 리턴합니다. 
  
## Dependency
ViewModel - <br>
androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.1 <br>
LiveData - <br>
androidx.lifecycle:lifecycle-livedata-ktx:2.4.1
  
**Test** <br>
- androidx.arch.core:core-testing:2.1.0
- androidx.test.ext:junit:1.1.3
- com.google.truth:truth:1.1.3
- org.mockito:mockito-core:4.4.0
- org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.1 
- org.robolectric:robolectric:4.7.3
  
**AndroidTest** <br>
- androidx.arch.core:core-testing:2.1.0
- androidx.test.ext:junit:1.1.3
- com.google.truth:truth:1.1.3
- org.jetbrains.kotlinx:kotlinx-coroutines-test:1.6.1
- androidx.test.espresso:espresso-core:3.4.0
  
## Ref.
https://developer.android.com/training/testing/fundamentals?hl=fi
https://developer.android.com/training/testing/fundamentals/test-doubles?hl=fi
https://truth.dev/
https://github.com/robolectric/robolectric
https://medium.com/androiddevelopers/unit-testing-livedata-and-other-common-observability-problems-bb477262eb04
