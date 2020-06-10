package algorithm_data_structure.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

class 순위 {
    /**
     * @param n       : 경기 참가 인원수 (1부터 순차 부여)
     * @param results : 경기 결과 목록 ... [[승리자 번호, 패배자 번호], [], ...]
     * @return        : 최종 순위를 확정할 수 있는 참가자의 수
     * 
     * 플로이드 알고리즘으로 연결 정보 탐색한다.
     * 아래 풀이에 비해 시간이 절반 정도만 걸린다.
     */
    public int otherSolution(int n, int[][] results) { 
        int answer = 0;
        // [a][b] a가 b를... 0: unknown, 1: win, -1: lose
        int[][] recoveredStatus = new int[n+1][n+1];

        for (int[] result : results) {
            recoveredStatus[result[0]][result[1]] = 1;
            recoveredStatus[result[1]][result[0]] = -1;
        }

        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (recoveredStatus[i][k] == 1 && recoveredStatus[k][j] == 1) {
                        recoveredStatus[i][j] = 1;
                    } else if (recoveredStatus[i][k] == -1 && recoveredStatus[k][j] == -1) {
                        recoveredStatus[i][j] = -1;
                    }
                }
            }    
        }

        for (int i = 1; i <= n; i++) {
            int zeroCount = 0;
            for (int j = 1; j <= n; j++) {
                if (recoveredStatus[i][j] == 0) {
                    zeroCount += 1;
                    if (zeroCount > 1) {
                        break;
                    }
                }
            }

            if (zeroCount == 1) {
                answer += 1;
            }
        }

        return answer;
    }

    /**
     * @param n       : 경기 참가 인원수 (1부터 순차 부여)
     * @param results : 경기 결과 목록 ... [[승리자 번호, 패배자 번호], [], ...]
     * @return        : 최종 순위를 확정할 수 있는 참가자의 수
     * 
     * 이동이 가능한 그래프 자료형을 생성하고
     * 한 사람마다 bfs로 탐색하여 확인할 수 있는 승리자, 패배자 목록을 구하고
     * 그 목록 수의 총합이 (경기 참가 인원수 - 1)일 경우 최종 순위를 확정할 수 있음
     */
    public int betterSolution(int n, int[][] results) {
        int answer = 0;
        Graph graph = new Graph(n, results);

        for (int i = 1; i <= n; i++) {
            if (graph.isRankable(i)) {
                answer += 1;
            }
        }

        return answer;
    }

    class Graph {
        private int nodeCount;
        private Map<Integer, Set<Integer>> toLoserGraph;
        private Map<Integer, Set<Integer>> toWinnerGraph;

        public Graph(int n) {
            nodeCount = n;
            toLoserGraph = new HashMap<>();
            toWinnerGraph = new HashMap<>();

            for (int i = 1; i <= n; i++) {
                toLoserGraph.put(i, new HashSet<>());
                toWinnerGraph.put(i, new HashSet<>());
            }
        }

        public Graph(int n, int[][] results) {
            this(n);

            for (int[] result : results) {
                int winner = result[0];
                int loser = result[1];

                toLoserGraph.get(winner).add(loser);
                toWinnerGraph.get(loser).add(winner);
            }
        }

        public boolean isRankable(int node) {
            int count = relatedNodesCount(node, true) + relatedNodesCount(node, false);
            return count == nodeCount - 1;
        }

        private int relatedNodesCount(int startNode, boolean toLoser) {
            Map<Integer, Set<Integer>> directedGraph = (toLoser) ? toLoserGraph : toWinnerGraph;
            boolean[] visited = new boolean[nodeCount + 1];
            Queue<Integer> checkQueue = new LinkedList<>();
            checkQueue.add(startNode);
            int count = 0;

            while (!checkQueue.isEmpty()) {
                int currNode = checkQueue.poll();

                Set<Integer> nextNodes = directedGraph.get(currNode);
                for (int nextNode : nextNodes) {
                    if (!visited[nextNode]) {
                        checkQueue.add(nextNode);
                        visited[nextNode] = true;
                        count += 1;
                    }
                }
            }

            return count;
        }
    }
}
