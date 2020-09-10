package algorithm_data_structure.etc.summer_winter_coding_2018;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class 스킬트리 {
    /**
     * @param skill : 선행 스킬 순으로 정렬한 스킬 목록
     * @param skill_trees : 유저들이 만든 스킬 트리 목록
     * @return 선행 스킬 규칙이 준수된 스킬 트리의 갯수 반환
     * 
     * Map 자료구조를 이용해 선행 스킬을 연결하고, 
     * 매번 선행 스킬을 학습했는지 Set 자료구조에 담고 확인한다.
     */
    public int mySolution(String skill, String[] skill_trees) {
        int answer = 0;
        Map<Character, Character> priorSkill = getPriorSkill(skill);

        for (String skill_tree : skill_trees) {
            if (isValid(priorSkill, skill_tree)) {
                answer += 1;
            }
        }
        return answer;
    }
    
    public Map<Character, Character> getPriorSkill(String skill) {
        Map<Character, Character> priorSkill = new HashMap<>();

        for (int i = 1; i < skill.length(); i++) {
            char prior = skill.charAt(i - 1);
            char curr = skill.charAt(i);
            
            priorSkill.put(curr, prior);
        }
        
        return priorSkill;
    }
    
    public boolean isValid(Map<Character, Character> priorSkill, String skill_tree) {
        boolean ret = true;
        Set<Character> isLearned = new HashSet<>();
        
        for (int i = 0; i < skill_tree.length(); i++) {
            char skill = skill_tree.charAt(i);
            if (!priorSkill.containsKey(skill) 
                    || isLearned.contains(priorSkill.get(skill))) {
                isLearned.add(skill);
            } else {
                ret = false;
                break;
            }
        }
        
        return ret;
    }

    /**
     * 특정 스킬트리에 속한 스킬 외 요소를 전부 지운 다음
     * 선행 스킬에 맞게 찍었다면 skill String과 일치할 것이다.
     */
    public int bestSolution(String skill, String[] skill_trees) {
        int answer = 0;

        for (String skill_tree : skill_trees) {
            if (skill.indexOf(skill_tree.replaceAll("[^" + skill  + "]", "")) == 0) {
                answer += 1;
            }
        }

        return answer;

        /*
        ** iterator를 사용하면 해당 리스트를 도는 중간에 요소들을 지울 수 있다. **
        ** Arrays.asList를 사용하면 int[] 형 같이 원시 타입 배열을 리스트로 한 번에 변환할 수 있다.

        List<String> skillTrees = new ArrayList<>(Arrays.asList(skill_trees));
        Iterator<String> it = skillTrees.iterator();
        while (it.hasNext()) {
            if (skill.indexOf(it.next().replaceAll("[^" + skill + "]", "")) != 0) {
                it.remove();
            }
        }
        */
    }
}