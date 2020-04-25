package algorithm_data_structure.hash;

import java.util.*;


class 완주하지_못한_선수 {
    /*
    http://tech.javacafe.io/2018/12/03/HashMap/
    http://javabypatel.blogspot.com/2015/10/time-complexity-of-hashmap-get-and-put-operation.html
    */
    public String bestSolution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> hm = new HashMap<>();
        for (String player : participant) hm.put(player, hm.getOrDefault(player, 0) + 1);
        for (String player : completion) hm.put(player, hm.get(player) - 1);
      
        // 이부분에서 hm.keySet()을 이용해도 된다.
        /*
        for (String name : hm.keySet()) {
            if (hm.get(name) != 0){
                answer = name;
            }
        }
        */

        // value값을 참조할거면 entrySet이 속도 면에서 빠르다.
        // https://stackoverflow.com/questions/3870064/performance-considerations-for-keyset-and-entryset-of-map
        Set<Map.Entry<String, Integer>> entries = hm.entrySet();
        for (Map.Entry<String, Integer> entry : entries) {
            if (entry.getValue() != 0){
                answer = entry.getKey();
            }
        }

        return answer;
    }

    public String my_solution(String[] participant, String[] completion) {
        /*
        best_solution과 비교했을 때 내가 배울 점
        1. return을 중간에 하지 말고 끝에 한 번, 대신 answer에 값을 저장
        2. 한 줄 표현이 가능하면 괄호 없이 한 줄로.
        3. getOrDefault 사용
        4. entrySet 사용 (keySet 사용후 value를 매번 가져오는 건 search 때문에 비효율적이라고 한다)
        */
        HashMap<String , Integer> map = new HashMap<String , Integer>();
         for(String name: completion){
            if (map.containsKey(name)){
                 map.put(name, map.get(name)+1);
            }else{
                 map.put(name, 1);
            }
        }

        for(String name: participant){
            if (!map.containsKey(name) || map.get(name) == 0){
                return name;
            }else {
                map.put(name, map.get(name)-1);
            }
        }
        return null;
    }


    
}