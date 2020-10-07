class Solution {

    /**
     * @param board 1과 0으로 채워진 표
     * @return 가장 큰 정사각형의 넓이 반환
     * 
     * 동적 프로그래밍으로 정사각형 성립 여부를 계산해간다.
     * 꼭 거꾸로 할 필요는 없고 위에서 아래 방향으로 가도 상관 없다.
     */
    public int solution(int[][] board) {
        int squareMaxWidth = 0;
        int widthLen = board[0].length;
        int heightLen = board.length;        
        int[][] dp = new int[heightLen][widthLen];
        
        for (int i = 0; i < heightLen; i++) {
            for (int j = 0; j < widthLen; j++) {
                dp[i][j] = board[i][j];
                if (board[i][j] == 1) {
                    squareMaxWidth = 1;
                }
            }
        }
        
        for (int i = heightLen - 2; i >= 0; i--) {
            for (int j = widthLen - 2; j >= 0; j--) {
                if (dp[i][j] != 0) {
                    dp[i][j] = Math.min(dp[i+1][j], Math.min(dp[i][j+1], dp[i+1][j+1])) + 1;
                    squareMaxWidth = Math.max(squareMaxWidth, dp[i][j]);
                }
            }
        }

        return squareMaxWidth * squareMaxWidth;
    }
}