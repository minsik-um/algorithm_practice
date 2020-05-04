import heapq as hq
from collections import deque


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
    * jobs는 정렬되어 있지 않아 시간 + 처리시간 순으로 정렬 필요
    * 작업을 끝냈을 때 다음 작업까지 실행 없이 대기하는 경우 고려
    '''
    answer = 0
    curr_time = 0
    sorted_jobs = deque(sorted(jobs))

    delayed_jobs = []

    # 모든 작업이 완료될 때까지 반복
    while sorted_jobs or delayed_jobs:
        # 우선순위에 따라 수행할 작업 선택
        job = None
        request_time = None
        processing_time = None

        if delayed_jobs:
            job = hq.heappop(delayed_jobs)
            processing_time, request_time = job
        else:
            job = sorted_jobs.popleft()
            request_time, processing_time = job

        curr_time += max(request_time - curr_time, 0) + processing_time
        answer += (curr_time - request_time)

        # 대기 작업 목록 업데이트
        while sorted_jobs and sorted_jobs[0][0] < curr_time:
            job = sorted_jobs.popleft()
            hq.heappush(delayed_jobs, (job[1], job[0]))

    return answer // len(jobs)
