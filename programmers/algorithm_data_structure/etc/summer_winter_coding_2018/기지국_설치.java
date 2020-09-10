package algorithm_data_structure.etc.summer_winter_coding_2018;

class 기지국_설치 {
    
    public int getNewStationCount(int leftStation, int leftAreaEnd, int unit) {
        int apartCount = leftAreaEnd - leftStation;
        int newStationCount = apartCount / unit;
        
        // Math.ceil 사용 시 시간초과 발생....?
        if (apartCount % unit > 0) {
            newStationCount += 1;
        }
        
        return newStationCount;
    }
    
    /**
     * @param n 아파트 수
     * @param stations 이미 설치된 기지국
     * @param w 기지국의 전파 범위
     * @return 추가로 설치해야 하는 기지국 수
     * 
     * 포인트
     * 1. 일일이 모든 아파트의 상태를 검사하고 저장할 필요가 없다.
     * 2. Math.ceil을 쓰면 미묘하게 시간이 증가해 시간 초과 발생
     * 
     * 매번 station을 하나씩 읽어가며 처리 못한 범위를 구하고,
     * 그 범위를 기지국 범위 넓이 단위로 나누어 필요한 기지국 수를 매번 구함
     * (새로 추가하는 애들끼리 범위가 겹치지 않아야 최소 기지국 수)
     */
    public int solution(int n, int[] stations, int w) {
        int addCount = 0;
        int leftStation = 1;
        int unit = 2 * w + 1;
        
        for (int station : stations) {
            int leftAreaEnd = station - w;
            int rightAreaEnd = station + w;
            
            if (leftStation < leftAreaEnd) {
                int apartCount = leftAreaEnd - leftStation;
                    
                addCount += getNewStationCount(leftStation, leftAreaEnd, unit);                                
            }
            
            leftStation = rightAreaEnd + 1;
        }
    
        // 남은 영역 처리
        addCount += getNewStationCount(leftStation, n + 1, unit);                                

        return addCount;
    }
}