# 알고리즘 연습 + 언어 숙련

연습 언어: Python3 (조만간 Java, R, Scala 연습 시작 예정)

#### Commit Message 예시

- 추가: 프로그래머스 / 힙 / 디스크 콘트롤러(py, README)
- 수정: 프로그래머스 / 힙 / 라면공장(py)

## Programmers
<img src="/imgs/icon_programmers.png" width="200px" alt="icon programmers"></img><br>https://programmers.co.kr/

## 해시(Hash)
Key: Value 형태의 Set.
Key는 Unique 하며 str이 가능. str 혹은 int으로 특정 값을 찾을 때 O(1)로 빠르게 탐색 가능한 점과 (str, tuple, ...)여러 타입의 Key로 value를 불러올 수 있는 점을 활용하자.

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
양쪽으로 접근하는 리스트와 달리 입구/출구를 정하여 수행할 데이터의 순서를 명확하게 지정한다. 불필요한 계산 과정을 줄이는 구조 선택이 중요하다. 예컨대 탑, 주식가격 문제에서 Stack+(break나 while)을 활용하여 실행 시간을 줄였다.<br>
[Python Stack/Queue Example](https://docs.python.org/3/tutorial/datastructures.html?highlight=list#using-lists-as-queues)
/
[스택_큐 개념](https://mygumi.tistory.com/357)

Stack: LIFO(Last Input First Output)<br>
push / pop

Queue: FIFO(first Input First Output)<br>
Insert(push) / Delete(shift)

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
<img src="/imgs/heap_example.png" width="330px" alt="heap example"></img><br>
Heap Property를 만족하는 Complete Binary Tree. **유동적인 리스트에서 최댓값/최솟값을 빠르게 찾기 위함.** root node로 올수록 클 경우 최대힙, 작으면 최소힙이다. heap property는 다음과 같다.
>A가 B의 부모노드(parent node) 이면, A의 키(key)값과 B의 키값 사이에는 대소관계가 성립한다.

출처
- https://ko.wikipedia.org/wiki/힙_(자료_구조)
- https://docs.python.org/3.7/library/heapq.html

힙을 활용하여 우선순위 큐를 구현할 수 있다.

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
