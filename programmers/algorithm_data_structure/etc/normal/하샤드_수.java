package algorithm_data_structure.etc.normal;

public class 하샤드_수 {
    /**
     * @param x : 검사할 숫자
     * @return : x가 하샤드 수인지 판단
     * 
     * /, % 연산자로 하나씩 구해가는 이 패턴을 기억하자.
     */
    public boolean solution(int x) {
        int curr = x;
        int sum = 0;
        
        while (curr > 0) {
            sum += curr % 10;
            curr /= 10;
        }
                
        return x % sum == 0;
    }
}