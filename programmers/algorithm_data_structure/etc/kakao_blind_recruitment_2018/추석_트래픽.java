package algorithm_data_structure.etc.kakao_blind_recruitment_2018;

import java.util.PriorityQueue;

public class 추석_트래픽 {
    /**
     * @param lines : 처리 시간 로그(https://programmers.co.kr/learn/courses/30/lessons/17676)
     * @return 초당 최대 처리량
     * 
     * 1초 범위가 움직이면서 처리 갯수를 셀 때
     * 처리량이 증가하는 순간은 새로운 트래픽이 포함되는 순간이다.
     * 
     * 처리 종료 시간 기준으로 lines가 정렬되어 있으므로
     * 역순으로 탐색해가며 (1초 범위가) 새로운 처리를 만나는 순간
     * 이미 끝난 처리량을 제외하고 현재 남은 처리들의 갯수를 센다.
     * 
     * 그중 최댓값을 반환한다.
     * 
     * [주의점]
     * double로 second 단위로 값을 판단하면 부동소수점 특성상
     * 값이 미세하게 부정확할 수 있어 결과에 오류가 생길 수 있다.
     * => millisecond 단위로 int가 되게 하여 값을 판단한다.
     */
    public int solution(String[] lines) {
        int answer = 0;
        int toCheckIdx = lines.length - 1;
        PriorityQueue<Log> logInTraffic = new PriorityQueue<>(
                (o1, o2) -> (int) (o2.getStartTime() - o1.getStartTime()));

        while (toCheckIdx >= 0) {
            Log nextLog = new Log(lines[toCheckIdx--]);
            logInTraffic.add(nextLog);

            int endTimeBoundary = nextLog.getEndTime() + 999;
            while (endTimeBoundary < logInTraffic.peek().getStartTime()) {
                logInTraffic.poll();
            }

            answer = Math.max(answer, logInTraffic.size());
        }

        return answer;
    }

    class Log {
        private int startTime;
        private int endTime;

        public Log(String line) {
            String[] splited = line.split(" ");
            int interval = interval(splited[2]);
            this.endTime = convertToMilli(splited[1]);
            this.startTime = endTime - interval + 1;
        }

        private int convertToMilli(String time) {
            String[] splited = time.split(":");
            int hour = Integer.parseInt(splited[0]);
            int minute = Integer.parseInt(splited[1]);
            int second = Integer.parseInt(splited[2].substring(0, 2));
            int milli = Integer.parseInt(splited[2].substring(3));

            return 1000 * ((hour * 60 * 60) + (minute * 60) + second) + milli;
        }

        private int interval(String str) {
            return (int) (Double.parseDouble(str.substring(0, str.length() - 1)) * 1000);
        }

        public int getStartTime() {
            return startTime;
        }

        public int getEndTime() {
            return endTime;
        }
    }
}