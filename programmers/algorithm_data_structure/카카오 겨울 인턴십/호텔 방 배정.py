def extract(container):
    if type(container) == int:
        return container
    else:
        return extract(container[0])

def insert(container, element):
    if type(container[0]) == int:
        container[0] = element
    else:
        insert(container[0])


def solution(k, room_number):
    answer = []
    room_graph = [[i] for i in range(k+1)]

    for room in room_number:
        # 머무를 방 결정
        assigned_room = extract(room_graph[room])
        answer.append(assigned_room)

        # 두 방의 그룹을 통합
        next_room = extract(room_graph[assigned_room+1])
        if assigned_room + 1 == next_room:
            room_graph[next_room] = room_graph[assigned_room]
            room_graph[next_room][0] = next_room
        else:
            insert(room_graph[assigned_room], room_graph[next_room])
            room_graph[assigned_room][0] = room_graph[next_room]

    return answer


if __name__ == "__main__":
    k = 10
    room_number = [1, 3, 4, 1, 3, 1]

    print(solution(k, room_number))
