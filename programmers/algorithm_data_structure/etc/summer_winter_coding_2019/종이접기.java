package algorithm_data_structure.etc.summer_winter_coding_2019;

public class 종이접기 {
    /**
     * @param n : 종이 접는 횟수
     * @return  접힌 부분의 모양을 숫자 리스트로 표현
     * 
     * 접었던 부분을 펼칠 때
     * 기존의 모양은 도장 찍어내듯 오른쪽에 옮겨지는데,
     * 이때 순서는 역순으로, 값들은 1과 0은 서로 바뀌어서 전달된다.
     * 
     * xor을 이용해 값을 뒤집고, 배열을 역순으로 접근하여 매번 값을 뒤에 추가한다.
     */
    public int[] solution(int n) {
        if (n == 1) {
            return new int[]{0};
        }

        int[] previous = solution(n-1);
        int[] ret = new int[previous.length * 2 + 1];

        for (int i = 0; i < previous.length; i++) {
            ret[i] = previous[i];
            ret[ret.length - 1 - i] = previous[i] ^ 1;
        }

        return ret;
    }
}