package com.ssafy.algo.study.week17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BJ_10282_해킹 {
	/*
	 * 문제: 해킹
	 * 링크: https://www.acmicpc.net/problem/10282
	 * 
	 * 접근:
	 * 1. 다익스트라로 방문 가능한 컴퓨터까지 도달하는 최소시간 모두 구하기
	 * 2. 각 컴퓨터에 도달하는 시간중 최대시간이 답
	 * 3. n이 10000 이므로 인접행렬이 아닌 인접리스트 사용
	 * 4. 테케 수가 최대 100개, n이 10000이므로 O(N^2)인 다익스트라로 풀면 시간 초과 발생 => pq 사용해서 O(NlogN)으로  만들기
	 * 
	 * 
	 * 시간복잡도:
	 * O(NlogN)
	 * 
	 * 
	 * 풀이에 걸린 시간:
	 */
	
	static class Node implements Comparable<Node>{
		int no, time;

		public Node(int no, int time) {
			super();
			this.no = no;
			this.time = time;
		}

		@Override
		public int compareTo(Node o) {
			return this.time-o.time;
		}
		
	}
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(in.readLine());
		StringTokenizer st;
		for (int T = 1; T <= tc; T++) {
			
			st = new StringTokenizer(in.readLine());
			
			int n = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			ArrayList<Node>[] adjList = new ArrayList[n+1];
			
			for (int i = 0; i < adjList.length; i++) {
				adjList[i] = new ArrayList<Node>();
			}

			for (int i = 0; i < d; i++) {
				st = new StringTokenizer(in.readLine());
				
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int s = Integer.parseInt(st.nextToken());
				
				adjList[b].add(new Node(a,s));
			}
			
			
			int[] dist = new int[n+1];
			Arrays.fill(dist, Integer.MAX_VALUE);
			boolean[] visited = new boolean[n+1];
			
			PriorityQueue<Node> pq = new PriorityQueue<>();
			
			pq.offer(new Node(c,0));
			dist[c] = 0;
			
			while(!pq.isEmpty()) {
				
				// 아직 감염되지 않은 지점 중 가장 빨리 감염되는 곳 선정
				Node current = pq.poll();
				if(visited[current.no]) continue; // 이미 방문한 지역인 경우 무시
				
				visited[current.no] = true; // 방문 처리
				
				
				// 아직 감염되지 않은 컴퓨터들 중 새롭게 감염된 컴퓨터로부터 감염될 수 있는 컴퓨터들의 감염되는 시간배열 갱신
				for (int i = 0; i < adjList[current.no].size(); i++) {
					Node adjNode = adjList[current.no].get(i);
					if(!visited[adjNode.no] && dist[adjNode.no] > dist[current.no] + adjNode.time) {
						dist[adjNode.no] = dist[current.no] + adjNode.time;
						pq.offer(new Node(adjNode.no, dist[adjNode.no]));
					}
				}
			}
			
			
			// 방문한 컴퓨터 개수와 최장시간 구하기
			int max = 0;
			int cnt = 0;
			for (int i = 0; i < dist.length; i++) {
				if(visited[i]) {
					cnt++;
					if(max<dist[i]) {
						max = dist[i];
					}
				}
			}
			
			System.out.println(cnt +" " + max);
		}
		
	}
}
