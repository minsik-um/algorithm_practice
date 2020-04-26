package algorithm_data_structure.graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedQueue;

class Solution {

    /*
     * 가지치기로 연결된 간선 수가 최댓값인 노드만 남겨서
     * union-find 방법으로 cyclic을 탐색한다.
     * 이때 한 번만 cyclic 검사하면 다른 후보 노드 상태도 동일하다.
     */
    static int bestSolution1(int n, int[][] edges) {
        // init graph
        var g = new Graph(n, edges);

        // prune leaf nodes
        g.prune();

        // get nodes of max degree
        var candidateNodes = g.getMaxDegreeNodes();
        var untested = true;
        var answer = 0;

        for (var node : candidateNodes) {
            if (untested) {
                // do single test with first candidate node
                g.removeNode(node);

                // means all candidates are not answer
                if (g.hasCycle()) break;

                // means all candidates are answer
                untested = false;
            }

            answer += node;
        }

        return answer;
    }

    static class Graph {
        HashMap<Integer, HashSet<Integer>> adjList;
        int size;

        // make graph
        Graph(int size, int[][] edges) {
            // make adjacency list
            // 미리 크기를 구해 넣어주며 생성하면 좋다.
            this.size = size;
            adjList = new HashMap<>(size);
            var estimatedEdgesCapacity = (int) Math.ceil((double) edges.length*2/size);
            for (var i=1; i<=size; i++) {
                adjList.put(i, new HashSet<>(estimatedEdgesCapacity));
            }

            // add edges
            for(var edge : edges) {
                var a = edge[0];
                var b = edge[1];
                adjList.get(a).add(b);
                adjList.get(b).add(a);
            }
        }

        // prune leaf nodes
        void prune() {
            // nodes stack sorted by ascending degree
            Stack<Integer> nodes = new Stack<>();
            nodes.addAll(adjList.keySet());
            // 정렬을 람다를 이용하여 간단하게 지정
            nodes.sort(Comparator.comparingInt(a -> -adjList.get(a).size()));

            while (!nodes.empty()) {
                var node = nodes.pop();
                var adjNodes = adjList.get(node);
                if (adjNodes == null) continue; // already removed by neighbor

                var size = adjNodes.size();  // 연결된 간선 수
                if (size == 0) continue;
                if (size != 1) break;

                // remove node from neighbor
                var adjNode = adjNodes.iterator().next();  // 이번에 가져온 건 제거되기에 다음에 가져오지 않음
                var adjAdjNodes = adjList.get(adjNode);
                adjAdjNodes.remove(node);  // 현재 node의 다음 노드에서 이걸 제거                

                // rearrange neighbor to top
                if (adjAdjNodes.size() == 1) nodes.push(adjNode);

                // remove node itself
                adjList.remove(node);
            }
        }

        // detect cycle by union-find
        boolean hasCycle() {
            var set = new DisjointSet(this.size);
            var visitedEdges = new HashSet<Integer>(this.size);
            for (var entry : adjList.entrySet()) {
                var node = entry.getKey();
                var adjNodes = entry.getValue();
                
                for (var adjNode : adjNodes) {
                    // already added edge?
                    var edgeHash = node * this.size + adjNode;
                    if (visitedEdges.contains(edgeHash)) continue;

                    // mark edge
                    visitedEdges.add(edgeHash);
                    visitedEdges.add(adjNode * this.size + node);

                    if (set.find(node) == set.find(adjNode)) return true;
                    set.union(node, adjNode);
                }
            }
            return false;
        }

        // get nodes of max degree
        ArrayList<Integer> getMaxDegreeNodes() {
            // nodes list sorted by descending degree
            var nodes = new ArrayList<>(adjList.keySet());
            nodes.sort(Comparator.comparingInt(a -> adjList.get(a).size()).reversed());

            var maxDegreeNodes = new ArrayList<Integer>();
            int maxDegree = 2; // nodes in cycle shall have 2 degree at minimum
            for (var node : nodes) {
                var degree = adjList.get(node).size();
                if (degree >= maxDegree) {
                    maxDegree = degree; // will not collect nodes of smaller degree than max degree
                    maxDegreeNodes.add(node);
                } else {
                    break;
                }
            }

            return maxDegreeNodes;
        }

        void removeNode(int node) {
            var adjNodes = adjList.get(node);

            // remove node from neighbors
            for (var adjNode : adjNodes) {
                adjList.get(adjNode).remove(node);
            }

            // remove node itself
            adjList.remove(node);
        }
    }

    static class DisjointSet {
        int[] parent;
        DisjointSet(int maxNodeNum) {
            parent = new int[maxNodeNum+1];
            for (var i=1; i<=maxNodeNum; i++) parent[i] = -1;
        }

