package algorithm_data_structure.etc._2017_tips;

class 예상_대진표 {
    /**
     * @param n : 게임 참가자 수
     * @param a : 참가자 1의 첫 라운드 번호
     * @param b : 참가자 2의 첫 라운드 번호
     * @return 둘이 만나는 라운드 
     * 
     * 매번 두 참가자는 이기므로 다음 숫자는
     * (k + 1) / 2 가 된다.
     */
    public int solution(int n, int a, int b) {
        int answer = 0;
        int currentA = Math.min(a, b);
        int currentB = Math.max(a, b);
        
        while (currentB != currentA) {
            currentA = nextNum(currentA);
            currentB = nextNum(currentB);

            answer += 1;
        }

        return answer;
    }
    
    public int nextNum(int currentNum) {
        return (currentNum + 1) / 2;
    }
}