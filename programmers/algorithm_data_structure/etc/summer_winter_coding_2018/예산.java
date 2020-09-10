package algorithm_data_structure.etc.summer_winter_coding_2018;

import java.util.*;

public class 예산 {

    /**
     * @param d : 부서벌 요청 예산 목록
     * @param budget : 총 예산 상한선
     * @return 지원해줄 수 있는 부서의 수 최댓값
     * 
     * 작은 예산부터 순차적으로 지원해주면 최대치가 되므로
     * 정렬 후 예산을 초과할 때까지 지원해준다.
     */
    public int solution(int[] d, int budget) {
        int answer = 0;
        int[] sorted = copyAndSort(d);
        
        int sum = 0;
        for (int money : sorted) {
            if (sum + money <= budget) {
                sum += money;
                answer += 1;
            } else {
                break;
            }            
        }
        
        return answer;
    }
    
    public int[] copyAndSort(int[] origin) {
        int[] ret = new int[origin.length];
        
        for (int i = 0; i < ret.length; i++) {
            ret[i] = origin[i];
        }
        
        Arrays.sort(ret);
        
        return ret;
    } 
}