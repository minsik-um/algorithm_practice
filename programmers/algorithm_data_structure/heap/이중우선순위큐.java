package algorithm_data_structure.heap;

import java.util.PriorityQueue;

public class 이중우선순위큐 {
    
    /*
     * 매번 최소/최댓값을 빼는 하나의 힙에서 얻는 최소/최댓값은
     * 최대 힙, 최소 힙을 따로 만들어 매번 값을 빼오는 것과 동일하다.
     * 최댓값/최솟값은 각 반대 양끝점이기 때문에 영향을 주지 않는다.
     * 
     * 삽입은 둘 다, 빼기는 대응되는 힙에서만 빼고
     * 원래 하나 힙이었으면 힙이 비게 되는 순간을 잘 처리해준다. 
     * (두 개로 만들어 관리하면 비지 않는 오류가 있기 때문이다)
    */
    public int[] mySolution(String[] operations) {
        int elementCount = 0;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((n1, n2) -> n2 - n1);

        for (String operation : operations) {
            String[] splitOps = operation.split(" ");
            String method = splitOps[0];
            int value = Integer.parseInt(splitOps[1]);

            // 명령어 실행
            if (method.equals("I")) {
                minHeap.add(value);
                maxHeap.add(value);
                elementCount += 1;
            } else if (method.equals("D")) {
                if (value == 1) {
                    maxHeap.poll();
                } else if (value == -1) {
                    minHeap.poll();
                }
                elementCount = Math.max(0, elementCount - 1);
            }

            if (elementCount == 0) {
                minHeap.clear();
                maxHeap.clear();
            }
        }

        int minValue = minHeap.isEmpty() ? 0 : minHeap.poll();
        int maxValue = maxHeap.isEmpty() ? 0 : maxHeap.poll();

        return new int[] { maxValue, minValue };
    }
}