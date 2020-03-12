import heapq


def my_solution(scoville, K):
    '''
    heap에 들어갈 value를 특정 계산식으로 거친 거라면
    우선순위 큐로서 기능한다.
    '''
    h = []
    answer = 0
    
    for value in scoville:
        heapq.heappush(h, value)
    
    while len(h) >= 2 and h[0] < K:
        first, second = heapq.heappop(h), heapq.heappop(h)    
        heapq.heappush(h, first + 2*second)
        answer += 1

    return answer if h[0] >= K else -1