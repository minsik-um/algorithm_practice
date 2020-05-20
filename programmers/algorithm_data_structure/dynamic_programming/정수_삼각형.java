package algorithm_data_structure.dynamic_programming;

public class 정수_삼각형 {
    
    public int mySolution(int[][] traingle) {
        int max = 0;
        int[][] dp = new int[traingle.length][];

        // dp에 trangle 복사
        for (int i = 0; i < traingle.length; i++) {
            dp[i] = new int[traingle[i].length];

            for (int j = 0; j < dp[i].length; j++) {
                dp[i][j] = traingle[i][j];
            }
        }

        // 이전 경로 중 값이 큰 경로를 선택해 이어감
        for (int i = 1; i < dp.length; i++) {
            int[] prevLayer = dp[i - 1];
            int[] currLayer = dp[i];

            for (int j = 0; j < currLayer.length; j++) {
                int leftSide = (j == 0) ? 0 : prevLayer[j-1]; 
                int rightSide = (j == currLayer.length - 1) ? 0 : prevLayer[j];
                currLayer[j] += Math.max(leftSide, rightSide);
            }
        }

        // 최댓값 경로 찾기
        for (int v : dp[dp.length - 1]){
            max = Math.max(max, v);
        }
        return max;   
    }
}