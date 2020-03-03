from collections import deque


def my_solution(arrangement):
    '''
    매번 (, )를 순서대로 검사하되 두 번째 기호를 같이 보며
    ()를 만나면 한 칸 더 넘어가며 3가지 정보를 검사하도록 함

    때문에 코드가 복잡해졌다. 아래 best solution이 훨씬 괜찮다.
    '''
    answer = 1
    on_laser = 0
    
    queue = deque(arrangement)

    while len(queue) >= 2:
        current = queue.popleft()
        
        if current == "(":
            if queue[0] == ")":
                answer += on_laser
                queue.popleft()
            else:
                on_laser += 1
        else:
            on_laser -= 1
            answer += 1
        
    return answer


def best_solution(arrangement):
    '''
    원하는 상태 분류가 (, ), () 총 3가지인데 사용 기호는 2개 뿐이라
    처리가 복잡하다. 그래서 () 는 다른 기호로 치환하여 간단하게 해결
    replace 함수 활용 
    '''
    answer = 0
    sticks = 0
    rasor_to_zero = arrangement.replace('()','0')

    for i in rasor_to_zero:
        if i == '(':
            sticks += 1
        elif i == '0':
            answer += sticks
        else :
            sticks -= 1
            answer += 1

    return answer