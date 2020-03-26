def best_solution(money):
    '''
    dp 리스트를 만들 필요 없이 이전 정보로 변수만 업데이트 하면 된다.
    * 여기서 dp는 각 집을 털었다/안털었다로 나뉨(다른 코드와 다름)
    x1, y1, z1: 첫 번째 집을 터는 경우의 수
        - x1: 2단계 이전, y1: 1단계 이전, z1: 최댓값
    x2, y2, z2: 첫 번째 집을 털지 않는 경우의 수
        - (개념 동일)   

    첫 번째 집을 털면 마지막 집을 못 털기에 뒤에서 2번째 집 기준으로 최대를 찾고
    (뒤에서 2번째 집이 앞의 정보를 모두 종합한 끝지점이며, 
    상황에 따라 털지 않고 이전 집을 털었을 때 값이 높은 경우가 있으므로 2개를 보는 거다)
    (x1: 뒤에서 2번째 집을 턴 경우 , y1: 뒤에서 2번째 집 털지 않은 경우 최대)
    첫 번째 집을 털지 않으면 마지막 집을 털 수 있어 y2, z2 중 1개을 고른다.
    (y2: 마지막 집을 털지 않았을 때 최대, z2: 마지막 집을 털었을 때 최대)
    '''
    x1, y1, z1 = money[0], money[1], money[0] + money[2]
    x2, y2, z2 = 0, money[1], money[2]
    for i in money[3:]:
        x1, y1, z1 = y1, z1, max(x1, y1)+i
        x2, y2, z2 = y2, z2, max(x2, y2)+i
    return max(x1, y1, y2, z2)


def best_solution_2(money):
    '''
    같은 원리로 푼 것이다. 각 단계에서 무조건 집을 털고
    집을 털기 위해 뒤에서 2번째, 뒤에서 3번째 집 중에서 어디를 털지 결정하는 것이다.
    '''
    if len(money) == 3:
        return max(money)
    else:

        with0 = [money[0], money[1], money[0] + money[2], max(money[0], money[1]) + money[3]]
        wout0 = [money[1], money[2], money[1] + money[3]]
        for m in money[4:]:
            with0.append(max(with0[-2], with0[-3]) + m)
            wout0.append(max(wout0[-2], wout0[-3]) + m)

    return max(with0[-3], with0[-2], wout0[-1])


def my_solution(money):
    '''
    질문하기에서 힌트를 얻어 겨우 했다. 
    스스로 생각하지 못한 케이스이므로 패턴을 잘 외우고
    다른 문제에서 비슷하게 변형해 적용하도록 연습하자.

    dp[i]: 위의 best 풀이와 달리 각 집까지 왔을 때 (그 집을 털 수도, 안털 수도 있음)
           최대값임
    max(dp_c1[hidx-2] + money[hidx-2], dp_c1[hidx-1]):
        dp_c1[hidx-2] + money[hidx-2]: 이번 차례에 이 집을 턴다.
                                       다른 풀이에 다뤄지는 dp_c1[hidx-3] + money[hidx-2]는 볼 필요가 없는데,
                                       dp_c1[hidx-3]은 dp_c1[hidx-2]에 포함되는 개념이다.                                       
        dp_c1[hidx-1]: 이번 차례에 집을 털지 않기에 바로 전 단계가 가진 최대값을 이어온다.
    hidx = house index

    (dynamic programming으로 풀기 좋도록)
    - idx 0 첫 번째 집부터 마지막집까지 순차적으로 간다고 생각하는 게 핵심
      (매번 가능한 집 중에서 하나 선택하는 게 아님)
    - 만약 이 집을 털 거야? -> 털고 다음 다음 집으로 이동
      만약 안 털거야? -> 안털고 다음 집으로 이동
    - 마지막집이 첫 번째 집과 연결되어 있어 두 가지 경우로 나눠야 함
        - 첫 번째 집을 털지 않았다. -> dp_c1
        - 마지막 집을 털지 않았다.  -> dp_c2
    '''
    dp_c1 = [0] * (len(money)+2)
    dp_c2 = [0] * (len(money)+2)
    
    for hidx in range(2, len(money)+1):
        dp_c1[hidx] = max(dp_c1[hidx-2] + money[hidx-2], dp_c1[hidx-1])

    for hidx in range(3, len(money)+2):
        dp_c2[hidx] = max(dp_c2[hidx-2] + money[hidx-2], dp_c2[hidx-1]) 

    return max(dp_c1[-2], dp_c2[-1])


if __name__ == "__main__":
    #import random
    #money = [random.randint(0, 1000) for _ in range(random.randint(3, 10))]

    money = [1, 2, 3, 1]
    print(solution(money))
