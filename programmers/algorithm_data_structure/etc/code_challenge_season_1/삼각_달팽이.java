class 삼각달팽이 {
    
    public int[] flatten(int[][] arr) {
        int area = (arr.length + 1) * arr.length / 2;
        int[] answer = new int[area];
        int idx = 0;
        
        for (int[] line : arr) {
            for (int num : line) {
                if (num != 0) {
                    answer[idx++] = num;                    
                }
            }
        }
        
        return answer;
    }

    /**
     * @param n 삼각형 한 변의 길이
     * @return 달팽이 채우기로 배열을 채운 다음 flatten 해서 반환
     * 
     * [내가 베스트 풀이에 비해 제대로 하지 못한 점]
     * 1. tri(이중 배열)의 line 길이를 굳이 다르게 할 필요 없다.
     * 그냥 int[n][n] 이렇게 하면 간단하다.
     * 
     * 2. 탐색 코드도 단순하게 할 수 있다. 
     * n 횟수만큼 직선 이동, 그 직선 길이는 매번 1씩 줄어든다. (아래 주석과 코드 참고)
     */
    public int[] solution(int n) {
        int[][] tri = new int[n][n];
        int count = 1;
        int yIdx = -1;
        int xIdx = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (i % 3 == 0) {
                    yIdx += 1;
                } else if (i % 3 == 1) {
                    xIdx += 1;
                } else if (i % 3 == 2) {
                    xIdx -= 1;
                    yIdx -= 1;
                }

                tri[yIdx][xIdx] = count;
                count += 1;
            }
        }

        /* [불필요하게 복잡한 탐색 코드]
        int level = 0;

        while (count <= area) {            
            for (int hIdx = level * 2;  hIdx <= (n - 1) - level; hIdx++) {
                tri[hIdx][level] = count;
                count += 1;
            }
            for (int wIdx = level + 1; wIdx <= (n - 1) - level * 2; wIdx++) {
                tri[n - 1 - level][wIdx] = count;
                count += 1;
            }
            for (int hIdx = (n - 2) - level; hIdx >= 1 + level * 2; hIdx--) {
                tri[hIdx][hIdx - level] = count;
                count += 1;
            }  
            
            level += 1;
        }
        */        

        return flatten(tri);
    }
}