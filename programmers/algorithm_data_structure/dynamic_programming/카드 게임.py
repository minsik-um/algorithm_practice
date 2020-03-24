def best_solution(left, right):
    '''
    길찾기 문제로 생각하면 된다.

    dp의 각 지점은 카드 상황이며
    dp[i][j]의 값은 i번째 left 카드(1번부터 시작)와
    j번째 right 카드 상황에서(아직 비교 안함) 앞으로 끝까지 했을 때
    얻을 수 있는 최대값.

    이전 최대 상황에서 가능한 수를 더하는 방향으로 갔을 때
    최대값이 유지되어 갈 것이다. 이 개념 하에 길 찾기를 한다.

    Q. 반대로 정방향에서 가면 도달할 수 없는 비교 지점 값을 계산하고,
    상황에 따라 그 가짜 값이 최종 값이 된다. 역으로 하면 그 영향은 왜 없을까?

    예시: left=[3, 1], right=[7, 1]
    dp[1][1]에서 오른쪽 카드를 버리지 못했기에 dp[1][2]도 오른쪽 카드를 버리지 못하며 
    dp[1][1] = dp[1][2] = 0 이어야 한다.
    
    그러나 코드에선 dp[1][2] 에서 left/right를 3/1 로 간주흐오
    오른쪽 카드를 버려 dp[1][2] = 1이 된다.

    A. 비교할 수 없는 지점 값이 영향을 미칠려면 2가지 경우다.
    - 왼쪽으로 이동: 이 경우 왼쪽 지점 기준에서 비교 후 오른쪽 값을 뺄 수 있다고 확인할 때만 가져온다.
                    도달할 수 없는 비교 지점이 될려면 그 전지점에서 오른쪽으로 이동할 수 없는 경우이므로
                    값 영향이 소멸된다. (0,0 은 항상 도달할 수 있기에)
    - 대각선으로 이동: i > j 인 경우에만 비교할 수 없는 지점이 존재할 수 있다.
                     아무리 대각선 타고 내려와도 (0, 0)에는 영향을 주지 않음
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
    위에서 설명한 정방향의 잘못된 풀이

    dp의 각 지점은 카드 상황이며 dp[i][j]의 값은 i번째 left 카드(1번부터 시작)와
    j번째 right 카드까지 왔고 거기서 비교 후 연산 1회 수행한 결과의 최대값.
    '''
    dp = [[0 for x in range(len(left)+1)] for y in range(len(right)+1)]
    for i in range(1, len(left)+1):
        for j in range(1, len(right)+1):

            if right[j-1] < left[i-1]:
                dp[i][j] = dp[i][j-1] + right[j-1]
            else:
                dp[i][j] = max(dp[i-1][j-1], dp[i-1][j])

    return dp[len(left)][len(right)]


def my_solution(left, right):
    '''
    쭉 전개한 다음 아래서부터 위로 올라가며
    (특정 상황에서 앞으로 더 얻을 최댓값)을 쌓아 결과를 낸다.
    결국 정답은 시작지점에서 앞으로 더 얻을 최댓값이다.

    재귀함수로 풀었으나 depth 제한에 걸려
    재귀함수를 쓰지 않지만 풀이 흐름은 같도록 했다.

    stack = [ [(현재 left index, right index), (부모의 index), score], ... ]
    '''
    stack = [[(0, 0), None]]
    max_list = {}
    temp = []
    
    while stack:
        current_idxs, parent_idxs = stack[-1]
        l_idx, r_idx = current_idxs
        
        # 노드의 자식을 볼 필요 없는지 3가지 조건 검사
        
        # 상위 노드로 올라왔을 때: 아래 탐색이 끝남
        if temp and temp[-1][1] == current_idxs:
            
            # 다른 계층 것이 앞에 있으므로 분리
            target = []
            while temp and temp[-1][1] == current_idxs:
                target.append(temp.pop())
            
            if len(target) == 1:
                max_list[current_idxs] = max_list[target[0][0]] + right[r_idx]
            else:
                max_list[current_idxs] = max([max_list[t[0]] for t in target])

            temp.append(stack.pop())
        # 완전 끝 노드에 도달
        elif l_idx >= len(left) or r_idx >= len(right):
            max_list[current_idxs] = 0
            temp.append(stack.pop())
        # 끝은 아니지만 굳이 탐색할 필요 없는 노드
        elif current_idxs in max_list:
            node = stack.pop()
            temp.append(node)
        else:
            # 노드를 전개
            if left[l_idx] > right[r_idx]:
                stack.append([(l_idx, r_idx+1), current_idxs])
            else:
                stack.append([(l_idx+1, r_idx+1), current_idxs])
                stack.append([(l_idx+1, r_idx), current_idxs])

    return max_list[(0, 0)]


def choose(scores, left, right):
    '''
    아래 풀이는 재귀함수를 활용했다.
    효율성 테스트에서 max_depth에 도달하여 런타임에러 발생

    재귀함수는 데이터 크기가 충분히 크면 실행이 안될 수 있으므로
    데이터 최대 가능 크기를 보고 판단하자
    '''
    if not (left and right):
        return 0
    elif (len(left), len(right)) in scores:
        return scores[(len(left), len(right))]
    else:   
        choices = []
        choices.append(choose(scores, left[1:], right[1:]))
        choices.append(choose(scores, left[1:], right))
        if right[0] < left[0]:
            choices.append(choose(scores, left, right[1:]) + right[0])
        scores[(len(left), len(right))] = max(choices)
        return scores[(len(left), len(right))]


def recur_solution(left, right):
    scores = {}

    return choose(scores, left, right)
