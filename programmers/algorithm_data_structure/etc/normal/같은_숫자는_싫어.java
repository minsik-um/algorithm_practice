package algorithm_data_structure.etc.normal;

import java.util.ArrayList;
import java.util.List;

public class 같은_숫자는_싫어 {
    /**
     * @param arr : 일련의 숫자 리스트
     * @return 연속된 수는 제외하고 순서는 유지한 숫자 리스트
     * 
     * 문제 그대로 매번 마지막 숫자와 비교해가며 숫자를 추가해간다.
     */
    public int[] solution(int[] arr) {
        List<Integer> distinct = new ArrayList<>();
        
        for (int num : arr) {
            int lastOne = distinct.get(distinct.size() - 1);
            if (lastOne != num) {
                distinct.add(num);                   
            }
        }

        int[] answer = new int[distinct.size()];

        for (int i = 0; i < distinct.size(); i++) {
            answer[i] = distinct.get(i);
        }

        return answer;
    }
}