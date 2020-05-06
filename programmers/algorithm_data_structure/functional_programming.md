# 함수형 프로그래밍
## Java 8 - streamAPI
_출처: https://swiftymind.tistory.com/108_

부수 효과를 없애고 순수 함수를 만들어 모듈화 수준을 높이는 프로그래밍 패러다임

- 부수 효과 : 주어진 값 이외의 외부 변수 및 프로그램 실행에 영향을 끼치는 것
- 순수 함수 : 모든 입력이 입력으로만, 모든 출력이 출력으로만 사용
- 비순수 함수 : 숨겨진 입력이나 출력이 존재

함수형 프로그래밍은 순수 함수를 지향, 최대한 숨겨진 입력과 출력을 제거하여 가능한 코드를 입력과 출력의 관계로 사용

객체 지향 프로그래밍은 명령형 프로그래밍이다. 데이터를 '어떻게' 처리할지 명령을 통해 풀어나갔다면, 함수형 프로그래밍은 선언적 함수를 통해 '무엇을' 풀어나갈지 결정한다.


```
List<String> myList =
            Arrays.asList("c1", "a2", "b3", "4", "5");
```

기존방식
```java
for(int i=0; i<myList.size(); i++){
    String s = myList.get(i);
    if(s.startsWith("c")){
        System.out.println(s.toUpperCase());
    }
}
```

stream API를 이용한 방식
```Java
myList.stream()
        .filter(s -> s.startsWith("c"))
        .map(String::toUpperCase)
        .forEach(System.out::println);
```

함수형 프로그래밍에서 함수는 1급 객체여야 한다. 특정 언어의 일급객체(first-class citizens, entity)는 다른 객체들에 적용 가능한 연산을 모두 지원하는 객체다.

- 변수나 데이터 구조 안에 담을 수 있다.
- 파라미터로 전달할 수 있다.
- 반환값(return value)으로 사용할 수 있다.
- 할당에 사용된 이름과 관계없이 고유한 구별이 가능하다.
- 동적으로 프로퍼티 할당이 가능하다.
- 기존 데이터의 불변성(Immutable)


베스트앨범.java
```java
/*
mapToObj: 원소 하나하나를 다른 걸로 바꿈
collect : reduce나 groupingBy 같이 집약적인 함수를 처리
groupingBy() 지정한 값을 기준으로 값을 모은다
sorted() : 지정한 비교함수로 처리
flatMap : 지정한 함수대로 펼쳐서 한 줄로 나열함
mapToInt : 지정한 수로 변경

여기서 sum()은 사용자가 외부에 직접 정의한 함수
*/
IntStream.range(0, genres.length)
        .mapToObj(i -> new Music(genres[i], plays[i], i))
        .collect(Collectors.groupingBy(Music::getGenre))
        .entrySet().stream()
        .sorted((a, b) -> sum(b.getValue()) - sum(a.getValue()))
        .flatMap(x->x.getValue().stream().sorted().limit(2))
        .mapToInt(x->x.id).toArray();
```

위장.java
```java
Arrays.stream(clothes)
    .collect(groupingBy(p -> p[1], mapping(p -> p[0], counting())))
    .values().stream()
    .collect(reducing(1L, (x, y) -> x * (y + 1))).intValue() - 1;
```

https://stackify.com/streams-guide-java-8/

