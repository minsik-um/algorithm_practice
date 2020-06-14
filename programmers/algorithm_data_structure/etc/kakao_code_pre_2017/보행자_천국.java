package algorithm_data_structure.etc.kakao_code_pre_2017;

public class 보행자_천국 {
    /**
     * @param m : 지도 격자의 가로 크기
     * @param n : 지도 격자의 세로 크기
     * @param cityMap : m * n 크기의 지도 격자, 
     *                city_map[i][j]의 값은 도로의 상황을 나타냄
     *                - 0 : 자동차 자유 이동
     *                - 1 : 자동차 통행 금지
     *                - 2 : 자동차 직진만 가능(왼쪽 -> 오른쪽, 위 -> 아래)
     * @return 오른쪽, 아래로만 이동 가능할 때, 
     *         m, n 지점에서 가능한 경로의 수
     * 
     * 특정 지점 기준에서 가능한 경로의 수는
     * (왼쪽에서 오는 경로의 수) + (위에서 내려오는 경로의 수) 이며,
     * 각각 왼쪽/위 지점 기준으로 도로 상태에 따라 경로 갯수를 제한해서 받는다.
     */
    public int solution(int m, int n, int[][] cityMap) {
        WayInfo[][] wayTally = new WayInfo[m+1][n+1];

        // initialize
        for (int i = 0; i <= m; i++) {
            wayTally[i][0] = new WayInfo(1, 0, 0);
        }
        for (int i = 0; i <= n; i++) {
            wayTally[0][i] = new WayInfo(1, 0, 0);
        }
        wayTally[0][1] = new WayInfo(0, 0, 1);

        // dynamic programming
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                wayTally[i][j] = new WayInfo(cityMap[i-1][j-1], wayTally[i][j-1], wayTally[i-1][j]);
            }
        }

        return wayTally[m][n].waySum();
    }

    class WayInfo {
        private static final int MOD = 20170805;

        private int fromLeftSide;
        private int fromUpSide;
        private int sign;

        public WayInfo(int sign, int fromLeftSide, int fromUpSide) {
            this.fromLeftSide = fromLeftSide;
            this.fromUpSide = fromUpSide;
            this.sign = sign;
        }

        public WayInfo(int sign, WayInfo leftInfo, WayInfo upInfo) {
            this(sign, 0, 0);

            if (sign != 1) {
                fromLeftSide = leftInfo.toRightSide();
                fromUpSide = upInfo.toDownSide();
            }
        }

        public int waySum() {
            return (fromUpSide + fromLeftSide) % MOD;
        }

        public int toRightSide() {
            if (sign <= 1) {
                return waySum();
            } else {
                return fromLeftSide;
            }
        }

        public int toDownSide() {
            if (sign <= 1) {
                return waySum();
            } else {
                return fromUpSide;
            }
        }
    }
}