def solution(genres, plays):
    '''
    1등답과 내 답 풀이방식이 동일하여 1등 답을 참고하여 코드 최적화함
    해시를 사용하면 장르별로 노래를 분류해 모임을 만들 때
    각 분류 이름을 key로 이용하여 O(1)로 빠르게 접근 가능

    1. 고유 번호 포함 데이터 전처리
    2. 1번 규칙을 위해 장르별 총 재생 횟수 계산 및 순서별로 정렬
    3. 장르 내에서 2,3번 규칙에 의해 수록될 노래 1-2개씩 선별

    sum([t[0] for t in data[x]]) -> sum(map(lambda x: x[0], data[x]))
    list comprehension은 map 함수로 대체 가능 
    '''    
    data = {genre: [] for genre in genres}
    for i, d in enumerate(zip(genres, plays)):
        data[d[0]].append([d[1],i])

    gen_order = sorted(set(genres), 
                       key=lambda x: sum([d[0] for d in data[x]]), 
                       reverse=True)
      
    answer = []
    for genre in gen_order:
        d = sorted(data[genre], key=lambda x:(x[0], -x[1]), reverse=True)
        answer += [t[1] for t in d[:2]]

    return answer