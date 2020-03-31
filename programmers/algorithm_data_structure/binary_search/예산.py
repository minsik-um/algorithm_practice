def my_solution(budgets, M):   
    '''
    이분 탐색을 이용해 시간을 단축했다.
    그러나 매번 sum으로 계산을 하므로 시간 효율성이 좋지 않음
    => best solution처럼 이전 결과를 저장하는 구조를 생각하자.
    '''
    start = 1
    end = max(budgets)
    answer = 0

    while start <= end:
        middle = (start + end) // 2
        result =  sum([(middle if b > middle else b) for b in budgets])        
        if result > M:
            end = middle-1
        else:
            start = middle+1
            # 조건을 만족하는 상한선을 찾는다
            if answer < middle:
                answer = middle

    return answer


def best_solution(budgets, M):
    '''
    최소 예산부터 차례대로 보면서
    각 예산을 상한선으로 했을 때 값을 비교하고
    만약 최대 예산을 넘어섰다면 (남은 예산 // 남은 요청들) 해서
    상한선을 계산한다.

    매번 다 계산하지 않고 cap에 이전 결과를 저장하여
    상한선이 늘어난 만큼만 계산하도록 하여 시간 단축

    압도적으로 빠르다.
    '''
    budgets.sort()
    l = len(budgets)
    cap = 0
    for i, budget in enumerate(budgets):
        level = (budget - cap) * (l - i)
        if level <= M:
            cap = budget
            M -= level
        else:
            cap += M // (l - i)
            break
    return cap
