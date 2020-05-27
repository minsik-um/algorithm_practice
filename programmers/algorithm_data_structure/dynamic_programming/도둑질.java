package algorithm_data_structure.dynamic_programming;

class 도둑질 {
    public int mySolution(int[] money) {
        int answer = 0;
        int[] dp1 = new int[money.length];  /* 첫 번째 집을 터는 경우의 수 */
        int[] dp2 = new int[money.length];  /* 첫 번째 집을 털지 않는 경우의 수 */

        dp1[0] = money[0];
        dp1[1] = dp1[0];  /* 아래 점화식이 2번째 원소부터 보기에 첫 번째 원소 초기화해줘야 함 */
        dp2[1] = money[1];

        for (int i = 2; i < dp2.length; i++) {
            dp1[i] = Math.max(dp1[i-2] + money[i], dp1[i-1]);
            dp2[i] = Math.max(dp2[i-2] + money[i], dp2[i-1]);
        }

        answer = Math.max(dp1[money.length - 2], dp2[money.length - 1]);
        return answer;
    }

    /*
    이전 데이터를 사용하지 않는다면 귀납적 풀이를 변수 2개로 처리할 수 있음
    */
    public int bestSolution(int[] money) {
        int answer = 0;
        int[] dp1 = new int[]{money[0], money[0]};
        int[] dp2 = new int[]{0, money[1]};

        for (int i = 2; i < money.length; i++) {
            dp1 = new int[]{dp1[1], Math.max(dp1[0] + money[i], dp1[1])};
            dp2 = new int[]{dp2[1], Math.max(dp2[0] + money[i], dp2[1])};
        }

        answer = Math.max(dp1[0], dp2[1]);
        return answer;
    }
}