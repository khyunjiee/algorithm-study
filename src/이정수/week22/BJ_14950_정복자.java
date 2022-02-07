package com.ssafy.algo.study.week22;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 문제: 정복자
 * 링크: https://www.acmicpc.net/problem/14950
 * 
 * 접근:
 * 프림 알고리즘
 * 간선이 추가될때 마다 모든 간선의 크기를 K씩 증가시켜야함.
 * 
 * 
 * 시간복잡도:
 * 
 * 
 * 소요 시간:
 * 
 */
public class BJ_14950_정복자 {
	
	static class Node{
		int vertex, edge;
		Node link;
		public Node(int vertex,int edge, Node link) {
			super();
			this.edge = edge;
			this.vertex = vertex;
			this.link = link;
		}
		@Override
		public String toString() {
			return "Node [vertex=" + vertex + ", edge=" + edge + ", link=" + link + "]";
		}
		
		
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int t = Integer.parseInt(st.nextToken());
		
		Node[] graph = new Node[N+1];
		
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			int A = Integer.parseInt(st.nextToken());
			int B = Integer.parseInt(st.nextToken());
			int C = Integer.parseInt(st.nextToken());
			
			graph[A] = new Node(B, C, graph[A]);
			graph[B] = new Node(A, C, graph[B]);
		}
		
		int answer = 0;
		int cnt = 0;
		int[] dist = new int[N+1];
		final int INF = Integer.MAX_VALUE;
		boolean[] visited = new boolean[N+1];
		Arrays.fill(dist, INF);
		
		PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {

			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[1] - o2[1];
			}
			
		});
		
		pq.add(new int[] {1, 0});
		dist[1] = 0;
		
		while(cnt<N) {
			// 가장 비용이 작은 경로 선택
			int[] curr = pq.poll();
			
			int currCity = curr[0];
			int weight = curr[1];
			
			if(visited[currCity]) { // 이미 방문한 도시면 스킵
				continue; 
			}
			
			// 방문 처리
			visited[currCity] = true;
			cnt++;
			
			// 비용 추가
			if(currCity!=1) {
				answer += weight + ((cnt-2) * t);
			}
			
			// 추가된 도시를 통해 이어지는 새로운 경로 비용 계산
			for(Node node = graph[currCity]; node!=null; node = node.link) {
				if(!visited[node.vertex] && dist[node.vertex] >node.edge) {
					dist[node.vertex] = node.edge;
					pq.add(new int[] {node.vertex, dist[node.vertex]});
				}
			}
		}
		
		System.out.println(answer);
		
	}

}
