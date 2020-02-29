from collections import deque

def solution(bridge_length, weight, truck_weights):
    '''
    내 코드
    deque에 sum() 함수를 이용해 매번 다리 위 무게를 재다보니
    시간 초과 발생... 불필요하게 똑같이 더하기 때문
    -> 변수를 추가하고 sum이 매번 O(1)이 되도록 지정
    '''
    t_w = deque(truck_weights)
    on_bridge = deque([0] * bridge_length)
    tick = 0
    sum_on_bridge = 0
    
    while t_w:
        sum_on_bridge -= on_bridge.popleft()
        if sum_on_bridge + t_w[0] <= weight:
            on_bridge.append(t_w.popleft())
            sum_on_bridge += on_bridge[-1]
        else:
            on_bridge.append(0)    
        tick += 1
    
    return tick + bridge_length

'''
추천 최다 코드
class를 쓰고 if문 검사를 넣어 안전한 코드를 만들었음.
대신에 속도가 감소.
test 5 기준 약 240ms(class 미사용 위 코드는 50ms)

2가지 요소가 실행 시간을 늘렸다.
1. self.- 속성이나 메소드 호출 시 시간이 증가. 
클래스 선언 자체는 시간에 큰 영향이 없다. 대신 속성을 불러오고
메소드로 속성을 수정하는 과정(속성 아니고 메소드 호출만 해도)
시간이 증가했다.

2. if문 사용
- bridge.push(DUMMY_TRUCK) 부분은 무조건 0을 넣는데
  굳이 if문을 써서 분기할 필요가 없다. 따로 메소드 파자.
- len(self._queue) < self._max_length: 이부분은 없어도 된다.
  매번 queue에서 1개씩 빼기 때문이다.

그외 여러 요소는 시간에 큰 영향을 주지 않는다.

'''
class Bridge(object):

    DUMMY_TRUCK = 0

    def __init__(self, length, weight):
        self._max_length = length
        self._max_weight = weight
        self._queue = deque()
        self._current_weight = 0

    def push(self, truck):
        next_weight = self._current_weight + truck
        if next_weight <= self._max_weight and len(self._queue) < self._max_length:
            self._queue.append(truck)
            self._current_weight = next_weight
            return True
        else:
            return False

    def pop(self):
        item = self._queue.popleft()
        self._current_weight -= item
        return item

    def __len__(self):
        return len(self._queue)

    def __repr__(self):
        return 'Bridge({}/{} : [{}])'.format(self._current_weight, self._max_weight, list(self._queue))


def solution_with_class(bridge_length, weight, truck_weights):
    bridge = Bridge(bridge_length, weight)
    trucks = deque(w for w in truck_weights)

    for _ in range(bridge_length):
        bridge.push(DUMMY_TRUCK)

    count = 0
    while trucks:
        bridge.pop()

        if bridge.push(trucks[0]):
            trucks.popleft()
        else:
            bridge.push(DUMMY_TRUCK)

        count += 1

    while bridge:
        bridge.pop()
        count += 1

    return count