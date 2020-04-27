package algorithm_data_structure.stack_queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 기능개발 {

    public int[] mySolution(int[] progresses, int[] speeds) {
        List<Integer> answer = new ArrayList<>();
      
        int currTurn = 0;
        for (int i = 0; i < progresses.length; i++){
            int days = (int) Math.ceil((100.0 - progresses[i]) / speeds[i]);

            if (days <= currTurn){
                answer.set(answer.size()-1, answer.get(answer.size()-1)+1);                
            }else{
                answer.add(1);
                currTurn = days;
            }
        }

        return answer.stream().mapToInt(i -> i).toArray();
    }

    /*
     * 매번 완료일자를 찾아 그 index에 1을 더한다.
     * 반환할 때 배열 내 0이 아닌 index의 값을 반환한다.
     * 
     * 좋은 코드라기보단, 이렇게 풀 수 있다 정도로 이해
     */
    public int[] bestSolution(int[] progresses, int[] speeds){
        int[] dayOfEnd = new int[100];
        int currTurn = 0;
        for(int i = 0; i < progresses.length; i++){
            int days = (int) Math.ceil((100.0 - progresses[i]) / speeds[i]);
            if (days > currTurn){
                currTurn = days;
            }
            dayOfEnd[currTurn-1] += 1;
        }

        return Arrays.stream(dayOfEnd).filter(i -> i != 0).toArray();
    }
}