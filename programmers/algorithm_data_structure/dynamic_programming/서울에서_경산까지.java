package algorithm_data_structure.dynamic_programming;

public class 서울에서_경산까지 {

    /*
    K : 걸리는 시간의 합의 최댓값 제한
    travel: 
    [도보 이동에 걸리는 시간, 도보 이동 시 얻을 모금액, 
    자전거 이동에 걸리는 시간, 자전거 이동 시 얻을 모금액]

    (생각의 흐름대로 정리)
    동적프로그래밍으로 최댓값을 보장하면서 나아가려면
    점화식이 필요하며, 각 단계마다 최댓값이라는 게 인정되고,
    이전 단계의 최댓값을 활용해 현재 단계의 선택지(도보 또는 자전거: 2개) 중
    1개를 선택해야 함

    각 지점을 순차적으로 방문하며 살펴보자
    우선 첫 지점에서는 K 값 제한이 가능한 선에서 도보, 자전거 순으로 선택하면 된다.
    이 개념을 확장하여

    n번째 지점에서는 도보, 자전거 선택이 가능하며, 도보를 선택했을 때 최댓값은
    n-1 번째 지점에서 K - (도보 시간) 제한일 때 최댓값에 n 번째 지점의 돈을 더한 값이다.
    (K를 꽉 채움)
    자전거도 마찬가지이며 두 선택 중 최댓값을 고르면 된다.

    K 숫자가 높아 dp 배열이 커보이지만 
    불필요한 부분을 제외하고 돌리면 그렇게 많지도 않다.
    */
    public int mySolution(int K, int[][] travel) {
        int answer = 0;
        int entryIdx = 0;
        int[][] dp = new int[travel.length+1][K+1];

        for (int i = 1; i < dp.length; i++) {
            int[] curr = travel[i-1];
            entryIdx += curr[2];

            for (int j = entryIdx; j <= K; j++) {
                dp[i][j] = Math.max(
                    (j >= curr[0]) ? (dp[i-1][j - curr[0]] + curr[1]) : 0,
                    dp[i-1][j - curr[2]] + curr[3]
                );
            }
        }

        answer = dp[dp.length - 1][K];
        return answer;
     }
}