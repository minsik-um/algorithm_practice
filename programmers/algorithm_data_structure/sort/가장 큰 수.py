def my_solution(numbers):
    '''
    가능한 큰 숫자들이 앞 자리수에 오게 해야 최대값이 나온다.
    -> 큰 숫자를 앞에 가지고 있는 숫자일수록 우선 배치해야 한다.
    
    1. 각 number의 앞부터 비교하여 숫자가 클수록 앞에 위치해야 한다.
    2. 자리수가 적은 number와 다른 수의 앞부분이 일치한다면?
       예. 34, 3433  /  345, 34534566    / 345, 34524666

       일단 그 앞자리는 일치하므로 제거하고 보자
       345     (345)24666
       345는 246보다 크다. 큰 숫자가 앞으로 와야 하는데
       두 번째 숫자가 앞에 가면 246이 앞에 가버리기에 큰 숫자 활용을 못한다.

       345     (345)345 66
       345 와 345는 동일하다. 지금까지 비교만 봐선 뭐가 먼저 오든 괜찮지만,
       345     (345)(345)66
       66이 345보다 큰 숫자를 앞에 가져온다. 두 번째 숫자를 앞에 두자.

       한 뭉치 단위로 보는 것을 명확하게 설명 못하겠지만
        결국 이렇게 비교하면 앞자리일수록 큰 숫자가 오게 된다...
        => 이것보다 명확하게 두 수씩 비교하여 목록을 정렬하는 
           best_solution이 보장되고 깔끔한 방법이다.
    '''
    str_numbers = map(str, numbers)

    max_len = len(str(max(numbers)))
    sort_key = lambda s: s*(max_len//len(s)) + s[:max_len%len(s)]
    answer = "".join(sorted(str_numbers, reverse=True, key=sort_key))
    return str(int(answer))


def better_solution(numbers):
    '''
    numbers의 원소는 0 이상 1,000 이하이므로
    1글자(가장 작은 자릿수) 짜리도 3자리 수로 맞추면 된다.

    만약 2를 곱하면 3자리 수 글자의 1의 자리 쪽 비교를 못하게 된다.
    '''
    numbers = list(map(str, numbers))
    numbers.sort(key=lambda x: x*3, reverse=True)
    return str(int(''.join(numbers)))



import functools

def comparator(a,b):
    '''
    t1이 크다면 1  // t2가 크다면 -1  //  같으면 0
    '''
    t1 = a+b
    t2 = b+a
    return (int(t1) > int(t2)) - (int(t1) < int(t2)) 

def best_solution(numbers):
    '''
    처리 속도는 다른 방법에 비해 느리다.
    comapare 함수를 이용한 방법은 python 2.x에서 주로 사용되었다.

    두 개씩 붙여 봐서 더 큰쪽을 선택하는 게 쌓이면
    결국 가장 큰 수를 선택하는 조합으로 정렬이 되는 것 같음.

    https://docs.python.org/ko/3/howto/sorting.html
    '''
    n = [str(x) for x in numbers]
    n = sorted(n, key=functools.cmp_to_key(comparator),reverse=True)
    answer = str(int(''.join(n)))
    return answer