package com.ssafy.algo.study.week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 문제: 최소 스패닝 트리
 * 링크: https://www.acmicpc.net/problem/1197
 * 
 * 접근:
 * 
 * 
 * 시간복잡도:
 * 
 * 
 * 소요 시간:
 * 
 */

// Kruskal Algorithm
public class BJ_1197_최소스패닝트리 {
	
	static class Edge implements Comparable<Edge>{
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			super();
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
		
	}
	
	static int[] parents;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		int V  = Integer.parseInt(st.nextToken());
		int E  = Integer.parseInt(st.nextToken());
		
		PriorityQueue<Edge> pq = new PriorityQueue<>();
		
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(in.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			pq.add(new Edge(from, to, weight));
		}
		
		// 서로소 집합 생성
		parents = new int[V+1];
		for (int i = 0; i < parents.length; i++) {
			parents[i] = i;
		}
		
		int cnt = 0;
		int result = 0;
		
		while(cnt<V-1) {
			// 간선 꺼내기
			Edge edge = pq.poll();
			
			// 사이클 형성 여부 확인
			if(findSet(edge.from)!=findSet(edge.to)) {
				// 사이클 형성 하지 않으면 UNION
				union(edge.from, edge.to);
				cnt++;
				result += edge.weight;
			}
		}
		
		System.out.println(result);
		
	}

	private static void union(int a, int b) {
		int rootA = findSet(a);
		int rootB = findSet(b);
		if(rootA!=rootB) {
			parents[rootA] = rootB;
		}
	}

	private static int findSet(int a) {
		if(parents[a]==a) return a;
		return parents[a] = findSet(parents[a]); // path compression
	}

}

//Prim Algorithm
