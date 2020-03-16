def best_solution(number, k):
    '''
    앞의 수부터 아래 조건 확인
    (제거했을 때 뒤의 수가 더 커서 최종 수가 더 커지는가?)

    내가 했던 생각을 발전하여
    각 수 기준에서 앞에 그 수보다 작은 모든 수를 
    아예 없애는 알고리즘으로 풀이
    +
    stack을 사용하여 현재 상태를 나타내 시간 축소
    '''
    stack = [number[0]]
    for num in number[1:]:
        while len(stack) > 0 and stack[-1] < num and k > 0:
            k -= 1
            stack.pop()
        stack.append(num)
    if k != 0:
        stack = stack[:-k]
    return ''.join(stack)


# 수를 하나씩 제거하는 알고리즘: 10번 시간초과 못넘음
def my_wrong_solution(number, k):
    '''
    앞에서부터 봐서 앞의 수가 뒤의 수보다 작으면
    삭제했을 때 수가 더 커지므로 삭제

    현재 상태를 리스트 슬라이싱으로 업데이트하고
    이미 검사한 수들을 또 검사하는 알고리즘이므로
    시간 효율성이 안좋음
    '''
    new_num = number
    for current_k in range(k):
        if current_k != len(number) - len(new_num):
            break

        for i in range(len(new_num)-1):
            if new_num[i] < new_num[i+1]:
                new_num = new_num[:i] + new_num[i+1:]
                break

    differ = (len(new_num) + k) - len(number)
    if differ:
        new_num = new_num[:-differ]
    return new_num


def other_solution(number, k):
    '''
    질문하기에 올라온 문제 코드를
    내가 해결하여 제출한 코드

    남은 k-1번 동안 쓰기 위한 수를 뒤에서부터 빼놓고
    앞의 수 중에서 가장 큰 수를 선택해 답에 넣는 방법이다.
    '''
    answer = ''
    idx = 0
    length = len(number)

    for i in range(0, length - k):
        max_num = '/'  # 0보다 아스키 코드가 작은 기호
        print(number)
        for e in range(0, len(number)-(length-k-i)+1):
            if number[e] == '9':
                max_num = '9'
                idx = e
                break
            else:
                if number[e] > max_num:
                    max_num = number[e]
                    idx = e
                    
        answer += str(max_num)     
        number = number[idx+1:]

    return answer

# data = "69240278" 2 => "940278"
print(solution_1("69240278", 2))