package algorithm_data_structure.greedy;


public class 체육복 {
    
    /* 
     * (mySolution) 
     * 처음엔 아래 순서로 반복문을 3번 돌았다.
     * 1. 도난 당했지만 스스로 여벌 가진 학생들 처리
     * 2. 도난 당했고 양쪽 중 1명만 있어서 그 사람이 주면 안되는 경우 처리
     * 3. 그외 도난 당했는데 수급 가능하면 처리
     * 
     * (bestSolution)
     * best 풀이를 참조하면 어자피 3번은 양쪽 중 아무거나 괜찮은 거니까
     * 2번과 합칠 수 있다. 또한 answer 계산 방식도 마지막에 끝까지 못받는 경우만
     * 처리하면 된다.
    */
    public int bestSolution(int n, int[] lost, int[] reserve) {
        int answer = n;
        int[] status = new int[n+2];  // 여벌 있음 : 1, 도난당하고 여벌 없음 : -1, 그외: 0
        
        for (int lo : lost){
            status[lo] = -1;
        }

        for (int re : reserve){
            status[re] += 1;            
        }

        for(int i = 1; i <= n; i++){
            if (status[i] == -1) {
                if (status[i-1] == 1){
                    status[i-1] = 0;
                }else if (status[i+1] == 1){
                    status[i+1] = 0;
                }else{
                    answer -= 1;
                }

            }
        }

        return answer;
    }
    
    public int mySolution(int n, int[] lost, int[] reserve) {
        int lostCount = 0;
        int[] status = new int[n+2];  // 여벌 있음 : 1, 도난당하고 여벌 없음 : -1, 그외: 0
        
        for (int lo : lost){
            status[lo] = -1;
            lostCount += 1;
        }

        // 도난 당했지만 스스로 여벌을 가진 학생들 처리
        for (int re : reserve){
            if (status[re] == -1){
                lostCount -= 1;
            }
            status[re] += 1;            
        }

        // 도난 당했고 양쪽 중 한 쪽만 있어서 그 사람이 주면 안되는 경우 처리
        // 이걸 먼저 처리해야 남은 사람들은 여벌을 어떻게 받든 최적 상태가 나옴
        for(int i = 1; i < status.length; i++){
            if (status[i] == -1) {
                if (status[i-1] == 1 && status[i+1] != 1){
                    status[i-1] = 0;
                    lostCount -= 1;    
                }else if (status[i-1] != 1 && status[i+1] == 1){
                    status[i+1] = 0;
                    lostCount -= 1;    
                } 
            }
        }

        // 남은 사람들은 순차적으로 처리
        for(int i = 1; i < status.length; i++){
            if (status[i] == -1 && status[i+1] + status[i-1] >= 1) {          
                lostCount -= 1;    
            }
        }

        return n - lostCount;
    }
}