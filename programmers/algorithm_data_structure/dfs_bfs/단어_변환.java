package algorithm_data_structure.dfs_bfs;

import java.util.LinkedList;
import java.util.List;

public class 단어_변환 {

    /*
     * 모든 경우의 수마다 개별 visited 상태를 체크할 필요는 없다.
     * 그냥 한 번에 visited 체크해주면 된다(best 풀이 참고함)
     *
     * 최단 거리의 경로 중에서 아직 방문하지 않는 노드들은
     * 각 단계에서만 접근 가능하므로 영향을 받지 않거나
     * 이미 접근 가능했던 것들은 그걸 가진 다른 경우의 수에서 최소 경로를 가진다.
     */
    public int betterSolution(String begin, String target, String[] words) {
        int answer = 0;
        int n = words.length;
        boolean[] visited = new boolean[n];
        List<String> currWords = new LinkedList<>();        
        currWords.add(begin);

        outer:
        for (int i = 0; i < n; i++) {
            List<String> nextWords = new LinkedList<>();

            for (String currWord : currWords) {
                for (int nextIdx = 0; nextIdx < n; nextIdx++) {
                    String nextWord = words[nextIdx];
                    if (!visited[nextIdx] && difference(currWord, nextWord)) {
                        if (nextWord.equals(target)) {
                            answer = i + 1;
                            break outer;
                        }else {
                            visited[nextIdx] = true;
                            nextWords.add(nextWord);
                        }
                    }
                }
            }

            currWords = nextWords;
        }

        return answer;
    }

    /*
     * BFS를 이용해 매번 단어 탐색 
     */
    public int mySolution(String begin, String target, String[] words) {
        int answer = 0;
        List<String> currWords = new LinkedList<>();
        currWords.add(begin);

        outer:
        for (int i = 0; i < words.length; i++) {
            List<String> nextWords = new LinkedList<>();

            for (String currWord : currWords) {
                for (String nextWord : words) {
                    System.out.println(currWord + "/" + nextWord + "/" + difference(currWord, nextWord));
                    if (difference(currWord, nextWord)) {
                        if (nextWord.equals(target)) {
                            answer = i + 1;
                            break outer;
                        }else {
                            nextWords.add(nextWord);
                        }
                    }
                }
            }
            currWords = nextWords;
        }

        return answer;
    }

    public boolean difference(String str1, String str2) {
        int count = 0;

        for (int i = 0; i < str1.length(); i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                count += 1;
                if (count == 2) {
                    break;
                }
            }
        }

        return (count == 1);
    }
}