```java
// 모든 element에 함수 적용
// 스트림이 끝나 더이상 이용 불가능하다(terminal operation)
// empList의 값이 바뀌어 있음
empList.stream().forEach(e -> e.salaryIncrement(10.0));

// 모든 element에 함수 적용
// 새로운 스트림을 만들어 반환(다른 타입 가능)
// 뒤에서 스트림에 다른 함수 적용 가능
List<Employee> employees = Stream.of(empIds)
      .map(employeeRepository::findById)
      .collect(Collectors.toList());

// 스트림에서 데이터를 얻어 반환
List<Employee> employees = empList.stream().collect(Collectors.toList());

// test를 통과하는 애들로만 새로운 스트림 생성
List<Employee> employees = Stream.of(empIds)
      .map(employeeRepository::findById)
      .filter(e -> e != null)
      .filter(e -> e.getSalary() > 200000)
      .collect(Collectors.toList());

// 처음으로 조건을 만족하는 애를 반환
// 아무겂도 없으면 null을 반환
Employee employee = Stream.of(empIds)
      .map(employeeRepository::findById)
      .filter(e -> e != null)
      .filter(e -> e.getSalary() > 100000)
      .findFirst()
      .orElse(null);

// array로 데이터를 뺀다면 collect 대신 간단하게 사용
// 아래 코드는 빈 배열 생성하고 스트림의 엘리먼트를 넣는다
// 참고로 ::는 클래스명::메소드명으로 메소드를 지정할 때 쓴다
Employee[] employees = empList.stream().toArray(Employee[]::new);

// 스트림은 아닌데 asList를 이용하면 배열로부터 리스트를 쉽게 생성할 수 있다
List<List<String>> namesNested = Arrays.asList( 
      Arrays.asList("Jeff", "Bezos"), 
      Arrays.asList("Bill", "Gates"), 
      Arrays.asList("Mark", "Zuckerberg"));

// 복잡한 데이터 구조를 flatten 함
// 예시에선 <List<List<String>>> 을 List<String>으로 바꿈
List<String> namesFlatStream = namesNested.stream()
      .flatMap(Collection::stream)
      .collect(Collectors.toList());

// 여러 개의 오퍼레이션을 각 엘리먼트에 제공하고 싶을 때
// peek 은 intermediate operation이다.
// forEach와 collect는 terminal operation에 속한다.
empList.stream()
      .peek(e -> e.salaryIncrement(10.0))
      .peek(System.out::println)
      .collect(Collectors.toList());
```

살펴보았듯이 stream operation은 intermediate operation과 terminal operation으로 나뉜다. `filter()`와 같은 intermediate operation은 프로세싱 이후 새로운 stream을 반환한다. `forEach()` 같은 terminal operation은 생산하지 않고 스트림을 소비한다.

stream pipeline은 스트림 소스(stream source)로 구성되어 있으며, 0이나 더 여러 intermediate operation과 1개의 terminal operation이 따라온다.

아래 예시는 empList라는 source, `filter`와 `count`라는 operation이 따라온다.

```java
Long empCount = empList.stream()
      .filter(e -> e.getSalary() > 200000)
      .count();
```

short-circuiting operation은 무한의 stream을 유한된 시간 내에 처리하도록 해준다.

```java
 Stream<Integer> infiniteStream = Stream.iterate(2, i -> i * 2);

    List<Integer> collect = infiniteStream
      .skip(3)  // 처음 3개 엘리먼트를 스킵한다
      .limit(5)  // 5개의 엘리먼트까지 수행한다
      .collect(Collectors.toList());

    // (2, 4, 8 생략)  16, 32, 64, 128, 256 (이후 중단ㄴ)
```

### Lazy Evaluation
stream에서는 lazy evaluation를 이용해 의미있는 연산을 할 수 있다.

source data를 처리하기 시작하는 건 terminal operation이 시작된 이후다. source의 요소(element)들은 필요할 때만 소비(consume)된다.

모든 intermediate 연산들은 lazy operation이다. 그들은 프로세싱의 결과가 필요할 때까지 수행되지 않는다.

예를 들어 `map` 연산은 얼마나 수행될까? input이 4개니까 4번 수행될까?

```java
Integer[] empIds = { 1, 2, 3, 4 };
    
Employee employee = Stream.of(empIds)
    .map(employeeRepository::findById)
    .filter(e -> e != null)
    .filter(e -> e.getSalary() > 100000)
    .findFirst()
    .orElse(null);
```

stream은 `map`과 2개의 `filter` 연산을 한 요소(element)에 동시에 수행한다. 

처음에는 모든 연산을 id 1에 적용한다. id 1의 salary는 100000보다 크지 않기에, 프로세싱은 다음 연산으로 이동한다. 그러나 ID 2에선 filter 2 조건을 만족하므로 terminal operation `fineFirst()`가 호출되어 결과를 반환한다. id 3, 4에 대해선 수행하지 않는다.

