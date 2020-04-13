def get_list_by_standard(standard, numbers):
    '''
    numbers 목록에서 인접한 수를 더할 수 있을 때
    standard 값을 최솟값으로(혹은 이거보다 작은 값이 없도록) 하는
    결과 리스트 반환
    '''
    result = [0]
    for number in numbers:
        if result[-1] < standard:
            result[-1] += number
        else:
            result.append(number)

    # 마지막 숫자가 기준에 미달 못하면 직전 숫자와 통합
    if len(result) >= 2 and result[-1] < standard:
        result[-2] += result[-1]
        result.pop()

    return result


def my_solution(distance, rocks, n):
    '''
    intervals : 징검다리에 존재하는 거리 간격 리스트
    middle : 돌을 특정 갯수 만큼 제거했을 때 업데이트 된 interval은 모든 값이 middle보다 같거나 크다.
    start, end : 이분탐색에서 middle을 구할 때 사용하는 초기지점/끝지점
    min_len, max_len = middle값이 최솟값으로서 유효한 interval 리스트의 길이 범위

    < 설명 > 
    문제에서 말하는 최솟값 중의 최댓값을 구하려면
    매번 돌 사이의 거리(interval)가 최소인 돌 중에서 하나를 제거해야 한다.
    최솟값만 존재하면 그 이상의 값들은 어떻게 되어도 상관 없기에
    특정 값이 최솟값일 때 intervals의 형태는 여러개가 있다.

    예) 7을 최솟값 [64, 7, 10, 7, 13], [64, 17, 7, 13], ...

    특정 값이 목표값일 때 가능한 intervals의 최종 형태 길이 범위를 구하고
    이분탐색으로 특정값을 조정해간다.
    
    최종 형태 길이 범위 구하는 게 까다로운데, 이게 인접한 수끼리 계속 더해가니까
    첫 번째 수부터 최솟값과 같거나 클 때까지 더하는 그런 흐름으로 계산하고
    마지막 수는 그 조건에 해당하지 않을 수 있어 마지막 수만 따로 체크해준다.
    '''
    answer = 0
    rocks = sorted(rocks)
    intervals = [n1-n2 for n1, n2 in zip(rocks+[distance], [0] + rocks)]

    start = 1
    end = distance

    while start <= end:
        middle = (start + end) // 2
        result = get_list_by_standard(middle, intervals)
        min_len = len(result) - result.count(middle) + (1 if middle in result else 0)
        max_len = len(result)

        if max_len < (len(intervals) - n):
            end = middle - 1
        elif min_len > (len(intervals) - n):
            start = middle + 1
        else:
            answer = max(answer, min(result))
            start = middle + 1  # 최댓값을 찾으므로 더 큰 기준으로 가서 검사

    return answer
