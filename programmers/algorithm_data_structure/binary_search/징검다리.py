def get_intervals(distance, rocks):
    sorted_rocks = sorted(rocks)
    intervals = [sorted_rocks[0]]
    for i in range(1, len(sorted_rocks)):
        intervals.append(sorted_rocks[i] - sorted_rocks[i-1])
    intervals.append(distance - sorted_rocks[-1])

    return intervals


def my_solution(distance, rocks, n):
    '''
    JAVA 풀이 참고
    '''
    start = 0
    end = distance
    target_interval_count = len(rocks) - n + 1    
    intervals = get_intervals(distance, rocks)

    while start <= end:
        middle = (start + end) // 2
        count = 0
        temp = 0

        for interval in intervals:
            temp += interval

            if temp >= middle:
                temp = 0
                count += 1

        if count >= target_interval_count:
            start = middle + 1
        else:
            end = middle - 1

    return end