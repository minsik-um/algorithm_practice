from itertools import permutations

def check(num: int, answer: list):
    '''
    부른 숫자와 정답을 받아와
    스트라이크/볼을 계산하는 함수
    '''
    strike = 0
    ball = 0
    
    for n, a in zip(map(int, str(num)), answer):
        if n == a:
            strike += 1
        elif n in answer:
            ball += 1

    return strike, ball

def my_solution(baseball):
    '''
    permutation으로 전체 순열을 만들고
    각 경기결과를 만족하지 않는 애들을 빼고
    남은 목록의 개수를 반환
    => set (bitwise 연산자: |, &, ... 사용) 활용
    '''
    candidates = set(permutations(range(1,9+1), 3))
    for num, strike, ball in baseball:
        remove_list = []
        for can in candidates:
            check_strike, check_ball = check(num, can)
            if not (strike == check_strike and ball == check_ball):
                remove_list.append(can)

        candidates -= set(remove_list)
            
    return len(candidates)

'''
another solution?
다른 사람 풀이 중에 각 경기결과를 바탕으로
(엄청나게 많은 and와 in , == 연산자 활용하여)
check 함수 사용 없이 리스트 컴프리 헨션으로
전체 가능 목록을 뽑아서 남은 목록과 & 연산으로 처리
'''