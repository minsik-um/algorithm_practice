import java.util.*;
import static java.util.stream.Collectors.*;

public class 위장 {

    public int my_solution(String[][] clothes) {
        /*
        Python의 Counter 기능을 구현한 셈이다.
        각 아이템의 개수를 세고 (그 종류 사용 안하는 경우)를 포함해 곱해주면
        가능한 경우의 수가 나온다. 이때 아무것도 안하는 경우를 빼준다.
        */
        int answer = 1;
        HashMap<String, ArrayList<String>> clothes_map = new HashMap<>();
        for (String[] cloth : clothes){
            if (!clothes_map.containsKey(cloth[1])){
                clothes_map.put(cloth[1], new ArrayList<>());                
            }
            clothes_map.get(cloth[1]).add(cloth[0]);
        }
        Set<Map.Entry<String, ArrayList<String>>> entries = clothes_map.entrySet();
        for (Map.Entry<String, ArrayList<String>> entry : entries){
            
            answer *= (entry.getValue().size() + 1);
        }
        answer -= 1;
        return answer;
    }

    /*
    출처: https://swiftymind.tistory.com/108
    함수형 프로그래밍
    부수 효과를 없애고 순수 함수를 만들어 모듈화 수준을 높이는 프로그래밍 패러다임

    부수 효과 : 주어진 값 이외의 외부 변수 및 프로그램 실행에 영향을 끼치는 것을 말함
    순수 함수 : 모든 입력이 입력으로만, 모든 출력이 출력으로만 사용
    비순수 함수  : 숨겨진 입력이나 출력이 존재

    함수형 프로그래밍은 순수 함수를 지향, 최대한 숨겨진 입력과 출력을 제거하여
    가능한 코드를 입력과 출력의 관계로 사용

    객체 지향 프로그래밍은 명령형 프로그래밍이다. 데이터를 '어떻게' 처리할지
    명령을 통해 풀어나갔다면, 함수형 프로그래밍은 선언적 함수를 통해
    '무엇을' 풀어나갈지 결정한다.

    List<String> myList =
                Arrays.asList("c1", "a2", "b3", "4", "5");
 
    // 기존방식
    for(int i=0; i<myList.size(); i++){
        String s = myList.get(i);
        if(s.startsWith("c")){
            System.out.println(s.toUpperCase());
        }
    }

    // stream API를 이용한 방식
    myList.stream()
            .filter(s -> s.startsWith("c"))
            .map(String::toUpperCase)
            .forEach(System.out::println);
 
    함수형 프로그래밍에서 함수는 1급 객체여야 한다.
    특정 언어의 일급객체(first-class citizens, entity)는
    다른 객체들에 적용 가능한 연산을 모두 지원하는 객체다.

    - 변수나 데이터 구조 안에 담을 수 있다.
    - 파라미터로 전달할 수 있다.
    - 반환값(return value)으로 사용할 수 있다.
    - 할당에 사용된 이름과 관계없이 고유한 구별이 가능하다.
    - 동적으로 프로퍼티 할당이 가능하다.
    - 기존 데이터의 불변성(Immutable)
    */

    public int best_solution(String[][] clothes) {
        /*
        JAVA 8부터 추가된 함수형 프로그래밍(Stream API) 적용
        참고: https://swiftymind.tistory.com/108
        */
        return Arrays.stream(clothes)
                .collect(groupingBy(p -> p[1], mapping(p -> p[0], counting())))
                .values()
                .stream()
                .collect(reducing(1L, (x, y) -> x * (y + 1))).intValue() - 1;
    }
}