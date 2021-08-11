package baekjoon.aug;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 백준 5639 이진 트리
 *
 * 접근 방법 :
 * Node와 Tree 클래스를 구현한다.
 * Node 클래스에는 data, left, right 멤버가 있다.
 * Tree 클래스에는 root 멤버만 존재하며, 데이터 삽입과 후위순회 메소드를 구현한다.
 * input 값에 정해진 기준이 없어, br.readLine() 이 null 이거나 길이가 0 이하라면 더 이상 입력을 받지 않도록 처리한다.
 *
 * 시간 복잡도 :
 * O(n)
 */

public class b5639 {
    public static void main(String[] args) throws IOException {
        Tree tree = new Tree();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";

        while (true) {
            input = br.readLine();

            if (input == null) break;
            if (input.length() <= 0) break;

            tree.addData(Integer.parseInt(input));
        }

        tree.dfsByPostOrder();
    }
}

class Node {
    int data;
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;
    }
}

class Tree {
    Node root;

    public void addData(int data) {
        if (root == null) {
            root = new Node(data);
            return;
        }
        addData(root, data);
    }

    private void addData(Node node, int data) {
        if (node.data < data) {
            if (node.right == null) node.right = new Node(data);
            else addData(node.right, data);
        } else {
            if (node.left == null) node.left = new Node(data);
            else addData(node.left, data);
        }
    }

    public void dfsByPostOrder() {
        dfsByPostOrder(root);
    }

    private void dfsByPostOrder(Node node) {
        if (node.left != null) dfsByPostOrder(node.left);
        if (node.right != null) dfsByPostOrder(node.right);
        System.out.println(node.data);
    }
}
