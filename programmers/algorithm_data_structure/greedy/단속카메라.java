package algorithm_data_structure.greedy;

import java.util.Arrays;
import java.util.LinkedList;

class 단속카메라 {
    /*
     * 자동차가 나가는 순서로 보았을 때
     * 아직 카메라 배치가 안된 다른 자동차는 지금 자동차보다 늦게 나가고
     * 지금 자동차보다 일찍 들어오거나 늦게 들어온다.
     * 
     * 이때 지금 자동차 포함하여 가장 많이 자동차 범위가 겹치는 구간은
     * 자동차가 나가는 그 순간이며,
     * 이때 도로 위에 오른 자동차들은 그 카메라 한 대로 다 처리 가능하다.
     * 
     * 이런 개념으로 카메라를 배치해나감
     * (기본적으로 routes가 들어온 순서대로 정렬되었다고 가정 + 나간 순서대로 정렬)
     */
    public int mySolution(int[][] routes) {
        int answer = 0;
        LinkedList<int[]> sortedRoutes = new LinkedList<>();

        for (int[] route : routes) {
            sortedRoutes.add(route);
        }
        sortedRoutes.sort((o1, o2) -> o1[1] - o2[1]);

        while (!sortedRoutes.isEmpty()) {
            int[] route = sortedRoutes.removeFirst();

            while(!sortedRoutes.isEmpty() && sortedRoutes.getFirst()[0] <= route[1]) {
                sortedRoutes.removeFirst();
            }
            answer += 1;
        }
 
        return answer;
    }

    /*
     * 위의 개념과 동일한 풀이지만 
     * for문과 if문으로 간단하게 풀이해냈다.
     * 
     * 위 코드보다 속도가 약간 빠르다.
     * 일단 빼고 넣는 게 없으며, 조건문 갯수도 적다.
     */
    public int bestSolution(int[][] routes) {
        int answer = 0;
        int last_camera = Integer.MIN_VALUE;
        int[][] sortedRoutes = new int[routes.length][2];

        for (int i = 0; i < routes.length; i++) {
            sortedRoutes[i] = routes[i];
        }
        Arrays.sort(sortedRoutes, (o1, o2) -> o1[1] - o2[1]);
        
        for (int[] a : sortedRoutes) {
            if (last_camera < a[0]) {
                answer += 1;
                last_camera = a[1];
            }
        }
        
        return answer;
    }
}