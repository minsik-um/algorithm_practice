from collections import defaultdict


def my_solution(n, edge):
    '''
    graph 구조는 dictionay(hash) 구조 사용
    - defaultdict로 코드 크기도 줄이고
    - (딕셔너리 컴프리헨션에 비해) 속도가 빠르다.

    매번 방문하지 않은 인접한 노드를 찾아 탐색해가면
    모든 노드를 최단 거리로 만난다.

    마지막 노드를 방문하기 전에 루프문을 나와서
    마지막으로 탐색할 노드 개수를 답으로 반환
    '''
    # graph 생성
    graph = defaultdict(set)
    for n1, n2 in edge:
        graph[n1].add(n2)
        graph[n2].add(n1)

    # 반복: 방문하지 않은 노드 탐색
    visited = set()
    next_visit = set([1])
    while True:
        # 방문한 노드 업데이트
        visited.update(next_visit)

        # 마지막 방문할 노드 차례인 경우 분기 종료
        if len(visited) == n:
            break

        # 다음 방문할 노드 업데이트
        temp = set()
        for node in next_visit:
            temp.update([n for n in graph[node] if n not in visited])
        next_visit = temp

    return len(next_visit)


def worse_solution(n, edge):
    # graph 생성
    graph = defaultdict(set)
    for n1, n2 in edge:
        graph[n1].add(n2)
        graph[n2].add(n1)

    # 반복: 방문하지 않은 노드 탐색
    visited = [0] * n
    next_visit = set([1])
    while True:
        # 방문한 노드 업데이트
        for node in next_visit:
            visited[node-1] = 1

        # 마지막 방문할 노드 차례인 경우 분기 종료
        if sum(visited) == n:
            break

        # 다음 방문할 노드 업데이트
        temp = set()
        for node in next_visit:
            temp.update([gn for gn in graph[node] if visited[gn-1] == 0])
        next_visit = temp

    return len(next_visit)
