package algorithm_data_structure.etc.normal;

public class two_x_n_타일링 {
    /**
     * @param n : 목표 가로의 길이
     * @return 목표 가로의 길이를 만족하는 모든 경우의 수를 반환
     * 
     * 더 이상 분리될 수 없는 단위가 [세로 긴 것 1개]와 [가로 긴 것 2개를 상하로 붙인 것] 이렇게 2개다.
     * 가로가 n일 때 경우의 수는 [끝 단위가 첫 번째 단위 경우의 수] + [끝 단위가 두 번째 단위 경우의 수]이다.
     * (가로 길이를 맞춰 생각하면)
     * 첫 번째 단위에서는 가로가 n-1일 때 각 경우의 수에 붙인 것이고,
     * 두 번째 단위일 때는 가로가 n-2일 때 각 경우의 수에 단위를 붙인 것이다.
     */
    public int solution(int n) {
        int[] dp = new int[n+1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i-1] + dp[i-2]) % 1000000007;
        }
        return dp[n];
    }
}