def my_solution(n, times):
    '''
    나올 최솟값을 목표로 해서 이분탐색한다.
    아래 경우의 수를 따져 약간 변형한다.

    times = [4, 7, 10] 이라고 하자.
    아래 시뮬레이션 돌리는 걸로 돌려보면

    1.
    최솟값(middle)이 20일 때
    n은 8, 9 둘 다 가능하지만 아래처럼 middle//time 하면 9가 나온다.
    하지만 원하는 n은 8이라면 어떻게 찾지?
    => 이 경우 그냥 목표 n이 8, 9라고 생각해도 되지 않나?
    만약 n = 8/9일 때 두 최솟값이 다르다면 예정대로 8에 갈 것이다.
    그러니까 사실 신경쓰지 않아도 되는 경우의 수

    2. 
    middle이 15일 때 result는 6이 나온다.
    하지만 15는 존재하지 않는 경우의 수다. n = 6일 때 최솟값은 14다.
    (같은 n 내에서 존재하는 최솟값은 1개이며 항상 범위 내 최소이다)
    n이 같을 때 아래 방향으로 이동하며 answer를 업데이트 하면 된다.
    '''
    start = 0
    end = max(times) * n
    answer = 0

    while start <= end:
        middle = (start + end) // 2
        result = sum([middle//time for time in times])
        if result < n:
            start = middle + 1
        elif result >= n:
            end = middle - 1
            answer = middle

    return answer


import heapq as hq


def wrong_solution(n, times):
    '''
    쌓여가는 패턴을 생각하지 않고
    시뮬레이션을 돌리는 방식

    당연히 시간 효율성이 낮다.
    문제에선 최솟값만 찾으면 되는데
    이 방법은 각각 어디로 들어가야하는지를 파악하므로
    불필요한 정보도 구한다.
    '''
    # [선택할 경우 증가 후 시간: heap 비교용, 한 번 걸리는 시간, 현재 시간]
    check = [[time, time] for idx, time in enumerate(times)]
    hq.heapify(check)
    answer = 0
    for _ in range(n):
        next_total, time = hq.heappop(check)        
        answer = next_total
        hq.heappush(check, [next_total+time,time])

    return answer