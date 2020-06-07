package algorithm_data_structure.binary_search;

public class 입국심사 {
    /**
     * @param n : 심사를 기다리는 사람 수
     * @param times : 각 i번째 심사위원이 한 사람을 처리하는 시간
     * @return : 총 심사시간의 최솟값
     * 
     * 최솟값을 구하기 위해 매번 사람이 (대기를 해서라도) 들어갈 장소는
     * (각 심사위원이 하던 사람 마저 처리 시간 + 이번 사람 처리하는 시간)을
     * 각 심사위원마다 따지고 최솟값을 가진 심사위원을 택하면 된다.
     * 
     * 따라서 매번 심사위원마다 위의 값을 구할려면
     * (하던 사람 포함 처리한 총 사람 수) * 처리 단위 시간 + 처리 단위 시간
     * 을 구하면 된다.
     * 
     * 여기서 매번 처리인원 수 카운트하며 선택하면 시간 초과가 발생...
     * 
     * 대신 이분탐색을 활용하여 풀이한다.
     * 목표 반환값을 k라고 하자. 
     * 심사위원마다 심사시간이 K보다 같거나 작으며,
     * 모든 심사위원이 한 번씩 더 심사할 경우 모두 K가 넘는다.
     * 따라서 K / (처리 단위 시간)으로 심사위원별로 처리 인원 수를 구하여
     * 총 처리 인원 수를 구할 수 있다.
     * 
     * 그럼 K 값의 범위를 탐색해가며 input n과 맞을 때까지 탐색하면 된다.
     * 
     * ++ 테스트 케이스 추가
     * solution(48, new int[]{1, 1, 1, 1, 1})
     * 위 테스트케이스에서는 계산 과정에서 k와 n이 일치하지 않는다.
     * 그렇지만 k > n 일 때 k 최솟값이 정답이 되므로,
     * 
     * k >= n 이 되는 결과값(총 처리시간)의 최솟값을 찾으면 된다.
     */
    public long solution(int n, int[] times) {
        long start = 0L;        
        long end = 1000000000L  * n;

        while (start <= end) {
            long middle = (start + end) / 2L;
            long peopleCount = 0L;

            for (int time : times) {
                peopleCount += middle / time;
            }
            
            if (peopleCount >= n) {
                end = middle - 1;
            } else {
                start = middle + 1;
            }
        }

        return start;
    }
}