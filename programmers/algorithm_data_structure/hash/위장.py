from collections import Counter
from functools import reduce

'''
Counter 사용법
https://www.daleseo.com/python-collections-counter/

reduce 설명
https://docs.python.org/3/library/functools.html#functools.reduce

모든 value에 1을 더하기 위해 앞에 마지막 매개변수에 1 넣었다.
그러면 1 * ... 부터 시작된다. 이렇게 안하면 처음 value에 1이 안더해진다.

내 풀이와 1위 답변이 유사하여 1개로 정리
'''

def solution(clothes):
    clothes_counter = Counter([cloth[1] for cloth in clothes])   
    answer = reduce(lambda x, y: x*(y+1), clothes_counter.values(), 1)
    answer -= 1

    return answer