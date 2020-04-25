package algorithm_data_structure.hash;

import java.util.*;

class 전화번호_목록 {
    public boolean my_solution(String[] phone_book) {        
        /**
         * 해시를 사용하지 않은 풀이
         * 1. startsWith() 사용
         * 2. return을 중간에 하기에 loop를 줄인다.
         * 
         * 프로그래머스 실행 시간은 아래 해시 이용 코드보다 빠르긴 하다.
         * 그러나 이건 중간에 return으로 빠져 나가서 그런 것이다.
         * worst case를 따졌을 때 이중 for문이 절대로 좋지 않다.
         * worst case를 본다면 hash 이용 다른 코드가 나을 수 있다.
         * 문제마다 각각 생각해볼 문제다.
         */
        for (int i = 0; i < phone_book.length; i++){
            for (int j = i+1;j < phone_book.length; j++){                
                if (phone_book[i].startsWith(phone_book[j])
                   || phone_book[j].startsWith(phone_book[i])){
                        return false;
                }                    
            }            
        }
        
        return true;
    }

    public boolean best_solution(String[] phone_book) {
        /**
         * 해시맵을 사용한 풀이
         * 각 단어가 가질 접두사 목록을 돌면서 해시맵에 있는지 확인
         * 
         * for(String number : phone_book) dictionary.put(number, 0);
         * 아무리 O(1)이라도 for문이 n번 돌면서 시간이 증가
         * 
         * https://stackoverflow.com/questions/4679746/time-complexity-of-javas-substring
         * https://stackoverflow.com/questions/3870064/performance-considerations-for-keyset-and-entryset-of-map
         * 
         * 
         * [ HashMap vs TreeMap vs LinkedHashMap ]
         * 
         * iteration을 돌 때 들어오는 값의 순서가 차이점이다.
         * HashMap: 순서를 보장하지 않는다.
         * TreeMap: string의 알파벳 순서
         * LinkedHashMap: put한 순서(before도 가능)
         * 거의 대부분의 경우 HashMap을 사용하자.
         * Key의 범위 검색이나 정렬이 필요하면 TreeMap/LinkedHashMap을 사용하자.
         * 
         * 참고
         * https://stackoverflow.com/questions/2889777/difference-between-hashmap-linkedhashmap-and-treemap
         * https://pridiot.tistory.com/74
         */
        HashMap<String, Integer> dictionary = new HashMap<>();
        for(String number : phone_book) dictionary.put(number, 0);
        
        for(String number : phone_book){
            for(int i = 0; i < number.length(); i++){
                if (dictionary.containsKey(number.substring(0, i))){
                    return false;
                }
            }
        }
        
        return true;
    }    
}


