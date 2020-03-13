
def my_solution(brown, red):
    '''
    가능한 red width/height를 탐색하며
    brown 개수 조건을 만족하는지 확인
    i: width
    '''
    for i in range(1,red+1):
        red_w, red_h = i, red//i
        if red_w * red_h == red and red_w >= red_h:
            if red_w*2 + red_h*2 + 4 == brown:            
                return [red_w + 2, red_h + 2]
                    
    return None

def best_solution(brown, red):
    '''
    widht/height 탐색 기준점을 red**(1/2)로 지정하여
    탐색 범위 축소 및 조건문 1개 줄였음
    '''
    for i in range(1, int(red**(1/2))+1):
        if red % i == 0:
            if 2*(i + red//i) == brown-4:
                return [red//i+2, i+2]
