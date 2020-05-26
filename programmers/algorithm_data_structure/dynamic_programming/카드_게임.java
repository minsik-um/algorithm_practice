package algorithm_data_structure.dynamic_programming;

public class 카드_게임 {
    /*
     * 왼쪽 카드 버리기, 오른쪽 카드 버리기를 각각 오른쪽 이동, 아래 이동으로 정하면
     * 둘 다 버리는 건 대각선 이동으로 표현할 수 있다 **
     * 그렇게 dp를 구성하고 dp의 각 내용은 index 수만큼 카드를 제거했을 때 최댓값이다.
     * 
     * 아래로 이동하는 건 조건이 붙는다.
     * 1. 이동하기 전 상태에서 오른쪽 카드가 더 작아야 하고,
     * 2. ** 이동하기 전 상태가 가능한 상태인지 봐야 한다.
     *       dp를 대각선으로 그었을 때 왼쪽 아래 삼각형 내 위치들은 상황에 따라 도달 불가능할 수 있다.
     *       왜냐하면 오른쪽 아이템은 순차적으로 위에 꺼가 다 제거되어야 아래꺼를 제거할 수 있기 때문이다.
     *       대신 대각선, 오른쪽 이동은 항상 가능하므로 오른쪽 위 삼각형 + 대각선 라인은 다 가능하다.
     *       아래로 내려가는 건 무조건 특정 값을 더하므로 왼쪽 아래 삼각형에서 값이 0이라면 불가능한 상태다.
     */
    public int mySolution(int[] left, int[] right) {
        int[][] dp = new int[left.length + 1][left.length + 1];

        for (int i = 0; i < right.length; i++) {
            if (left[0] <= right[i]){
                break;
            }
            dp[i+1][0] = right[i] + dp[i][0];           
        }

        for(int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length - 1; j++) {
                dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j-1]);
                if ((i-1 <= j || dp[i-1][j] != 0) && left[j] > right[i-1]) {
                    dp[i][j] = Math.max(dp[i][j], right[i-1] + dp[i-1][j]);
                }
            }
        }

        return dp[dp.length-1][dp[0].length-2];
   }
}