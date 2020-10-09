import java.util.*;

class 자물쇠와_열쇠 {

    public List<int[]> rotateAll(List<int[]> posList, int[][] arr, int rotationCount) {
        List<int[]> rotatedKeyOnes = new ArrayList<>();     

        for (int[] pos : posList) {
            rotatedKeyOnes.add(rotate(pos, arr, rotationCount));
        }

        return rotatedKeyOnes;
    }

    public int[] rotate(int[] pos, int[][] arr, int rotationCount) {
        int[] rotated = {pos[0], pos[1]};

        for (int i = 0; i < rotationCount; i++) {
            rotated = new int[]{rotated[1],  arr.length - 1 - rotated[0]};
        }

        return rotated;
    }

    public List<int[]> getPosListByVal(int[][] arr, int val) {
        List<int[]> posList = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == val) {
                    posList.add(new int[]{i, j});
                }
            }
        }   

        return posList;
    }

    public boolean unlock(int[] matchKeyPos, int[] matchLockPos, List<int[]> keyOnes,
                         int lockZeroCount, int[][] lock) {    
        boolean isMatch = true;
        int offsetY = matchLockPos[0] - matchKeyPos[0];
        int offsetX = matchLockPos[1] - matchKeyPos[1];        
        int remainZeroCount = lockZeroCount;

        for (int[] keyPos : keyOnes) {
            int[] target = {keyPos[0] + offsetY, keyPos[1] + offsetX};

            if ((target[0] < 0 || target[0] >= lock.length) 
                    || (target[1] < 0 || target[1] >= lock.length)) {
                continue;
            } else if (lock[target[0]][target[1]] == 1) {
                isMatch = false;
                break;
            } else {
                remainZeroCount -= 1;
            }
        }

        return isMatch && (remainZeroCount == 0);
    }

    /**
     * @param key 열쇠의 형태
     * @param lock 자물쇠의 형태
     * @return 해당 열쇠로 자물쇠를 열 수 있는가?
     * 
     * 회전-이동 시켜서 가능한 경우의 수를 다 맞춰보면 된다.
     * 이때 key에서 돌기(1) 부분만 영향을 미치므로 keyOnes 만 따로 빼서 사용했으며,
     * 
     * 자물쇠로 열 수 있으면 최소한 하나 이상 자물쇠 홈과 열쇠 돌기가 맞으므로
     * 그 맞는 조합을 다 테스트 해봄.
     */
    public boolean solution(int[][] key, int[][] lock) {
        boolean answer = false;

        List<int[]> keyOnes = getPosListByVal(key, 1);
        List<int[]> lockZeros = getPosListByVal(lock, 0);

        if (keyOnes.size() < lockZeros.size()) {
            return false;
        }

        // 처음엔 lock이 모두 1일 때 key는 0으로 가득차야하는 줄 알고 했다가 테스트 2, 4, 12에서 헤맸고,
        // 결국 질문하기에서 나와 똑같이 틀리는 분 말 참고함. key는 상관없이 인정되어야 한다고 함...
        if (lockZeros.size() == 0) {
            return true;
        }

        all:
        for (int rotation = 0; rotation < 4; rotation++) {
            List<int[]> rotatedKeyOnes = rotateAll(keyOnes, key, rotation);

            for (int[] keyPos : rotatedKeyOnes) {
                for (int[] lockPos : lockZeros) {
                    boolean ret = unlock(keyPos, lockPos, rotatedKeyOnes, lockZeros.size(), lock);

                    if (ret) {
                        answer = true;
                        break all;
                    }
                }
            }
        }

        return answer;
    }
}