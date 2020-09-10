package algorithm_data_structure.etc._2017_tips;

import java.util.Stack;

class 짝지어_제거하기 {
    /**
     * @param s : 검사할 문자열
     * @return 짝지어 제거로 문자열을 모두 제거할 수 있는지 판단
     * 
     * 스택을 이용하여 짝지어 제거 가능한지 판단
     */
    public int solution(String s) {
        int answer = 0;
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            
            if (!stack.empty() && stack.peek() == c) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        
        if (stack.empty()) {
            answer = 1;
        }
        
        return answer;
    }
}