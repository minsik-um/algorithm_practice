package algorithm_data_structure.etc.kakao_blind_recruitment_2019;

import java.util.Arrays;

public class 실패율 {
    /**
     * @param N : 스테이지의 갯수
     * @param stages : 유저별 머무는 스테이지 현황
     * @return 실패율(스테이지 지금 머무는 사람 수 / 스테이지 도착 사람 수)
     *         높은 스테이지 순으로 스테이지 번호 반환
     * 
     * 1. counter : 스테이지별 머무는 사람 수를 계산한다.
     *    (모든 스테이지를 완료해 N+1인 유저수도 세야 한다)
     * 2. rate : 실패율과 대응 스테이지 번호를 저장한다. 
     *           역순으로 counter를 보면서 스테이지 도착 사람 수를 계산한다.
     * 3. rate를 실패율 값으로 내림차순 정렬한다.
     * 4. 스테이지번호를 answer에 담아 반환한다.
     */
    public int[] solution(int N, int[] stages) {
        int[] answer = new int[N];
        int[] counter = new int[N+1];
        float[][] rate = new float[N][2];
        
        for (int stage : stages) {
            counter[stage-1] += 1;
        }
        
        int total = counter[N];
        for (int i = N-1; i >= 0; i--) {
            int stay = counter[i];
            total += stay;
            rate[i][0] = i+1;
            rate[i][1] = stay * 1f / total;
        }

        Arrays.sort(rate, (o1, o2) -> {
            float ret = o2[1] - o1[1];
            if (ret > 0) {
                return 1;
            } else if (ret < 0) {
                return -1;
            } else {
                return 0;
            }
        });

        for (int i = 0; i < answer.length; i++) {
            answer[i] = (int)rate[i][0];           
        }
        
        return answer;
    }
}