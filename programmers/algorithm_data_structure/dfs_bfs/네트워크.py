def solution(n, computers):
    '''
    visited를 1차원 리스트로 해두고
    dfs를 돌면서 방문한 곳을 재귀 함수 안에서 바로 1로 채운다.

    DFS/BFS는 stact/queue 에서 pop <-> append 구조로 하면 된다.
    굳이 어렵게 재귀함수를 쓰지 않아도 된다. 
    
    아직 방문하지 않은 index를 찾을 때
    효율성보다 가독성이 중요하다면
    아래와 같이 해도 된다.

    while 0 in visited:
        dfs(computers, visited, visited.index(0))
        answer += 1
    '''
    def dfs(computers, visited, start):
        stack = [start]
        while stack:
            j = stack.pop()
            visited[j] = 1
        
            for i in range(len(computers)):
                if computers[j][i] and not visited[i]:
                    stack.append(i)

    answer = 0
    visited = [0] * n

    i = 0
    while 0 in visited:
        if visited[i] == 0:
            dfs(computers, visited, i)
            answer += 1
        i += 1

    return answer


def best_solution_2(n, computers):
    '''
    BFS를 사용한 풀이.
    어자피 전부 방문해야 하므로 dfs/bfs 차이는 별로 없다.
    index(0)과 try-except로 0이 아닌 조건을 풀어낸 것이 특징.
    '''
    def BFS(node, visit):
        queue = [node]
        visit[node] = 1
        while queue:
            v = queue.pop(0)
            for i in range(n):
                if computers[v][i] == 1 and visit[i] == 0:
                    visit[i] = 1
                    queue.append(i)
        return visit

    visit = [0] * n
    answer = 0

    # for문을 돌며 index(0)이 오류를 낼 경우 바로 끝낸다.
    for i in range(n):
        try:
            visit = BFS(visit.index(0), visit)
            answer += 1
        except:
            break
    return answer

# -------------------

def dfs(idx, computers, network=None):
    if network is None:
        network = {idx}

    direct = [i for i in range(len(computers[idx])) 
              if computers[idx][i] == 1 and i not in network]
    
    for d in direct:
        network |= dfs(d, computers, network|{d})
    
    return network

    
def my_solution(n, computers):    
    answer = 0
    remain = set(range(n))
    while remain:
        start_idx = list(remain)[0]
        remain -= dfs(start_idx, computers)
        answer += 1

    return answer