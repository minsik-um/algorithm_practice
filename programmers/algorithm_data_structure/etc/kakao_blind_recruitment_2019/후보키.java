package algorithm_data_structure.etc.kakao_blind_recruitment_2019;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class 후보키 {
    public int solution(String[][] relation) {
        int answer = 0;
        int columnSize = relation[0].length;
        List<Integer> uniqueSubsets = new ArrayList<>();

        // 컬럼을 선택한 모든 경우의 수 탐색
        for (int subset = 1; subset < (1 << columnSize); subset++) {
            if (isPossible(uniqueSubsets, subset) && isCandidateKey(relation, subset)) {
                answer += 1;
                uniqueSubsets.add(subset);
            }
        }

        return answer;
    }

    public boolean isPossible(List<Integer> uniqueSubsets, int subset) {
        boolean ret = true;

        for (int uniqueSubset : uniqueSubsets) {
            if ((subset & uniqueSubset) == uniqueSubset) {
                ret = false;
                break;
            }
        }

        return ret;
    }

    public boolean isCandidateKey(String[][] relation, int subset) {
        boolean ret = true;
        int columnSize = relation[0].length;
        int rowSize = relation.length;
        Set<String> rows = new HashSet<>();

        for (int i = 0; i < rowSize; i++) {
            StringBuilder builder = new StringBuilder();

            for (int j = 0; j < columnSize; j++) {
                if (((1 << j) & subset) > 0) {
                    builder.append(relation[i][j]);
                    builder.append(" ");
                }
            }
            
            String row = builder.toString();

            if (rows.contains(row)) {
                ret = false;
                break;
            } else {
                rows.add(row);
            }
        }

        return ret;
    }
}