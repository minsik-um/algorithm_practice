
# 매번
def solution1(distance, rocks, n):
    rocks = sorted(rocks)
    intervals = [n1-n2 for n1, n2 in zip(rocks+[distance], [0] + rocks)]
    print(intervals)
    queue = [intervals]
    for _ in range(n):
        new_queue = []
        for q in queue:  
            # 매번 최솟값을 제거해나감
            min_interval = min(q)
            for idx in range(len(q)-1):
                if q[idx] == min_interval or q[idx+1] == min_interval:
                    new_queue.append(q[:idx] + [q[idx]+q[idx+1]] + q[idx+2:])
        queue = new_queue
        print(queue)
        
    return max(map(min, queue))


def solution2(distance, rocks, n):
    '''
    '''
    print()
    rocks = sorted(rocks)
    intervals = [n1-n2 for n1, n2 in zip(rocks+[distance], [0] + rocks)]
    # print(intervals)
    
    for _ in range(n):
        # 매번 최솟값을 제거해나감
        min_interval = min(intervals)
        remove_idx = 0
        
        for idx in range(len(intervals)-1):
            if intervals[idx] == min_interval or intervals[idx+1] == min_interval:
                if (intervals[idx] + intervals[idx+1]) < (intervals[remove_idx] + intervals[remove_idx+1]):
                    remove_idx = idx

        intervals = intervals[:remove_idx] \
                    + [intervals[remove_idx]+intervals[remove_idx+1]] \
                    + intervals[remove_idx+2:]
        print(intervals)
    return min(intervals)



def solution(distance, rocks, n):
    '''
    '''
    rocks = sorted(rocks)
    intervals = [n1-n2 for n1, n2 in zip(rocks+[distance], [0] + rocks)]
    # print(intervals)
    
    start = 0
    end = distance
    middle = (start + end) // 2
    answer = 0
    
    while start <= end:        
        middle = (start + end) // 2
        # middle은 예상하는 (거리의 최솟값 중 가장 큰 값)
        # middle이 있을 때 대응되는 n을 계산해야한다...
        
        min_delete_num = len([i for i in intervals if middle > i])
        # print('middle', middle, 'min_delete_num', min_delete_num)        
        
        if min_delete_num > n:
            answer = middle
            end = middle - 1
        elif min_delete_num <= n:
            start = middle + 1
        

    return answer


distance = 50
rocks = [40, 14, 11, 1, 4, 9, 21, 17]
n = 5

print(solution1(distance, rocks, n))
print(solution2(distance, rocks, n))
