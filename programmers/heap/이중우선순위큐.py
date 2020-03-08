from heapq import heappush, heappop


def my_solution(operations):
    '''
    최소 힙 1개를 사용하여 최소값을 처리하고
    매번 max() 로 최대값을 찾아 remove()를 이용한다.

    => max()와 remove()에서 이미 O(n)을 넘어갔기에
    절대로 좋은 코드가 아니다. (운 좋게 통과했을 뿐)
    heap을 쓰는 의미가 사라진다.
    이 문제에서 heap 안써도 된다고 하는 건 heap의 의도를 모르는 사람이다.
    '''
    heap = []
    for op in operations:
        key, value = op.split(" ")
        if key == "I":
            heappush(heap, int(value))
        elif heap:
            if value == '1':
                heap.remove(max(heap))
            else:
                heappop(heap)
            
    if heap:
        return [max(heap), heap[0]]
    else:
        return [0, 0]


def best_solution(operations):
    '''
    max_heap, min_heap 2개를 만들고 매번 넣어주면 된다.
    max_heap과 min_heap이 전체 heap의 상태와 동일한 상태이어야
    max_heap[0], min_heap[0]이 전체 heap의 최대/최소값임을 보장한다.

    => 넣을 때는 2개 heap에 다 넣지만 뺄 때는 한 쪽에서만 빼준다.
       (두 heap 전부 빼려면 pop이 아니라 탐색 후 제거해야 하므로)
       아래 설명이 있어 한 쪽에서만 빼주어도 된다.
       최대 heap 기준에서 최소값 빼는 건 최대 heap[0]에 영향을 주지 않으며,
       최소 heap 기준에서 최대값 빼는 건 최소 heap[0]에 영향을 주지 않기 때문이다.

       다만 이러다보니 전체 heap의 모든 원소가 제거되었지만 두 heap은 비어있지 않은
       상황이 생긴다. 그래서 아래 2가지 상황에 동기화를 해준다.

    1. 한쪽 heap 원소가 없다면 전체 heap이 비었다는 뜻이므로
       나머지 heap도 비워준다.
    2. -max_heap[0] < min_heap[0]: 이해가 어려운데
       max_heap 남은 원소들은 이미 min_heap에서 다 제거된 상태고,
       min_heap 남은 원소들은 이미 max_heap에서 다 제거된 상태다.
       그래야 서로가 반전된 이 상황이 나오고 사실상 다 제거된 상태이므로
       전부 제거해준다.
       예) I 9 -> I 5 -> I -5 -> D 1 -> D -1 -> D 1

    Time complexity 참고
    https://en.wikipedia.org/wiki/Binary_heap
    (정답 코드를 간소화했음)
    '''
    max_heap = []
    min_heap = []
    for operation in operations:
        if operation == "D 1":
            if max_heap:
                heappop(max_heap)
                if not max_heap or -max_heap[0] < min_heap[0]:
                    min_heap = []
                    max_heap = []
        elif operation == "D -1":
            if min_heap:
                heappop(min_heap)
                if not min_heap or -max_heap[0] < min_heap[0]:
                    min_heap = []
                    max_heap = []
        else:
            num = int(operation[2:])
            heappush(max_heap, -num)
            heappush(min_heap, num)

    if min_heap:
        return [-heappop(max_heap), heappop(min_heap)]
    else:
        return [0, 0]
