package com.ssafy.algo.study.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 우주신과의교감_1774 {
	/*
	 * 접근: MST문제입니다. 프림 알고리즘을 사용하려고 했지만 이미 연결된 통로들이 모두 연결되어 있지
	 * 않은 경우 프림 알고리즘을 사용할 수 없습니다.따라서 크루스칼 알고리즘을 사용합니다.
	 *  이미 연결된 통로들로부터 minEdge를 초기화한 상태에서 시작하여야합니다.
	 * 
	 * 시간복잡도: 1000log1000
	 * 
	 * 걸린 시간:
	 */
	
	static class Edge implements Comparable<Edge>{
		int start, end;
		double weight;

		public Edge(int start, int end, double weight) {
			super();
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.weight, o.weight);
		}

		@Override
		public String toString() {
			return "Edge [start=" + start + ", end=" + end + ", weight=" + weight + "]";
		}
		
	}
	
	// N개의 서로소집합 생성
	static void make() {
		for (int i = 1; i < N+1; i++) parents[i] = -1;
	}
	
	// 주어진 vetex의 대표자 찾기
	static int find(int a) {
		if(parents[a]<0) return a;
		return parents[a] = find(parents[a]);
	}
	
	// 합집합 연산
	static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot==bRoot)return false;
		parents[aRoot] = bRoot;
		return true;
	}
	
	static int N;
	static int[][] vertexes;
	static int[] parents;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		Edge[] edges = new Edge[N*(N-1)/2];
		vertexes = new int[N+1][];
		parents = new int[N+1];
		
		// vertex 배열 생성
		for (int i = 1; i < N+1; i++) {
			st = new StringTokenizer(in.readLine());
			vertexes[i] = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
		}
		
		// 완전 그래프의 간선 생성
		int idx=0;
		for (int i = 0; i < N; i++) {
			for (int j = i+1; j < N; j++) edges[idx++] = new Edge(i+1,j+1, calcDistance(i+1, j+1));
		}
		
		// 완전 그래프 간선 배열 오름차순으로 정렬
		Arrays.sort(edges);
		make();
		
		int cnt=0;
		// 이미 존재하는 통로를 활용한 거리 배열 초기화
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int v1 = Integer.parseInt(st.nextToken());
			int v2 = Integer.parseInt(st.nextToken());
			union(v1,v2); // 합집합
			++cnt;
		}
		
		// 아직 사용하지 않은 간선을 가장 짧은 순서로 시도
		double result = 0; // 만들어야할 통로 길이
		for (Edge edge: edges) {
			if(union(edge.start, edge.end)) {// 서로 다른 집합인 경우에 합집합
				result += edge.weight;
				if(++cnt == N-1) break;
			}
		}
		
		System.out.printf("%.2f",result);
	}
	private static double calcDistance(int v1, int v2) {
		int x1 = vertexes[v1][0];
		int y1 = vertexes[v1][1];
		int x2 = vertexes[v2][0];
		int y2 = vertexes[v2][1];
		return Math.sqrt(Math.pow(x1-x2,2)+ Math.pow(y1-y2, 2));
	}
}
