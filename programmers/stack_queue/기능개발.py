from collections import deque


def my_solution(progresses, speeds):
    queue = deque(zip(progresses, speeds))
    answer = []

    while queue:
        queue = deque(map(lambda x: (x[0]+x[1], x[1]), queue))

        temp = 0
        while queue and queue[0][0] >= 100:
            queue.popleft()
            temp += 1

        if temp:
            answer.append(temp)

    return answer


def best_solution(progresses, speeds):
    '''
    1.
    내 방법은 모든 과정이 100이 넘을 때까지 계속 반복하고
    매번 deque를 재생성하지만,
    (speeds와 progresses 수치가 낮을수록 시간 증가)

    여기선 남은 일수의 대소 비교를 통해 전체 일 갯수 만큼만
    돌면 되서 아주 빠르며 굳이 queue를 안써도 된다.
    (두 수치가 크든 작든 상관 없음)

    2.
    math.ceil((100-p)//s) 대신에 -((p-100)//s)를 사용하여
    자동으로 올림을 사용
    '''
    Q = []
    for p, s in zip(progresses, speeds):
        if len(Q) == 0 or Q[-1][0]<-((p-100)//s):
            Q.append([-((p-100)//s), 1])
        else:
            Q[-1][1] += 1
    return [q[1] for q in Q]


def new_solution(progresses, speeds):
    '''
    best solution을 기억하며 다시 작성한 코드
    '''
    answer = []
    
    for p, s in zip(progresses,speeds):
        remain_day = -((p-100)//s)
        
        if not answer or remain_day > answer[-1][0]:
            answer.append([remain_day, 1])
        else:
            answer[-1][1] += 1

    return [x[1] for x in answer]