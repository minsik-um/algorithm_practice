package algorithm_data_structure.etc.kakao_blind_recruitment_2018;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 압축 {
    /**
     * @param msg : 압축할 메시지 평문
     * @return LZW로 압축한 암호화 메시지
     * 
     * LZW 절차 그대로 알고리즘으로 구현한다.
     */
    public int[] solution(String msg) {
        List<Integer> codes = new ArrayList<>();
        LzwDictionary dict = new LzwDictionary();
        String msgCopy = msg;
        
        while (msgCopy.length() > 0) {
            String w = dict.matchedWord(msgCopy);
            codes.add(dict.get(w));
            msgCopy = msgCopy.substring(w.length());
            if (msgCopy.length() > 0) {
                dict.put(w + msgCopy.charAt(0));
            }
        }

        return toIntArray(codes);
    }

    public int[] toIntArray(List<Integer> origin) {
        int[] ret = new int[origin.size()];

        for (int i = 0; i < ret.length; i++) {
            ret[i] = origin.get(i);
        }

        return ret;
    }

    class LzwDictionary {
        private int maxLength;
        private Map<String, Integer> dict;

        public LzwDictionary() {
            maxLength = 1;
            dict = new HashMap<>();
            for (int i = (int)'A'; i <= (int)'Z'; i++) {
                dict.put(String.valueOf((char)i), dict.size() + 1);
            }
        }

        public String matchedWord(String str) {
            int tempLength = Math.min(maxLength, str.length());
            String w = str.substring(0, tempLength);
            while (!dict.containsKey(w)) {
                tempLength -= 1;                
                w = str.substring(0, tempLength);
            }

            return w;
        }

        public void put(String str) {
            dict.put(str, dict.size()+1);
            maxLength += 1;
        }

        public int get(String str) {
            return dict.get(str);
        }
    }
}