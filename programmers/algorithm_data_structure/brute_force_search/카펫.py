
def my_solution(brown, yellow):    
    '''
    (자바 버전 풀이 참고)
    아래 코드는 자바 버전 풀이와 달리
    yellow 를 기준으로 탐색해간다.
    '''
    for yellow_w in reversed(range(int(yellow**0.5), yellow+1)):
        yellow_h = yellow / yellow_w
        width, height = yellow_w + 2, yellow_h + 2

        if width * height == brown + yellow: 
            return [width, height]

    return None
