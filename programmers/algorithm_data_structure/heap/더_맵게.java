package algorithm_data_structure.heap;

import java.util.*;

class 더_맵게 {
    public int mySolution(int[] scoville, int K) {
        int initialSize = scoville.length;
        int answer = 0;
        
        // heap 생성
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        for (int value : scoville){
            heap.add(value);
        }

        // 스코빌 지수가 K보다 같거나 클 때까지 합침
        while (heap.size() >= 2 && heap.peek() < K){
            int first = heap.poll();
            int second = heap.poll();

            int newScoville = first + (second * 2);
            heap.add(newScoville);
        }

        // 스코빌 지수가 제대로 올랐는지 확인
        if (heap.peek() < K){
            answer = -1;
        }else{
            answer = initialSize - heap.size();
        }
        
        return answer;
    }
}