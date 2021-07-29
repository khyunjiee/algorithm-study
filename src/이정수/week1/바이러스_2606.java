package com.ssafy.algorithm;

import java.util.ArrayList;
import java.util.Scanner;

public class 바이러스_2606 {
	/*
	 * 접근: 그래프 탐색(dfs or bfs), 그래프는 ArrayList의 배열로 표현, 재귀호출을 사용한 dfs구현
	 * 
	 * 시간 복잡도: 모든 노드를 최대 한번씩 방문하므로 O(N)시간
	 */
	
	
	public static void main(String[] args) {
		Scanner sc =new Scanner(System.in);
		int com = sc.nextInt();
		int iter = sc.nextInt();
		
		ArrayList<Integer>[] graph = new ArrayList[com+1];
		boolean[] check = new boolean[com+1];
		
		for(int i=0;i<iter;i++) {
			int node1 = sc.nextInt();
			int node2 = sc.nextInt();
			
			// 비어있으면 ArrayList 생성후 채우기
			if(graph[node1]==null) {
				graph[node1] = new ArrayList<>();
				graph[node1].add(node2);
			}
			// 이미 있으면 ArraList에 하나 더 넣기
			else graph[node1].add(node2);
			
			
			// 비어있으면 ArrayList 생성후 채우기
			if(graph[node2]==null) {
				graph[node2] = new ArrayList<>();
				graph[node2].add(node1);
			}// 이미 있으면 ArraList에 하나 더 넣기
			else graph[node2].add(node1);
		}
		
		// dfs
		dfs(check, graph, 1);
		
		
		// 1번을 제외한 감염된 컴퓨터 수 카운트
		int cnt = 0;
		for(int i=2;i<check.length;i++) {
			if(check[i]) {
				cnt++;
//				System.out.println(i);
			}
			
		}
		System.out.println(cnt);
		
		
//		for(int i =0;i<=com;i++) {
//			if(graph[i]!=null) {
//				System.out.print(i+": ");
//				for(int arr:graph[i]) {
//					System.out.print(arr+" ");
//				}
//				System.out.println();
//			}
//		}
	}
	
	static void dfs(boolean[] check, ArrayList<Integer>[] graph, int node) { // 재귀 방식
		// 방문 체크
		check[node] =true;
		for(int nd:graph[node]) {
			// 방문 안했을시 방문
			if(check[nd]==false) dfs(check, graph, nd);
		}
	}

}
