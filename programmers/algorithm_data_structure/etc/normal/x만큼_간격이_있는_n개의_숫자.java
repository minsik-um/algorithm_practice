package algorithm_data_structure.etc.normal;

public class x만큼_간격이_있는_n개의_숫자 {
    /**
     * @param x : 시작 숫자이자 간격
     * @param n : 출력할 갯수
     * @return : x부터 시작해 x씩 증가하는 숫자를 n개 지니는 리스트
     * 
     * 기초적인 패턴을 알 수 있는 문제다.
     * 특히 두번째 풀이처럼 이전 값을 활용해 지금 값을 구하는 방법은
     * 다른 문제를 접근할 때 유용하다.
     */
    public long[] solution(int x, int n) {
        long[] answer = new long[n];
        
        for (int i = 0; i < answer.length; i++) {
            answer[i] = (long)x * (i + 1);
        }
        
        return answer;
    }

    public long[] solution2(int x, int n) {
        long[] answer = new long[n];
        answer[0] = x;
        
        for (int i = 1; i < answer.length; i++) {
            answer[i] = answer[i - 1] * x;
        }
        
        return answer;
    }
}