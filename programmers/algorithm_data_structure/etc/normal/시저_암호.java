package algorithm_data_structure.etc.normal;

public class 시저_암호 {
    /**
     * @param s : 암호화 이전 평문
     * @param n : offset
     * @return offset 만큼 이동해 암호화한 시저 암호문
     * 
     * 알고리즘은 시저 암호 방식 그대로 했으며,
     * 주의점은 다음과 같다.
     * 
     * - char와 int를 연산하면 int가 나온다.
     * - 'a' 또는 'A'에서 얼만큼 더해야하는지 계산하는 수식
     */
    public String solution(String s, int n) {
        String answer = "";
        
        int length = s.length();
        
        for (int i = 0; i < length; i++) {
            char letter = s.charAt(i);

            if (letter >= 'a') {
                letter = (char)((letter - 'a' + n) % 26 + 'a');
            } else if (letter >= 'A') {
                letter = (char)((letter - 'A' + n) % 26 + 'A');
            }

            answer += letter;
        }

        return answer;
    }
}