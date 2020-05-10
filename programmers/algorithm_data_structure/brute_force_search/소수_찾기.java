package algorithm_data_structure.brute_force_search;

import java.util.HashSet;
import java.util.Set;

public class 소수_찾기 {

    /* 
     * permutation을 구할 때는 아래처럼 재귀적인 방법을 이용하자.
     * 소수 판단은 제곱근을 홀수로 나눠보며 판단하면 빠르다.
     * (특정 범위 소수 구하기는 에라토스테네스의 체 방법 이용)
     * 
     * permutation을 구할 때 prefix 방향 suffix 방향 상관 없으며,
     * suffix를 이용하면 끝자리가 0 또는 2의 배수인지 처음에 판단하여
     * 가지치기가 가능하다.
     */
    public void permutation(String numbers, Set<Integer> output){
        for (int i = 0; i < numbers.length(); i++){
            String number = numbers.substring(i, i+1);
            int n = Integer.parseInt(number);

            if (n == 2){
                output.add(n);
            }

            if (n % 2 != 0){
                permutation(numbers.substring(0, i) + numbers.substring(i+1, numbers.length()),
                number, output);
            }
        }            
    }

    public void permutation(String numbers, String suffix, Set<Integer> output){
         output.add(Integer.parseInt(suffix));

        if (numbers.length() > 0){
            for (int i = 0; i < numbers.length(); i++){
                permutation(numbers.substring(0, i) + numbers.substring(i+1, numbers.length()),
                            numbers.charAt(i) + suffix, output);
            }            
        }
    }

    public int solution(String numbers) {
        int answer = 0;
        Set<Integer> concatedNumbers = new HashSet<>();

        permutation(numbers, concatedNumbers);
        System.out.println(concatedNumbers);

        outer:
        for (int number : concatedNumbers){
            if (number < 2){
                continue outer;
            }

            int sqrt = (int) Math.sqrt((double)number);
            for(int i = 3; i <= sqrt; i += 2){
                if (number % i == 0){
                    continue outer;
                }
            }

            answer += 1;
        }

        return answer;
    }
}