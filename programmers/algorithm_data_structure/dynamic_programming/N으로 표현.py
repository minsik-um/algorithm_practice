def operate(a, b):
    '''
    a, b를 사칙연산한 결과를 넣어 반환
    여기서 미리 set() 만드는 것보다 밖에서 한 번에 set 하는 게 빠르다
    중복된 연산을 줄이기 위해 set을 사용하여 중복된 수를 제거
    '''
    answer = []
    for i in a:
        for j in b:
            answer.append(i * j)
            answer.append(i + j)
            answer.append(i - j)
            if j != 0:
                answer.append(i // j)
    return answer


def my_solution(N, number):
    '''
    k개를 사용해 만드는 수를 계산해간다.
    k=1 부터 하며 상위 단계에선 하위 단계 결과를 그대로 활용하여 
    계산 시간을 줄인다.
    예) 4개 사용 ->
    (1개 사용 x 3개 사용), (2개 사용 x 2개 사용), (3개 사용 x 1개 사용)
    results: index 개수만큼 N을 써서 만들 수 있는 결과의 모음
    '''
    results = [{}, {N}]
    count = 1

    while number not in results[-1] and count <= 8:
        count += 1
        temp = set()
        for c in range(1, count):
            temp.update(operate(results[c], results[count - c]))
            temp.add(int(str(N)*count))
        results.append(set(temp))

    return count if count <= 8 else -1
