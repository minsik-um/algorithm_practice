from itertools import permutations


def best_solution(n):
    '''
    1.
    set 끼리 + 연산은 되지 않는다.
    대신 | (bitwise OR) 을 사용하면 된다.
    https://stackoverflow.com/questions/417396/what-does-the-sign-mean-in-python

    2.
    itertools에 permutations 함수를 이용하면
    원하는 길이의 순열을 만들 수 있다.

    3.
    에라토스테네스의 체를 활용해 소수가 아닌 수 제거
    -> set, range 함수를 활용하여 아주 깔끔하게 해결
    -> 제곱근까지만 검사해도 되는 점을 활용
       (그 곱하기 조합의 중간지점)
    '''
    a = set()
    for i in range(len(n)):
        a |= set(map(int, map("".join, permutations(list(n), i + 1))))

    print(a)
    # 소수만 남기는 단계 - 에라토스테네스 체 활용
    a -= set(range(0, 2))
    for i in range(2, int(max(a) ** 0.5) + 1):
        a -= set(range(i * 2, max(a) + 1, i))
    return len(a)



data = '7843'
best_solution(data)

# -----------------------------------
# 아래는 내 풀이


def check_prime(n):
    if n == 0 or n == 1:
        return False

    if n == 2:
        return True
    
    for i in range(2, n):
        if n%i == 0:
            return False

    return True
   

def recur_sol(answer, numbers, num=""):
    '''
    재귀함수로 쭉 가능한 상황을 전개하고
    각 상황 기준에서 이어진 수를 answer에 넣는다.
    '''
    for i in range(len(numbers)):
        # 아래처럼 call by value로 num을 update 해줘야 함
        num_new = num+numbers[i]
        answer.append(num_new)
        recur_sol(answer, numbers[:i]+numbers[i+1:], num_new)


def my_solution(numbers):
    answer = []
    recur_sol(answer, numbers)
    print(sorted(set(map(int, answer))))


def my_solution_2(numbers):
    '''
    재귀함수를 쓰지 않고 numbers의 최대 범위에 맞춰
    for문을 7개나 합친 코드

    실행 속도는 비슷할 수 있으나
    코드 관리 및 수정, 오타 등에 대처하기가 너무 어렵다.
    게다가 numbers 범위가 달라지면 코드 다시 짜야 함    
    '''
    answer = []
    for i1, n1 in enumerate(numbers):
        numbers_2 = numbers[:i1] + numbers[i1+1:]
        answer.append(n1)
        if not numbers_2:                
                continue
        for i2, n2 in enumerate(numbers_2):       
            answer.append(n1+n2)
            numbers_3 = numbers_2[:i2] + numbers_2[i2+1:]
            if not numbers_3:
                continue
            for i3, n3 in enumerate(numbers_3):
                answer.append(n1+n2+n3)
                numbers_4 = numbers_3[:i3] + numbers_3[i3+1:]
                if not numbers_4:                    
                    continue
                for i4, n4 in enumerate(numbers_4):   
                    answer.append(n1+n2+n3+n4)
                    numbers_5 = numbers_4[:i4] + numbers_4[i4+1:]
                    if not numbers_5:                        
                        continue
                    for i5, n5 in enumerate(numbers_5):
                        answer.append(n1+n2+n3+n4+n5)
                        numbers_6 = numbers_5[:i5] + numbers_5[i5+1:]
                        if not numbers_6:                            
                            continue
                        for i6, n6 in enumerate(numbers_6):
                            answer.append(n1+n2+n3+n4+n5+n6)
                            numbers_7 = numbers_6[:i6] + numbers_6[i6+1:]
                            if not numbers_7:                                
                                continue
                            for i7, n7 in enumerate(numbers_7):
                                answer.append(n1+n2+n3+n4+n5+n6+n7)

    answer = set(map(int, answer))       
    print(sorted(answer))
    return [ans for ans in answer if check_prime(ans)]
