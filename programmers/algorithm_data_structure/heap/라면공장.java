package algorithm_data_structure.heap;

import java.util.*;

class 라면공장 {
    /*
     * 1일씩 진행하며 가능한 공급 중에서 1개씩 선택하는 풀이
     * (파이썬 버전 풀이와 유사함)
     * 개선점 1: 다만 map을 만들지 않아도 되었음 -> bestSolution 참고
     * 개선점 2: 모든 날짜를 보지 않아도 됨 -> bestSolution2, 3 참고 
     */
    public int mySolution(int stock, int[] dates, int[] supplies, int k) {
        int answer = 0;
        int currStock = stock;
        Map<Integer, Integer> supplyMap = new HashMap<>();
        PriorityQueue<Integer> validSupplies = new PriorityQueue<>((n1, n2) -> n2 - n1);

        for (int i = 0; i < dates.length; i++){
            supplyMap.put(dates[i], supplies[i]);
        }

        for(int date = 0; date < k; date++){

            if (supplyMap.containsKey(date)){
                validSupplies.add(supplyMap.get(date));
            }

            if (currStock == 0){
                currStock += validSupplies.poll();
                answer += 1;
            }
            currStock -= 1;
        }
        
        return answer;
    }

    public int bestSolution(int stock, int[] dates, int[] supplies, int k){
        int answer = 0;
        int idxToSupply = 0;
        int currStock = stock;
        PriorityQueue<Integer> validSupplies = new PriorityQueue<>((n1, n2) -> n2 - n1);

        for(int date = 0; date < k; date++){

            if (idxToSupply < dates.length && date == dates[idxToSupply]){
                validSupplies.add(supplies[idxToSupply]);
                idxToSupply += 1;
            }

            if (currStock == 0){
                currStock += validSupplies.poll();
                answer += 1;
            }
            currStock -= 1;
        }
        
        return answer;
    }

    public int bestSolution2(int stock, int[] dates, int[] supplies, int k){
        /*
         * 조건 다 무시하고 그냥 매번 최댓값을 선택하면 된다... 
         */
        int answer = 0;
        int totalStock = stock;
        int idxToSupply = 0;
        PriorityQueue<Integer> validSupplies = new PriorityQueue<>((n1, n2) -> n2 - n1);

        while (totalStock < k){
            while (idxToSupply < dates.length && dates[idxToSupply] <= totalStock){
                validSupplies.add(supplies[idxToSupply]);
                idxToSupply += 1;
            }

            totalStock += validSupplies.poll();
            answer += 1;
        }

        return answer;
    }

    /*
     * 다음 공급일까지 무조건 버텨야 한다는 걸 이용한 풀이
     * 공급을 기준으로 이동하며 계산한다.
     * 
     * 1. 가능한 공급을 힙에 업데이트
     * 2. 다음 공급일 기준까지 버틸 수 없다면 남은 공급 중 최대 공급 사용
     * 
     * 3. k에 도달할 때까지 공급 사용
     */
    public int bestSolution3(int stock, int[] dates, int[] supplies, int k) {
        int answer = 0;
        int totalStock = stock;
        int idxToSupply = 0;
        PriorityQueue<Integer> validSupplies = new PriorityQueue<>((n1, n2) -> n2 - n1);

        while (totalStock < k){
            while (idxToSupply < dates.length && dates[idxToSupply] <= totalStock){
                validSupplies.add(supplies[idxToSupply]);
                idxToSupply += 1;
            }

            // 모든 공급이 가능하다면 바로 k와 비교를 위해 break
            if(idxToSupply >= dates.length){
                break;
            }

            while (!validSupplies.isEmpty() && totalStock < dates[idxToSupply]){
                totalStock += validSupplies.poll();
                answer += 1;
            }
        }

        while (!validSupplies.isEmpty() && totalStock < k){
            totalStock += validSupplies.poll();
            answer += 1;
        }

        return answer;
    }
}