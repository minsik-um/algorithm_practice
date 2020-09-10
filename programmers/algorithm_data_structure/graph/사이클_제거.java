package algorithm_data_structure.graph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class 사이클_제거 {
    public int solution(int n, int[][] edges) {
        Graph graph = new Graph(n, edges);

        graph.pruning();
        graph.searchCircleDfs();
        return graph.sumOfCommonCircleNodes();
    }

    class Graph {
        private int nodeMaxNo;  // 부여된 번호 중에 최댓값
        private Map<Integer, Set<Integer>> graph;
        private Set<Integer> commonCircleNodes;

        public Graph(int n, int[][] edges) {
            nodeMaxNo = n;
            graph = new HashMap<>();
            commonCircleNodes = new HashSet<>();

            for (int i = 1; i <= n; i++) {
                graph.put(i, new HashSet<>());
            }

            for (int[] edge : edges) {
                graph.get(edge[0]).add(edge[1]);
                graph.get(edge[1]).add(edge[0]);
            }
        }

        public void pruning() {
            Queue<Integer> connectOneNodes = new LinkedList<>();
            for (int node : graph.keySet()) {
                connectOneNodes.add(node);
            }

            while (!connectOneNodes.isEmpty()) {
                int node = connectOneNodes.poll();
                if (graph.containsKey(node) && graph.get(node).size() == 1) {
                    for (int nextNode : graph.get(node)) {
                        connectOneNodes.add(nextNode);
                        graph.get(nextNode).remove(node);
                    }
                    graph.remove(node);    
                }
            }
        }

        public void searchCircleDfs() {
            boolean[] visited = new boolean[nodeMaxNo];
            boolean[] checked = new boolean[nodeMaxNo];

            for (int node : graph.keySet()) {
                commonCircleNodes.add(node);
            }

            searchCircleDfs(-1, graph.keySet().iterator().next(), new LinkedList<>(), visited, checked);
        }
    
        private void searchCircleDfs(int lastNode, int currNode,
                LinkedList<Integer> path, boolean[] visited, boolean[] checked) {
    
            path.add(currNode);
            visited[currNode-1] = true;
            Set<Integer> nextNodes = graph.get(currNode);
            
            for (int nextNode : nextNodes) {
                if (!visited[nextNode-1]){
                    searchCircleDfs(currNode, nextNode, path, visited, checked);
                }else if (lastNode != nextNode){                   
                    boolean isValid = false;
                    Set<Integer> circle = new HashSet<>();
                    int temp = path.size() - 1;
                    path.addFirst(nextNode);
                    
                    while (path.get(temp) != nextNode) {
                        if (!checked[path.get(temp) - 1]) {
                            isValid = true;
                        }
                        checked[path.get(temp) - 1] = true;
                        circle.add(path.get(temp));
                        temp -= 1;
                    }
                    circle.add(nextNode);
                    checked[nextNode - 1] = true;

                    if (isValid) {
                        commonCircleNodes.retainAll(circle);
                    }
                }
            }
    
            path.remove(path.size()-1);
            visited[currNode-1] = false;
        }

        public int sumOfCommonCircleNodes(){
            int sum = 0;

            for (int node : commonCircleNodes) {
                sum += node;
            }

            return sum;
        }
    }
}