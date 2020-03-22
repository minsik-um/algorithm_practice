def my_solution(numbers, target):
    tree = [[0]]
    for num in numbers:
        temp = []
        for t in tree[-1]:
            temp.append(t + num)
            temp.append(t - num)
        tree.append(temp)
    return tree[-1].count(target)


def best_sol_1(numbers, target):
    '''
    DFS는 재귀호출로 쉽게 구현 가능
    target에 수를 계속 더하고 빼어
    재귀 끝에 갔을 때 target이 0이 되면
    경우의 수를 1개 더하는 방법
    '''
    if not numbers and target == 0:
        return 1
    elif not numbers:
        return 0
    else:
        return best_sol_1(numbers[1:], target - numbers[0]) \
            + best_sol_1(numbers[1:], target + numbers[0])


from itertools import product


def best_sol_2(numbers, target):
    '''
    product 연산의 의미: https://programmers.co.kr/learn/courses/4008/lessons/12835
    애스터리스크(*)의 의미: https://mingrammer.com/understanding-the-asterisk-of-python/

    itertools의 product(곱집합)를 이용하여
    한 번에 가능한 조합을 계산

    *l을 이용하여 리스트 내 원소를 언패킹하여 전달
    (따라서 1개의 리스트가 인자로 들어가지 않고 여러개의 인자로 들어간다)
    '''
    l = [(x, -x) for x in numbers]
    s = list(map(sum, product(*l)))
    return s.count(target)


from itertools import combinations


def best_sol_3(numbers, target):
    '''
    mypermutation: - 연산을 적용할 number의 조합
    각 조합이 target을 만드는지 알기 위해
    [ 조합 내 원소들이 + 에서 - 연산으로 바뀌었을 때 결과값의 변화 ]를
    계산해 goal과 비교!

    numbers[k]*2 설명
    [ numbers[k]를 1번 포함해 + 연산을 취소하고,
    1번 더 포함해 - 연산으로 바꿔 ] 변화량을 구함.
    '''
    answer = 0
    alist = list(range(len(numbers)))

    goal = sum(numbers) - target

    for j in range(len(numbers)):
        combi = combinations(alist, j+1)
        for s in combi:
            asum = sum([numbers[k]*2 for k in s])

            if asum == goal:
                answer += 1

    return answer
