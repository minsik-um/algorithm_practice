package algorithm_data_structure.greedy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class 섬_연결하기 {

    /* 
     * 크루스칼 알고리즘(+ find-union) 사용 풀이
     * find-union 방법 : https://gmlwjd9405.github.io/2018/08/31/algorithm-union-find.html
     * 
     * 최소 비용의 필요 조건은 최소 연결이므로
     * 연결 노드 수가 n-1개가 되면 수행 종료 
     */
    public int mySolution1(int n, int[][] costs) {
        int answer = 0;
        int connectCount = 0;
        int[] root = new int[n];
        int[] level = new int[n];

        List<int[]> sortedCosts = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            root[i] = i;
        }
        for(int[] cost : costs) {
            sortedCosts.add(cost);
        }
        sortedCosts.sort((o1, o2) -> o1[2] - o2[2]);

        for (int[] cost : sortedCosts) {
            if (find(cost[0], root) != find(cost[1], root)) {
                union(cost[0], cost[1], root, level);
                answer += cost[2];
                connectCount += 1;
            }

            if (connectCount == n-1) {
                break;
            }
        }
        
        return answer;
    }
    
    public int find(int node, int[] root) {
        if (node != root[node]) {
            root[node] = find(root[node], root);
            return root[node];
        } else {
            return node;
        }
    }

    public void union(int node1, int node2, int[] root, int[] level) {
        int root1 = find(node1, root);
        int root2 = find(node2, root);

        if (level[root1] < level[root2]) {
            root[root1] = root2;
        } else if (level[root1] > level[root2]) {
            root[root2] = root1;
        } else {
            root[root2] = root1;
            level[root1] += 1;
        }
    }

    // 프림 알고리즘: 위 크루스칼 알고리즘과 비슷한 실행 시간
    public int mySolution2(int n, int[][] costs) {
        int answer = 0;
        Set<Integer> connected = new HashSet<>();
        connected.add(0);

        while (connected.size() < n) {
            int[] selected = null;

            for (int[] cost : costs) {
                if (connected.contains(cost[0]) && !connected.contains(cost[1])
                   || connected.contains(cost[1]) && !connected.contains(cost[0])) {
                    if (selected == null || selected[2] > cost[2]) {
                        selected = cost;
                    }
                }
            }

            connected.add(selected[0]);
            connected.add(selected[1]);
            answer += selected[2];
        }

        return answer;
    }

    // 프림 알고리즘 사용 2
    // costs 자료형을 매번 줄여나가지만
    // 줄이는 비용이 오히려 커서 앞의 코드보다 시간이 더 나감
    public int mySolution3(int n, int[][] costs) { 
        int answer = 0;
        List<int[]> copiedCosts = new ArrayList<>();
        for (int i = 0; i < costs.length; i++) {
            copiedCosts.add(costs[i]);
        }
        Set<Integer> connected = new HashSet<>();
        connected.add(0);

        while (connected.size() < n) {
            int[] selected = null;
            int selectedIndex = -1;

            for (int i = 0; i < copiedCosts.size(); i++) {
                int[] cost = copiedCosts.get(i);
                if (connected.contains(cost[0]) && !connected.contains(cost[1])
                        || connected.contains(cost[1]) && !connected.contains(cost[0])) {
                    if (selected == null || selected[2] > cost[2]) {
                        selected = cost;
                        selectedIndex = i;
                    }
                }
            }

            copiedCosts.remove(selectedIndex);
            connected.add(selected[0]);
            connected.add(selected[1]);
            answer += selected[2];
        }

        return answer;
    }
}