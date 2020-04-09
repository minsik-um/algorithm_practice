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