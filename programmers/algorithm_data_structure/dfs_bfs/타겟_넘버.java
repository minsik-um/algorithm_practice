package algorithm_data_structure.dfs_bfs;

public class 타겟_넘버 {
    /*
     * BFS로 모든 조합을 검사한다.
     * 어자피 모든 조합을 살펴봐야하므로 DFS, BFS 차이가 없음. 
     * 
     * DFS에 비해 변수 생성이 많아 시간이 2배로 걸렸음
     */
    public int mySolution(int[] numbers, int target) {
        int answer = 0;
        int[] currNumbers = new int[1];

        for (int number : numbers) {
            int[] nextNumbers = new int[currNumbers.length * 2];

            for (int i = 0; i < currNumbers.length; i++){
                nextNumbers[i * 2] = currNumbers[i] + number;
                nextNumbers[i * 2 + 1] = currNumbers[i] - number;
            }

            currNumbers = nextNumbers;
        }

        for (int currNumber : currNumbers) {
            if (currNumber == target) {
                answer += 1;
            }
        }

        return answer;
    }

    /* 
     * DFS 사용 풀이
     */
    public int mySolution2(int[] numbers, int target) {
        return dfs(numbers, target, 0, 0);
    }

    public int dfs(int[] numbers, int target, int currIdx, int currNum) {
        int answer = 0;

        if (currIdx == numbers.length) {
            answer = (currNum == target) ? 1 : 0;
        }else{
            answer = dfs(numbers, target, currIdx + 1, currNum + numbers[currIdx])
                    + dfs(numbers, target, currIdx + 1, currNum - numbers[currIdx]);
        }

        return answer;
    }
}