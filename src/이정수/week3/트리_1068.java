package com.ssafy.algo.study.week3;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 트리_1068 {
	/*
	 * 접근: 루트 노드 부터 시작하여 해당 노드를 부모로 하는 노드를 찾아 내려갑니다.
	 * 자신을 부모로하는 노드가 없으면 해당 노드는 자식노드입니다.
	 * 
	 * 시간복잡도: 각 노드를 찾을때마다 전체 배열을 다시 검색하므로 O(N^2)
	 */
	
	static int tree[], N, node, leafCnt;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		tree = new int[N]; // tree 저장 배열
		for(int i=0;i<N;i++) tree[i] = sc.nextInt();
		
		node= sc.nextInt();
		
		// 주어진 노드 지우기
		boolean flag= true;
		tree[node]  = -2;
		Queue<Integer> queue = new LinkedList<>();
		queue.offer(node);
		while(!queue.isEmpty()) {
			int current = queue.poll();
			tree[current] = -2; // 노드 지우기
			for(int i=0;i<N;i++) {
				if(tree[i]==current) queue.offer(i); // 자식노드에 해당하면 큐에 넣기
			}
		}
		
		// 리프 카운팅
		
		for(int i=0;i<N;i++)if(tree[i]==-1) {// 루트 노드 찾기
			queue.offer(i);
			break;
		}
		while(!queue.isEmpty()) {
			int current = queue.poll();
			boolean isLeaf = true;
			for(int i=0;i<N;i++) {
				if(tree[i]==current) {
					queue.offer(i); // 자식노드에 해당하면 큐에 넣기
					isLeaf = false;
				}
			}
			if(isLeaf) leafCnt++; // 자식노드가 없으면 leaf node
		}
		
		System.out.println(leafCnt);
	}

}
