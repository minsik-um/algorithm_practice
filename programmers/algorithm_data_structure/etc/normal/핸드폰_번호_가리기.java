package algorithm_data_structure.etc.normal;

public class 핸드폰_번호_가리기 {
    
    /**
     * @param phone_number : 가릴 핸드폰 번호
     * @return : 뒤 4자리 제외 앞부분은 *로 바꾼 핸드폰 번호
     */
    public String bestSolution(String phone_number) {
        char[] ch = phone_number.toCharArray();
        
        for (int i = 0; i < ch.length - 4; i++) {
            ch[i] = '*';
        }
        
        return String.valueOf(ch);
    }

    /* 
     * String과 char[] 간의 속도 비교가 중점이다.
     * 위처럼 char[]를 사용한 방법이 훨씬 빠르다.
     * 
     * 위 방법은 0.xxx 초
     * 아래 방법은 35.xxx 초
     */
    public String mySolution(String phone_number) {
        String answer = "";
        int hidedLength = phone_number.length() - 4;
        
        for (int i = 0; i < hidedLength; i++) {
            answer += "*";
        }
        
        for (int i = hidedLength; i < phone_number.length(); i++) {
            answer += phone_number.charAt(i);
        }
        
        return answer;
    }
}