### stream 연산에 기반한 비교 연산
#### Sorted
```java
/* string을 sort */
List<Employee> employees = empList.stream()
      .sorted((e1, e2) -> e1.getName().compareTo(e2.getName()))
      .collect(Collectors.toList());
```

`sorted()`는 short-circuiting이 아니다. 이후에 `findFirst()`가 있다고 해도 모든 element에 대해 sort 연산을 수행한다. 

#### min, max
`findFirst()` 처럼 optional operation이다. 만약 값이 존재하지 않으면 대신 반환할 값을 지정해준다.

```java
 Employee firstEmp = empList.stream()
      .min((e1, e2) -> e1.getId() - e2.getId())
      .orElseThrow(NoSuchElementException::new);
```

`Comparator.comparing()`을 이용하여 comparison logic을 숨길 수 있다.

```java
Employee maxSalEmp = empList.stream()
      .max(Comparator.comparing(Employee::getSalary))
      .orElseThrow(NoSuchElementException::new);
```

#### distinct
중복되는 값을 모두 제거해 반환한다.

```java
List<Integer> distinctIntList = intList.stream().distinct().collect(Collectors.toList());
```

#### allMatch, anyMatch, noneMatch
boolean 값을 반환하는 연산이다. short-circuiting이 적용되어 값이 결정되면 연산을 멈춘다.

```java
boolean allEven = intList.stream().allMatch(i -> i % 2 == 0);
boolean oneEven = intList.stream().anyMatch(i -> i % 2 == 0);
boolean noneMultipleOfThree = intList.stream().noneMatch(i -> i % 3 == 0);
```

### 특화된 stream
`Stream`의 특화형으로 `IntStream`, `LongStream`, `DoubleStream` 등이 있다. 숫자 원시값(primitives, int, long, double 같은 거)을 많이 처리할 때 유용하다.

위의 것들은 Stream을 상속받는 게 아니라 `BaseStream`을 상속받는다. BaseStream은 Stream이 상속받은 것이다.

stream의 모든 연산이 특화된 stream에 지원되진 않는다. 예를 들어 min(), max()에서 comparator를 받지 않고 수행한다.

#### 생성
아래 2가지 방법으로 IntStream을 생성한다.

```java
Integer latestEmpId = empList.stream()
      .mapToInt(Employee::getId)
      .max()
      .orElseThrow(NoSuchElementException::new);

IntStream.of(1, 2, 3);
IntStream.range(10, 20);  // inclusive, exclusive
```

아래 코드들은 IntStream이 아니라 `Stream<Integer>`를 반환한다.

```java
Stream.of(1, 2, 3);
empList.stream().map(Employee::getId);
```

#### 연산
```java
Double avgSal = empList.stream()
      .mapToDouble(Employee::getSalary)
      .average()
      .orElseThrow(NoSuchElementException::new);
```

### reduce 연산
`DoubleStream.sum()`을 아래처럼 효과적으로 구현한다.

```java
Double sumSal = empList.stream()
    .map(Employee::getSalary)
    .reduce(0.0, Double::sum);

```

