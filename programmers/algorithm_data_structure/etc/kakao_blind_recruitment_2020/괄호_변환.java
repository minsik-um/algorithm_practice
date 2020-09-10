package algorithm_data_structure.etc.kakao_blind_recruitment_2020;

public class 괄호_변환 {

    /**
     * @param p : 변환활 괄호 문자열
     * @return 괄호 열고 닫기가 제대로 된 변환 문자열
     * 
     * 문제에 주어진 알고리즘 그대로 구현한다.
     */
    public String solution(String p) {
        return convert(p);
    }

    public String convert(String p) {
        if (p.isEmpty()) {
            return "";
        }
    
        String ret = "";

        String u = getBalancedStr(p);
        String v = p.substring(u.length());
        boolean isValid = isValid(u);
       
        if (isValid) {
            ret = u + convert(v);
        } else {
            ret = "(" +  convert(v) + ")";
            u = u.substring(1, u.length()-1);
            u = toggle(u);
                        
            ret += u;
        }
    
        return ret;
    }

    public boolean isValid(String balancedStr) {
        int open = 0;

        for (int i = 0; i < balancedStr.length(); i++) {
            char c = balancedStr.charAt(i);

            if (c == '(') {
                open += 1;
            } else {
                open -= 1;
                
                if (open < 0) {
                    return false;
                }
            }
        }

        return true;
    }

    public String getBalancedStr(String p) {
        int open = 0;
        String ret = "";

        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            if (c == '(') {
                open += 1;
            } else {
                open -= 1;                
            }
            
            if (open == 0) {
                ret = p.substring(0, i + 1);
                break;
            }
        }

        return ret;
    }

    public String toggle(String p) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);

            if (c == '(') {
                sb.append(')');
            } else {
                sb.append('(');
            }
        }

        return sb.toString();
    }
}
