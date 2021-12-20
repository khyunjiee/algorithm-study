package com.ssafy.algo.study.week14;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class PG_42892_길찾기게임 {
	/*
	 * 접근:
	 * 
	 * 시간복잡도:
	 * 
	 * 
	 * 풀이에 걸린 시간:
	 */
	public static void main(String[] args) {
		System.out.println(Arrays.deepToString(solution(new int[][] {{5,3},{11,5},{13,3},{3,5},{6,1},{1,3},{8,6},{7,2},{2,2}})));
	}
	
	static List<Integer> result1, result2;
	static public int[][] solution(int[][] nodeinfo) {
		
		Node[] nodes = new Node[nodeinfo.length];
		
		for (int i = 0; i < nodes.length; i++) {
			nodes[i] = new Node(nodeinfo[i][0],nodeinfo[i][1],i+1);
		}
		
		Arrays.sort(nodes, new Comparator<Node>() {

			@Override
			public int compare(Node o1, Node o2) {
				if(o1.y==o2.y) {
					return o1.x-o2.x;
				}
				return o2.y-o1.y;
			}
			
		});
		
		Node root = nodes[0];
		
		for (int i = 1; i < nodes.length; i++) {
			root.insertNode(nodes[i]);
		}
		
		// 전위 순회
		result1 = new LinkedList<>();
		preOrderSearch(root);
		
		// 후위 순회
		result2 = new LinkedList<>();
		postOrderSearch(root);
		
        int[][] answer = {result1.stream().mapToInt(i->i).toArray(), result2.stream().mapToInt(i->i).toArray()};
        return answer;
    }
	

	private static void postOrderSearch(Node node) {
		if(node.leftChild!=null)
			postOrderSearch(node.leftChild);
		if(node.rightChild!=null)
			postOrderSearch(node.rightChild);
		result2.add(node.number);
	}


	private static void preOrderSearch(Node node) {
		result1.add(node.number);
		if(node.leftChild!=null)
			preOrderSearch(node.leftChild);
		if(node.rightChild!=null)
			preOrderSearch(node.rightChild);
	}


	static class Node{
		int x, y, number;
		Node leftChild=null, rightChild=null;
		
		public Node(int x, int y, int number) {
			super();
			this.x = x;
			this.y = y;
			this.number = number;
		}

		public void insertNode(Node node) {
			if(node.x<this.x) {
				if(this.leftChild!=null) {
					leftChild.insertNode(node);
				}else {
					leftChild = node;
				}
			}else {
				if(this.rightChild!=null) {
					rightChild.insertNode(node);
				}else {
					rightChild = node;
				}
			}
		}

		@Override
		public String toString() {
			return "Node [x=" + x + ", y=" + y + ", number=" + number + ", leftChild=" + leftChild + ", rightChild="
					+ rightChild + "]";
		}
		
	}
}
