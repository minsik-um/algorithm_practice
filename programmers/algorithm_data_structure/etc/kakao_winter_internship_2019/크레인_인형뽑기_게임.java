package algorithm_data_structure.etc.kakao_winter_internship_2019;

import java.util.Stack;

public class 크레인_인형뽑기_게임 {
    /**
     * @param board : board[i][j] 가로 j번째 칸의 세로 i 번째(0이 천장)칸 인형 종류
     * @param moves : 크레인이 매번 선택한 가로 칸
     * @return 크레인이 정해진대로 움직이는 과정에서 basket에서 인접한 2개가 만나
     *         터지는 횟수를 반환
     * 
     * int 배열로 각 칸의 인형 쌓인 높이를 저장하고,
     * 매번 크레인으로 빼서 stack 구조의 바구니에 넣어 터지는지 검사한다.
     */
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        int boardHeight = board.length - 1;
        int[] topIdx = new int[board[0].length];
        Stack<Integer> basket = new Stack<>();
        basket.add(-1);
        
        for (int i = 0; i < board[0].length; i++) {
            while (topIdx[i] < board.length && board[topIdx[i]][i] == 0) {
                topIdx[i] += 1;
            }
        }
        
        for (int moveColumn : moves) {
            if (topIdx[moveColumn-1] <= boardHeight) {
                int obj = board[topIdx[moveColumn-1]++][moveColumn-1];
                if (basket.peek() == obj) {
                    basket.pop();
                   answer += 2;
                } else {
                    basket.add(obj);                
                }
            }
        }
        
        return answer;
    }
}