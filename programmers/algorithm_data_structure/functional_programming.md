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
