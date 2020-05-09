package algorithm_data_structure.brute_force_search;

import java.util.LinkedList;
import java.util.List;

public class 모의고사 {

    /*
     * 패턴을 cycle 순환할 때 나머지 연산을 활용
     */
    public int[] solution(int[] answers) {
        int[] correctCount = {0, 0, 0};
        int[][] patterns = {{1, 2, 3, 4, 5},
                            {2, 1, 2, 3, 2, 4, 2, 5},
                            {3, 3, 1, 1, 2, 2, 4, 4, 5, 5}};
        
        // 채점
        for (int person = 0; person < 3; person++){
            int[] pattern = patterns[person];

            for (int idx = 0; idx < answers.length; idx++) {
                correctCount[person] += (pattern[idx % pattern.length] == answers[idx]) ? 1 : 0;
            }
        }

        // 가장 점수 높은 그룹 추출
        int maxCorrect = Math.max(Math.max(correctCount[0], correctCount[1]), correctCount[2]);
        List<Integer> result = new LinkedList<>();
        for(int i = 0; i < correctCount.length; i++){
            if (correctCount[i] == maxCorrect){
                result.add(i+1);
            }
        }

        // 결과 타입을 맞추기 위해 형변환
        // 여기서 stream api를 쓰면 한 줄로 바꿀 수 있지만 실행속도가 갑자기 증가하므로 조심
        int[] answer = new int[result.size()];
        for(int i = 0; i < result.size(); i++){
            answer[i] = result.get(i);
        }
        return answer;
    }
}