package algorithm_data_structure.brute_force_search;

public class 카펫 {

    /*
     * 같은 넓이라도 카펫의 가로 길이-세로 길이에 따라
     * 내부 노란색의 넓이는 달라진다.
     * 
     * 가능한 카펫 가로 길이를 돌면서 노란색의 넓이를 구하고
     * 그 넓이가 주어진 노란색 넓이 목표와 똑같은지 판단
     * (카펫 가로 길이가 세로보다 같거나 길다는 조건 추가)
     * 
     * loop를 돌면서 width와 height는 유효하지 않을 수 있다.
     * 예를 들어 넓이 16 카펫이라면 4* 4 같은 건 되지만 
     * height는 3이 될 수 없다. 대응되는 width도 마찬가지다.
     * 
     * 그러나 딱히 위 부분을 신경쓰지 않아도 yellowArea는 목표 값이 나오지 않을 것이다.
     * width, height와 yellow는 1대1 대응 관계이므로(width >= height 전제)
     */
    public int[] mySolution(int brown, int yellow) {
        int[] answer = {};
        int area = brown + yellow;
        int maxWidth = area / 3;  // 3은 height의 최소 길이

        for(int width = maxWidth; width >= 3; width--){
            int height = area / width;
            int yellowArea = (width - 2) * (height - 2);

            if (yellowArea == yellow){
                answer = new int[]{width, height};
                break;
            }
        }

        return answer;
    }
}