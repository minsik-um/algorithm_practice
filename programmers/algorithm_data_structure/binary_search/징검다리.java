package algorithm_data_structure.binary_search;

import java.util.ArrayList;
import java.util.List;

public class 징검다리 {
    /**
     * @param distance : 건너야 하는 두 지점 간 거리
     * @param rocks    : 두 지점 사이에 있는 바위
     * @param n        : 제거할 바위 수
     * @return         : 바위를 제거하는 모든 경우의 수의 
     *                   간격 최솟값들 중 최댓값
     * 
     * 최솟값들 중 최댓값이 나오는 바위 제거의 경우는 무엇일까?
     * 가장 짧은 간격을 매번 제거하도록 바위를 제거해야 한다.
     * 
     * 결국 간격의 최솟값이 매번 갱신되며 상승한다.
     * 그 최솟값을 기준으로 이분탐색을 수행한다.
     * 
     * 간격의 최솟값을 특정값 K로 지정하면
     * 인접한 간격의 합으로 정의되는 결과 간격(바위 제거 후)들은
     * 모두 K보다 같거나 커지는 그 경계값 만큼 간격들이 합쳐진다.
     * (K에 의해 interval의 갯수가 결정된다)
     * 
     * 목표 interval 갯수는 고정값이므로 이분탐색이 가능하다.
     */
    public int mySolution(int distance, int[] rocks, int n) {
        int start = 0;
        int end = distance;
        int targetIntervalCount = (rocks.length - n) + 1;
        List<Integer> intervals = intervals(distance, rocks);

        while (start <= end) {
            int middle = (start + end) / 2;
            int count = 0;
            int temp = 0;

            for (int interval : intervals) {
                temp += interval;

                if (temp >= middle) {
                    temp = 0;
                    count += 1;
                }
            }

            if (count >= targetIntervalCount) {
                start = middle + 1;
            } else {
                end = middle - 1;
            }
        }

        return end;
    }

    public List<Integer> intervals(int distance, int[] rocks) {
        List<Integer> sortedRocks = deepCopy(rocks);
        sortedRocks.sort((i1, i2) -> i1 - i2);
        List<Integer> intervals = new ArrayList<>();

        intervals.add(sortedRocks.get(0));
        for (int i = 1; i < sortedRocks.size(); i++) {
            intervals.add(sortedRocks.get(i) - sortedRocks.get(i-1));
        }
        intervals.add(distance - sortedRocks.get(sortedRocks.size() - 1));

        return intervals;
    }

    public List<Integer> deepCopy(int[] origin) {
        List<Integer> ret = new ArrayList<>(origin.length);

        for (int i : origin) {
            ret.add(i);
        }

        return ret;
    }
}