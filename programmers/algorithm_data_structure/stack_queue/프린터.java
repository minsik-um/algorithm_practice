package algorithm_data_structure.stack_queue;

import java.util.*;

public class 프린터 {

    class Document {
        private int priority;
        private int initialLocation;

        public Document(int priority, int location){
            this.priority = priority;
            this.initialLocation = location;
        }

        public int getPriority(){
            return priority;
        }

        public int getInitialLocation(){
            return initialLocation;
        }
    }

    /*
     * 아래 다른 사람 코드를 학습하여 개선한 코드
     * var는 애매하거나 복잡할 때만 사용하고
     * 아래처럼 명시적으로 queue, int 와 같이 인터페이스/타입을 선언해주자
     */
    public int myBetterSolution(int[] priorities, int location) {
        int answer = 0;
        int lastIdx = priorities.length - 1;
        boolean targetIsPrinted = false;

        // 우선순위 값 기준으로 우선순위 정렬
        int[] sortedPriorities = Arrays.stream(priorities).toArray();
        Arrays.sort(sortedPriorities);        

        // 초기 문서 위치를 포함하여 우선순위 저장
        Queue<Document> documents = new LinkedList<>();
        for (int i = 0; i < priorities.length; i++){
            documents.add(new Document(priorities[i], i));
        }

        // 가장 큰 우선순위 값과 일치하면 인쇄
        while (!documents.isEmpty()){
            if (documents.peek().getPriority() == sortedPriorities[lastIdx-answer]){
                answer += 1;
                Document print = documents.poll();
                if (print.getInitialLocation() == location){
                    targetIsPrinted = true;
                    break;
                }
            }else{
                documents.add(documents.poll());
            }
        }

        if (! targetIsPrinted){
            throw new IllegalArgumentException();
        }
        
        return answer;
    }

    /*
     * 제출 당시 코드
     */
    public int mySolution(int[] priorities, int location) {
        int answer = 0;

        // 내림차 순으로 우선순위 정렬
        var sortedPriorities = new LinkedList<Integer>();
        for (int priority : priorities){
            sortedPriorities.add(priority);
        }
        sortedPriorities.sort(Comparator.comparingInt(x -> -x));

        // 초기 문서 위치를 포함하여 우선순위 저장
        var documents = new LinkedList<Document>();
        for (int i = 0; i < priorities.length; i++){
            documents.add(new Document(priorities[i], i));
        }

        // 가장 큰 우선순위 값과 일치하면 인쇄
        while (!documents.isEmpty()){
            if (documents.get(0).getPriority() == sortedPriorities.get(0)){
                answer += 1;
                Document print = documents.removeFirst();
                sortedPriorities.removeFirst();
                if (print.getInitialLocation() == location){
                    break;
                }
            }else{
                documents.addLast(documents.removeFirst());
            }
        }

        return answer;
    }

    /*
     * 나와 코드의 차이점
     * 1. Queue 인터페이스로 처리하여 메소드명이나 quque 이름 자체가 명시적이다.
     * 2. location을 문서 단위에 따로 저장하지 않았다. 
     *    (대신 l의 위치를 매번 추적함 => if문이 더 많이 있는데 좋을까?)
     * 3. priorities에서 remove를 하지 않고 answer를 활용해 인덱스 접근
     *    (remove 연산을 안해도 된다)
     */
    public int bestSolution1(int[] priorities, int location) {
        int answer = 0;
        int l = location;

        Queue<Integer> que = new LinkedList<Integer>();
        for(int i : priorities){
            que.add(i);
        }

        Arrays.sort(priorities);
        int size = priorities.length-1;

        while(!que.isEmpty()){
            Integer i = que.poll();
            if(i == priorities[size - answer]){
                answer++;
                l--;
                if(l < 0)
                    break;
            }else{
                que.add(i);
                l--;
                if(l < 0)
                    l=que.size()-1;
            }
        }

        return answer;
    }


    /*
     * 내 코드와 차이점
     * 1. final 을 선언하여 추가 수정 방지
     * 2. stream의 anyMatch 메소드를 이용
     *    (당연히 효율성은 떨어지지만 함수형 프로그래밍 특유의 가독 간결)
     * 3. return 대신에 illegalArgumentException을 마지막에 던진다.
     * 4. location 처리가 위 코드보다 훨씬 간단함
     */
    public int bestSolution2(int[] priorities, int location) {
        List<Integer> list = new ArrayList<>();
        for (int priority : priorities) {
            list.add(priority);
        }
    
        int turn = 1;
        while (!list.isEmpty()) {
            final Integer j = list.get(0);

            if (list.stream().anyMatch(v -> j < v)) {
                list.add(list.remove(0));
            } else {
                if (location == 0) {
                    return turn;
                }
                list.remove(0);
                turn++;
            }
    
            if (location > 0) {
                location--;
            } else {
                location = list.size() - 1;
            }
        }
    
        throw new IllegalArgumentException();
      } 
}