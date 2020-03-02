# 알고리즘 연습 + 언어 숙련

연습 언어: Python3 (조만간 Java, R, Scala 연습 시작 예정)

#### Commit Message 예시

* 추가: 프로그래머스 / 스택_큐 / 프린터(py, README)
* 수정: 프로그래머스 / 해시 / 위장(py)

## Programmers
<img src="/imgs/icon_programmers.png" width="200px" alt="icon programmers"></img><br>https://programmers.co.kr/

### 해시(Hash)
Key: Value 형태의 Set. Key는 Unique 하며 str이 가능. str 혹은 int으로 특정 값을 찾을 때 O(1)로 빠르게 탐색 가능하다. 만약 리스트를 이용하면 매번 목표 Key와 현재 Key를 대조하며 찾아야 함.

* Level 1 / 완주하지 못한 선수 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42576?language=python3)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/hash/완주하지%20못한%20선수.py)

* Level 2 / 전화번호 목록 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42577)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/hash/전화번호%20목록.py)

* Level 2 / 위장 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42578)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/hash/위장.py)

* Level 3 / 베스트앨범 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42579)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/hash/베스트앨범.py)

### 스택/큐(Stack/Queue)
양쪽으로 접근하는 리스트와 달리 확실히 입구/출구를 정하여 **수행할 데이터의 순서를 명확하게 지정**하려고 쓴다. 이때 모든 접근 속도가 O(1)이 되도록 해준다. 예컨대 스택은 컴퓨터 코드 실행 구조가 있고 큐는 네트워크 상태 확인이 있다. Python List는 Stack 접근 속도가 빠르지만 Queue의 insert 속도가 느려(ArrayList) 다른 객체를 제공하고 있다.<br>
[Python Stack/Queue Example](https://docs.python.org/3/tutorial/datastructures.html?highlight=list#using-lists-as-queues)
/
[스택/큐 개념](https://mygumi.tistory.com/357)


Stack: LIFO(Last Input First Output)<br>
push / pop

Queue: FIFO(first Input First Output)<br>
Insert(push) / Delete(shift)

* Level 2 / 탑 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42588)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/stack_queue/탑.py)

* Level 2 / 다리를 지나는 트럭 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42583)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/stack_queue/다리를%20지나는%20트럭.py)

* Level 2 / 기능개발 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42586)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/stack_queue/기능개발.py)

* Level 2 / 프린터 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42587)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/stack_queue/프린터.py)