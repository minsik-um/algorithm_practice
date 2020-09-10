package algorithm_data_structure.etc.kakao_internship_2020;

public class 키패드_누르기 {
    /**
     * @param numbers : 매번 눌러야 하는 번호
     * @param hand : 왼손잡이/오른손잡이 여부(동일 우선순위에서 어느쪽 손으로 버튼을 누를지)
     * @return 매번 누르는 손가락을 차례대로 담아 반환
     * 
     * [특이점]
     * 좌표 기준 : 왼쪽 위를 (0, 0), 오른쪽 아래를 (2, 3)으로 설정
     * 좌표를 저렇게 하면 숫자의 배열 특성상 %, / 연산으로 바로 구할 수 있음
     */
    public String solution(int[] numbers, String hand) {
        String answer = "";
        int[] leftPos = {0, 3};
        int[] rightPos = {2, 3};
        
        for (int number : numbers) {
            int[] targetPos = getPosByNumber(number);
            
            if (targetPos[0] == 0) {
                answer += "L";
                leftPos = targetPos;
            } else if (targetPos[0] == 2) {
                answer += "R";
                rightPos = targetPos;
            } else {
                int leftDist = distance(leftPos, targetPos);
                int rightDist = distance(rightPos, targetPos);
                
                if (leftDist < rightDist) {
                    answer += "L";
                    leftPos = targetPos;                    
                } else if (leftDist > rightDist) {
                    answer += "R";
                    rightPos = targetPos;
                } else if (hand.equals("left")) {
                    answer += "L";
                    leftPos = targetPos;
                } else {
                    answer += "R";
                    rightPos = targetPos;
                }
            }

        }
        
        return answer;
    }
    
    public int distance(int[] pos1, int[] pos2) {
        return Math.abs(pos1[0] - pos2[0]) + Math.abs(pos1[1] - pos2[1]);
    }
    
    public int[] getPosByNumber(int number) {
        if (number == 0) {
            return new int[]{1, 3};
        }
        
        return new int[]{(number - 1) % 3, (number - 1) / 3};
    }
}
