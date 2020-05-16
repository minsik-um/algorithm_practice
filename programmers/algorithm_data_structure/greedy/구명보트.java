package algorithm_data_structure.greedy;

import java.util.Arrays;

class 구명보트 {

    /*
     * 매번 제일 무거운 사람과 가벼운 사람을 태워 보낸다.
     */
    public int solution(int[] people, int limit) {
        int answer = 0;
        int lightPerson = 0;
        int heavyPerson = people.length - 1;
        int[] sortedPeople = Arrays.copyOf(people, people.length);
        Arrays.sort(sortedPeople);

        while (lightPerson < heavyPerson) {
            if (sortedPeople[lightPerson] + sortedPeople[heavyPerson] <= limit) {
                lightPerson += 1;
                heavyPerson -= 1;
            }else{
                heavyPerson -= 1;
            }
            answer += 1;
        }

        // 한 사람만 남은 경우
        if (lightPerson == heavyPerson){
            answer += 1;
        }

        return answer;
    }
}