def my_solution(name):
    '''
    결과값이 바뀌는 변수는 (얼마나 ←→ 버튼을 최소한으로 누르는가) 이다.
    1. ↑↓ 은 고정값이므로 미리 up_down에 계산해둔다.
    2. Greedy
       시뮬레이션을 실제로 돌려보며
       매번 왼쪽/오른쪽 중에서 A가 아닌 값까지의 거리가
       작은 방향으로 이동한다.
    '''
    up_down = [ord(n)-ord('A')
               if ord(n)-ord('A') <= 13
               else 26-(ord(n)-ord('A'))
               for n in name]

    left_right = 0
    remain_i = [1 if i else 0 for i in up_down]
    remain_i[0] = 0
    curr_i = 0

    for _ in range(sum(remain_i)):
        left, right = curr_i - 1, curr_i + 1
        while remain_i[left] == 0:
            left -= 1
        while right >= len(name) or remain_i[right] == 0:
            if right >= len(name) - 1:
                right = 0
            else:
                right += 1

        if abs(curr_i - left) < abs(right - curr_i):
            left_right += abs(curr_i - left)
            curr_i = left if left > 0 else len(name) + left
        else:
            left_right += abs(right - curr_i)
            curr_i = right

        remain_i[curr_i] = 0

    return sum(up_down) + left_right
