package algorithm_data_structure.etc.kakao_blind_recruitment_2018;

import java.util.Stack;

public class 다트게임 {

    /**
     * @param dartResult : 일정한 규칙에 의해 기록된 결과
     * @return 총 점수의 합
     * 
     * 스택을 활용하는 점과
     * 매번 한 문자씩 읽어들여 파싱하는 이런 흐름을 기억하자.
     */
    public int bestSolution(String dartResult) {
        int answer = 0;
        Stack<Integer> scores = new Stack<>();

        for (int i = 0; i < dartResult.length(); i++) {
            char chr = dartResult.charAt(i);

            if (Character.isDigit(chr)) {
                if (chr == '1' && dartResult.charAt(i + 1) == '0') {
                    scores.add(10);
                    i += 1;
                } else {
                    scores.add(chr - '0');
                }
            } else {
                int currScore = scores.pop();
                if (chr == 'D') {
                    currScore = (int) Math.pow(currScore, 2);
                } else if (chr == 'T') {
                    currScore = (int) Math.pow(currScore, 3);
                } else if (chr == '*') {
                    currScore *= 2;
                    if (!scores.isEmpty()) {
                        int prevScore = scores.pop();
                        scores.add(prevScore * 2);
                    }
                } else if (chr == '#') {
                    currScore *= (-1);
                }

                scores.add(currScore);
            }
        }

        while (!scores.isEmpty()) {
            answer += scores.pop();
        }

        return answer;
    }
}