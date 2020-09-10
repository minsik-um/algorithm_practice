package algorithm_data_structure.etc.kakao_winter_internship_2019;

class 징검다리_건너기 {

    /**
     * @param stones : 돌마다 밟을 수 있는 횟수
     * @param k : 현재 위치에서 (k-1) 갯수만큼 돌을 건너뛸 수 있음
     * @return 처음으로 건너뛸 수 없는 순간까지 건넌 아이들의 수
     * 
     * stones의 숫자 중에서 연속한 k개의 숫자 그룹 내 최댓값 중에서 최솟값을 구해야 한다.
     * k 범위가 클수록 숫자 그룹 내 최댓값은 커질 수 밖에 없다.
     * 비례 관계를 활용하여 역으로 return 값을 기준으로 이분탐색을 수행해간다.
     * 
     * 최댓값이 n 이라면, 가능한 k의 범위의 최댓값은 바로 구할 수 있다.
     * n이 정답보다 낮다면 대응되는 k 범위 최댓값은 낮고,
     * n이 정답보다 같다면 대응되는 k 범위 최댓값은 주어진 k와 같거나 클 것이다.
     * 
     * 이를 이용해 이분탐색을 수행한다.
     */
    public int solution(int[] stones, int k) {
        int start = 1;
        int end = getMax(stones);
        int answer = end;
        
        while (start <= end) {
            int middle = (start + end) / 2;
            int maxK = getMaxK(middle, stones);
            
            if (maxK >= k) {
                answer = Math.min(answer, middle);
                end = middle - 1;
            } else {
                start = middle + 1;                
            }
        }

        return answer;
    }

    public int getMaxK(int middle, int[] stones) {
        int maxK = 0;
        int currK = 0;

        for (int stone : stones) {
            if (stone > middle) {
                maxK = Math.max(maxK, currK);
                currK = 0;
            } else {
                currK += 1;
            }                
        }
        maxK = Math.max(maxK, currK);

        return maxK;
    }
    
    public int getMax(int[] stones) {
        int maxValue = 0;
        
        for (int stone : stones) {
            maxValue = Math.max(maxValue, stone);
        }
        
        return maxValue;
    }
}