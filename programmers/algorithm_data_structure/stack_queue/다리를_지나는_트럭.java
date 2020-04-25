package algorithm_data_structure.stack_queue;

import java.util.*;

class 다리를_지나는_트럭 {

    static class Solution{
                
        public int mySolution(int bridge_length, int weight, int[] truck_weights) {        
            var bridge = new Bridge(weight, bridge_length);
            int time = 0;
        
            for (int truckWeight : truck_weights) {
                time += bridge.insert(truckWeight);
            }
        
            time += bridge.moveAllTrucks();
        
            return time;
        }

        static class Bridge {
            private LinkedList<Integer> onBridge;
            private int currWeight;
            private int maxWeight;
            private int bridgeLength;
            
            public Bridge(int maxWeight, int bridgeLength){
                var list = new LinkedList<Integer>();
                for (int i = 0; i < bridgeLength; i++) {
                    list.add(0);
                }

                this.onBridge = list;
                this.currWeight = 0;
                this.maxWeight = maxWeight;
                this.bridgeLength = bridgeLength;
            }
            
            // 트럭이 올라갈 수 있을 때까지 트럭들을 이동하고나서 트럭을 올리고 걸린 시간 반환
            public int insert(int truckWeight){
                int answer = 1;
                currWeight -= onBridge.removeLast();
                currWeight += truckWeight;
                
                while (currWeight > maxWeight){
                    onBridge.addFirst(0);
                    currWeight -= onBridge.removeLast();
                    answer += 1;
                }            
                onBridge.addFirst(truckWeight);
            
                return answer;
            }
            
            // 모든 트럭을 내리고 걸린 시간 반환
            public int moveAllTrucks(){
                int answer = bridgeLength;
                while (onBridge.removeFirst() == 0){
                    answer -= 1;
                }

                return answer;
            } 
        }

        // ---------------------------------------------

        /*
         * bridgeMap : 현재 다리 위에 있는 트럭을 넣어둠
         * truckStack : 아직 건너지 못한 트럭
         * 
         * 트럭이 내릴 시간이 된 트럭들은 제거함
         * 현재 다리 위에 올라갈 수 있다면 트럭 스택에서 빼서 (이 트럭이 내리는 시간 = 현재까지 지난 시간 + 다리 길이)를 key로 넣음
         * 
         * 여기서 스택은 중요하지 않음(큐로 대체 가능).
         * 맵 구조를 이용해서 dummyTruck(0)을 만들지 않아도 된다.
         * 공간 효율성이 증가한다.
         */
        public int bestSolution(int bridge_length, int weight, int[] truck_weights) {
            Stack<Integer> truckStack = new Stack<>();
            Map<Integer, Integer> bridgeMap = new HashMap<>();

            for (int i = truck_weights.length-1; i >= 0; i--)
                truckStack.push(truck_weights[i]);

            int answer = 0;
            int sum = 0;
            while(true) {
                answer++;

                if (bridgeMap.containsKey(answer))
                    bridgeMap.remove(answer);

                sum = bridgeMap.values().stream().mapToInt(Number::intValue).sum();

                if (!truckStack.isEmpty())
                    if (sum + truckStack.peek() <= weight)
                        bridgeMap.put(answer + bridge_length, truckStack.pop());

                if (bridgeMap.isEmpty() && truckStack.isEmpty())
                    break;


            }
            return answer;
    }
    }
}