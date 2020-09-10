package algorithm_data_structure.etc.normal;

public class 가운데_글자_가져오기 {

    /**
     * @param s 목표 문자열
     * @return 가운데 문자(짝수이면 가운데 두 글자) 반환
     */
    public String solution(String s) {        
        int startIdx = (s.length() - 1)/2;
        int endIdx = startIdx + 1 + (s.length() + 1) % 2;
        
        return s.substring(startIdx, endIdx);
    }
}