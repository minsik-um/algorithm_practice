package algorithm_data_structure.etc.normal;

public class 땅따먹기 {
    /**
     * @param land 각 땅의 숫자
     * @return 땅따먹기 게임 수행 후 얻을 수 있는 최댓값
     *         매번 인접한 같은 열을 밟을 수 없음
     * 
     * 아래 bestSolution() 처럼 재귀함수를 이용하여 가독성을 늘릴 수 있다.
     */
    public int solution(int[][] land) {
        int answer = 0;
        int[][] dp = new int[land.length][land[0].length];
        dp[0] = land[0];
        
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                for (int k = 0; k < dp[0].length; k++) {
                    if (j == k) {
                        continue;
                    }
                    
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][k]);
                }      
                dp[i][j] += land[i][j];
            }
        }
        
        for (int i = 0; i < dp[0].length; i++) {
            answer = Math.max(answer, dp[dp.length - 1][i]);
        }

        return answer;
    }

    /**
     * 재귀함수를 이용하여 가독성 향상
     */
    public int bestSolution(int[][] land) {
        int answer = 0;
        int[] dp = new int[land[0].length];
        for (int i = 0; i < dp.length; i++) {
            dp[i] = land[0][i];
        }

        setMaxToDp(land, dp, 1);
        
        for (int i = 0; i < dp.length; i++) {
            answer = Math.max(answer, dp[i]);
        }

        return answer;
    }

    public void setMaxToDp(int[][] land, int[] dp, int rowIdx) {
        int[] tempDp = new int[dp.length];

        for (int i = 0; i < dp.length; i++) {
            int maxField = 0;

            for (int j = 0; j < dp.length; j++) {
                if (i != j) {
                    maxField = Math.max(maxField, dp[j]);
                }
            }

            tempDp[i] = land[rowIdx][i] + maxField;
        }

        // copy
        for (int i = 0; i < dp.length; i++) {
            dp[i] = tempDp[i];
        }

        // execute next row
        if (rowIdx + 1 < land.length) {
            setMaxToDp(land, dp, rowIdx + 1);
        }
    }
}