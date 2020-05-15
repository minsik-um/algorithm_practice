package algorithm_data_structure.greedy;

import java.util.Stack;

public class 큰_수_만들기 {
    
    /*
     * 스택을 사용하지 않은 풀이
     * 이전에 본 값과 최댓값을 이후에 활용하지 않기에
     * 본 값을 다시 확인하는 중복이 발생
     * => 아래 있는 best 풀이처럼 스택 황용
     */
    public String mySolution(String number, int k) {
        String prefix = "";
        String currNumber = number;
        int currK = k;

        while (currK > 0 && prefix.length() < number.length() - k) {
            int maxIndex = 0;
            char maxValue = currNumber.charAt(maxIndex);

            for (int i = 1; i < currK + 1; i++){
                if (maxValue < currNumber.charAt(i)){
                    maxIndex = i;
                    maxValue = currNumber.charAt(maxIndex);
                }
            }

            currNumber = currNumber.substring(maxIndex + 1, currNumber.length());
            currK -= maxIndex;
            prefix += maxValue;
        }

        if (currK == 0){
            prefix += currNumber;
        }
        
        return prefix;
    }

    /*
     * [ 스택을 활용한 코드 ]
     * 앞자리에 올수록 큰 숫자가 와야 최댓값이 나오므로, 
     * 앞자리의 숫자보다 뒷자리가 더 크면 (뒷자리 숫자가 앞자리 숫자들을 대체 가능한 만큼) 
     * 앞자리 숫자를 지우고 뒷자리를 대신 앞에 앉힌다.
     * 
     * 앞에서부터 숫자를 확인해가는 반면 
     * 비교는 최근의 뒷자리부터 앞자리 순으로 해나가기에
     * 스택 구조를 사용
     */
    public String bestSolution(String number, int k) {
        char[] result = new char[number.length() - k];
        Stack<Character> stack = new Stack<>();

        for (int i=0; i<number.length(); i++) {
            char c = number.charAt(i);
            while (!stack.isEmpty() && stack.peek() < c && k-- > 0) {
                stack.pop();
            }
            stack.push(c);
        }
        for (int i=0; i<result.length; i++) {
            result[i] = stack.get(i);
        }
        return new String(result);
    }
}