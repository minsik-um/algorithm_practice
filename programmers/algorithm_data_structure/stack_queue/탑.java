package algorithm_data_structure.stack_queue;

import java.util.Stack;

public class 탑 {
    /**
     * py 파일의 설명 참고
     */

    public int[] mySolution(int[] heights) {
        int[] answer = new int[heights.length];  // 기본값 0이 자동으로 채워짐
        
        for(int i = heights.length-1; i >= 0; i--){
            for (int j = i-1; j >= 0; j--){                
                if (heights[i] < heights[j]){
                    answer[i] = j+1;
                    break;
                }
            }
        }
        return answer;
    }

    class Tower {
        int idx;
        int height;

        public Tower(int idx, int height) {
            this.idx = idx;
            this.height = height;
        }
    }

    public int[] betterSolution(int[] heights) {
        Stack<Tower> st = new Stack<>();
        int[] answer = new int[heights.length];

        for (int i = 0; i < heights.length; i++) {
            Tower tower = new Tower(i + 1, heights[i]);
            int receiveIdx = 0;

            while (!st.isEmpty()) {
                Tower top = st.peek();

                if (top.height > tower.height) {
                    receiveIdx = top.idx;
                    break;
                }

                st.pop();
            }

            st.push(tower);
            answer[i] = receiveIdx;
        }

        return answer;
    }

    public int[] bestSolution(int[] heights) {
        Stack<Tower> st = new Stack<>();
        int[] answer = new int[heights.length];

        for (int i = heights.length - 1; i >= 0; i--) {

            while (! st.isEmpty() && st.peek().height < heights[i]){
                Tower popedTower = st.pop();
                answer[popedTower.idx] = i + 1;
            }

            Tower tower = new Tower(i, heights[i]);
            st.push(tower);
        }

        return answer;
    }
}