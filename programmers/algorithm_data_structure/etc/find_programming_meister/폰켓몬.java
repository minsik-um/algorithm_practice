package algorithm_data_structure.etc.find_programming_meister;

import java.util.HashSet;
import java.util.Set;

public class 폰켓몬 {
    /**
     * @param nums : 포켓몬 목록(번호는 타입을 나타냄)
     * @return 그중 절반의 포켓몬을 자유롭게 선택할 수 있을 때, 
     *         가장 많이 가져갈 수 있는 포켓몬 종류 갯수
     * 
     * set을 이용하여 포켓몬의 종류 갯수를 계산한다.
     */
    public int solution(int[] nums) {
        Set<Integer> uniqueNums = new HashSet<>();
        
        for (int num : nums) {
            uniqueNums.add(num);
        }
            
        return Math.min((nums.length / 2), uniqueNums.size());
    }
}