package com.ssafy.algo.study.week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 이진검색트리_5639 {
	/*
	 * 접근: 전위 순회 순서대로 노드를 삽입해서 트리를 생성하고 후위 순회하여 출력합니다.
	 * 완전 이전 트리가 아니므로 배열로 트리를 구현할 수 없습니다. 링크드 리스트로 구현합니다.
	 * 
	 * 시간복잡도: 노드 삽입시 최대 O(N^2)? 후위 순회 시 O(N)  total O(N^2) -> 딱 1초
	 */
	static int[] tree;
	public static void main(String[] args) throws IOException {
		BufferedReader bin = new BufferedReader(new InputStreamReader(System.in));
		
		// 트리 생성
		String  n;
		while((n = bin.readLine())!= null && n.length() != 0) {
			Tree.addNode(Integer.parseInt(n));
		}
		
		
		// 후위 순회
		Tree.dfs(Tree.root);
	}
	
	static class Node{
		int key;
		Node left;
		Node right;
		public Node(int key) {
			super();
			this.key = key;
		}
		
	}
	
	static class Tree{
		public static Node root;
		public static void addNode(int key) {
			// root가 null일때
			if(root==null) {
				root= new Node(key);
				return;
			}
			
			Node current = root;
			while(true) {// 빈 링크를 만날때 까지
				if(current.key<key && current.right!=null) current = current.right;// 오른쪽 자식 노드
				else if(current.key>key && current.left!=null) current = current.left; // 왼쪽 자식 노드
				else break;
			}
			// 빈노드에  키값 넣기
			if(current.key<key)current.right = new Node(key);
			else current.left = new Node(key);
		}
		
		public static void dfs(Node current) {
			// 왼쪽 자식 노드 방문
			if(current.left!=null) dfs(current.left);
			// 오른쪽 자식 노드 방문
			if(current.right!=null) dfs(current.right);
			System.out.println(current.key);// 후위 순회
		}
	}

}
