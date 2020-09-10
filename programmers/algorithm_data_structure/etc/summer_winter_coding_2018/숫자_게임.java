package algorithm_data_structure.etc.summer_winter_coding_2018;

import java.util.Arrays;

public class 숫자_게임 {
    /**
     * @param A : A팀이 가진 숫자들
     * @param B : B팀이 가진 숫자들
     * @return B팀이 게임에서 얻을 수 있는 최대 스코어
     * 
     * 두 팀을 정렬하여 서로 큰 숫자끼리 비교한다.
     * B가 더 크면 그대로 채가면 되고,
     * A가 더 크거나 같으면 B의 최솟값과 대응하면 된다.
     */
    public int solution(int[] A, int[] B) {
        int answer = 0;
        int[] sortedA = copyAndSort(A);
        int[] sortedB = copyAndSort(B);
        int bStart = 0;
        
        for (int a : sortedA) {
            if (a < sortedB[bStart]) {
                answer += 1;
                bStart += 1;
            }
        }

        return answer;
    }
    
    public int[] copyAndSort(int[] origin) {
        int[] copy = new int[origin.length];
        
        for (int i = 0; i < origin.length; i++) {
            copy[i] = origin[i];
        }
        Arrays.sort(copy);
        
        return copy;
    }
}