from collections import deque


def solution(people, limit):
    '''
    매번 가장 무거운 사람을 1명 넣고
    가장 가벼운 사람 순으로 나머지 무게를 채워 넣는다.

    deque를 이용하여 양쪽에서 빼는 시간을 최소화
    (다른 사람 코드를 보면 index 변수 2개를 만들어 간단하게 처리)
    '''
    sorted_people = deque(sorted(people))
    answer = 0
    while sorted_people:
        boat = []
        while sorted_people and sum(boat) + sorted_people[0] <= limit:
            boat.append(sorted_people.popleft())

        answer += 1

    return answer
