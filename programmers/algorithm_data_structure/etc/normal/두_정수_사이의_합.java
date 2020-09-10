package algorithm_data_structure.etc.normal;

public class 두_정수_사이의_합 {

    /**
     * @param a 숫자1
     * @param b 숫자2
     * @return 두 정수 사이의 합 구하기
     */
    public long solution(int a, int b) {
        long answer = 0;
        int min = Math.min(a, b);
        int max = Math.max(a, b);
        
        for (int i = min; i <= max; i++) {
            answer += i;
        }
        
        return answer;
    }

    /**
     * 등차수열의 합을 계산하면 된다.
     * 이때 return 계산 과정에서 데이터가 손실되지 않도록
     * long 타입으로 계산하자.
     * https://mathbang.net/609
     */
    public long bestSolution(int a, int b) {
        long answer = 0;
        long min = Math.min(a, b);
        long max = Math.max(a, b);
        long len = Math.abs(max - min) + 1;

        return len * (min + max) / 2;
    }
}