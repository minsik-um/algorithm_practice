/**
 * @param n 지정한 수
 * @return n보다 더 크면서 비트 수가 같은 최솟값
 */
public int solution(int n) {
    int answer = n + 1;
    int targetCount = countOneOfBinary(n);
    
    while (countOneOfBinary(answer) != targetCount) {
        answer += 1;
    }
        
    return answer;
}

public int countOneOfBinary(int num) {
    int count = 0;
    while (num > 0) {
        if (num % 2 == 1) {
            count += 1;
        }
        
        num /= 2;
    }
    
    return count;
}
/**
 * https://www.geeksforgeeks.org/java-integer-bitcount-method/
 * 
 * Integer.bitCount() 함수를 쓰면 간단하다.
 * 나중에 비슷하게 할 때 Integer.toBinaryString() 함수를 활용해도 좋다.
 */
public int bestSolution(int n) {
    int answer = n + 1;
    int targetCount = Integer.bitCount(n);
    
    while (Integer.bitCount(answer) != targetCount) {
        answer += 1;
    }
        
    return answer;
}