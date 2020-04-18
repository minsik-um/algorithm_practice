def my_solution(heights):
    '''
    break를 넣어도 worst O(n^2)이므로 결코 좋지 않다.
    '''
    answer = [0] * len(heights)
    for i in reversed(range(len(heights))):
        for j in reversed(range(i)):
            if heights[i] < heights[j]:
                answer[i] = j+1
                break
    return answer


def better_solution(heights):
    '''
    java 풀이의 best 풀이를 참고하여 구현
    위 코드에 비해 압도적으로 빠르다.

    여기서 stack에 저장되는 것은 왼쪽 타워 중 
    stack : 오른쪽에 나올 탑보다 더 높을 수 있는 탑들
            오른쪽 탑부터 신호 수신 우선순위가 있어
            스택 구조로 오른쪽 탑부터 비교해가야 한다.

    오른쪽 탑보다 커서 점수를 기록한 탑은 스택에 남겨놓고
    한 번이라도 오른쪽 탑보다 낮은 탑은 스택에서 제거한다.
    (더 큰 오른쪽 탑이 스택 마지막에 들어가므로 굳이 남아 있을 필요가 없다)

    스택 내부를 전부 도는 경우 이때 스택이 크기 1개로 쭉 줄어들어 다음 계산이 줄어들고
    반대 경우엔 스택 외부 끝만 돌고 크기 1개 추가라서
    O(n^2)가 절대 되지 않는다.
    '''
    left_tower = []
    answer = [0] * len(heights)

    for idx in range(len(heights)):
        received_tower_index = 0

        while left_tower:
            if left_tower[-1][1] > heights[idx]:
                received_tower_index = left_tower[-1][0]
                break
            else:
                left_tower.pop()

        left_tower.append([idx+1, heights[idx]])
        answer[idx] = received_tower_index

    return answer


def best_solution(heights):
    '''
    여기서 stack에 저장되는 것은 아직 수신할 탑을 찾지 못한 오른쪽 탑들이다.
    
    queue를 써서 뒤쪽부터 처리하도록 하면 안된다. 
    왜냐하면 쌓여있는 걸 보면 우측으로 갈수록 값이 커지는 형태다.
    뒤에 큰 값을 먼저 보고 stack[-1][1] >= heights[i]: 이라고 판단해서 나가버리면
    그 앞에 정말로 처리될 애들이 처리되지 못한다.

    *** 인사이트 ***
    스택 끝에만 보기 때문에 (그 내부는 스택 끝보다 같거나 크기에 스택 끝보다 작은 수는 건너 뛴다)
    '이중 for문에서 아직 못찾은 애들의 중첩된 탐색 범위'를 1번만 봐서
    그만큼 속도가 빨라지는 것이다
    '''
    answer = [0] * len(heights)
    stack = [] 

    for i in reversed(range(len(heights))):
        while stack and stack[-1][1] < heights[i]:
            idx, height = stack.pop()  # 이렇게 풀어주어 가독성을 높이자
            answer[idx] = i+1
        stack.append((i, heights[i]))

    return answer


import time
import random


def time_check(func, heights):
    start = time.time()
    func(heights)
    return time.time() - start


if __name__ == "__main__":
    heights = [random.randint(1, 100) for i in range(100000)]

    print("첫 번째 실행 ", time_check(my_solution, heights))
    print("두 번째 실행 ", time_check(better_solution, heights))
    print("세 번째 실행 ", time_check(best_solution, heights))