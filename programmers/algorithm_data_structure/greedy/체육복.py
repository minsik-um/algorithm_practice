def my_solution(n, lost, reserve):
    '''
    (해설은 자바 풀이 참고)
    '''
    answer = n
    status = [0] * (n+2)

    for lo in lost:
        status[lo] = -1

    for re in reserve:
        status[re] += 1

    for i in range(1, n+1):
        if status[i] == -1:
            if status[i-1] == 1:
                status[i-1] = 0
            elif status[i+1] == 1:
                status[i+1] = 0
            else:
                answer -= 1
        
    return answer
