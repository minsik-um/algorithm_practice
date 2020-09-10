package algorithm_data_structure.etc.kakao_internship_2020;

import java.util.ArrayList;
import java.util.List;

public class 수식_최대화 {
    /**
     * @param expression : 주어진 계산식
     * @return 모든 연산자 우선순위를 적용했을 때 나올 수 있는 
     *         계산 결과의 최댓값
     * 
     * 1. 재귀적으로 순열을 구한다.
     * 2. split과 재귀탐색을 이용해 연산을 구해간다.
     * (참고로 연산자 문자열의 정렬 순서는 우선순위 낮은 것부터 높은 순으로)
     */
    public long solution(String expression) {
        long answer = 0;
        List<String> priorities = getPriorities(expression);

        for (String priority : priorities) {
            long ret = customCalculate(priority, expression);
            answer = Math.max(answer, Math.abs(ret));
        }
        
        return answer;
    }
    
    // 가능한 우선순위 순열 반환
    public List<String> getPriorities(String expression) {
        List<String> ret = new ArrayList<>();
        char[] operators = new char[]{'*', '+', '-'};
        boolean[] visited = new boolean[operators.length];
        
        appendPriorities(operators, 0, ret, "", visited);
        
        return ret;
    }
    
    private void appendPriorities(char[] operators, int depth, List<String> container, String curr, boolean[] visited) {   
        if (depth == operators.length) {
            container.add(curr);
        } else {
            for (int i = 0; i < operators.length; i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    appendPriorities(operators, depth + 1, container, curr + operators[i], visited);
                    visited[i] = false;
                }
            }
        }
    }
    
    // 주어진 우선순위에 따라 계산
    public long customCalculate(String priority, String expression) {
        if (priority.length() == 0) {
            return Long.parseLong(expression);
        }
        
        long ret = 0L;
        String operator = priority.substring(0, 1);
        String[] splited = expression.split("\\" + operator);
        
        if (splited.length == 1) {
            ret = customCalculate(priority.substring(1), splited[0]);
        } else if (operator.equals("*")) {
            ret = 1L;
            for (int i = 0; i < splited.length; i++) {
                ret *= customCalculate(priority.substring(1), splited[i]);
            }
        } else if (operator.equals("+")) {
            for (int i = 0; i < splited.length; i++) {
                ret += customCalculate(priority.substring(1), splited[i]);
            }
        } else {
            ret = customCalculate(priority.substring(1), splited[0]);
            for (int i = 1; i < splited.length; i++) {
                ret -= customCalculate(priority.substring(1), splited[i]);
            }                
        }
        
        return ret;
    }
}