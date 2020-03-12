import heapq as hq


def my_solution(stock, dates, supplies, k):
    '''
    (다른 사람 답변을 참고하여 완성함 진짜 어려웠음...)
    1. 매일 공급량을 검사하며 바닥난 날로 이동
    2. 그날 포함 지난 날 중에 가능했던 공급 중 최대 공급을 적용
    3. 다음 바닥난 날로 이동

    이때 가능했던 공급들을 최대 힙에서 관리하여
    빠르게 최대값을 찾아내는 게 포인트
    (python은 최소 힙을 제공하므로 -를 곱하여 최대 힙 활용)

    속도를 위해 if문을 최소한으로 쓰고, zip 등의 함수도 조심
    모든 날을 검사하지 않고 바닥난 날만 검사하여 속도 향상
    '''
    current_stock = stock
    last_index = 0
    remain_h = []
    answer = 0

    while current_stock < k:
        while last_index < len(dates) and dates[last_index] <= current_stock:
            hq.heappush(remain_h, (-1) * supplies[last_index])
            last_index += 1

        current_stock += (-1) * hq.heappop(remain_h)
        answer += 1

    return answer