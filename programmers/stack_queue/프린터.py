from collections import deque


def my_solution(priorities, location):
    '''
    가능한 매번 따져보지 않고 간단히 계산하고 싶지만 
    다른 답도 그렇고 그냥 프린터 출력 과정 그대로 따라가며
    매번 따져봐야 함.

    Queue 이용 과정은 모두 같으나 다른 풀이가 훨씬 간단한 이유
    속도는 따져봐야 함.

    1. 굳이 sort를 하지 않고 max를 실행
    - time complexity는 sort가 nlogn, max가 n이다.
    그러나 sort는 한 번만 하면 되지만 max는 찾을 때까지 매번 실행해야 된다.
    적은 횟수로 max가 실행되면 괜찮겠지만 최악의 경우엔 sort가 유리할 것이다.
    (테스트 해봐야 함)
    2. any 함수 내 generator 부분도 결국 한 번에 n 번 돈다.
    '''
    # 가장 우선순위가 높은 애들의 원래 인덱스를 저장한 데이터
    sorted_pri = deque(sorted(priorities, reverse=True))    
    queue = deque(zip(priorities, range(len(priorities))))
    
    answer = 1
    while not (queue[0][1] == location and queue[0][0] == sorted_pri[0]):
        check = queue.popleft()
        print(check)
        if check[0] == sorted_pri[0]:
            sorted_pri.popleft()
            answer += 1
        else:
            queue.append(check)
        
    return answer


def best_solution_1(p, l):
    '''
    실시간으로 목표 문서의 위치를 업데이트하고
    max() 함수로 매번 최고 높은 값을 찾는다.

    만약 출력을 실행하는데 목표 문서가 첫 번째 위치라면
    답변 반환
    '''
    ans = 0
    m = max(p)
    while True:
        v = p.pop(0)
        if m == v:
            ans += 1
            if l == 0:
                break
            else:
                l -= 1
            m = max(p)
        else:
            p.append(v)
            if l == 0:
                l = len(p)-1
            else:
                l -= 1
    return ans

def best_solution_2(priorities, location):
    '''
    any 함수를 이용하여 출력할 문서가
    다른 문서 하나보다 우선순위가 낮으면 뒤로 넘기기
    '''
    queue =  [(i,p) for i,p in enumerate(priorities)]
    answer = 0
    while True:
        cur = queue.pop(0)
        if any(cur[1] < q[1] for q in queue):
            queue.append(cur)
        else:
            answer += 1
            if cur[0] == location:
                return answer
