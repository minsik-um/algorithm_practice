def my_solution(K, travel):
    '''    
    전부 도보로 이동한다고 기본값을 설정하고
    각 경로를 자전거 이동으로 바꾸는 개념으로 생각

    dp => {자전거 이동으로 바꾼 만큼 감소할 시간 총량: 왼쪽 값 기준에서 감소하는 총 모금액의 최솟값}
    w_t, w_m: time on foot
    b_t, b_m: time, money by bicycle

    dictionay 자료형을 이용해 indexing 시간을 최소화
    매번 K 조건 검사하고 dp에서 제외하는 것보다
    나중에 한 번에 빼는 게 더 속도가 빠르다.
    (다른 문제에서도 똑같이 적용되는지는 봐야함)
    '''
    dp = {0: 0}

    for w_t, w_m, b_t, b_m in travel:
        items = list(dp.items())  # 에러 방지를 위해 list 처리
        for key, value in items:
            new_key, new_value = key + (w_t-b_t), value + (w_m-b_m)

            if new_key not in dp:
                dp[new_key] = new_value
            else:
                dp[new_key] = min(dp[new_key], new_value)

    MAX_MONEY = sum([tr[1] for tr in travel])
    MAX_TIME = sum([tr[0] for tr in travel])

    valid_list = [v for i, v in dp.items() if MAX_TIME-i <= K]
    return MAX_MONEY - min(valid_list)


def best_solution(K, travel):
    '''
    개념은 똑같다. 각각 k 값에서 가지는 최대값을 활용했다.
    이전 단계 k값에서 가지는 최대값을 활용하는 점화식이다.

    다만 실행 시간은 내 풀이가 5배 정도 빠르다.
    '''
    n = len(travel)

    memo = [[0 for j in range(K+1)] for x in range(n+1)]

    for i in range(1, n+1):
        t_walk, v_walk, t_bike, v_bike = travel[i-1]

        for j in range(K+1):
            walk = memo[i-1][j-t_walk]+v_walk if j>=t_walk and memo[i-1][j-t_walk]!=-1 else -1
            bike = memo[i-1][j-t_bike]+v_bike if j>=t_bike and memo[i-1][j-t_bike]!=-1 else -1

            memo[i][j] = max(walk, bike)

    return  memo[n][K]
