package algorithm_data_structure.dynamic_programming;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class 등굣길 {
    /*
     * 문제 자체는 전형적인 dp 문제이지만,
     * 가능한 경우의 수를 따져 자료형을 결정해야 한다.
     * 중간 과정에서도 int는 물론이고 long 범위를 넘을 수 있어
     * 매번 나머지로 나눠줘야 한다.
     * 
     * int -> -2,147,483,648 .. 2,147,483,647
     * long -> -9,223,372,036,854,775,808 .. 9,223,372,036,854,775,807
     * 
     * 이 문제에서 우리는 위로 올라갈 필요가 있다
     * 아래 예시를 보면 오른쪽, 아래로 간다고 되는 게 아니다
     * solution(5,4,[[2,1],[2,2],[2,3],[4,4],[4,3],[4,2]])
     */
    public int mySolution(int m, int n, int[][] puddles) {
        int[][] dp = new int[m+1][n+1];

        for (int[] puddle : puddles) {
            dp[puddle[0]][puddle[1]] = -1;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                dp[i][j] = (dp[i][j] + 1) * (dp[i-1][j] + dp[i][j-1]);
                dp[i][j] %= 1000000007;
            }
        }

        return dp[m][n];
    }

    /*
     * 위/왼쪽으로도 이동 가능하다는 조건이 있을 때 풀이
     * 자세한 설명은 등굣길_문제해설.md 참고
     */
    enum Visit {
        NOT_VISITED,
        VISITING,
        VISITED
    }

    public int mySolution2(int m, int n, int[][] puddles) {
        int[][] dp = new int[m+1][n+1];
        Visit[][] visited = new Visit[m+1][n+1];
        List<int[]> currNodes = new LinkedList<>();
        List<int[]> newNodes = null;

        for (int i = 0; i < visited.length; i++){
            for (int j = 0; j < visited[i].length; j++){
                visited[i][j] = Visit.NOT_VISITED;
            }
        }

        // 시작지점 방문
        currNodes.add(new int[]{1, 1});
        dp[1][1] = 1;
        visited[1][1] = Visit.VISITED;

        // 웅덩이 등록        
        for (int[] puddle : puddles) {
            visited[puddle[0]][puddle[1]] = Visit.VISITED;
        }

        // 경로 탐색
        do {
            newNodes = new LinkedList<>();

            // 같은 거리의 최단 경로들 보면서 갯수 업데이트
            for (int[] node : currNodes) {
                int[] up = new int[]{node[0], node[1] - 1};
                int[] left = new int[]{node[0] - 1, node[1]};
                int[] down = new int[]{node[0], node[1] + 1};
                int[] right = new int[]{node[0] + 1, node[1]};

                List<int[]> nextGroup = Arrays.asList(up, left, down, right);
                for (int[] next : nextGroup){
                    if (next[0] >= 1 && next[0] <= m && next[1] >= 1 && next[1] <= n) {
                        if (visited[next[0]][next[1]] != Visit.VISITED) {
                            dp[next[0]][next[1]] += dp[node[0]][node[1]];
                            dp[next[0]][next[1]] %= 1000000007;
            
                            if (visited[next[0]][next[1]] == Visit.NOT_VISITED) {
                                newNodes.add(next);
                                visited[next[0]][next[1]] = Visit.VISITING;
                            }
                        }
                    }
                }        
            }

            // 방문 처리
            for (int[] node : newNodes) {
                visited[node[0]][node[1]] = Visit.VISITED;
            }
            currNodes = newNodes;
        } while (!newNodes.isEmpty());

        return dp[m][n];
    }

}