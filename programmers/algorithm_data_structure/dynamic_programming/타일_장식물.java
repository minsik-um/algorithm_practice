package algorithm_data_structure.dynamic_programming;

public class 타일_장식물 {

    public long mySolution(int N) {
        long prev = 0;
        long curr = 1;

        for (int i = 1; i < N; i++) {
            long next = curr + prev;
            prev = curr;
            curr = next;
        }

        return 2 * (curr + (curr + prev));
    }
}