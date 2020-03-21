def recur_sol(N, current_len = 1, last_len = 0):
    '''
    재귀호출을 이용한 풀이
    패턴 다음에도 활용하자
    => 매개변수=초기값, 재귀호출하는 부분에 반복문 내용, else분기에 최종 반환값
    '''
    if N > 1:
        return recur_sol(N-1, current_len+last_len, current_len)
    else:
        return (current_len * 2 + last_len) * 2


def my_solution(N):
    current_len, last_len = 1, 0
    
    for i in range(1, N):
        current_len, last_len = current_len + last_len, current_len
        
    return (current_len * 2 + last_len) * 2


def other_solution(N):
    '''
    리스트에 저장해서 이어가는 방식
    '''
    l=[1,1]
    for i in range(2,N):
        l.append(l[-1]+l[-2])
    return (l[-1]*2+l[-2])*2