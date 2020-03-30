from collections import defaultdict 
'''
출처: https://dongdongfather.tistory.com/69
defaultdict는 인자로 넣어준 타입을 기본 value로 초기화해준다.
존재하지 않는 key로 호출해도 기본 value가 반환된다.

dictionay로 graph 개념을 아래와 같이 만들 수 있다.

graph를 bfs에서 사용한다면?
(일단 재귀함수는 안되고 Queue 구조에서)
각 단계에서 footprint의 모든 원소를 제거 후 
검사해서 추가해 넣고
다시 복구하는 방식
'''


def dfs(graph, N, key, footprint):
    if len(footprint) == N + 1:
        return footprint

    for idx, country in enumerate(graph[key]):
        graph[key].pop(idx)

        # 아래처럼 안하고 footprint + [country] 같이 바로 넣으면
        # 아주 미세하게 느린 걸 확인했다.
        tmp = footprint[:]
        tmp.append(country)

        # dfs로 파고 든다.
        ret = dfs(graph, N, country, tmp)

        # 다음 단계에서 잘 쓰도록 다시 복원
        graph[key].insert(idx, country)

        if ret:
            return ret


def best_solution(tickets):
    answer = []

    graph = defaultdict(list)

    # graph 생성
    for ticket in tickets:
        graph[ticket[0]].append(ticket[1])
        graph[ticket[0]].sort()

    return dfs(graph, len(tickets), "ICN", ["ICN"])

# ----------------------------

def get_next(tickets, curr_idxs=None):    
    if curr_idxs is None:
        return [idx for idx in range(len(tickets)) if tickets[idx][0] == 'ICN']
    else:
        return [idx for idx in range(len(tickets))
                if tickets[idx][0] == tickets[curr_idxs[-1]][1]
                and idx not in curr_idxs]


def my_solution(tickets):
    '''
    끝까지 탐색이 가능한 첫 경우를 빠르게 찾아야 하므로
    DFS를 사용한다. 

    'ICN', 'ATL' 같이 매번 상태를 경로로 이어가니 코드가 복잡했다.
    그래서 tickets의 index로 상태를 저장하니 코드가 간단해졌다.
    (이미 방문한 곳은 찾지 않는 걸 구현할 때 따로 리스트를 만들지 않아도 된다.)

    미리 정렬을 해두어 알파벳 순으로 선택하도록 한다.
    스택 구조이므로 내림차순으로 정렬해야 A에 가까울수록 먼저 검사한다.
    '''
    sorted_tickets = sorted(tickets, key=lambda x: x[1], reverse=True)
    stack = [[idx] for idx in get_next(sorted_tickets)]

    while stack:
        curr_idxs = stack.pop()
        if len(curr_idxs) == len(sorted_tickets):
            return ['ICN'] + [sorted_tickets[idx][1] for idx in curr_idxs]
        else:
            next_idxs = get_next(sorted_tickets, curr_idxs)
            stack.extend([curr_idxs+[idx] for idx in next_idxs])
