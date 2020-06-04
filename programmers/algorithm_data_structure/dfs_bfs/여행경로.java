package algorithm_data_structure.dfs_bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class 여행경로 {
    /**
     * 스택 구조를 이용해 dfs를 구현
     * 다만 매번 방문하지 않은 노드를 찾기 위해
     * 현재 경로의 길이만큼 걸린다는 문제가 있음
     * (better solution 참고)
     */
    public String[] mySolution(String[][] tickets) {
        String[] answer = new String[tickets.length + 1];
        answer[0] = "ICN";
        String[][] sortedTickets  = deepCopyAndSort(tickets, true);
        Stack<List<Integer>> paths = new Stack<>();

        // 시작점 설정
        for (int i = 0; i < sortedTickets.length; i++) {
            if (sortedTickets[i][0].equals("ICN")) {
                List<Integer> first = new ArrayList<>();
                first.add(i);
                paths.add(first);
            }
        }

        // 탐색
        while (paths.peek().size() < tickets.length) {
            List<Integer> currPath = paths.pop();

            for (int i = 0; i < sortedTickets.length; i++) {
                int lastTicketIdx = currPath.get(currPath.size() - 1);
                String recentLocation = sortedTickets[lastTicketIdx][1];
                if (!currPath.contains(i) && recentLocation.equals(sortedTickets[i][0])) {
                    List<Integer> copiedPath = deepCopy(currPath);
                    copiedPath.add(i);
                    paths.add(copiedPath);
                }
            }
        }

        // 정답 형식에 맞게 변환
        for (int i = 0; i < paths.peek().size(); i++) {
            answer[i+1] = sortedTickets[paths.peek().get(i)][1];
        }
        
        return answer;
    }

    /*
     * dfs를 재귀함수로 구현한 풀이
     * 위의 풀이에서 스택을 사용할 경우
     * 실패한 탐색을 되돌리는 과정(방문 초기화)을 구별하는 게 복잡함
     * 
     * 재귀함수로 이용하면 구별이 간단함
     */
    public String[] betterSolution(String[][] tickets) {
        String[] answer = new String[tickets.length + 1];
        answer[0] = "ICN";
        boolean[] visited = new boolean[tickets.length];
        String[][] sortedTickets = deepCopyAndSort(tickets, false);
        List<Integer> ret = null;

        for (int i = 0; i < sortedTickets.length; i++) {
            if (sortedTickets[i][0].equals("ICN")) {
                List<Integer> first = new ArrayList<>();
                first.add(i);
                visited[i] = true;
                ret = dfs(sortedTickets, visited, first);
                if (ret != null) {
                    break;
                }
                visited[i] = false;
            }
        }

        for (int i = 0; i < ret.size(); i++) {
            answer[i+1] = sortedTickets[ret.get(i)][1];
        }

        return answer;
    }

    public List<Integer> dfs(String[][] tickets, boolean[] visited, List<Integer> curr) {
        List<Integer> ret = null;
        if (curr.size() == tickets.length) {
            return curr;
        }

        String lastAirport = tickets[curr.get(curr.size() - 1)][1];
        for (int i = 0; i < tickets.length; i++) {
            if (!visited[i] && tickets[i][0].equals(lastAirport)) {
                List<Integer> next = deepCopy(curr);
                next.add(i);
                visited[i] = true;
                ret = dfs(tickets, visited, next);
                if (ret != null) {
                    break;
                }
                visited[i] = false;
            }
        }

        return ret;
    }

    public List<Integer> deepCopy(List<Integer> origin) {
        List<Integer> copy = new ArrayList<>(origin.size());
        for (int value : origin) {
            copy.add(value);
        }
        return copy;
    }

    public String[][] deepCopyAndSort(String[][] origin, boolean reverse){
        String[][] sortedCopy = new String[origin.length][2];

        for (int i = 0; i < origin.length; i++) {
            sortedCopy[i] = origin[i];
        }

        Arrays.sort(sortedCopy, (o1, o2) -> {
            if (reverse) {
                int compareResult = o2[0].compareTo(o1[0]);
                return (compareResult == 0) ? o2[1].compareTo(o1[1]) : compareResult;
            } else {
                int compareResult = o1[0].compareTo(o2[0]);
                return (compareResult == 0) ? o1[1].compareTo(o2[1]) : compareResult;
            }
        });

        return sortedCopy;
    }

}