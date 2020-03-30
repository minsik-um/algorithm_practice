from collections import deque

def is_valid(word1, word2):
    check = 0
    for c1, c2 in zip(word1, word2):
        if c1 != c2:
            check += 1
        if check > 1:
            return False
    return True

def solution(begin, target, words):
    '''
    BFS로 탐색
    '''
    answer = 0
    queue = deque([begin])
    visited = set(queue)

    while queue:
        for _ in range(len(queue)):
            curr_word = queue.popleft()
            if curr_word == target:
                return answer
            else:
                for word in words:
                    if is_valid(curr_word, word) and word not in visited:
                        queue.append(word)
                        visited.add(word)

        answer += 1

    return 0    
