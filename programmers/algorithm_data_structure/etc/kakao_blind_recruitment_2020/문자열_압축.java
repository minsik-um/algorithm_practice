package algorithm_data_structure.etc.kakao_blind_recruitment_2020;

public class 문자열_압축 {
    /**
     * @param s : 압축할 문자열 원문
     * @return 다양한 길이 단위로 압축했을 때 나오는 압축 문자열의 최소 길이
     * 
     * 문자를 떼어가며 압축해간다.
     * 매번 문자를 새로 생성하지 않고 index를 활용할 순 있으나,
     * 아래 코드가 가독성이 더 좋다고 판단했다.
     */
    public int solution(String s) {
        int minLen = s.length();
        int lastUnit = s.length() / 2;
        
        for (int unit = 1; unit <= lastUnit; unit++) {
            int len = lenOfCompressedString(unit, s);
            if (len < minLen) {
                minLen = len;
            }            
        }
        
        return minLen;
    }
    
    public int lenOfCompressedString(int unit, String s) {
        int len = 0;
        int count = 1;
        String copy = s;
        String target = copy.substring(0, unit);
        copy = copy.substring(unit);

        while (copy.length() >= unit) {
            String subStr = copy.substring(0, unit);
            copy = copy.substring(unit);
            
            if (target.equals(subStr)) {
                count += 1;
            } else {
                len += truncate(unit, count);
                
                target = subStr;
                count = 1;
            }
        }        
        
        len += truncate(unit, count);
        len += copy.length();
        
        return len;
    }

    private int truncate(int unit, int count) {
        int ret = 0;

        ret += unit;
        if (count > 1) {
            ret += Integer.toString(count).length();
        }

        return ret;
    }
}