        void union(int a, int b) {
            var ap = find(a);
            var bp = find(b);
            if (ap != bp) {
                var sizeA = -parent[ap];
                var sizeB = -parent[bp];
                if (sizeA > sizeB) {
                    parent[bp] = ap;
                    parent[ap] -= sizeB;
                } else {
                    parent[ap] = bp;
                    parent[bp] -= sizeA;
                }
            }
        }

        int find(int a) {
            var p = a;
            while (parent[p] > 0) p = parent[p];
            if (p != a) {
                var t = a;
                while (parent[t] > 0) {
                    var tp = parent[t];
                    parent[t] = p;
                    t = tp;
                }
            }
            return p;
        }
    }

    /* ----------------------------------------------------
     * 위의 방법과 유사하지만 cycle 판단을 DFS로 한다는 차이점이 있다.
     */ 
    public int solution(int n, int[][] edges) {
        Map<Integer, Set<Integer>> nodeAdjMap = buildNodeAdjMap(edges);
        nodeAdjMap = diet(nodeAdjMap);  // 
        int e = 0;
        int v = 0;
        for (Integer k : nodeAdjMap.keySet()) {
            v++;
            e += nodeAdjMap.get(k).size();
        }
        e /= 2;
        if (e == v) {
            return nodeAdjMap.keySet().stream().mapToInt(Integer::intValue).sum();
        }
        int answer = 0;
        for (Integer i : nodeAdjMap.keySet()) {
            int x = nodeAdjMap.get(i).size();
            int re = e - x;
            int rv = v - 1;
            if (re + 1 > rv) { // 그래프가 안쪼개지더라도 사이클 무조건 존재
                continue;
            }    
            if (!checkCycle(n, nodeAdjMap, i, rv - re)) { // 실제 사이클이 없는지 확인
                answer += i;
            }
        }
        return answer;
    }

    private Map<Integer, Set<Integer>> buildNodeAdjMap(int [][] edges) {
        Map<Integer, Set<Integer>> m = new HashMap<>();
        for (int [] e : edges) {
            m.computeIfAbsent(e[0], k -> new HashSet<>()).add(e[1]);
            m.computeIfAbsent(e[1], k -> new HashSet<>()).add(e[0]);
        }
        return m;
    }

    private Map<Integer, Set<Integer>> diet(Map<Integer, Set<Integer>> nodeAdjMap) {
        Map<Integer, Set<Integer>> m = new HashMap<>();
        Queue<Integer> q = new ConcurrentLinkedQueue<>();
        for (Integer v : nodeAdjMap.keySet()) {
            m.put(v, new HashSet<>(nodeAdjMap.get(v)));
            if (nodeAdjMap.get(v).size() == 1) {
                q.add(v);
            }
        }
        while (!q.isEmpty()) {
            Integer v = q.remove();
            Set<Integer> adj = m.remove(v);
            for (Integer a : adj) {
                Set<Integer> adj2 = m.get(a);
                adj2.remove(v);
                if (adj2.size() == 1) {
                    q.add(a);
                } 
            }
        }
        return m;
    }

