def solution(phone_book):
    '''
    해시를 사용하지 않았지만
    이번 문제에 한하여 아래 방법보다 속도가 더 빠른 것에 불과하며,
    이중 loop가 절대 좋은 것은 아니다.
    위 방법은 O(n^2)에 가깝지만
    아래 방법은 len(phone_book) * (각 문자열 길이, 최대 20) 만큼 loop를 돈다.
    '''
    for idx, num1 in enumerate(phone_book):
        for num2 in phone_book[idx+1:]:
            if num1.startswith(num2) or num2.startswith(num1):
                return False

    return True


def other_solution(phone_book):
    '''
    해시를 이용해서 푼 다른 답변을 간소화
    각 번호에서 가능한 접두사가 해시 맵에 있는지 검사

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


# 트라이(Trie)를 이용한 풀이
# 출처: https://programmers.co.kr/learn/questions/10365

# 트라이 내에 담을 노드
class Node(object):
    def __init__(self, key, data=None):
        self.key = key
        self.data = data
        self.children = {}


class Trie(object):
    def __init__(self):
        self.head = Node(None)

    def insert(self, string):
        curr_node = self.head

        for char in string:
            # 이 문자의 접두사가 trie에 있다면 False 반환
            if curr_node.data is not None:
                return False

            if char not in curr_node.children:
                curr_node.children[char] = Node(char)

            curr_node = curr_node.children[char]

        curr_node.data = string
        return True

    def search(self, string):
        curr_node = self.head

        for char in string:
            if char in curr_node.children:
                # 이 문자의 접두사가 trie에 있다면 False 반환
                if curr_node.data is not None:
                    return False
                curr_node = curr_node.children[char]

        return True


def trie_solution(phone_book: list) -> bool:
    tri = Trie()
    for p in phone_book:
        if not tri.insert(p):
            return False
    for p in phone_book:
        if not tri.search(p):
            return False
    return True
