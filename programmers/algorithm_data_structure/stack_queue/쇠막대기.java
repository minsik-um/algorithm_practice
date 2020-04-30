package algorithm_data_structure.stack_queue;

import java.util.*;

class 쇠막대기 {

    public int mySolution(String arrangement) {
        int answer = 0;
        int openedBar = 0;
        char lastSign = ' ';
        
        for (int i = 0; i < arrangement.length(); i++){
            final char sign = arrangement.charAt(i);            
            
            if (sign == '('){
                openedBar += 1;
            }else{
                if (lastSign == '('){     // () 레이저
                    openedBar -= 1;       // 레이저의 '('를 bar로 잘못 올린 거 제거
                    answer += openedBar;
                }else{
                    openedBar -= 1;
                    answer += 1;
                }                
            }
            lastSign = sign;
        }
        
        return answer;
    }

    /*
     * 스택을 사용해서 특별한 풀이법이 있는 건 아니다.
     * 두 방법 모두 지난 기호 중에 가장 최근의 기호를 분기에 활용한다.
     * 대신 스택을 활용한 이런 구조는 코드 확장이 좋다.
     * 스택에 숫자 대신에 막대기 객체를 넣으면 다른 속성도 활용할 수 있다.
     */
    public int bestSolution(String arrangement) {
        int answer = 0;
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < arrangement.length(); i++) {
            if (arrangement.charAt(i) == '(') {
                st.push(i);
            } else if (arrangement.charAt(i) == ')') {
                if (st.peek() + 1 == i) {
                    st.pop();
                    answer += st.size();
                } else {
                    st.pop();
                    answer += 1;
                }
            }
        }
        return answer;
    }
}