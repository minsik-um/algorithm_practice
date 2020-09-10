package algorithm_data_structure.etc.normal;

public class n개의_최소공배수 {

    /**
     * @param arr : 최소공배수를 구할 문자의 모음
     * @return 최소공배수
     * 
     * 유클리드 호제법을 사용하여 최대공약수와 최소공배수를 구한다.
     * 최소공배수와 다음 수의 최소공배수를 구하는 방식으로 결정한다.
     */
    public int mySolution(int[] arr) {
        int answer = arr[0];

        for (int num : arr) {
            int gcm = (answer * num) / getGcd(answer, num);
            answer = gcm;
        }

        return answer;
    }

    public int getLcm(int num1, int num2) {
        int gcd = getGcd(num1, num2);

        return num1 * num2 / gcd;
    }

    public int getGcd(int num1, int num2) {
        int bigOne = Math.max(num1, num2);
        int smallOne = Math.min(num1, num2);
        
        if (smallOne == 0) {
            return bigOne;
        } else {
            return getGcd(smallOne, bigOne % smallOne);
        }
    }


    public int mySolution2(int[] arr) {
        int answer = 0;
        int[] multipleNums = deepCopy(arr);

        while (!isAllEqual(multipleNums)) {
            int idx = getIndexOFMinValue(multipleNums);
            multipleNums[idx] += arr[idx];
        }
                
        return multipleNums[0];
    }
    
    public int[] deepCopy(int[] arr) {
        int[] copy = new int[arr.length];
        
        for (int i = 0; i < copy.length; i++) {
            copy[i] = arr[i];
        }
        
        return copy;
    }
    
    public int getIndexOFMinValue(int[] arr) {
        int idx = 0;
        int value = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (value > arr[i]) {
                value = arr[i];
                idx = i;
            }
        }
        
        return idx;
    }
    
    public boolean isAllEqual(int[] arr) {
        boolean allEqual = true;
        int key = arr[0];
        
        for (int i = 1; i < arr.length; i++) {
            if (key != arr[i]) {
                allEqual = false;
                break;
            }
        }
        
        return allEqual;
    }
}