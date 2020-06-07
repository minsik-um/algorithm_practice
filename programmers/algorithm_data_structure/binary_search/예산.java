package algorithm_data_structure.binary_search;

public class 예산 {
    /*
     * 입력값
     * - budgets : 요청한 예산 값 목록
     * - M : 예산 총합 최댓값
     * 
     * 반환값
     * - 예산 총합 최댓값 이하이면서 최대한 돈을 나눠주는 개별 예산 상한액 기준값
     * - 요청한 모든 금액이 가능하면 상한액은 요청 금액의 최댓값으로 한다.
     * 
     * 과정
     * - 가능한 반환값 범위를 이분탐색한다.
     */
    public int mySolution(int[] budgets, int M) {
        int start = 1;
        int maxValue = 0;
        for (int budget: budgets) {
            maxValue = Math.max(maxValue, budget);
        }
        int end = maxValue;

        while (start <= end) {
            int middle = (start + end) / 2;
            int sum = 0;

            for (int budget : budgets) {
                sum += (budget > middle) ? middle : budget;
            }

            if (sum > M) {
                end = middle - 1;
            }else {
                start = middle + 1;
            } 
        }
       
        return end;
    }
}