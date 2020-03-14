def my_solution(n, lost, reserve):
    '''
    1. 여벌이 있지만 자기가 잃어버린 사람들을 제외한다.
    2. 시뮬레이션을 돌리며 앞사람에게 빌릴 수 있는지,
    안되면 뒷사람에게 빌릴 수 있는지 확인한다. (local optimal)

    아래 다른 방법들은 좀 더 간단하다.
    '''
    answer = n - len(lost)

    updated_lost = []
    for lo in lost:
        if lo in reserve:
            reserve.remove(lo)
            answer += 1
        else:
            updated_lost.append(lo)
            
    for lo in updated_lost:
        if lo-1 in reserve:
            reserve.remove(lo-1)
            answer += 1
        elif lo+1 in reserve:
            reserve.remove(lo+1)
            answer += 1
        
    return answer


def better_solution_1(n, lost, reserve):
    '''
    list comprehension 내에 if문을 추가하여 
    여벌이 있지만 자기가 잃어버린 사람을 제외한다.
    (굳이 이후 계산에 포함할 필요 없음)
    '''
    _reserve = [r for r in reserve if r not in lost]
    _lost = [l for l in lost if l not in reserve]
    for r in _reserve:
        f = r - 1
        b = r + 1
        if f in _lost:
            _lost.remove(f)
        elif b in _lost:
            _lost.remove(b)
    return n - len(_lost)


def better_solution_2(n, lost, reserve):
    '''
    set()을 활용한 풀이가 좋다.
    '''
    reserved = 0
    lostN = list(set(lost) - set(reserve))
    reserveN = list(set(reserve) - set(lost))
    lostN.sort()
    for l in lostN:
        for x in range(l-1, l+2):
            if x in reserveN:
                reserveN.remove(x)
                reserved += 1
                break
    return n - len(lostN) + reserved
