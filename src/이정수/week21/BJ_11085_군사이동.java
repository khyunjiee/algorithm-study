package com.ssafy.algo.study.week21;

import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 문제:군사 이동
 * 링크:https://www.acmicpc.net/problem/11085
 * 
 * 접근:
 * 1. 엣지의 길이가 긴순서로 연결을 시도
 * 2. Union-find 알고리즘으로 서로소 집합 여부 판별
 * 
 * 시간복잡도:
 * O(nlogn)
 * 
 * 
 * 소요 시간:
 * 1h
 * 
 */
public class BJ_11085_군사이동 {

	static class Edge implements Comparable<Edge>{
		int start, end, width;
		
		public Edge(int start, int end, int width) {
			super();
			this.start = start;
			this.end = end;
			this.width = width;
		}

		@Override
		public int compareTo(Edge o) {
			return o.width - this.width;
		}
		
	}
	
	static int parent[];
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int p = sc.nextInt();
		int w = sc.nextInt();
		int c = sc.nextInt();
		int v = sc.nextInt();
		
		parent = new int[p];
		
		for (int i = 0; i < p; i++) {
			parent[i] = i;
		}
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		for (int i = 0; i < w; i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			int width = sc.nextInt();
			pq.add(new Edge(start, end, width));
		}
		
		while (!pq.isEmpty()) {
			// 경로 추가
			Edge edge = pq.poll();
			union(edge.start, edge.end);
			
			if(find(c)==find(v)) {
				System.out.println(edge.width);
				return;
			}
			
		}
	}
	
	static int find(int a) {
		if(parent[a]==a) return a;
		return parent[a] = find(parent[a]);
	}
	
	static boolean union(int a, int b) {
		int rootA = find(a);
		int rootB = find(b);
		if(rootA!=rootB) {
			parent[rootA] = rootB;
			return true;
		}
		return false;
	}

}
