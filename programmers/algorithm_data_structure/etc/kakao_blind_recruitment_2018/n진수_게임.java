package algorithm_data_structure.etc.kakao_blind_recruitment_2018;

public class n진수_게임 {
    /**
     * @param n : 진법
     * @param t : 미리 구할 숫자 갯수
     * @param m : 게임에 참가하는 인원
     * @param p : 튜브의 순서
     * @return 튜브가 말해야 하는 숫자 t개를 공백 없이 차례대로 나타낸 문자열.
     *         단, 10~15는 각각 대문자 A~F로 출력한다.
     */
    public String solution(int n, int t, int m, int p) {
        StringBuilder answer = new StringBuilder();
        String sequence = getSequence(n, t, m, p);

        for (int i = 0; i < t; i++) {
            int charIdx = (p - 1) + m * i;
            answer.append(sequence.charAt(charIdx));
        }
        
        return answer.toString();
    }
    
    public String getSequence(int n, int t, int m, int p) {
        int targetLength = (t - 1) * m + p;
        StringBuilder sequence = new StringBuilder("0");
        int num = 1;
        
        while (sequence.length() < targetLength) {
            sequence.append(getNumBySystem(n, num));
            num += 1;
        }
        
        return sequence.toString();
    }
    
    public String getNumBySystem(int n, int decimal) {
        String nums = "0123456789ABCDEF";
        StringBuilder sb = new StringBuilder();
        int copy = decimal;

        while (copy > 0) {
            sb.append(nums.charAt((copy % n)));
            copy /= n;
        }
        
        // reverse()를 대체하기 위해 stack에 자료를 저장하고
        // stack 원소를 빼는 식으로 구현해도 좋다.
        return sb.reverse().toString();
    }
}