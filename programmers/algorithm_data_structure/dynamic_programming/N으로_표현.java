package algorithm_data_structure.dynamic_programming;

    import java.util.ArrayList;
    import java.util.HashSet;
    import java.util.List;
    import java.util.Set;

public class N으로_표현 {

    public int solution(int N, int number) {
        List<Set<Integer>> numsGroup = new ArrayList<>();
        Set<Integer> nums = new HashSet<>();
        nums.add(N);
        numsGroup.add(nums);

        if (N == number) {
            return 1;
        }

        while (numsGroup.size() <= 7) {
            Set<Integer> newNums = new HashSet<>();
            int onlyN = 0;
            for (int i = 1; i <= 10 * newNums.size(); i *= 10) {
                onlyN += (i * N); 
            }
            /*
            [ best solution ]
            11111 * N을 만들기 위해
            (2의 i제곱 - 1) = 11111... 을 만들고 int로 변환하였음

            int NNN = Integer.parseInt(Integer.toBinaryString((int) Math.pow(2, i) - 1)) * N;
            */
            newNums.add(onlyN);

            for (int i = 0; i < numsGroup.size() / 2.0f; i++) {
                int other = numsGroup.size() - i - 1;

                for (int a : numsGroup.get(i)){
                    for(int b : numsGroup.get(other)) {
                        newNums.add(a + b);
                        newNums.add(a - b);
                        newNums.add(b - a);
                        newNums.add(a * b);
                        if (b != 0){
                            newNums.add(a / b);
                        }
                        if (a != 0){
                            newNums.add(b / a);
                        }
                    }
                }
            }

            if (newNums.contains(number)){
                break;
            }else{
                numsGroup.add(newNums);
            }
        }

        return (numsGroup.size() < 8) ? (numsGroup.size() + 1) : -1;
    }

    /* [ Best Solution ]
    enum의 반복문을 돌릴 수 있다.
    
    for (Operator o : Operator.values()) {
        int calculate = o.calculate(itValue1, itValue2);
        if (calculate == number){
            answer = i;
            break loop;
        }

        set.add(calculate);
    }
    */

    /* [ Best Solution ]
    이렇게 enum(열거형)을 사용할 수 있다.
    
    enum Operator {
        PLUS {
            @Override
            public int calculate(int i, int j) {
                return i + j;
            }
        }, MINUS {
            @Override
            public int calculate(int i, int j) {
                return i - j;
            }
        }, BACKMINUS {
            @Override
            public int calculate(int i, int j) {
                return j - i;
            }
        }, MUL {
            @Override
            public int calculate(int i, int j) {
                return i * j;
            }
        }, DIV {
            @Override
            public int calculate(int i, int j) {
                if (j == 0){
                    return 0;
                }
                return i / j;
            }
        }, BACKDIV {
            @Override
            public int calculate(int i, int j) {
                if (i == 0){
                    return 0;
                }
                return j / i;
            }
        };

        public abstract int calculate(int i, int j);
    }
    */
}