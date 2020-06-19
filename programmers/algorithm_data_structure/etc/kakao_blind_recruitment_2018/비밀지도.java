package algorithm_data_structure.etc.kakao_blind_recruitment_2018;

class 비밀지도 {
    /**
     * @param n : 지도의 한 변의 길이
     * @param arr1 : 첫 번째 지도(10진수로 표현)
     * @param arr2 : 두 번째 지도(10진수로 표현)
     * @return 두 지도를 or연산으로 합쳐 나온 지도 모양을 반환
     */
    public String[] solution(int n, int[] arr1, int[] arr2) {
        String[] answer = new String[arr1.length];

        /*
         * [ replaceAll을 이용해 코드를 간단하게 할 수 있음 ]
         *  for (int i = 0; i < n; i++) { result[i] = String.format("%" + n + "s",
         * result[i]); result[i] = result[i].replaceAll("1", "#"); result[i] =
         * result[i].replaceAll("0", " "); }
         */

        for (int i = 0; i < arr1.length; i++) {
            char[] ret = new char[n];
            int[] b1 = toBinary(n, arr1[i]);
            int[] b2 = toBinary(n, arr2[i]);

            for (int j = 0; j < ret.length; j++) {
                int sign = b1[j] | b2[j];
                ret[j] = (sign == 1) ? '#' : ' ';
            }

            answer[i] = String.valueOf(ret);
        }
        return answer;
    }

    // Integer.toBinaryString(); 로 대체 가능한 함수
    public int[] toBinary(int n, int decimal) {
        int[] binary = new int[n];

        int temp = decimal;
        for (int i = n - 1; i >= 0; i--) {
            binary[i] = temp % 2;
            temp /= 2;
        }

        return binary;
    }
}