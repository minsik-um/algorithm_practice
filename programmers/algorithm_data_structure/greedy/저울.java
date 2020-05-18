package algorithm_data_structure.greedy;

import java.util.ArrayList;
import java.util.List;

public class 저울 {

    /*
     * 1 1 2 3 6 7 30 을 예로 들자.
     * 우선 무게의 최솟값은 가장 작은 추 무게인 1이다.
     * 
     * (1, 1, 2)로 만드는 수의 조합과 (1, 1, 2, 3)로 만드는 수의 조합을 보면
     * 3을 포함하여 만드는 수 중에서 가장 작은 수는 3이다(그 새로운 추의 무게 자체)
     * 
     * 다시 말해 한 상태에서 다음으로 큰 추 하나를 추가했을 때
     * 그 조합 결과 수들이 연속될려면 새롭게 추가되는 조합의 최솟값(새로운 추의 무게)이
     * 기존 조합의 최댓값 + 1 과 같거나 작아야 한다.
     * 
     * 측정 불가능한 무게의 최솟값을 구해야 하므로 추를 작은 순으로 정렬하고
     * 작은 추를 하나씩 포함해가며 연속성이 깨지는 순간을 찾는다.
     */
    public int solution(int[] weight) {
        List<Integer> sortedWeight = new ArrayList<>();
        int maxNum = 0;  // 연속된 수 중에서 최댓값

        for (int w : weight) {
            sortedWeight.add(w);            
        }
        sortedWeight.sort((o1, o2) -> o1 - o2);

        for (int w : sortedWeight) {
            if (maxNum + 1 < w) {
                break;
            }
            maxNum += w;
        }

        return (maxNum + 1);
    }
}
