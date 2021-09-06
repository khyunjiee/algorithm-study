package com.sssafy.algo.study.week5;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 게리맨더링_17471 {
	/*
	 * 접근:
	 * 완전탐색으로 1개이상 N/개 이상의 구역을 선택하는 부분집합의 경우의 수를
	 * 모두 구하고 dfs or bfs로 나누어진 선거구의 연결 여부와 인구수 차를 계산
	 * 
	 * 시간복잡도:
	 * 부분집합 최대 2^10
	 * 각각의 부분집합에 대해 bfs or dfs 두번 => 최대 방문 노드 10개
	 * 2^10*10 = 10000 시간초과 X
	 * 
	 */
	
	static class Node{
		int vertex;
		Node link;
		public Node(int vertex, Node link) {
			super();
			this.vertex = vertex;
			this.link = link;
		}
		@Override
		public String toString() {
			return "Node [vertex=" + vertex + ", link=" + link + "]";
		}
		
	}
	
	
	static int[] result;
	static int min=Integer.MAX_VALUE;
	static int[] population;
	static int N;
	static Node[] adjList;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		
		population = new int[N+1]; // 각 지역구의 인구수 저장 배열
		for (int i = 1; i < N+1; i++) population[i] = sc.nextInt();
		
		// adjList 생성
		adjList  = new Node[N+1];
		for (int i = 1; i < N+1; i++) {
			int adjNodesNum = sc.nextInt(); 
			for (int j = 0; j < adjNodesNum; j++) {
				adjList[i] = new Node(sc.nextInt(),  adjList[i]);
			}
		}
		
		// 부분집합 생성
		subSet(0,0, N, 0);
		if(min == Integer.MAX_VALUE) System.out.println(-1);
		else System.out.println(min);
	}

	private static void subSet(int cnt, int size, int N, int isSelected) {
		if(size==N/2 || cnt==N) {
			if(size==0) return; // 하나도 선택하지 않은 경우 배제
			// 선거구 분할
			int[] set1 = new int[size];
			int[] set2 = new int[N-size];
			int idx1 =0, idx2 = 0;
			
			for (int i = 1; i < N+1; i++) {
				if((isSelected & (1<<i))!=0) set1[idx1++] = i;
				else set2[idx2++] = i;
			}
			
			
			// 연결여부 확인
			if(isSetConnected(set1) && isSetConnected(set2)) { // 둘 다 연결되어 있는 경우
				
				// 차 구하기
				int diff = sub(set1, set2);
				if(min>diff) min = diff;
			}
			return;
		}

		// 선택한 경우
		subSet(cnt+1, size+1, N, isSelected | (1<<cnt+1));
		// 선택하지 않은 경우
		subSet(cnt+1, size, N, isSelected);
	}

	private static int sub(int[] set1, int[] set2) {
		int sum1=0, sum2=0; //각 선거구 당 인구수 총합
		for(int i:set1) sum1 += population[i];
		for(int i:set2) sum2 += population[i];
		return Math.abs(sum1-sum2);
	}
	
	
	// bfs로 연결여부 확인
	private static boolean isSetConnected(int[] set) {
		Queue<Integer> queue = new LinkedList<>();
		boolean visited[] = new boolean[N+1];
		queue.offer(set[0]); // 시작점 세팅
		visited[set[0]] = true; // 시작점 방문 여부 체크
		int cnt=0;
		while(!queue.isEmpty()) {
			int current = queue.poll();
			cnt++;
			for(Node temp = adjList[current];temp!=null;temp=temp.link) { // 인접한 노드들에 대해
				if(!visited[temp.vertex] && isIncluded(set, temp.vertex)) {// 방문 기록이 없고 선거구에 속하는 구역인 경우
					queue.offer(temp.vertex); // 큐에 추가
					visited[temp.vertex] = true; // 방문여부 기록
				}
			}
		}
		if(set.length == cnt) {
			return true; // set의 크기와 bfs 총방문 횟수가 같으면 모두 연결된 것
		}
		return false; // 다른 경우 연결 되지 않음
	}
	
	// 배열에 특정값이 포함되어 있는지 확인 해주는 메소드
	private static boolean isIncluded(int[] set, int vertex) {
		for(int i:set) if(vertex==i) return true;
		return false;
	}

}
