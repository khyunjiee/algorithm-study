package com.ssafy.algo.study.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 타임머신 {
	/*
	 * 접근: 간선의 가중치에 음수가 포함되어 있으므로 벨만 포드 알고리즘을 사용합니다.
	 * 
	 * 시간복잡도: O(VE)
	 */
	
	static class Edge{
		int start, end, weight;

		public Edge(int start, int end, int weight) {
			super();
			this.start = start;
			this.end = end;
			this.weight = weight;
		}

		@Override
		public String toString() {
			return "Edge [start=" + start + ", end=" + end + ", weight=" + weight + "]";
		}
		
	}
	
	static int N,M;
	static Edge[] edgeList;
	static long[] dist;
	static final long INF = Long.MAX_VALUE;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		N = Integer.parseInt(st.nextToken()); // 도시 수
		M = Integer.parseInt(st.nextToken()); // 버스 수
		
		edgeList= new Edge[M];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int A = Integer.parseInt(st.nextToken()); // 출발 도시
			int B = Integer.parseInt(st.nextToken()); // 도착 도시
			int C = Integer.parseInt(st.nextToken()); // 소요 시간
			
			edgeList[i] = new Edge(A,B,C);
		}
		
		dist = new long[N+1];
		Arrays.fill(dist, INF);
		if(bellmanFord()) {
			for (int i = 2; i < N+1; i++) {
				if(dist[i]==Long.MAX_VALUE) {
					System.out.println(-1);
					continue;
				}
				System.out.println(dist[i]);
			}
		}else System.out.println(-1);

	}
	
	private static boolean bellmanFord() {
		dist[1] = 0; // 시작점 최단거리
		
		for(int i=1;i<=N;i++) { // N번 반복
			for (int j = 0; j < M; j++) {// 모든 egde를 확인
				int start = edgeList[j].start;
				int end = edgeList[j].end;
				int weight = edgeList[j].weight;
				if(dist[start] != INF // 방문했던 출발점이고
						&& dist[end]>dist[start] + weight) { // 거리가 더 줄어들면
					dist[end] = dist[start] + weight; // 갱신
					if(i==N) return false; // 음의 사이클이 존재하는 경우
				}
			}
		}
		return true;
	}
}
