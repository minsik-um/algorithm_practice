package algorithm_data_structure.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class 가장_먼_노드 {
    /**
     * @param n     : 노드의 갯수(1번부터 n번까지 존재)
     * @param edge  : 노드 간선 집합, (a, b)는 a와 b를 잇는 간선임
     * @return      : 1번 노드에서 가장 멀리 떨어진 노드의 갯수
     *                (각 노드로 가는 최단거리로 거리 구분)
     * 
     * map, set 자료구조를 활용해 그래프를 만들고
     * 매번 방문하지 않은 노드를 찾아 나서며 최단 거리 탐색
     * (사이클 방지)
     */
    public int mySolution(int n, int[][] edge) {
        int answer = 0; 
        boolean[] visited = new boolean[n+1];
        Map<Integer, Set<Integer>> graph = getGraph(n, edge);

        List<Integer> currNodes = new ArrayList<>();
        currNodes.add(1);
        visited[1] = true;

        while (!currNodes.isEmpty()) {
            List<Integer> nextNodes = new ArrayList<>();

            for (int node : currNodes) {
                for (int next : graph.get(node)) {
                    if (!visited[next]) {
                        nextNodes.add(next);
                        visited[next] = true;
                    }
                }
            }
            
            answer = currNodes.size();
            currNodes = nextNodes;
        }

        return answer;
    }    
    
    public Map<Integer, Set<Integer>> getGraph(int n, int[][] edge) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();

        for (int i = 1; i <= n; i++) {
            graph.put(i, new HashSet<>());
        }

        for (int[] e : edge) {
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }

        return graph;
    }

    public int mySolution2(int n, int[][] edge) {
        int answer = 0;

        return answer;
    }
}