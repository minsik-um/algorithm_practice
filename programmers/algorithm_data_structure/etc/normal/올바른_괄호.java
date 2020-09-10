package algorithm_data_structure.etc.normal;

public class 올바른_괄호 {
    /**
     * @param s : 괄호 목록
     * @return 괄호 목록이 올바르게 열고 닫혔는지 확인
     */
    boolean solution(String s) {
        int openCount = 0;
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (c == '(') {
                openCount += 1;
            } else {
                openCount -= 1;
                
                if (openCount < 0) {
                    break;
                }
            }
        }

        return openCount == 0;
    }
}