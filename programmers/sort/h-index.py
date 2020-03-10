def my_solution(citations):
    '''
    (h-index) 수치를 하나씩 테스트하며 찾는 방식
    인용 횟수를 대소관계로 정렬하여 테스트를 효율적으로 함
    (정렬해두었기에 특정 index 이후 숫자는 해당 index의 수보다 작거나 같음)

    정렬을 제대로 활용하지 못했다.
    큰 순서대로 정렬이 되어있고 앞 수부터 확인하는데
    sorted_cit[h-1] >= h 이걸 확인할 필요는 없다.
    => 아래 my_better_solution 함수 및 best code 참고

    주의: h중에서 최대값이 h-index임
    '''
    sorted_cit = sorted(citations, reverse=True)

    if sum(sorted_cit) == 0:
        return 0
    
    h = 1
    while h < len(sorted_cit) and \
    not (sorted_cit[h-1] >= h and sorted_cit[h] <= h):         
        h += 1
                
    return h


def my_better_solution(citations):
    '''
    - 특정 횟수 이상 논문이 h-index편 이상 -
    조건을 만족하는 최소한의 논문 모음이
    0 - (h-1) index 까지이므로,

    sorted_cit[h]는 그 특정 횟수 이하의 논문 인용 수 중에서
    가장 큰 인용 횟수이며 이게 h-index 이하라면 h-index가 정해진다.

    h-index 경우의 수는 여러가지가 있으며 최댓값이 되야 하므로
    - 특정 횟수 이상 인용 수가 특정 횟수 이상 - 조합의
    최소 범위를 우선으로 보며 h-index를 구해간다.
    (해당 조합의 최대 범위부터 보거나
    특정 횟수 이하의 나머지 인용수 조합의 최소 범위를 우선으로 보면
    낮은 h-index가 나온다)

    => 이해 돕기: 가능한 h-index 모음을 쭉 봤을 때
    최대값은 위의 조건을 만족한다.
    '''
    sorted_cit = sorted(citations, reverse=True)
    
    h = 0
    while h < len(sorted_cit) and not sorted_cit[h] <= h:         
        h += 1
                
    return h


def best_solution(citations):
    '''
    가능한 h-index 최대값에서 점점 값을 낮춰가며 검사한다.
    
    (다른 풀이와 달리 정렬을 낮은 값으로 했음에 주의)
    citations[i]는 h-index가 l-i일 때 
    - 특정 횟수 이상 논문이 h-index편 이상 -
    조건을 만족하는 논문 중 가장 작은 횟수의 인용수를 뜻한다.

    이게 h-index 후보보다 같거나 크다면 그 수는 h-index다.
    '''
    citations = sorted(citations)
    l = len(citations)
    for i in range(l):
        if citations[i] >= l-i:
            return l-i
    return 0


def best_solution_2(citations):
    '''
    enumerate에서 starts를 1로 하면
    모든 원소를 돌되 시작이 0이 아닌 1이다.

    start=1로 부여한 index는
    sorted_cit[index] 횟수 이상 인용한 index 개의 논문 개수이며,

    sorted_cit[index] 횟수가 index보다 작거나 같아지는 그 순간을
    (index 이상 인용된 논문이 index편 이상: 이 조건이 True인 마지막 순간)
    구하는 알고리즘이다.
    '''
    citations.sort(reverse=True)
    answer = max(map(min, enumerate(citations, start=1)))
    return answer
