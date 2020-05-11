package algorithm_data_structure.brute_force_search;

import java.util.LinkedList;
import java.util.List;

public class 숫자_야구 {

    /*
     * 가능한 모든 수의 순열을 구하고
     * 직접 비교를 해보며 유효한 것만 남겨가는 방식
     * 
     * 순열을 구할 때 재귀함수를 사용
     * 
     * 아래와 같이 조합 수가 적을 땐 삼중 for문을 사용해도 된다.
     * 아래처럼 for문 3개에 if문 하거나
     * 
     * for (int i = 0 ...)
     *     for(int j = 0 ...)
     *         for(int k = 0 ...)
     *             if (i != j && j == k && i != k) {
     *                 ...
     *             }
     * 
     * 각각 i, j를 기준으로 하위 for문을 2개로 쪼개면 if문이 필요 없다.
     * (단, 코드 길이가 길어지고 중복이 많으며 다른 경우에 재사용이 어려움)
     */
    public int mySolution(int[][] baseball) {
        List<String> permutations = permutation();
        
        for(int[] info : baseball){
            String num = Integer.toString(info[0]);
            int strike = info[1];
            int ball = info[2];

            List<String> newPermutations = new LinkedList<>();

            for (String answer : permutations){
                int[] checkOutput = check(answer, num);

                if (checkOutput[0] == strike && checkOutput[1] == ball){
                    newPermutations.add(answer);
                }
            }

            permutations = newPermutations;
        }
        
        return permutations.size();
    }

    public int[] check(String answer, String number){
        int strike = 0;
        int ball = 0;

        for (int i = 0; i < number.length(); i++){
            // 값이 없으면 -1, 있으면 index 반환
            int idxInAnswer = answer.indexOf(number.charAt(i));
            if (idxInAnswer >= 0){
                if (idxInAnswer == i){
                    strike += 1;
                }else{
                    ball += 1;
                }
            }
        }

        return new int[]{strike, ball};
    }

    public List<String> permutation(){
        String range = "123456789";
        List<String> output = new LinkedList<>();
        
        permutation(range, "", output);
        return output;
    }

    public void permutation(String range, String prefix, List<String> output){
        if (prefix.length() == 3){
            output.add(prefix);
            return;
        }

        for (int i = 0; i < range.length(); i++){
            permutation(range.substring(0, i) + range.substring(i+1, range.length()),
                        prefix + range.charAt(i), output);
        }
    }
}