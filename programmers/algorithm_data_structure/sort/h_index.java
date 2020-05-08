package algorithm_data_structure.sort;

import java.util.Arrays;

public class h_index {

    /*
     * h-index가 최댓값이려면 
     * h-index는 아무리 해도 최대 원소 길이를 넘을 수 없다
     * (인용 횟수가 h-index이 넘는 논문이 h-index 갯수 이상 있어야 한다)
     * 그러니까 최대 인용 수부터 하나씩 줄여가며
     * 유효할 때까지 확인하는 것이다.
     * 
     * 아래 알고리즘과 차이점은,
     * 오른쪽 구간에서 출발해 이동하다가 유효하면 바로 나간다
     */
    public int mySolution(int[] citations) {
        int answer = 0;        
        int last = -1;
        int h_index = citations.length;
        var copiedCitations = Arrays.stream(citations).sorted().toArray();

        for (int curr : copiedCitations){
            if (curr >= h_index && last <= h_index){
                answer = h_index;
                break;
            }else{
                last = curr;
                h_index -= 1;
            }
        }

        return answer;
    }

    /*
     * 특정 구간에서 유효한 h-index
     * = 특정 논문 기준에서 그 인용 수 이상 논문 갯수를 어떻게 구할까?
     * 
     * 6 5 3 1 0  (논문 인용 수 정렬)
     * 1 2 3 4 5  (각 논문 기준에서 그 인용 수 이상 논문 갯수)
     * 
     * 보면 3 (정답) 기준으로 왼쪽 구간에선
     * h-index가 그 인용 수 논문 갯수이며
     * 오른쪽 구간에선 인용 수가 h-index이다.
     * 
     * 결국 2개 중 최솟값이 h-index가 되며, 가능한 경우의 수가 여러가지일 때
     * 최댓값을 고른다.
     */
    public int bsetSolution(int[] citations) {
        int answer = 0;
        var copiedCitations = Arrays.stream(citations).sorted().toArray();

        for(int i = 0; i < citations.length; i++){
            int h_index = Math.min(copiedCitations[i], citations.length - i);
            answer = Math.max(answer, h_index);
        }
        
        return answer;
    }
}