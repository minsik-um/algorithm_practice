package algorithm_data_structure.dfs_bfs;

public class 네트워크 {

    /*
     * dfs로 들어가며 방문 체크,
     * 최상위에서 루트 노드를 새로 파서
     * dfs 시작할 때마다 새로운 네트워크가 생긴다.
     * 
     * 이 문제에서 bfs를 택해도 어자피 모든 노드를 방문해야 하므로
     * 눈에 띄는 차이는 없음
     */
    public int mySolution1(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            if (visited[i] == false) {
                dfs(computers, visited, i);
                answer += 1;
            }
        }        
 
        return answer;
    }

    public void dfs(int[][] computers, boolean[] visited, int curr) {
        if (visited[curr] == false) {
            visited[curr] = true;

            for (int i = 0; i < computers[curr].length; i++) {
                if (computers[curr][i] == 1) {
                    dfs(computers, visited, i);
                }
            }
        }
    }

    /*
     * find-union 알고리즘으로 독립된 네트워크의 갯수 계산 
     */
    public int mySolution2(int n, int[][] computers) {
        int answer = n;
        int[] root = new int[n];
        int[] level = new int[n];

        for (int i = 0; i < n; i++) {
            root[i] = i;
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (computers[i][j] == 1 && union(root, level, i, j)) {
                        answer -= 1;
                }                    
            }
        }
        
        return answer;
    }
    
    public int find(int[] root, int node) {
        if (node != root[node]) {
            root[node] = find(root, root[node]);
            return root[node];
        } else {
            return node;
        }
    }

    public boolean union(int[] root, int[] level, int node1, int node2) {
        boolean answer = false;
        int root1 = find(root, node1);
        int root2 = find(root, node2);                

        if (root1 == root2) {
            return answer;
        }

        if (level[root1] < level[root2]) {
            root[root1] = root2;
        } else if (level[root1] > level[root2]) {
            root[root2] = root1;
        } else {
            root[root2] = root1;
            level[root1] += 1;
        }

        answer = true;
        return answer;
    }
}

