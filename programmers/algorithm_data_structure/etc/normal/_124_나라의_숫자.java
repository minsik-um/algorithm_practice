package algorithm_data_structure.etc.normal;

import java.util.ArrayList;
import java.util.List;

public class _124_나라의_숫자 {

    /**
     * @param n : 124 숫자로 변환할 10진법 숫자
     * @return 변환된 124 숫자 문자열
     * 
     * 단순한 3진법의 문자 치환이 아니다.
     * 124 규칙에 따라 숫자를 한 자리씩 올려 계산했다.
     * 
     * 다른 사람 풀이는 더 간단하다.
     * 풀이가 명확히 이해하긴 어렵지만,
     * 비슷한 패턴을 인지할 경우 이 패턴에서 숫자만 바꿔서 해보자.
     * 
     * String[] num = {"4","1","2"};
     * String answer = "";
     * 
     * while(n > 0){
     *      answer = num[n % 3] + answer;
     *      n = (n - 1) / 3;
     * }
     * return answer;
     */
    public String mySolution(int n) {
        Number124 num = new Number124();
        num.up(n);
        
        return num.toString();
    }
    
    class Number124 {
        private List<Integer> count;
        
        public Number124(){
            count = new ArrayList<>();
            count.add(-1);
        }
        
        public void up(int amount) {
            count.set(0, count.get(0) + amount);
            
            int idx = 0;
            while (idx < count.size() && count.get(idx) > 2) {
                int div = count.get(idx) / 3;
                int mod = count.get(idx) % 3;

                count.set(idx, mod);
                if (count.size() - 1 == idx) {
                    count.add(-1);
                }
                count.set(idx + 1, count.get(idx + 1) + div);
                idx += 1;
            }                
        }
            
        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            for (int i = count.size() - 1; i >= 0; i--) {
                int num = count.get(i);
                if (num > -1) {
                    sb.append(Integer.toString((int)Math.pow(2, num)));                    
                }                
            }
            
            return sb.toString();
        }
    }    
}