    private boolean checkCycle(int n, Map<Integer, Set<Integer>> nodeAdjMap, int except, int graph) {
        boolean [] visited = new boolean[n + 1];
        int g = 0;
        for (Integer start : nodeAdjMap.keySet()) {
            if (start != except && !visited[start]) {
                if (++g > graph) {
                    return true;
                }
                if (checkCycle(start, -1, nodeAdjMap, except, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean checkCycle(int curt, int before, Map<Integer, Set<Integer>> nodeAdjMap, int except, boolean [] visited) {
        if (visited[curt]) {
            return true;
        }
        visited[curt] = true;
        for (int next : nodeAdjMap.get(curt)) {
            if (next != except && next != before) {
                if (checkCycle(next, curt, nodeAdjMap, except, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    /* --------------------------------------------------
     *     visited : 0이면 미방문, 
     *               0이상이면 방문 : 트리에서 몇 번째 계층인지를 뜻함 (계층은 1부터 시작)
     *       child : 1번째 노드를 루트로 dfs를 돌며 만든 tree
     *         adj : 그래프 자료구조
     *
     * parent_edge : i의 부모를 도착점으로 하는 (strong) back edge 개수
     *   weak_edge : i를 루트로 하는 서브트리에 조금이라도 포함된 back edge 개수 (백 에지 시작점)
     * strong_edge : i를 루트로 하는 서브트리에 완전히 포함된 back edge 개수 (백 에지 도착점)
     * 
     * 아래 3가지 케이스를 통과하는지를 봐야 한다.
     * removed Node를 v라고 하자.
     * 1. v 루트 트리 하층 서브 트리에서 back edge가 존재하면 v를 제거해도 back edge가 그대로 남아있어 사이클이 있다.
     * 2. 트리 상에서 v보다 밑에 있는 정점과 v보다 위에 있는 정점이 연결된 back edge가 2개 이상 존재한다면 v는 정답이 될 수 없다.
     *    v를 제거하면 하나는 tree edge가 되고 나머지는 back edge가 된다.
     * 3. v를 루트로 하는 트리 외부에서 back edge가 존재하면 v를 제거해도 back edge는 그대로 남아있다.
     * 
     * 트리에서 모든 백에지가 v에게 도착하거나 v에서 출발할 때(v를 제거하면 백에지가 끊기고) 통과하는 셈이다.
     */

    int N, M, C;
    int[] visited;
    int[] parant_edge, weak_edge, strong_edge;
    List<LinkedList<Integer>> adj, child;

    public int bestSolution3(int n, int[][] edges) {
        int answer = 0;

        visited = new int[n+1];
        parant_edge = new int[n+1];
        weak_edge = new int[n+1];
        strong_edge = new int[n+1];

        adj = new ArrayList<LinkedList<Integer>>(n+1);
        child = new ArrayList<LinkedList<Integer>>(n+1);

        // 각 변수를 초기화
        for(int i = 0; i <= n; i++) {
            adj.add(new LinkedList<>());
            child.add(new LinkedList<>());
        }

        // 그래프 생성
        for(int i = 0; i < edges.length; i++) {
            adj.get(edges[i][0]).add(edges[i][1]);
            adj.get(edges[i][1]).add(edges[i][0]);
        }

        // 첫 번째 노드 방문 표시
        visited[1] = 1;
        
        // dfs를 돌며 백 에지를 찾음
        dfs(1, 0);
        
        // 순서대로 node를 하나씩 제거한다고 가정하고
        removeLoop:
        for(int removedNode = 1; removedNode <= n; removedNode++) {
            // 제거한 노드의 서브 트리를 보며 사이클 케이스 1, 2를 확인
            for(int v : child.get(removedNode)) {
                /* 
                 * 서브 트리에 백에지가 완전히 포함되거나.
                 * (하층에서 올라가는 백에지) - (그중에서 removedNode로 도달하는 백에지) = 하층과 상층 연결하는 백에지
                 * 가 2보다 크면 사이클이 사라지지 않음
                 */
                if(strong_edge[v] > 0 || weak_edge[v]-parant_edge[v] >= 2) {
                    continue removeLoop;
                }
            }

            // 지금은 해당 노드 아래쪽엔 백에지가 없는 상태
            // 만약 제거할 노드와 관련된 백에지를 전부 제거한 다음에
            // 백에지가 (외부에) 없다면 스패닝 트리가 되므로 아래 조건을 만족해야 함
            // (트리가 아니라 그래프 단위 모든 엣지 갯수) - (남은 백 에지 저거) ->? 그래프가 되는가?
            if(edges.length - weak_edge[removedNode] == (n-1)) {
                answer += removedNode;
            }
        }       
        return answer;
    }

    void dfs(int curr, int from) {
        // dfs 처리를 위해 current node 기준으로 다음 노드 분기를 실행
        for(int next : adj.get(curr)){

            // 트리를 만드는 과정 : 이전 노드로 돌아가는 경우는 제외
            if(from != next) {
                // 다음 노드가 방문한 적이 없다면
                if(visited[next] == 0) {
                    visited[next] = visited[curr] + 1;  // 방문 처리
                    child.get(curr).add(next);          // 트리에 노드 추가
                    int temp = strong_edge[next];       // 서브 트리 업데이트 하기 전 값을 저장

                    dfs(next, curr);  // dfs 들어감

                    // 다음 노드 계층(하위 계층)을 전부 처리하고 edge 배열들을 업데이트한 이후
                    parant_edge[next] = strong_edge[curr] - temp;  // 부모 노드를 도착점으로 하는 수를 저장한다.
                    strong_edge[curr] += strong_edge[next];        // 서브 트리의 엣지 수를 전부 더해준다.
                    weak_edge[curr] += weak_edge[next];            // 서브 트리의 엣지 수를 전부 더해준다.
                }
                // 다음 노드에 방문한 적이 있고 현재 노드보다 계층이 상위라면 : 백에지
                else if(visited[curr] > visited[next]) {
                    weak_edge[curr] += 1;    // 현재 노드 기준에서 '약한 엣지' 추가
                    strong_edge[next] += 1;  // 백에지 도착점 상위 노드 기준에서 '강한 엣지' 추가
                }
            }
        }
    }
}