package algorithm_data_structure.sort;

import java.util.Arrays;
import java.util.stream.IntStream;

public class k번째수 {

    // 함수형 프로그래밍을 적용해 직접 풀어봄
    // stream api를 안쓰고 풀면 풀이 시간이 10배 가까이 단축된다.
    class SubArrayWithTargetIndex{
        private int[] subArray;
        private int targetIdx;

        public SubArrayWithTargetIndex(int[] array, int begin, int end, int target){
            this.subArray = Arrays.copyOfRange(array, begin, end);
            this.targetIdx = target;
        }

        public SubArrayWithTargetIndex sort(){
            Arrays.sort(this.subArray);
            return this;
        }

        public int getTarget(){
            return this.subArray[this.targetIdx];
        }
    }

    public int[] mySolution(int[] array, int[][] commands) {
        return Arrays.stream(commands)
                        .mapToInt(c -> Arrays.stream(
                                Arrays.copyOfRange(array, c[0]-1, c[1])
                            ).sorted()
                            .toArray()[c[2]-1]
                        )
                        .toArray();                        
    }

    // 함수형 프로그래밍을 이용한 풀이 2
    public int[] mySolution2(int[] array, int[][] commands) {
        return IntStream.range(0, commands.length)
                        .mapToObj(i -> new SubArrayWithTargetIndex(array, commands[i][0]-1, commands[i][1]-1, commands[i][2]-1))
                        .map(o -> o.sort())
                        .mapToInt(o -> o.getTarget())
                        .toArray();
    }

    // 원래 풀이
    public int[] mySolution3(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];
        
        for (int i = 0; i < commands.length; i++){
            int[] c = commands[i];
            
            int[] subArray = Arrays.copyOfRange(array, c[0]-1, c[1]);
            Arrays.sort(subArray);            
            answer[i] = subArray[c[2]-1];
        }
        
        return answer;
    }
}