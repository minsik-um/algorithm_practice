from collections import defaultdict


def mySolution(arrows):
    '''
    오일러의 정리를 이용한 풀이
    점의 개수와 엣지 개수와 다면체 개수의 합은 일정 상수다.
    그래프가 아니라 닫힌 도형에서 말함

    v + e + f = 1 (2차원에서 오일러 지표는 1)
    '''
    curr_pos = (0, 0)
    dirs = [(0, 1), (1, 1), (1, 0), (1, -1),
            (0, -1), (-1, -1), (-1, 0), (-1, 1)]
    graph = defaultdict(set)

    # 그래프 생성
    for arrow in arrows:
        next_pos = (dirs[0] + curr_pos[0], dirs[1] + curr_pos[1])
        middle_pos = ((curr_pos[0] + next_pos[0]) / 2,
                      (curr_pos[1] + next_pos[1]) / 2)

        graph[middle_pos].add(curr_pos)
        graph[curr_pos].add(middle_pos)

        graph[middle_pos].add(next_pos)
        graph[next_pos].add(middle_pos)

        curr_pos = next_pos

    # 점의 갯수와 엣지의 갯수 계산
    vertex = len(graph)
    edge = sum(map(len, graph.values())) / 2

    return 1 - vertex + edge


def bestSolution1(arrows):
    '''
    (건너본적 없는 라인이고 -- 이부분을 내가 제대로 처리 못함)
    방문한 적 있는 점에 도달하는 순간 새로운 면이 생겨난다.
    '''
    answer = 0
    curr_pos = (0, 0)
    dirs = [(0, 1), (1, 1), (1, 0), (1, -1),
            (0, -1), (-1, -1), (-1, 0), (-1, 1)]
    visited_nodes = set()
    visited_edges = set()
    visited_nodes.add(curr_pos)

    for arrow in arrows:
        next_pos = (dirs[arrow][0] + curr_pos[0], dirs[arrow][1] + curr_pos[1])
        middle_pos = ((curr_pos[0] + next_pos[0]) / 2,
                      (curr_pos[1] + next_pos[1]) / 2)
        edge = tuple(sorted([curr_pos, next_pos]))

        if edge not in visited_edges:
            if next_pos in visited_nodes:
                answer += 1
            if middle_pos in visited_nodes:
                answer += 1

        visited_nodes.update([next_pos, middle_pos])
        visited_edges.add(edge)
        curr_pos = next_pos

    return answer


# --------------------------------------------------


def get_crossed(edge):
    '''
    항상 앞순서 vertex가 작은 순서로 정렬되어 있다는 점 활용
    '''
    x1, y1 = edge[0]
    x2, y2 = edge[1]
    return tuple(sorted([(x1+1, y1),  (x2-1, y2)]))


def bestSolution2(arrows):
    '''
    중간점을 저장하지 않고 도착점만 매번 계산하면서
    그 선이 대각선일 경우 다른 대각선이 기존에 있었는지 확인해 교차점도 고려
    '''
    answer = 0
    curr_pos = (0, 0)
    dirs = [(0, 1), (1, 1), (1, 0), (1, -1),
            (0, -1), (-1, -1), (-1, 0), (-1, 1)]
    visited_nodes = set()
    visited_edges = set()
    visited_nodes.add(curr_pos)

    for arrow in arrows:
        next_pos = (dirs[arrow][0] + curr_pos[0], dirs[arrow][1] + curr_pos[1])
        edge = tuple(sorted([curr_pos, next_pos]))

        if edge not in visited_edges and next_pos in visited_nodes:
            answer += 1

        if (edge[0][0] != edge[1][0] and edge[0][1] != edge[1][1]) \
                and (get_crossed(edge) in visited_edges):
            answer += 1

        visited_nodes.add(next_pos)
        visited_edges.add(edge)
        curr_pos = next_pos

    return answer
