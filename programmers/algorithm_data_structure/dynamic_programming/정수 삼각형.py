def my_solution(triangle):
    '''
    제일 위에서부터 차례로 내려오며
    각 자리에서 가지는 최대값을 저장해가는 방식

    효율성 1번: 50.52ms
    if문과 enumerate() 사용 등이 차이가 있기에 이부분이 시간 증가에 영향
    '''
    answer = 0
    max_list = [triangle[0]]
    for idx in range(1, len(triangle)):
        temp = []      
        for i, v in enumerate(triangle[idx]):
            left = max_list[idx-1][(i-1 if i > 0 else 0)]
            right = max_list[idx-1][i if i < len(max_list[idx-1]) else -1]
            temp.append(v + max(left, right))
        max_list.append(temp)
    return max(max_list[-1])


# 함수 줄 포함 말도 안되는 1줄 풀이...
# lambda의 앞부분은 함수 매개변수 선언처럼 써주면 된다.
best_solution = lambda t, l = []: max(l) if not t else solution(t[1:], [max(x, y)+z for x,y,z in zip([0]+l, l+[0], t[0])])


def recur_solution(tri, max_l = []):
    '''
    효율성 1번: 27.28ms
    => if문 개수를 줄이고 enumerate 미사용: [0] 더하는 테크닉으로
    
    테스트 결과 max_i를 쌓든 여기처럼 업데이트하든 시간 차이는 별로 없다.
    (아래 비교 코드 넣었음)
    '''

    if tri:
        new_max_l = [t + max(a, b) for a, b, t in zip([0]+max_l, max_l+[0], tri[0])]
        return recur_solution(tri[1:], new_max_l)
    else:
        return max(max_l)



def test_recur_solution(tri, max_l = [[]]):
    '''
    효율성 1번: 25.82ms
    '''
    if tri:
        max_l.append([t + max(a, b) for a, b, t in zip([0]+max_l[-1], max_l[-1]+[0], tri[0])])
        return test_recur_solution(tri[1:], max_l)
    else:
        return max(max_l[-1])