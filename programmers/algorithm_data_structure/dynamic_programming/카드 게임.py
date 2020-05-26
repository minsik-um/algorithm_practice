def best_solution(left, right):
    '''
    (java 풀이 참고)
    +
    여기서는 역방향으로 이동하여 문제를 해결한다.
    도달할 수 없는 지점은 0이므로 최댓값에서 밀려 영향이 사라진다.
    '''
    dp = [[0] * (len(left)+1) for _ in range(len(right)+1)]
    
    for i in reversed(range(len(left))):
        for j in reversed(range(len(right))):
            if right[j] < left[i]:
                dp[i][j] = dp[i][j+1] + right[j]
            else:
                dp[i][j] = max(dp[i+1][j], dp[i+1][j+1])

    return dp[0][0]


def wrong_solution(left, right):
    '''
    정방향의 잘못된 풀이
    도달할 수 없는 지점이 영향을 끼친다.
    '''
    dp = [[0 for x in range(len(left)+1)] for y in range(len(right)+1)]
    for i in range(1, len(left)+1):
        for j in range(1, len(right)+1):

            if right[j-1] < left[i-1]:
                dp[i][j] = dp[i][j-1] + right[j-1]
            else:
                dp[i][j] = max(dp[i-1][j-1], dp[i-1][j])

    return dp[len(left)][len(right)]
