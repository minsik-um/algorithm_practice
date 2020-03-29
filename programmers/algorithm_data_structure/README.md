# Algorithm Practice on Programmers

<img src="/imgs/icon_programmers.png" width="200px" alt="icon programmers"></img>
<br>
출처: 프로그래머스 코딩 테스트 연습,
<br>
https://programmers.co.kr/learn/challenges

- Python3
- 하루 1문제씩(lv1 문제는 풀지만 할당량에서 제외)
- 코딩 테스트 연습에 공개된 문제가 아닌, 기업 입사 코딩 테스트 등에 나온 문제는 법적 문제상 올리면 안된다. [출처](https://programmers.zendesk.com/hc/ko/articles/360034546572-프로그래머스의-알고리즘-문제-풀이를-개인-블로그-깃헙-기타-사이트에-올려도-되나요-)
- itertools, operator 라이브러리를 활용하여 Pythonic code를 만들 수 있다.<br>https://hamait.tistory.com/803


## 해시(Hash)
>Key: Value 형태의 Set
- Key는 Unique 하며 str이 가능. str 혹은 int으로 특정 값을 찾을 때 O(1)로 빠르게 탐색 가능
- (str, tuple, ...)여러 타입의 Key로 value를 불러올 수 있음

문제 풀이
- Level 1 / 완주하지 못한 선수 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42576?language=python3)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/algorithm_data_structure/hash/완주하지%20못한%20선수.py)

- Level 2 / 전화번호 목록 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42577)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/algorithm_data_structure/hash/전화번호%20목록.py)

- Level 2 / 위장 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42578)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/algorithm_data_structure/hash/위장.py)

- Level 3 / 베스트앨범 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42579)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/algorithm_data_structure/hash/베스트앨범.py)


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
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/algorithm_data_structure/stack_queue/탑.py)

- Level 2 / 다리를 지나는 트럭 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42583)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/algorithm_data_structure/stack_queue/다리를%20지나는%20트럭.py)

- Level 2 / 기능개발 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42586)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/algorithm_data_structure/stack_queue/기능개발.py)

- Level 2 / 프린터 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42587)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/algorithm_data_structure/stack_queue/프린터.py)

- Level 2 / 쇠막대기 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42585)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/algorithm_data_structure/stack_queue/쇠막대기.py)

- Level 2 / 주식개발 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42584)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/algorithm_data_structure/stack_queue/주식개발.py)


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
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/algorithm_data_structure/heap/더%20맵게.py)

- Level 2 / 라면공장 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42629)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/algorithm_data_structure/heap/라면공장.py)

- Level 3 / 디스크 콘트롤러 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42627)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/algorithm_data_structure/heap/디스크%20콘트롤러.py)

- Level 3 / 이중우선순위큐 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42628)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/algorithm_data_structure/heap/이중우선순위큐.py)


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
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/algorithm_data_structure/sort/k번째수.py)

- Level 2 / 가장 큰 수 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42746)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/algorithm_data_structure/sort/가장%20큰%20수.py)

- Level 2 / H-Index / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42747)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/algorithm_data_structure/sort/h-index.py)


## 완전탐색(Brute-force Search)
- 정답을 보장하지만 시간 복잡도는 포기한 최후의 방법 (거의 O(n**2) 이상)
- 사용 조건
    - 문제 데이터 크기가 제한되었거나
    - 'Heuristic'으로 탐색할 목록 범위를 처리 가능한 크기까지 줄인다면
- itertools에 permutations 등을 최대한 활용하자
- 목표에 특정 패턴이 없어 효율적인 방법을 찾을 수 없다면 완전탐색을 하자...
<br>예. '소수찾기' 문제: 수를 조합하기 전에 어떤 패턴으로 소수를 만들 수 있는지 알 수 없음. 일일이 다 만들어보며 소수인지 검사해야 함.

참고
- https://en.wikipedia.org/wiki/Brute-force_search
- https://en.wikipedia.org/wiki/Heuristic_(computer_science)

문제 풀이
- Level 1 / 모의고사 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42840)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/algorithm_data_structure/brute_force_search/모의고사.py)

- Level 2 / 소수 찾기 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42839)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/algorithm_data_structure/brute_force_search/소수%20찾기.py)

- Level 2 / 숫자 야구 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42841)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/algorithm_data_structure/brute_force_search/숫자%20야구.py)

- Level 2 / 카펫 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42842?language=python3)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/algorithm_data_structure/brute_force_search/카펫.py)


## 탐욕법(Greedy Algorithm)
- global optimal choice를 찾기 위해 매번 local optimal choice를 고르는 Heuristic.
- **단계를 거듭해도 각 단계의 수행 시간이 거의 일정하여 빠르다.**
- global optimal choice를 보장하진 않지만 근사치에 접근한다.
- 완전 탐색하기엔 시간이 부족하거나 global optiaml choice가 증명되면 사용한다.
- [풀이 요령]
    - 완전탐색처럼 쭉 출력해보며 주어진 문제의 패턴을 본다.
    - 그 패턴을 표현하는 가장 간단한 방법을 찾아낸다.
    (저울 문제 참고)
