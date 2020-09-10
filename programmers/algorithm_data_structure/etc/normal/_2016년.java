package algorithm_data_structure.etc.normal;

public class _2016년 {
    /**
     * @param a month
     * @param b day
     * @return 지정한 날짜의 요일 문자열을 반환
     */
    public String solution(int a, int b) {
        String[] weekOfDayNames = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
        int idxOfFirstDate = 5;
        int idxOfTargetDate = (idxOfFirstDate + getDiff2016(1, 1, a, b)) % weekOfDayNames.length;
        return weekOfDayNames[idxOfTargetDate];
    }
    
    public int getDiff2016(int month1, int day1, int month2, int day2) {
        if (month1 == month2) {
            return day2 - day1;
        }
        
        int[] endDays2016 = {-1, 31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int diff = day2 + (endDays2016[month1] - day1);
        
        for (int i = month1 + 1; i <= month2 - 1; i++) {
            diff += endDays2016[i];
        }
        
        return diff;
    }
}