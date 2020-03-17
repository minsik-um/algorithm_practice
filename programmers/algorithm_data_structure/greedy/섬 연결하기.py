
def my_solution(n, costs):
    '''
    매번 연결된 섬과 연결되지 않은 섬을 연결하는 다리 목록을 계산하여
    그중 최소 비용의 다리를 선택해 건설하는 Greedy 알고리즘.

    -------------------------------
    [ Prim's Algorithm ] 에 해당한다.
    https://ko.wikipedia.org/wiki/프림_알고리즘
    => 아래 best_prim_solution() 함수 방법대로 구현하자
    -------------------------------
    다른 코드에 비해 시간 효율성이 안좋다.
    (매번 목록을 새로 만들기 때문)
    '''
    answer = 0

    remain_n = set(range(1, n))  
    while remain_n:
        avail_bridge = [cost for cost in costs
                        if (cost[0] in remain_n and cost[1] not in remain_n)
                        or (cost[0] not in remain_n and cost[1] in remain_n)]

        i1, i2, c = min(avail_bridge, key=lambda x:x[2])
        remain_n -= set([i1, i2])
        answer += c

    return answer


def best_prim_solution(n, costs):
    '''
    좀 더 실행 시간이 짧은 코드
    (타인이 작성한 코드를 정리함)
    `prim algorithm`도 정렬을 해두면 빠르게 처리한다.
    '''
    answer = 0
    sorted_costs = sorted(costs, key=lambda x: x[2])

    visited = set([0])
    while len(visited) < n:
        for i in range(len(sorted_costs)):
            v1, v2, cost = sorted_costs[i]
            if v1 in visited and v2 in visited:
                sorted_costs.pop(i)
                break
            elif v1 in visited or v2 in visited:
                visited |= set([v1, v2])
                answer += cost
                sorted_costs.pop(i)
                break

    return answer

# ---------------------------------------------
def find(parents, i):
    if parents[i] == i:
        return i
    else:
        return find(parents, parents[i])


def union(parents, node1, node2):
    parents[find(parents, node2)] = find(parents, node1)


def kruskal_solution(n, costs):
    '''
    https://gmlwjd9405.github.io/2018/08/29/algorithm-kruskal-mst.html
    https://yabmoons.tistory.com/186

    [ Kruskal Algorithm ]
    네트워크의 모든 정점을 최소 비용으로 연결하는 최적 해답 알고리즘
    MST(최소 비용 신장 트리)에 근거하여
    1. 최소 비용의 간선으로 구성
    2. 사이클을 포함하지 않음
    -------------------------------
    1. 그래프의 간선을 오름차순으로 정렬한다.
    2. 사이클을 만들지 않는 간선 중 최소 비용 간선을 선택한다.
       (union - find 방법 사용)

    * 주의 *
    parents 내 원소는 항상 tree의 root 원소를 의미하지 않지만
    find 함수를 거치면 root가 나온다.
    '''
    answer = 0
    parents = list(range(n))
    sorted_costs = sorted(costs, key=lambda x: x[2], reverse=True)

    # 모두 연결될 때까지(모두 뿌리가 같을 때) 실행
    while len(set(map(lambda x: find(parents, x), range(n)))) > 1:
        i1, i2, cost = sorted_costs.pop()
        if find(parents, i1) != find(parents, i2):
            union(parents, i1, i2)
            answer += cost

    return answer
