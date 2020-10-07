class 풍선_터트리기 {

    /**
     * @param a 풍선 숫자 목록
     * @return 규칙에 따라 제거했을 때 최후에 남을 수 있는 풍선 숫자의 갯수
     * 
     * 특정 풍선 기준이 최후에 남으려면
     * 양쪽의 풍선 그룹에서 제거를 마치고 1개씩 남은 상황에서 선택지에 따라 갈린다.
     * - 한 쪽만 남아있다면 (풍선 양끝): 가능
     * - 두 쪽에 숫자가 남아있을 때
     *      - 두 쪽 숫자 모두 해당 숫자보다 크면: 가능
     *      - 한 쪽 숫자가 해당 숫자보다 크고, 1개씩 남을 때까지 작은 수 제거 찬스를 안 쓴 경우: 가능
     *      - 두 쪽 모두 해당 숫자보다 작으면: 불가능
     * 
     * 따라서 특정 수 기준에서 양쪽 풍선 그룹의 각 최솟값과 비교했을 때,
     * 하나 이상이 해당 숫자보다 크면 최후까지 남을 수 있다.
     * 
     * [각 위치에서 최솟값 계산하기 : 동적 프로그래밍]
     * 미리 우측 풍선 그룹의 최솟값을 동적 프로그래밍으로 계산하는 게 중요.
     */
    public int solution(int[] a) {
        int answer = 2;
        int[] leftMinList = new int[a.length];      
        int[] rightMinList = new int[a.length];      
        
        leftMinList[0] = a[0];
        rightMinList[a.length - 1] = a[a.length - 1];                 
        for (int i = 1; i < a.length; i++) {
            int rightIdx = a.length - 1 - i;
            leftMinList[i] = Math.min(leftMinList[i-1], a[i]);
            rightMinList[rightIdx] = Math.min(rightMinList[rightIdx + 1], a[rightIdx]);
        }        

        for (int i = 1; i < a.length - 1; i++) {
            int num = a[i];
            
            if (leftMinList[i-1] > num || rightMinList[i+1] > num) {
                answer += 1;
            }
        }
        
        return answer;
    }
}