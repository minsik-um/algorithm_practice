package algorithm_data_structure.heap;

import java.util.*;

class 디스크_콘트롤러 {
    public int solution(int[][] jobs) {
        /*
         * 0. 가장 먼저 들어오는 작업(시각이 같으면 처리 시간이 짧은 작업) 순으로 jobs를 정렬
         *    (문제에서 jobs 데이터가 먼저 들어오는 순으로 정렬되었다는 보장이 없음)
         * 1. 밀린 작업이 없다면 가장 먼저 들어오는 작업(시각이 같으면 처리 시간이 작은 것)을 처리
         * 2. 밀린 작업이 있다면 그중 작업 시간이 가장 짧은 것 처리
         * 
         * 작업 시간이 가장 짧아야 남은 작업들의 대기 시간이 최소화된다.
         */
        int timeSum = 0;
        int currTime = 0;
        PriorityQueue<int[]> sortedJobs = new PriorityQueue<>(
            (a1, a2) -> (a1[0] != a2[0]) ? (a1[0] - a2[0]) : (a1[1] - a2[1])
        );
        PriorityQueue<int[]> delayedJobs = new PriorityQueue<>((a1, a2) -> a1[1] - a2[1]);

        for (int[] job : jobs){
            sortedJobs.add(job);
        }

        while (!delayedJobs.isEmpty() || !sortedJobs.isEmpty()){
            int[] job = delayedJobs.isEmpty() ? sortedJobs.poll() : delayedJobs.poll();
            int requestTime = job[0];
            int processingTime = job[1];

            currTime += (Math.max(requestTime - currTime, 0) + processingTime);
            timeSum += (currTime - requestTime);

            while (!sortedJobs.isEmpty() && sortedJobs.peek()[0] < currTime) {
                delayedJobs.add(sortedJobs.poll());
            }
        }

        return (int) (1.0 * timeSum / jobs.length);
    }
}