from collections import Counter

def solution(participant, completion):    
    '''
    내가 작성한 답
    '''
    new_pa = sorted(participant)
    new_co = sorted(completion) + [""]
    
    for pa, co in zip(new_pa, new_co):
        if pa != co:
            return pa


def solution2(participant, completion):    
    '''
    1위 답변
    Counter는 해시 구조로 되어 있으며
    연산하여 Value가 0 이하(-포함)가 되면 Key가 소멸한다.
    '''
    answer = Counter(participant) - Counter(completion)
    return list(answer)[0]