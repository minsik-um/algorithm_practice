package algorithm_data_structure.etc.normal;

public class 가장_긴_팰린드롭 {

    /**
     * @param s : 검사할 문자열
     * @return : 부분 문자열 중에서 가장 긴 팰린드롭의 길이
     * 
     * 팰린드롭의 유형은 홀수와 (중간 문자 기준 앞뒤 같음)
     * 짝수(중간문자없이 뒤집어도 같은 문자열)가 있다. 
     * 
     * 각각 차례대로 검사해간다.
     */
    public int solution(String s) {
        int answer = 1;

        for (int i = 1; i < s.length() - 1; i++) {
            int len = 3;
            int leftIdx = i - 1;
            int rightIdx = i + 1;

            while (s.charAt(leftIdx) == s.charAt(rightIdx)) {
                leftIdx -= 1;
                rightIdx += 1;
                len += 2;

                if (leftIdx < 0 || rightIdx >= s.length()) {
                    break;
                }
            }

            answer = Math.max(answer, len - 2);
        }

        for (int i = 0; i < s.length() - 1; i++) {
            int len = 2;
            int leftIdx = i;
            int rightIdx = i + 1;

            while (s.charAt(leftIdx) == s.charAt(rightIdx)) {
                leftIdx -= 1;
                rightIdx += 1;
                len += 2;

                if (leftIdx < 0 || rightIdx >= s.length()) {
                    break;
                }
            }

            answer = Math.max(answer, len - 2);
        }

        return answer;
    }
}