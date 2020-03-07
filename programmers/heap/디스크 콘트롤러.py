import heapq as hq
from collections import deque

'''
(다른 답들과 방법은 유사하여 내 풀이만 저장)    
클래스(속성, 메소드)를 활용하여 가독성을 높인 코드 있었음.
'''

def my_solution(jobs):
    '''
    매번 대기중인 작업 중에서 실행 시간이 가장 작은 작업을
    heap을 활용해 고르는 게 중요

    [설명]
    A -> C -> B
    A는 0초 기다림
    C는 (A초 실행시간 - 요청 시작 지점) 만큼 기다림
    B는 (A초 실행시간 + C초 실행시간 - 요청 시작 지점) 만큼 기다림

    (요청 시작 지점은 상수이므로) 각 작업 실행 시점까지 총 작업 시간을
    최소화하며 가야 최소 평균 시간을 보장한다. (dynamic programming)

    [주의점]
    * 요청 시간이 각 시점에서 이미 지나야 선택 항목으로 쓸 수 있음
    * jobs는 정렬되어 있지 않아 시간순으로 정렬 필요
    * 작업을 끝냈을 때 다음 작업까지 실행 없이 대기하는 경우 고려
    '''
    answer = 0
    curr_time = 0
    remain_jobs = deque(sorted(jobs))
    heap = []

    # 모든 작업이 대기/완료될 때까지 반복
    while remain_jobs:    
        # 대기 작업 목록 업데이트
        while remain_jobs and remain_jobs[0][0] <= curr_time:
            job = remain_jobs.popleft()
            hq.heappush(heap, (job[1], job[0]))
            
        if heap:
            # 대기 작업이 있으면 최소 시간 작업 선택해 적용
            select = hq.heappop(heap)
            curr_time += select[0]  # 실행한 작업 만큼 시간 이동
            answer += (curr_time-select[1])
        elif remain_jobs:
            # 대기 작업이 없으면 다음 작업 시간으로 이동
            curr_time = remain_jobs[0][0]

    # 나머지 대기 작업 처리
    while heap:
        select = hq.heappop(heap)
        curr_time += select[0]
        answer += (curr_time-select[1])
                            
    return answer // len(jobs)