package algorithm_data_structure.etc.normal;

public class 콜라즈_추측 {
    /**
     * @param num : 콜라즈 추측으로 1로 만들어볼 숫자
     * @return 1로 만들기 위해 계산한 횟수(500 초과 시 -1 반환)
     */
    public int solution(int num) {
        return collatz(num);
    }
    
    public int collatz(int num) {
        int count = 0;
        long copy = num;
        
        while (copy > 1 && count < 500) {
            if (copy % 2 == 0) {
                copy /= 2;
            } else {
                copy = copy * 3 + 1;
            }
            count += 1;
        }
                
        if (count == 500 && copy > 1) {
            count = -1;
        }
        
        return count;
    }
}