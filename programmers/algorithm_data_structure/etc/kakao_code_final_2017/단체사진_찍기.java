package algorithm_data_structure.etc.kakao_code_final_2017;

import java.util.ArrayList;
import java.util.List;

public class 단체사진_찍기 {
    
    /**
     * @param n : 제약 조건의 갯수
     * @param data : 제약조건 목록
     * @return 제약 조건을 만족하는 모든 경우의 수 갯수 반환
     * 
     * 처음 빈 스트링에서 하나씩 추가하며 유효한 경우의 수만 남긴다.
     * 가지치기가 핵심.
     */
    public int solution(int n, String[] data) {
        char[] people = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
        boolean[] visited = new boolean[people.length];      
        List<String> possibleList = new ArrayList<>();
        dfs(visited, people, "", data, possibleList);
                
        return possibleList.size();
    }
    
    public void dfs(boolean[] visited, char[] people, String curr, String[] data, List<String> container) {
        if (curr.length() == people.length) {
            container.add(curr);
        } 

        for (int i = 0; i < people.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                boolean isValid = true;
                String newStr = curr + people[i];

                for (String constraint : data) {
                    char c1 = constraint.charAt(0);
                    char c2 = constraint.charAt(2);
                    char sign = constraint.charAt(3);            
                    int amount = Integer.parseInt("" + constraint.charAt(4));

                    int idxC1 = newStr.indexOf(c1);
                    int idxC2 = newStr.indexOf(c2);

                    if (idxC1 < 0 || idxC2 < 0) {
                        break;
                    }
                                    
                    if (sign == '<') {
                        if (Math.abs(idxC1 - idxC2) - 1 >= amount) {
                            isValid &= false;
                            break;
                        }
                    } else if (sign == '>') {
                        if (Math.abs(idxC1 - idxC2) - 1 <= amount) {
                            isValid &= false;
                            break;
                        }
                    } else {
                        if (Math.abs(idxC1 - idxC2) - 1 != amount) {
                            isValid &= false;
                            break;
                        }
                    }
                }
                
                if (isValid) {
                    dfs(visited, people, newStr, data, container);
                }
                visited[i] = false;
            }
        }
    }
}