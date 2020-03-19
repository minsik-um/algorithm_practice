def my_solution(weight):
    '''
    [생각의 흐름]
    완전탐색에서 greedy로 바꾸기
    Q. 연속되었음을 optimal로 보고
    local optimal을 생각해보자.
    처음으로 연속성이 깨지는 부분은?
    
    1 1 2 3 6 7 '30' 에서 처음으로 발생했다.
    7까지 했을 때 최대값(20) 보다 큰 수가 등장했다는 조건
    => 20, 21까진 이어갈 수 있으나, 22가 등장하는 순간 21을 만들 방법이 없다!
    '''
    sorted_weight = sorted(weight)
    
    max_num = 0
    for w in sorted_weight:
        if w > max_num + 1:
            break
        max_num += w
            
    return max_num+1


def bad_solution(weight):
    '''
    완전 탐색에 해당하는 풀이
    효율성 테스트 0점
    '''
    sorted_weight = sorted(weight)
    
    max_num = 0
    avail_nums = set([0])
    for w in sorted_weight:
        avail_nums.update([num+w for num in avail_nums])
        max_num += w
        
        if len(avail_nums) < max_num+1:
            break

    return min(set(range(max_num+2)) - avail_nums)