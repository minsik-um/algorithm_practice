def my_solution(array, commands):
    '''
    필요 기술: list slicing & indexing, sort
    '''
    answer = []    

    for i, j, k in commands:
        answer.append(sorted(array[i-1:j])[k-1])
    return answer


def bset_solution(array, commands):
    '''
    map()과 lambda를 활용한 풀이
    '''
    return list(map(lambda x: sorted(array[x[0]-1:x[1]])[x[2]-1], commands))