### collect 활용
`Collectors.toList()` 이외에 element로 collect 하는 방법을 알아보자
```java
String empNames = empList.stream()
      .map(Employee::getName)
      .collect(Collectors.joining(", "))
      .toString();

Set<String> empNames = empList.stream()
            .map(Employee::getName)
            .collect(Collectors.toSet());

// Supplier<Colection>
Vector<String> empNames = empList.stream()
            .map(Employee::getName)
            .collect(Collectors.toCollection(Vector::new));

// 통계 정보를 담은 객체를 반환
DoubleSummaryStatistics stats = empList.stream()
      .collect(Collectors.summarizingDouble(Employee::getSalary));

DoubleSummaryStatistics stats = empList.stream()
      .mapToDouble(Employee::getSalary)
      .summaryStatistics();

assertEquals(stats.getCount(), 3);
assertEquals(stats.getSum(), 600000.0, 0);
assertEquals(stats.getMin(), 100000.0, 0);
assertEquals(stats.getMax(), 300000.0, 0);
assertEquals(stats.getAverage(), 200000.0, 0);

// 파티션 키를 만드는 함수를 제공하여 파티셔닝을 한다.
// 2개로만 나눌 수 있다.
List<Integer> intList = Arrays.asList(2, 4, 5, 6, 8);
Map<Boolean, List<Integer>> isEven = intList.stream().collect(
    Collectors.partitioningBy(i -> i % 2 == 0));

assertEquals(isEven.get(true).size(), 4);
assertEquals(isEven.get(false).size(), 1);

// groupBy는 advanced partitioning이다. 
// 2개 이상의 그룹으로 나눌 수 있다.
Map<Character, List<Employee>> groupByAlphabet = empList.stream().collect(
      Collectors.groupingBy(e -> new Character(e.getName().charAt(0))));

assertEquals(groupByAlphabet.get('B').get(0).getName(), "Bill Gates");
assertEquals(groupByAlphabet.get('J').get(0).getName(), "Jeff Bezos");
assertEquals(groupByAlphabet.get('M').get(0).getName(), "Mark Zuckerberg");

// 위의 결과는 input 타입과 output 타입이 일치한다.
// 아래 방법은 다른 타입으로 map에 저장하는 방법이다.
Map<Character, List<Integer>> idGroupedByAlphabet = empList.stream().collect(
    Collectors.groupingBy(
            e -> new Character(e.getName().charAt(0)),
            Collectors.mapping(Employee::getId, Collectors.toList())
        )
    );

// reduce()와 reducing()은 유사하게 작동한다.
Double percentage = 10.0;
Double salIncrOverhead = empList.stream().collect(Collectors.reducing(
    0.0, e -> e.getSalary() * percentage / 100, (s1, s2) -> s1 + s2));

// reducing()은 groupBy(), partitioningBy()와 함께 쓰일 때 여러 단계의 reduction을 사용하기 적합하다. 간단한 reduction은 reduce()를 사용한다.
// 아래 예제는 가장 긴 이름을 value로 저장한다.
Comparator<Employee> byNameLength = Comparator.comparing(Employee::getName);
    
Map<Character, Optional<Employee>> longestNameByAlphabet = empList.stream().collect(
    Collectors.groupingBy(e -> new Character(e.getName().charAt(0)),
    Collectors.reducing(BinaryOperator.maxBy(byNameLength))));

assertEquals(longestNameByAlphabet.get('B').get().getName(), "Bill Gates");
assertEquals(longestNameByAlphabet.get('J').get().getName(), "Jeff Bezos");
assertEquals(longestNameByAlphabet.get('M').get().getName(), "Mark Zuckerberg");
```

#### Parallel Streams
병행 처리?를 할 수 있다. 아마 성능 향상과 관련있어 보인다.

```java
Employee[] arrayOfEmps = {
    new Employee(1, "Jeff Bezos", 100000.0), 
    new Employee(2, "Bill Gates", 200000.0), 
    new Employee(3, "Mark Zuckerberg", 300000.0)
};

List<Employee> empList = Arrays.asList(arrayOfEmps);

empList.stream().parallel().forEach(e -> e.salaryIncrement(10.0));

assertThat(empList, contains(
    hasProperty("salary", equalTo(110000.0)),
    hasProperty("salary", equalTo(220000.0)),
    hasProperty("salary", equalTo(330000.0))
));
```

parallel stream을 이용하여 multi-threaded code를 작성하려면 아래 사항을 유념해야 한다.

