package algorithm_data_structure.etc.kakao_winter_internship_2019;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class 튜플 {
    /**
     * @param s 주어진 조건에 따라 나열된 튜플 문자열
     * @return 튜플
     * 
     * String 메소드를 최대한 활용하자.
     * replaceAll, split 등을 최대한 활용하자.
     */
    public int[] solution(String s) {
        List<Integer> tuple = new ArrayList<>();
        Set<Integer> addedNums = new HashSet<>();
        
        String[] parsedNums = s.replaceAll("\\{", " ").replaceAll("\\}", " ").trim().split(" , ");
        Arrays.sort(parsedNums, (a, b) -> a.length() - b.length());
                
        for (String nums : parsedNums) {
            String[] splitedNums = nums.split(",");
            for (String num : splitedNums) {
                int n = Integer.parseInt(num);
                if (addedNums.add(n)) {
                    tuple.add(n);
                }                 
            }
        }
        
        return toArray(tuple);
    }
    
    public int[] toArray(List<Integer> list) {
        int[] ret = new int[list.size()];
        
        for (int i = 0; i < ret.length; i++) {
            ret[i] = list.get(i);
        }
        
        return ret;
    }
}