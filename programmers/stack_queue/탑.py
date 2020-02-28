'''
이 문제의 포인트는 각각 탑에서 
첫 번째 탑부터 오른쪽으로 검사하지 않고
바로 왼쪽부터 첫번째까지 왼쪽 방향으로 검사하여 
불필요한 검사 시간을 줄이는 것이다.

뒤에서부터 접근하기에 Stack 구조다.
'''

def my_solution(heights):
    '''
    숫자 indexing 대신에 while문을 활용한 풀이
    while문에서 조건을 left_towers만 쓰고
    if문 안에 break를 넣어도 된다.
    (다양한 코드 실습을 위해 이렇게 적음)
    '''
    answer = [0] * len(heights)
    for index, height in enumerate(heights):
        left_towers = heights[:index]
        while left_towers and answer[index]==0:
            if left_towers.pop() > height:
                answer[index] = len(left_towers)+1
      
    return answer

def best_solution(h):
    '''
    데이터의 뒤에서부터 숫자 indexing으로 접근하는 방식
    데이터 들어가는 과정이 없어 애매하지만
    굳이 말하면 stack이다.
    '''
    ans = [0] * len(h)
    for i in range(len(h)-1, 0, -1):
        for j in range(i-1, -1, -1):
            if h[i] < h[j]:
                ans[i] = j+1
                break
    return ans
