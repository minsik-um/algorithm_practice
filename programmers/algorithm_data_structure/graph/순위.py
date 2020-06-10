from collections import defaultdict


def recur_search(player, graph, direction, result):
    '''
    player: 기준점이 될 플레이어 번호
    graph: 승패를 기록한 그래프
    direction: 'w', 'l' 중 1개 선택
    result: 이미 탐색한 결과는 바로 사용 (memoization)

    중복된 계산을 막기 위해 set() 형태로 위쪽 선수 번호들을 모음
    '''
    if result[(player-1)][direction] == -1:
        others = [other[1] for other in graph[player] if other[0] == direction]
        answer = set(others)
        for other in others:
            answer |= recur_search(other, graph, direction, result)
        result[(player-1)][direction] = answer

    return result[(player-1)][direction]


def my_solution(n, results):
    '''
    그래프를 만들었을 때
    자신과 이김 관계로 직-간접적으로 연결된 플레이어 수를 세고
    자신과 짐 관계로 직-간접적으로 연결된 플레이어 수를 세서
    둘의 합이 (총 플레이어 수 - 1) 이라면 등수가 확정된다.
    -1을 하는 이유는 나 자신을 제외해서.

    recursive solution + memoization을 사용했다.
    '''
    graph = defaultdict(set)
    for winner, loser in results:
        graph[winner].add(('w', loser))
        graph[loser].add(('l', winner))

    answer = 0
    result = [{'w': -1, 'l': -1} for _ in range(n)]  # (idx+1)player의 w, l 방향 함수 실행 결과
    for player in range(1, n+1):
        above_count = len(recur_search(player, graph, 'l', result))
        below_count = len(recur_search(player, graph, 'w', result))
        if above_count + below_count == (n-1):
            answer += 1

    return answer

# ----------

def best_solution(n, results):
    '''
    이긴 사람, 진 사람을 set() 함수 범위 안에 채워나가며
    동시에 총 인원 수도 더해간다.

    이후 아래 반복 과정에서 간접 결과가 중첩되며 각 위치에 쌓여간다.

    filter(lambda, ...)은 함수형 프로그래밍에 좋은 함수다.
    '''
    fight = [[set(), set(), 0] for _ in range(n)]
    for win, lose in results:
        fight[win - 1][1].add(lose - 1)
        fight[win - 1][2] += 1
        fight[lose - 1][0].add(win - 1)
        fight[lose - 1][2] += 1

    for i, (win, lose, cnt) in enumerate(fight):
        # (i+1) 플레이어를 이긴 선수들
        for w in win:
            fight[w][1].update(lose)  # 간접적으로 이긴 선수들을 추가
            fight[w][2] = len(fight[w][1]) + len(fight[w][0])

        # (i+1) 플레이어에게 진 선수들
        for l in lose:
            fight[l][0].update(win)   # 간접적으로 진 선수들을 추가
            fight[l][2] = len(fight[l][1]) + len(fight[l][0])

    return len(list(filter(lambda x: x[2] == n-1, fight)))
