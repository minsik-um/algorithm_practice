package algorithm_data_structure.etc.normal;

import java.util.ArrayList;
import java.util.List;

public class 나누어_떨어지는_숫자_배열 {

    /**
     * @param arr 숫자 배열
     * @param divisor 나눌 수
     * @return arr의 원소 중에서 divisor로 나눠 떨어지는 숫자들의 정렬 목록
     */
    public int[] solution(int[] arr, int divisor) {
        int[] answer = {-1};
        List<Integer> targets = new ArrayList<>();
        
        for(int num : arr) {
            if (num % divisor == 0) {
                targets.add(num);
            }
        }
        
        targets.sort((n1, n2) -> n1 - n2);
        
        if (!targets.isEmpty()) {
            answer = new int[targets.size()];
            for (int i = 0; i < answer.length; i++) {
                answer[i] = targets.get(i);
            }
        } 

        return answer;
    }
}