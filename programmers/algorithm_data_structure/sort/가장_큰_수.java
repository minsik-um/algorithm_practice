package algorithm_data_structure.sort;

import java.util.Arrays;
import java.util.stream.Collectors;

class 가장_큰_수 {

    /*
     * 두 수를 합쳤을 때 숫자가 더 커지는 순서로 두 수의 순서를 조정한다.
     * 이게 쌓이면 앞으로 갈수록 숫자를 더 크게 만드는 애들이 위치한다.
    */
    public String solution(int[] numbers) {
        String answer = Arrays.stream(numbers)
                            .mapToObj(Integer::toString)
                            .sorted((o1, o2) -> Integer.parseInt(o2 + o1) - Integer.parseInt(o1 + o2))
                            .collect(Collectors.joining(""))
                            .toString();

        return (answer.charAt(0) == '0') ? "0" : answer;
    }

    /*
     * (python 풀이법과 동일, 설명을 python 풀이 참고)
    */
    public String solution2(int[] numbers) {
        String answer = Arrays.stream(numbers)
                        .mapToObj(NumberForConcat::new)
                        .sorted((o1, o2) -> o2.compareTo(o1))
                        .map(NumberForConcat::toString)
                        .collect(Collectors.joining(""))
                        .toString();

        return (answer.charAt(0) == '0') ? "0" : answer;
    }

    class NumberForConcat implements Comparable<NumberForConcat> {
        private static final int MAX_DIGIT_LENGTH = 4;

        private int number;

        public NumberForConcat(int number) {
            this.number = number;
        }

        public String toString() {
            return Integer.toString(number);
        }

        public int getNumber() {
            return number;
        };

        @Override
        public int compareTo(NumberForConcat o) {
            String s1 = String.valueOf(this.number);
            String s2 = String.valueOf(o.getNumber());

            while (s1.length() < MAX_DIGIT_LENGTH) {
                s1 += s1;
            }
            s1 = s1.substring(0, MAX_DIGIT_LENGTH);
            while (s2.length() < MAX_DIGIT_LENGTH) {
                s2 += s2;
            }
            s2 = s2.substring(0, MAX_DIGIT_LENGTH);
            return s1.compareTo(s2);
        }
    }

}