def my_solution(answers):
    '''
    best solutuion 과 해결책은 같지만
    input type인 int와 달리 pattern을 str로 쓰면서
    추가로 type 변환을 해야 한다.
    '''
    patterns = ['12345', '21232425', '3311224455']
    scores = [0] * len(patterns)

    for i, pattern in enumerate(patterns):
        for j, an in enumerate(answers):
            scores[i] += int(pattern[j%len(pattern)] == str(an))
        
    return sorted([i+1 for i, score in enumerate(scores) 
                   if score == max(scores)])


from itertools import cycle

def best_solution(answers):
    '''
    다른 분의 댓글에 의하면 
    cycle을 써서 공간복잡도까지 고려한 코드라고 한다.

    itertools는 pythonic한 데이터 조작 기능이 굉장히 많다.
    https://hamait.tistory.com/803

    operator도 활용하자 (예. itemgetter(a))
    https://fenderist.tistory.com/158
    '''
    giveups = [
        cycle([1,2,3,4,5]),
        cycle([2,1,2,3,2,4,2,5]),
        cycle([3,3,1,1,2,2,4,4,5,5]),
    ]
    scores = [0, 0, 0]
    for num in answers:
        for i in range(3):
            if next(giveups[i]) == num:
                scores[i] += 1
    highest = max(scores)

    return [i + 1 for i, v in enumerate(scores) if v == highest]