- Kruskal's Algorithm, Prim's Algorithm (섬 연결하기 문제 참고)

참고
- https://en.wikipedia.org/wiki/Greedy_algorithm

문제 풀이
- Level 1 / 체육복 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42862)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/algorithm_data_structure/greedy/체육복.py)

- Level 2 / 조이스틱 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42860)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/algorithm_data_structure/greedy/조이스틱.py)

- Level 2 / 큰 수 만들기 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42883)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/algorithm_data_structure/greedy/큰%20수%20만들기.py)

- Level 2 / 구명보트 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42885)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/algorithm_data_structure/greedy/구명보트.py)

- Level 3 / 섬 연결하기 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42861)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/algorithm_data_structure/greedy/섬%20연결하기.py)

- Level 3 / 단속카메라 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42884)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/algorithm_data_structure/greedy/단속카메라.py)

- Level 3 / 저울 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42886)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/algorithm_data_structure/greedy/저울.py)


## 동적계획법(Dynamic Programming)
- 문제를 여러개의 작은 문제로 나누고 그것을 결합하여 풀면서(Divide and Qunquer)
- 이전 단계의 결과를 저장하고 다음 단계에 활용하여 중복된 계산을 최소화한다.
- **귀납적 사고**를 하면 된다. 최댓값을 이어간다: 이전 상태 최댓값으로 현재 상태 최댓값을 계산한다.
    - 도둑질, 서울에서 경산까지 문제처럼 주어진 개념을 길찾기 문제처럼 접근하자.
- (최대 가치) 길찾기 문제가 주로 등장하며 2차원 리스트(행렬) 혹은 해시 구조에 정보를 저장해간다.
- 재귀함수를 이용하면 memoization을 활용하자(예. 정수삼각형 및 상위 레벨)
    - memoization에는 dictionary를 쓰자. 열거형 key(hashble type)는 tuple이어야 한다.
    - 코드는 간단해보여도 대부분 시간 효율성이 안좋거나 depth limit에 걸린다.
- 길찾기 탐색 방향을 정방향/역방향으로 할지 고민해봐야 한다(예. 카드 게임)

참고
- https://ko.wikipedia.org/wiki/동적_계획법
- https://www.zerocho.com/category/Algorithm/post/584b979a580277001862f182

문제 풀이
- Level 3 / N으로 표현 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42895)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/algorithm_data_structure/dynamic_programming/N으로%20표현.py)

- Level (1 같은) 3 / 타일 장식물 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/43104)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/algorithm_data_structure/dynamic_programming/타일%20장식물.py)

- Level 3 / 정수 삼각형 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/43105)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/algorithm_data_structure/dynamic_programming/정수%20삼각형.py)

- Level 3 / 등굣길 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42898)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/algorithm_data_structure/dynamic_programming/등굣길.py)

- Level 4 / 카드 게임 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42896)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/algorithm_data_structure/dynamic_programming/카드%20게임.py)

- Level 4 / 도둑질 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42897)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/algorithm_data_structure/dynamic_programming/도둑질.py)

- Level 4 / 서울에서 경산까지 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/42899)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/algorithm_data_structure/dynamic_programming/서울에서%20경산까지.py)


## 깊이/너비 우선 탐색(DFS/BFS)
- Graph: 정점과 간선으로 이뤄진 자료구조 / G = (V, E)
- Graph Search: 특정 정점에서 시작해 차례대로 모든 정점을 한 번씩 방문해보기
- 두 방법 모두 노드 방문 여부를 반드시 검사해야 무한루프에 빠지지 않음
- 노드 방문 여부는 보통 1차원 리스트 visited에 0/1 을 채우며 검사한다.

### 깊이 우선 탐색(DFS, Depth-First Search)
<img src="/imgs/dfs.png" width="450px" alt="depth-first search"></img>
- 루트 노드에서 시작하여 다음 분기(Branch)로 넘어가기 전에 해당 분기를 완벽히 탐색한다.
- 자기 자신을 호출하는 순환 알고리즘 형태
- Stack 자료구조 혹은 재귀 탐색 사용

### 너비 우선 탐색(BFS, Breadth-First Search)
<img src="/imgs/bfs.png" width="450px" alt="breadth-first search"></img>
- 인접한 노드를 먼저 탐색한다.
- 두 노드 사이의 (최단) 경로 찾기에 이용(DFS는 모든 관계 탐색을 해야 한다)
- 재귀 탐색이 불가능하며 Queue 사용

참고
- https://yunyoung1819.tistory.com/86
- https://en.wikipedia.org/wiki/Depth-first_search
- https://en.wikipedia.org/wiki/Breadth-first_search

문제 풀이
- Level 2 / 타겟 넘버 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/43165)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/algorithm_data_structure/dfs_bfs/타겟%20넘버.py)

- Level 3 / 네트워크 / 
[문제](https://programmers.co.kr/learn/courses/30/lessons/43162)
/
[풀이](https://github.com/minsik-um/algorithm_practice/blob/master/programmers/algorithm_data_structure/dfs_bfs/네트워크.py)
