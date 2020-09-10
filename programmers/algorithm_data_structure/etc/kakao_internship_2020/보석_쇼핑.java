package algorithm_data_structure.etc.kakao_internship_2020;

import java.util.*;

class 보석_쇼핑 {
    /**
     * @param gems : 전시된 순서대로 나열된 보석 목록
     * @return 모든 보석 종류가 포함되는 연속된 부분 집합 중에서 최소 길이인 범위의 시작/끝 번호
     * 
     * 1. 오른쪽으로 하나씩 포함한다.
     * 2. 포함된 보석 종류 갯수가 유지되는 선에서 왼쪽 보석을 제거한다.
     * 3. 만약 모든 보석 종류가 포함되었다면 시작/끝 번호 기억
     * 4. 1-3을 오른쪽 끝까지 반복.
     * 5. 가장 길이가 짧은 시작/끝 번호 조합을 반환
     */
    public int[] solution(String[] gems) {
        int[] answer = null;
        int minLength = gems.length;
        int allGemTypesCount = countGemType(gems);
        ApeachBoundary boundary = new ApeachBoundary(gems);
        
        while (!boundary.isEnd()) {
            boundary.extendRight();

            if (boundary.uniqueGemCount() == allGemTypesCount) {
                if (answer == null || boundary.length() < minLength) {
                    answer = boundary.toArray();
                    minLength = boundary.length();
                }
                boundary.trimLeft();
            }
        }

        return answer;
    }
    
    public int countGemType(String[] gems) {
        Set<String> uniqueGems = new HashSet<>();
        
        for (String gem : gems) {
            uniqueGems.add(gem);
        }
        
        return uniqueGems.size();
    }
    
    class ApeachBoundary {
        private int startIdx;
        private int endIdx;
        private Map<String, Integer> countByType;
        private String[] gems;

        public ApeachBoundary(String[] gems) {
            startIdx = 0;
            endIdx = -1;
            countByType = new HashMap<>();
            
            this.gems = new String[gems.length];
            for (int i = 0; i < gems.length; i++) {
                this.gems[i] = gems[i];
            }
        }

        public void extendRight() {
            endIdx += 1;
            String newGem = gems[endIdx];
            countByType.put(newGem, countByType.getOrDefault(newGem, 0) + 1);
            
            while (countByType.get(gems[startIdx]) > 1) {
                trimLeft();
            }
        }

        public void trimLeft() {
            String removedGem = gems[startIdx];
            countByType.put(removedGem, countByType.get(removedGem) - 1);
            if (countByType.get(removedGem) == 0) {
                countByType.remove(removedGem);
            }                
            startIdx += 1;
        }

        public boolean isEnd() {
            return endIdx == gems.length - 1;
        }

        public int[] toArray() {
            return new int[]{startIdx + 1, endIdx + 1};
        }

        public int length() {
            return endIdx - startIdx;
        }

        public int uniqueGemCount() {
            return countByType.size();
        }
    }
}