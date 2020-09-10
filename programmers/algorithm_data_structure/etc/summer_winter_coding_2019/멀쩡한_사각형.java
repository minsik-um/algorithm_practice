package algorithm_data_structure.etc.summer_winter_coding_2019;

public class 멀쩡한_사각형 {
    /**
     * @param w : 종이의 너비
     * @param h : 종이의 높이
     * @return : 대각선으로 잘랐을 때, 남은 1x1 사각형의 갯수
     * 
     * 2차원 그래프에 대각선을 하나의 함수로 생각하자.
     * y값이 정수가 되는 지점을 기준으로 y축에 수직되게 잘라서 보자.
     * 구간마다 손상된 사각형의 갯수가 같다.
     * 
     * 첫 구간에서 해당 직선을 지나는 사각형 갯수를 구하고
     * 전체 구간 수만큼 곱하면 된다.
     * 
     * **주의**
     * int * int, int / int 등은 아무리 
     * long = int * int 라고 하더라도 int 형으로 짤리고나서
     * long에 저장되므로 데이터 손상이 일어난다.
     * (long)a + b 이렇게 하나 이상 데이터 타입을 미리 바꿔야 한다.
     * 
     * 참고로 int 타입은 20억대까지만 지원한다.
     */
    public long mySolution(int w, int h) {
        long answer = 0;
        double lastY = h;

        for (int i = 1; i <= w; i++) {
            double y = func(i, w, h);
            answer += ((long)Math.ceil(lastY) - Math.floor(y));
            
            if (y == Math.floor(y)) {
                answer *= (w/i);
                break;
            }

            lastY = y;
        }
                
        return (long)w * h - answer;
    }
       
    public double func(int x, int w, int h) {
        return -((double)h / w) * x + h;
    }

    /**
     * https://ko.wikipedia.org/wiki/유클리드_호제법
     * [ 유클리드 호제법(유클리드 알고리즘) ]
     * 2개의 자연수 또는 정식의 최대공약수를 구하는 알고리즘이다.
     * 호제법이란 말은 두 수가 서로 상대방 수를 나누어 결국 원하는 수를 얻는 것이다.
     * 
     * 두 개의 자연수 a, b에 대하여 (a > b)
     * a를 b로 나눈 나머지를 r이라 하면,
     * a와 b의 최대 공약수는 b와 r의 최대공약수와 같다.
     * 
     * 이 성질을 반복하여 나머지가 0이 되었을 때 나누는 수가 a와 b의 최대공약수이다.
     * 
     * 예. 1071, 1029의 최대공약수를 구하자.
     * 1071 % 1029 = 42
     * 1029 % 42 = 21
     * 42 % 21 = 0  => 21이 최대공약수
     * 
     * int gcd(int a, int b){
	 * while(b!=0){
	 *     int r = a%b;
	 *     a= b;
	 *     b= r;
	 * }
	 * return a;
     * 
     * -------------------------------------------
     * 
     * 최소공배수는 최대공약수를 활용하여 구한다.
     * (최소 공배수 * 최대공약수 = a * b)
     * 
     * lcm = a * b / gcd(a,b
     * 
     * ------------------------------------------
     * 
     * [ 이 문제의 풀이 활용 ]
     * 위에서 말한 구간은 w, h가 서로소인 구간이다.
     * 비슷한 개념으로 구간을 최대공약수로 나누면 최소 구간이 나온다.
     * 
     * 이때 w, h가 서로소인 구간은 
     * w 길이 + h 길이 - 1 만큼의 고정 길이를 가진다는 걸 알 수 있다.
     * 이를 활용하여 값을 한 번에 구한다.
     */
    public long bestSolution(int w,int h) {
        long min = Math.min(w, h);
        long max = Math.max(w, h);
        long remain = 1;

        while (remain > 0) {
            remain = max % min;
            max = min;
            min = remain;
        }
        long gcd = max;

        long answer = (long)w*h - gcd * (w/gcd + h/gcd -1);
        return answer;
    }
}