1. thread-safe가 보장되어야 한다. 공유된 데이터를 사용할 때 주의를 기울여야 한다.
2. 다음 순서에 영향을 미칠 경우 사용하면 안된다. 예를 들어 findFirst()를 하려면 병행처리를 할 때 다른 결과를 낼 수 있다.
3. [JAVA 프로그래밍 튜닝 팁](https://stackify.com/java-performance-tuning/)를 보며 이해를 하자.

### 무한한 stream
```java
// random
Stream.generate(Math::random)
      .limit(5)
      .forEach(System.out::println);

// 2의 배수
Stream<Integer> evenNumStream = Stream.iterate(2, i -> i * 2);

List<Integer> collect = evenNumStream
    .limit(5)
    .collect(Collectors.toList());
```

### file operations
#### write
```java
String[] words = {
    "hello", 
    "refer",
    "world",
    "level"
};

try (PrintWriter pw = new PrintWriter(
    Files.newBufferedWriter(Paths.get(fileName)))) {
    Stream.of(words).forEach(pw::println);
}
```

#### read
```java
private List<String> getPalindrome(Stream<String> stream, int length) {
    return stream.filter(s -> s.length() == length)
        .filter(s -> s.compareToIgnoreCase(
        new StringBuilder(s).reverse().toString()) == 0)
        .collect(Collectors.toList());
}

@Test
public void whenFileToStream_thenGetStream() throws IOException {
    List<String> str = getPalindrome(Files.lines(Paths.get(fileName)), 5);
    assertThat(str, contains("refer", "level"));
}
```

### java 9
위에선 java 8에서 이용 가능한 기능들이다. api 9에서 더욱 강화했다.

#### takeWhile
`takeWhile`은 while 조건이 true인 동안만 stream의 element를 받는다(take). false가 생기면 흐름을 끊고 반환한다.

```java
Stream.iterate(1, i -> i + 1)
                .takeWhile(n -> n <= 10)
                .map(x -> x * x)
                .forEach(System.out::println);
```

filter를 사용하면 비슷하나 filter는 끊기지 않고 element 끝까지 본다.

```java
Stream.iterate(1, i -> i + 1)
                .filter(x -> x <= 10)
                .map(x -> x * x)
                .forEach(System.out::println);

Stream.of(1,2,3,4,5,6,7,8,9,0,9,8,7,6,5,4,3,2,1,0)
                .takeWhile(x -> x <= 5)
                .forEach(System.out::println);

Stream.of(1,2,3,4,5,6,7,8,9,0,9,8,7,6,5,4,3,2,1,0)
                .filter(x -> x <= 5)
                .forEach(System.out::println);
```

결과는 아래와 같은 차이가 있다.

```java
// takeWhile
1
2
3
4
5

// filter
1
2
3
4
5
0
5
4
3
2
1
0
```

#### dropWhile
takeWhile과 달리 true인 동안 값을 버리고 나머지 stream을 반환한다.

```java
Stream.of(1,2,3,4,5,6,7,8,9,0,9,8,7,6,5,4,3,2,1,0)
        .dropWhile(x -> x <= 5)
        .forEach(System.out::println);

// 결과
6
7
8
9
0
9
8
7
6
5
4
3
2
1
0
```

#### iterate
java 8에 있던 iterate는 quit 조건을 적지 못하는 단점이 있었다. java 9에서는 새로운 파라미터를 추가한다. takeWhile과 유사하다.

```java
Stream.
	iterate(1, i -> i < 256, i -> i * 2)
    .forEach(System.out::println);
```

위의 코드는 아래 for문과 동일하다.

```java
for (int i = 1; i < 256; i*=2) {
	System.out.println(i);
}
```

#### ofNullable
null pointer exception을 피하기 위해 새롭게 추가되었다.

number가 외부의 신뢰가 보장되지 않은 곳에서 받았다고 하자. null이 들어올 수 있으며 null element의 stream을 생성하는 대신 empty stream을 만들고 싶다. 우선 null check는 아래와 같이 할 수 있다.

```java
Stream<Integer> result = number != null
        ? Stream.of(number)
        : Stream.empty();
```

위 코드는 아래처럼 간단하게 바꿀 수 있다. null이 들어오면 empty Optionals 를 반환하여 runtime error를 방지한다.

```java
Stream<Integer> result = Stream.ofNullable(number);
```

github에서 소스를 보며 공부하자.