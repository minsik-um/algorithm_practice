package algorithm_data_structure.etc.kakao_code_pre_2017;

import java.util.HashSet;
import java.util.Set;

public class 카카오프렌즈_컬러링북 {
    /**
     * @param m : 그림 너비 
     * @param n : 그림 높이
     * @param picture : 그림의 컬러
     * @return 그림의 영역 갯수와 한 영역의 최대 크기
     * 
     * 방문 여부를 set으로 검사하고
     * 방문하지 않은 위치(색깔이 있는) 하나를 seed로 선정,
     * 연결된 노드를 쭉 탐색해간다.
     * 
     * Set을 사용하고, Set의 구분을 위해 오브젝트에 equals, hashcode를 정의한다.
     * 
     * (더 좋은 다른 풀이가 있어 아래 적어두었음...
     *  가능하면 재귀함수를 활용하여 가독성을 늘리자)
     */
    public int[] mySolution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        
        Set<Position> remainPos = new HashSet<>();
        
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] > 0) {
                    remainPos.add(new Position(i, j, picture[i][j]));
                }
            }
        }
        
        while (!remainPos.isEmpty()) {
            int count = 1;
            Position pos = remainPos.iterator().next();
            remainPos.remove(pos);
            
            Set<Position> nextPosList = getNextPos(pos, remainPos);
            
            while (!nextPosList.isEmpty()) {
                for (Position nextPos : nextPosList) {
                    remainPos.remove(nextPos);
                    count += 1;
                }
                
                Set<Position> temp = new HashSet<>();
                for (Position nextPos : nextPosList) {
                    temp.addAll(getNextPos(nextPos, remainPos));
                }
                nextPosList = temp;
            }      
            
            numberOfArea += 1;
            maxSizeOfOneArea = Math.max(maxSizeOfOneArea, count);
        }
        
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    
    public Set<Position> getNextPos(Position curr, Set<Position> remainPos) {
        Set<Position> allNextPos = new HashSet<>();
        Set<Position> validNextPos = new HashSet<>();
        
        allNextPos.add(new Position(curr.x - 1, curr.y, curr.color));
        allNextPos.add(new Position(curr.x + 1, curr.y, curr.color));
        allNextPos.add(new Position(curr.x, curr.y + 1, curr.color));
        allNextPos.add(new Position(curr.x, curr.y - 1, curr.color));
        
        for (Position nextPos : allNextPos) {
            if (remainPos.contains(nextPos)) {
                validNextPos.add(nextPos);
            }
        }
            
        return validNextPos;
    }
    
    class Position {
        public int x;
        public int y;
        public int color;
        
        public Position(int x, int y, int color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }
        
        // equals, hashCode 함수가 둘 다 정의되어야 Set 기능이 작동한다.
        
        @Override
        public boolean equals(Object obj) {
            Position other = (Position)obj;
            return this.x == other.x && this.y == other.y && this.color == other.color;
        }
        
        @Override
		public int hashCode() {
			String str = Integer.toString(this.x + 1) + "0" + Integer.toString(this.y + 1)
                        + Integer.toString(color);
			return Integer.parseInt(str);
		}
    }

    /**
     * visited 자료구조와 재귀함수를 이용한 풀이
     * 재귀함수를 사용하면 훨씬 가독성 있게 탐색할 수 있다.
     */
    public int[] otherSolution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        boolean[][] visited = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (picture[i][j] > 0 && !visited[i][j]) {
                    int count = search(picture[i][j], i, j, m, n, visited, picture);

                    numberOfArea += 1;
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, count);
                }
            }
        }
        
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    
    public int search(int color, int posM, int posN, int m, int n, boolean[][] visited, int[][] picture) {
        if (0 > posM || m <= posM || 0 > posN || n <= posN
                || visited[posM][posN]
                || picture[posM][posN] != color) {
            return 0;
        }

        int ret = 1;
        visited[posM][posN] = true;

        ret += search(color, posM + 1, posN, m, n, visited, picture);
        ret += search(color, posM - 1, posN, m, n, visited, picture);
        ret += search(color, posM, posN + 1, m, n, visited, picture);
        ret += search(color, posM, posN - 1, m, n, visited, picture);

        return ret;
    }
}