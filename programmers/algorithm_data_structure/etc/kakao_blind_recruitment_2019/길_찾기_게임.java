package algorithm_data_structure.etc.kakao_blind_recruitment_2019;

import java.util.ArrayList;
import java.util.List;

class 길_찾기_게임 {
    /**
     * @param nodeinfo : 노드의 x, y 값을 번호 순서대로 저장한 배열
     * @return : 주어진 길 찾기 게임 규칙에 따라 트리 생성 후 전위탐색, 후위탐색 결과 반환
     * 
     * 1. y값이 큰 (같다면 x 값이 작은) 순서로 노드를 정렬한다.
     * 2. 게임 규칙에 따라 모든 노드를 넣어 트리를 만든다.
     * 3. 재귀함수로 전위탐색, 후위탐색을 수행한다.
     */
    public int[][] solution(int[][] nodeinfo) {
        Tree tree = new Tree(nodeinfo);

        int[] preOrderNums = tree.preOrderSearch();
        int[] postOrderNums = tree.postOrderSearch();

        return new int[][] { preOrderNums, postOrderNums };
    }

    class Tree {
        private Node root;

        public Tree(int[][] nodeinfo) {
            List<Node> ySorted = ySort(nodeinfo);
            this.root = ySorted.get(0);

            for (int i = 1; i < ySorted.size(); i++) {
                Node newNode = ySorted.get(i);
                this.addNode(this.root, newNode);
            }
        }

        private List<Node> ySort(int[][] nodeinfo) {
            List<Node> ret = new ArrayList<>();

            for (int i = 0; i < nodeinfo.length; i++) {
                ret.add(new Node(i + 1, nodeinfo[i][0], nodeinfo[i][1]));
            }

            ret.sort((o1, o2) -> {
                int diffY = o2.getY() - o1.getY();
                if (diffY != 0) {
                    return diffY;
                } else {
                    return o1.getX() - o2.getX();
                }
            });

            return ret;
        }

        private void addNode(Node currNode, Node newNode) {
            if (currNode.getX() < newNode.getX()) {
                Node rightNode = currNode.getRight();
                if (rightNode != null) {
                    addNode(rightNode, newNode);
                } else {
                    currNode.setRight(newNode);
                }
            } else {
                Node leftNode = currNode.getLeft();
                if (leftNode != null) {
                    addNode(leftNode, newNode);
                } else {
                    currNode.setLeft(newNode);
                }
            }
        }

        public int[] preOrderSearch() {
            List<Integer> ret = new ArrayList<>();
            preOrderSearch(this.root, ret);
            return toIntArray(ret);
        }

        private void preOrderSearch(Node currNode, List<Integer> path) {
            if (currNode != null) {
                path.add(currNode.getNum());
                preOrderSearch(currNode.getLeft(), path);
                preOrderSearch(currNode.getRight(), path);
            }
        }

        public int[] postOrderSearch() {
            List<Integer> ret = new ArrayList<>();
            postOrderSearch(this.root, ret);
            return toIntArray(ret);
        }

        private void postOrderSearch(Node currNode, List<Integer> path) {
            if (currNode != null) {
                postOrderSearch(currNode.getLeft(), path);
                postOrderSearch(currNode.getRight(), path);
                path.add(currNode.getNum());
            }
        }

        private int[] toIntArray(List<Integer> origin) {
            int[] ret = new int[origin.size()];

            for (int i = 0; i < ret.length; i++) {
                ret[i] = origin.get(i);
            }

            return ret;
        }
    }

    class Node {
        private int num;
        private int posX;
        private int posY;
        private Node left;
        private Node right;

        public Node(int num, int posX, int posY) {
            this.num = num;
            this.posX = posX;
            this.posY = posY;
            this.left = null;
            this.right = null;
        }

        public int getNum() {
            return this.num;
        }

        public int getX() {
            return this.posX;
        }

        public int getY() {
            return this.posY;
        }

        public void setLeft(Node node) {
            this.left = node;
        }

        public void setRight(Node node) {
            this.right = node;
        }

        public Node getLeft() {
            return this.left;
        }

        public Node getRight() {
            return this.right;
        }
    }
}
