package algorithm_data_structure.greedy;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class 조이스틱 {

    // 본 문제의 두 가지 풀이법에 대한 설명은 따로 문서를 만들었음
    // ./조이스틱_문제해설.md

    public int mySolution1(String name) {
        int answer = 0;
        int currIdx = 0;
        LinkedList<Integer> status = new LinkedList<>();

        // 위 아래 조작 횟수 및 방문해야 하는 위치 집계
        for (int i = 0; i < name.length(); i++) {
            if (name.charAt(i) != 'A'){
                int updownCount = Math.min((int)name.charAt(i) - (int)'A',
                                            (int)'Z' - (int)name.charAt(i) + 1);
                answer += updownCount;
                status.add(i);
            }
        }

        // idx 0(시작지점)은 처리하고 시작하므로 제거
        if (!status.isEmpty() && status.getFirst() == 0){
            status.removeFirst();
        }

        // 매번 '반드시 방문해야 하는 노드를 최단거리로 가는 방향'으로 이동 조작
        while (!status.isEmpty()) {
            int leftDiff = (status.getLast() < currIdx)
                            ? currIdx - status.getLast()
                            : currIdx + (name.length() - status.getLast());

            int rightDiff = (status.getFirst() > currIdx)
                            ? status.getFirst() - currIdx
                            : status.getFirst() + (name.length() - currIdx);

            if (leftDiff < rightDiff) {
                currIdx = status.removeLast();
                answer += leftDiff;
            }else{
                currIdx = status.removeFirst();
                answer += rightDiff;
            }
        }

        return answer;
    }

    public int mySolution2(String name) {
        int upDownSum = 0;
        int leftRightSum = 0;   
        List<Series> seriesGroup = getSeriesGroup(name);  // name 내 연속된 A 문자열 리스트

        // up-down 조이스틱 집계
        for (int i = 0; i < name.length(); i++){
            char target = name.charAt(i);
            upDownSum += Math.min((int)target - (int)'A', 
                                (int)'Z' - (int)target + 1);
        }

        // A 밖에 없으면 바로 종료
        if (upDownSum == 0){
            return 0;
        }

        // left-right 조이스틱 집계
        if (seriesGroup.isEmpty()) {
            leftRightSum = name.length() - 1;  // 모두 A가 아니면 그냥 순방향 이동
        } else {
            leftRightSum = name.length() * 2;  // 적당히 큰 값으로 초기화
            for (Series series : seriesGroup) {
                leftRightSum = Math.min(leftRightSum, series.leftRight());
            }
        }

        return upDownSum + leftRightSum;
    }

    private List<Series> getSeriesGroup(String str){
        LinkedList<Series> seriesGroup = new LinkedList<>();

        Pattern pattern = Pattern.compile("A+"); 
        Matcher m = pattern.matcher(str);

        while (m.find()) {           
            seriesGroup.add(new Series(m.start(), m.end()-1, str));
        }

        // 마지막과 처음은 연결되었으므로 A가 이어지면 결합
        if (seriesGroup.size() >= 2 
                && seriesGroup.getLast().isConnectable(seriesGroup.getFirst())) {
            Series connectedSeries = seriesGroup.removeLast().connect(seriesGroup.removeFirst());
            seriesGroup.add(connectedSeries);
        }

        return seriesGroup;
    }

    class Series {
        private int startIdx;      // inclusive
        private int endIdx;        // inclusive
        private int parentLength;
        private int length;

        public Series(int startIdx, int endIdx, String parent) {
            this.startIdx = startIdx;
            this.endIdx = endIdx;
            this.parentLength = parent.length();
            this.length = (startIdx <= endIdx)
                        ? (endIdx - startIdx + 1) 
                        : ((endIdx + 1) + (this.parentLength - startIdx));
        }

        boolean isConnectable(Series next) {
            return (this.endIdx == this.parentLength - 1) && (next.startIdx == 0);
        }

        public Series connect(Series next) {
            this.endIdx = next.endIdx;
            this.length += next.length;
            return this;
        }

        public int leftRight() {
            /*
            * 해당 a series를 지나지 않을 때 좌우 이동의 최솟값 반환 
            */
            int leftRepeat = 0;   // T() 왼쪽 끝으로 이동하는 횟수
            int rightRepeat = 0;  // T() 오른쪽 끝으로 이동하는 횟수
            int contentLength = (parentLength - length - 1);

            if (startIdx <= endIdx) {
                leftRepeat = (endIdx == parentLength - 1) ? 0 : (parentLength - endIdx + 1);
                rightRepeat = (startIdx == 0) ? 0 : (startIdx - 1);
            } else {
                leftRepeat = (parentLength - startIdx - 1);
                rightRepeat = (endIdx + 1);
            }

            return contentLength + Math.min(length, Math.min(leftRepeat, rightRepeat));
        }
    }
}