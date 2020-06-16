package algorithm_data_structure.etc.kakao_blind_recruitment_2018;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class 셔틀버스 {
    /**
     * @param n : 셔틀 운행 횟수
     * @param t : 셔틀 운행 간격
     * @param m : 한 셔틀에 할 수 있는 최대 크루 수
     * @param timetable : 크루가 대기열에 도착하는 시각의 목록
     * @return 셔틀을 타고 사무실에 갈 수 있는 제일 늦은 도착 시각
     * 
     * 결국 마지막 버스를 타야 하는데,
     * 마지막 버스에 남은 좌석이 있으면 버스 도착 시간에 오면 되고,
     * 남은 좌석이 없으면 이미 탄 사람 중에서 제일 늦게 온 사람보다
     * 1분 빨리 도착하면 된다.
     */
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        int startBusTime = stringToMinutes("09:00");
        int currBusTime = startBusTime;
        List<Integer> bus = new ArrayList<>(m);
        Queue<Integer> waitingPeople = copyAndSort(timetable);
        
        for (int i = 0; i < n; i++) {
            bus.clear();
            while (bus.size() < m && !waitingPeople.isEmpty() && waitingPeople.peek() <= currBusTime) {
                bus.add(waitingPeople.poll());
            }
            currBusTime += t;
        }

        if (bus.size() < m) {
            int lastBusTime = startBusTime + (n-1) * t;
            answer = minutesToString(lastBusTime);
        } else {
            int lastPersonTime = bus.get(bus.size() - 1);
            answer = minutesToString(lastPersonTime - 1);
        }

        return answer;
    }
    
    public Queue<Integer> copyAndSort(String[] origin) {
        LinkedList<Integer> copy = new LinkedList<>();
        for (String str : origin) {
            copy.add(stringToMinutes(str));
        }
        
        copy.sort((o1, o2) -> o1 - o2);

        return (Queue<Integer>)copy;
    }
    
    public int stringToMinutes(String time) {
        String[] splited = time.split(":");
        int hour = Integer.parseInt(splited[0]);
        int minute = Integer.parseInt(splited[1]);
        
        return hour * 60 + minute;
    }
    
    public String minutesToString(int totalMinute) {
        int hour = totalMinute / 60;
        int minute = totalMinute % 60;
                    
        return String.format("%02d", hour) + ":" +  String.format("%02d", minute);
    }
}