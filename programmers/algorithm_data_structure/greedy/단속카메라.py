def my_solution(routes):
    '''
    * 아래 best 풀이 반드시 참고 *
    => 훨씬 간단하고 더 greedy하며 빠르게 풀어냈다.

    차의 동시 만족 범위를 최대한 겹치어
    범위가 1 미만이 되었을 때 단속 카메라 설치 후
    다시 범위를 계산한다
    '''
    answer = 0
    sorted_routes = sorted(routes, reverse=True)

    last_in, first_out = sorted_routes.pop()
    while sorted_routes:
        car_in, car_out = sorted_routes.pop()
        last_in = (car_in if car_in > last_in else last_in)
        first_out = (car_out if car_out < first_out else first_out)

        if last_in > first_out:
            answer += 1
            last_in = car_in
            first_out = car_out

    return answer + 1  # 나머지 처리


def best_solution(routes):
    '''
    마지막으로 설치된 카메라 지점이 
    차의 진입 지점 이전에 있으면
    새로운 카메라를 차의 진입 이후 지점에 설치한다.
    (최대한 다른 차도 수용하는 local optimal)
    '''
    routes = sorted(routes, key=lambda x: x[1])
    last_camera = -30000

    answer = 0

    for route in routes:
        if last_camera < route[0]:
            answer += 1
            last_camera = route[1]

    return answer