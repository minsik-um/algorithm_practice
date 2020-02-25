def solution2(phone_book):
    '''
    내 풀이
    해시를 이용하지 않았으며
    최악의 경우 O(n**2) 이므로 좋지 않은 풀이    
    '''
    sorted_book = sorted(phone_book, key=lambda x: len(x))
    for index, num1 in enumerate(sorted_book):
        for num2 in sorted_book[index+1:]:
            if num1 == num2[:len(num1)]:
                return False
    
    return True
    

def solution(phone_book):
    '''
    해시를 이용해서 푼 다른 답변을 간소화
    각 번호에서 가능한 접두사가 해시 맵에 있는지 검사

    hash를 사용하기에 굳이 sort하지 않아도 되기에 효율적
    위 풀이보다 조금 더 빠르게 풀렸음

    이중 for문처럼 보이지만 위의 방법과 다르다.
    안의 for문은 최대 20(글자수 최대)으로 한정된 상수로 계산
    '''
    hash_map = {phone_number: 1 for phone_number in phone_book}

    for phone_number in phone_book:
        for i in range(len(phone_number)):
            pre_num = phone_number[:i]
            if pre_num in hash_map and pre_num != phone_number:
                return False

    return True

