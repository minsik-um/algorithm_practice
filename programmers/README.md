# Algorithm Practice on Programmers

<img src="/imgs/icon_programmers.png" width="200px" alt="icon programmers"></img>
<br>
출처: 프로그래머스 코딩 테스트 연습,
<br>
https://programmers.co.kr/learn/challenges

- Python3
- 하루 1문제씩(lv1 문제는 풀지만 할당량에서 제외)
- 코딩 테스트 연습에 공개된 문제가 아닌, 기업 입사 코딩 테스트 등에 나온 문제는 법적 문제상 올리면 안된다. [출처](https://programmers.zendesk.com/hc/ko/articles/360034546572-프로그래머스의-알고리즘-문제-풀이를-개인-블로그-깃헙-기타-사이트에-올려도-되나요-)


## 해시(Hash)
>Key: Value 형태의 Set
- Key는 Unique 하며 str이 가능. str 혹은 int으로 특정 값을 찾을 때 O(1)로 빠르게 탐색 가능
- (str, tuple, ...)여러 타입의 Key로 value를 불러올 수 있음

문제 풀이
- Level 1 / 완주하지 못한 선수 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42576?language=python3)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/hash/완주하지%20못한%20선수.py)

- Level 2 / 전화번호 목록 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42577)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/hash/전화번호%20목록.py)

- Level 2 / 위장 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42578)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/hash/위장.py)

- Level 3 / 베스트앨범 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42579)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/hash/베스트앨범.py)


## 스택/큐(Stack/Queue)
>Stack: LIFO(Last Input First Output)<br>
Queue: FIFO(first Input First Output)

- 리스트(ordered)의 입구/출구를 정하여 수행할 데이터의 순서를 명확하게 지정하여 불필요한 계산 과정을 줄일 수 있다. 예컨대 함수 실행 처리를 Stack 대신 Queue를 이용한다면, 매번 각 함수 처리 전 하위 함수 처리가 완료되었는지 따져야 한다. 
- 대개 Insert(push) / Delete(pop, shift)를 사용한다.
- 메모리와 스케줄링 등의 예시가 있으며, DFS, BFS 등 그래프 자료형의 기본 개념이 된다.
- Deque는 stack과 queue를 합친 형태다. Python에서는 deque 라이브러리를 제공한다.

참고
- [Python Stack/Queue Example](https://docs.python.org/3/tutorial/datastructures.html?highlight=list#using-lists-as-queues)
- [스택_큐 개념](https://mygumi.tistory.com/357)

문제풀이
- Level 2 / 탑 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42588)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/stack_queue/탑.py)

- Level 2 / 다리를 지나는 트럭 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42583)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/stack_queue/다리를%20지나는%20트럭.py)

- Level 2 / 기능개발 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42586)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/stack_queue/기능개발.py)

- Level 2 / 프린터 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42587)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/stack_queue/프린터.py)

- Level 2 / 쇠막대기 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42585)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/stack_queue/쇠막대기.py)

- Level 2 / 주식개발 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42584)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/stack_queue/주식개발.py)


## 힙(Heap)
<img src="/imgs/heap_example.png" width="330px" alt="heap example"></img>
>A가 B의 부모노드(parent node) 이면, A의 키(key)값과 B의 키값 사이에는 대소관계가 성립하는 완전이진트리
- Heap Property를 만족하는 Complete Binary Tree. **유동적인 리스트에서 최댓값/최솟값을 빠르게 찾기 위함.** root node로 올수록 클 경우 max-heap, 작으면 min-heap이다.
- 두 개를 합쳐 최대/최소값 접근을 O(1)로 하는 min-max heap도 있다. 아종으로 symmetric min-max heap이 있다. 간단한 문제라면 - 이중우선순위큐 문제 참고 - heap 2개를 이용하여 유사 min-max heap을 만들 수 있다.
- 우선순위 큐(Priority Queue)를 실질적으로 구현
https://hannom.tistory.com/36

출처
- https://ko.wikipedia.org/wiki/힙_(자료_구조)
- https://docs.python.org/3.7/library/heapq.html

문제 풀이
- Level 2 / 더 맵게 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42626)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/heap/더%20맵게.py)

- Level 2 / 라면공장 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42629)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/heap/라면공장.py)

- Level 3 / 디스크 콘트롤러 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42627)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/heap/디스크%20콘트롤러.py)

- Level 3 / 이중우선순위큐 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42628)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/heap/이중우선순위큐.py)


## 정렬(Sort)
- 컴퓨터 처리 데이터는 양이 매우 많아 search 할 때 많은 시간이 소요된다.
- 정렬을 해두면 이진 탐색(logn) 등 빠른 탐색이 가능하다.
- 다양한 정렬 방법이 있으며 Comparison Sort는 O(nlogn)이 최대 속도다.
- Non-comparision Sort도 있으며 특정 조건이 필요한 걸로 보인다.
- compare 함수를 이용한 방법이 명확하게 풀이할 수 있다. (예. 가장 큰 수)

참고
- https://en.wikipedia.org/wiki/Sorting_algorithm
- [파이썬 정렬 사용](https://docs.python.org/ko/3/howto/sorting.html)

문제 풀이
- Level 1 / K번째수 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42748)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/sort/k번째수.py)

- Level 2 / 가장 큰 수 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42746)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/sort/가장%20큰%20수.py)

- Level 2 / H-Index / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42747)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/sort/h-index.py)


## 완전탐색(Brute-force Search)
- 내용 1
- 내용 2

참고
- 링크 1
- 링크 2

문제 풀이
- 문제 1
