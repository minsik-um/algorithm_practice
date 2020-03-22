def my_solution(m, n, puddles):
    '''
    1. **
    문제에는 언급 없지만 조건이 필요함
    => 오른쪽/아래로만 이동해야 한다
    (질문하기에 이 조건이 없을 경우 위/왼쪽도 고려해야 한다는 반례가 있음)

    2.
    왼쪽/위 위치가 가진 최소 경로의 수를 더한 것이 이번 위치의 최소 경로의 수

    3.
    아래처럼 이중 리스트를 생성할 경우 리스트들이 다 공유되어
    하나만 바꿔도 같은 열의 수들이 전부 바뀐다.
    min_num = [[1] * (m+1)] * (n+1) 

    => 아래처럼 하자.
    min_num = [[1] * (m+1) for i in range(n+1)]
    '''
    min_num = [[0] * m for _ in range(n+1)]

    for x, y in puddles:
        min_num[y-1][x-1] = -1

    for j in range(n):
        for i in range(m):
            if min_num[j][i] == -1:
                min_num[j][i] = 0
            else:
                min_num[j][i] += min_num[j-1][i] if j-1 >= 0 else 0
                min_num[j][i] += min_num[j][i-1] if i-1 >= 0 else 0

    return min_num[-1][-1] % 1000000007


def other_solution(m, n, puddles):
    '''
    dictionary와 재귀함수를 이용한 풀이

    원래 재귀함수를 돌려 마지막-> 첫번째 위치로 가면
    각 위치들을 중복계산하게 되지만,
    이미 계산한 곳은 dictionary에 저장된 값을 불러와서 중복 계산을 막았음.

    참고: https://gomguard.tistory.com/126

    setdefault: Key(첫 번째 인자)의 value가 없으면 키값에 두 번째 인자를 넣고
                만약 있으면 원래 있는 값 유지 후 최종 value를 반환
    '''
    # 이미 확정된 부분은 미리 넣어줌
    info = {(2, 1): 1, (1, 2): 1}
    for puddle in puddles:
        info[tuple(puddle)] = 0

    def func(m, n):
        if m < 1 or n < 1:  # index를 벗어난 위치에 대응
            return 0
        if (m, n) in info:  # 이미 계산된 위치는 바로 적용
            return info[(m, n)]
        else:
            return info.setdefault((m, n), func(m - 1, n) + func(m, n - 1))

    return func(m, n) % 1000000007


import numpy as np

def np_solution(m, n, puddles):
    '''
    numpy를 이용한 풀이
    import numpy 하는 것만으로도 5ms 만큼 시간이 늘어났다.
    numpy는 대용량 처리하는데 사용하자
    '''
    min_num = np.zeros((n, m))
    min_num[0, 0] = 1

    for x, y in puddles:
        min_num[y-1, x-1] = -1
    
    for j in range(n):
        for i in range(m):
            if min_num[j, i] == -1:
                min_num[j, i] = 0
            else:
                min_num[j, i] += min_num[j-1, i] if j-1 >= 0 else 0
                min_num[j, i] += min_num[j, i-1] if i-1 >= 0 else 0

    return min_num[-1][-1] % 1000000007
