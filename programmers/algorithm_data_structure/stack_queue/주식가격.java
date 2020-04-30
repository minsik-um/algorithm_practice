package algorithm_data_structure.stack_queue;

import java.util.*;

class 주식가격 {
    /*
     * beginIdxs : 아직 처리되지 않은(주식 가격 떨어지는 시점을 못찾은) 시점(index)
     * 
     * stack에 시점과 주식 가격을 같이 저장하는 방법을 처음에 썼지만
     * => idx만 알면 prices에서 조회하여 가격을 알 수 있어 idx만 저장하도록 수정
     */
    public int[] mySolution(int[] prices) {
        int[] answer = new int[prices.length];
        Stack<Integer> beginIdxs = new Stack<>();
        
        // 주식가격을 보면서 가격이 떨어지는 시점 찾기
        for (int currIdx = 0; currIdx < prices.length; currIdx++){
            while (!beginIdxs.isEmpty() && prices[beginIdxs.peek()] > prices[currIdx]){
                int popidx = beginIdxs.pop();

                answer[popidx] += currIdx - popidx;
            }
             
            beginIdxs.add(currIdx);                          
        }

        // 스택에 남은 값들 처리(가격이 끝까지 떨어지지 않음)
        while (! beginIdxs.isEmpty()){
            int popidx = beginIdxs.pop();
            answer[popidx] += ((prices.length -1) - popidx);
        }

        return answer;
    }
}