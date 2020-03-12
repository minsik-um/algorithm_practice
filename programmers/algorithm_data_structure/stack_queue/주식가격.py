from collections import deque


def my_solution(prices):
    '''
    queue를 이용하여 정말로 정직하게 순서 따라가며 풀이
    그러나 속도가 빠르지 않다.
    '''
    answer = []
    queue = deque(prices)

    while queue:
        start_price = queue.popleft()
        answer.append(0)
        for price in queue:
            if start_price <= price:
                answer[-1] += 1

    return answer


def best_solution(p):
    '''
    queue 대신에 stack을 쓴 방법.
    코드가 이해하기 어려우나
    속도가 2배 이상 빠르다.

    stack: 주식 가격이 처음으로 떨어지는 지점을 아직 못찾은 가격의 index 모음    
    i for문을 돌며 'stack에 남은 것들이 i 번째에 처음으로 가격이 떨어지는가?'를 검사
    
    이때 queue를 쓰지 않고 stack을 써서 역으로 index를 검사하는 건
    stack 내 뒤쪽 것이 p[i]보다 가격이 같거나 작다면,
    그 앞의 것들은 i index에서 최초로 가격이 떨어질리 없기에 
    굳이 검사하지 않고 break로 시간을 줄일 수 있기 때문.
    '''

    ans = [0] * len(p)
    stack = [0]
    for i in range(1, len(p)):
        print('i:', i, ' stack:', stack, ' p[i]:', p[i],
         ' p[stack[-1]]:', p[stack[-1]])

        if p[i] < p[stack[-1]]:
            for j in stack[::-1]:
                print('i:', i, ' j:', j, ' p[i]:', p[i], ' p[j]:', p[j])
                if p[i] < p[j]:
                    ans[j] = i-j
                    stack.remove(j)
                else:
                    break
        stack.append(i)

        print('answer:', ans, '\n')

    for s in stack[:-1]:
        ans[s] = len(p) - s - 1
    return ans
