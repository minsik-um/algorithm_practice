package algorithm_data_structure.etc.summer_winter_coding_2018;

import java.util.HashSet;
import java.util.Set;

public class 영어_끝말잇기 {
    public int[] solution(int n, String[] words) {
        int[] answer = new int[]{0, 0};
        Set<String> saidWords = new HashSet<>();
        saidWords.add(words[0]);

        for (int i = 1; i < words.length; i++) {
            String lastWord = words[i-1];
            String word = words[i];
            
            if (lastWord.charAt(lastWord.length() - 1) != word.charAt(0)
                    || saidWords.contains(word)) {
                answer = new int[]{i % n + 1, i / n + 1};
                break;
            }
            
            saidWords.add(word);
        }

        return answer;
    }
}