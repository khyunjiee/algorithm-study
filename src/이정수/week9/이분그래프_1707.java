package com.ssafy.algo.study.week9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 이분그래프_1707 {
	/*
	 * 접근:
	 * 1. 인접 리스트로 그래프 표현
	 * 2. 그래프의 한 정점에 집합 번호(1) 부여하고 bfs 수행 
	 * 3. 인접한 정점에 모두 상대 집합 번호(2) 부여
	 * 4. 인접한 정점중 아직 방문하지 않은 정점들은 큐에 넣기
	 * 5. 인접 정점의 집합 번호가 자신의 집합 번호와 같으면 이분그래프 생성 불가능
	 * 6. 연결 그래프가 아닐 수 있으므로 모든 정점을 방문할 때까지 반복
	 * 
	 * 시간복잡도:
	 * ?
	 * 
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
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine().trim());
		
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(in.readLine().trim());
			int V = Integer.parseInt(st.nextToken());
			int E = Integer.parseInt(st.nextToken());
			
			// 인접 리스트 생성
			Node []adjList = new Node[V+1];
			for (int i = 0; i < E; i++) {
				st = new StringTokenizer(in.readLine().trim());
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				adjList[from] = new Node(to, adjList[from]);
				adjList[to] = new Node(from, adjList[to]);
			}
			
			int[] group = new int[V+1]; // 각각의 정점에 배정된 집합 표시
			boolean[] visited = new boolean[V+1];
			boolean isBipartite = true;
			Queue<Integer> queue = new LinkedList<>();
			
			label: for (int i = 1; i < V+1; i++) {
				if(!visited[i]) {
					queue.add(i);
					visited[i] = true;
					group[i] = 1; // 집합 1 배정
					int otherGroup = 0;
					
					while(!queue.isEmpty()) {
						int current = queue.poll();
						
						// 자신과 다른 집합 넘버 부여
						if(group[current]==1) otherGroup = 2;
						else otherGroup = 1;
						
						for(Node temp = adjList[current]; temp != null; temp = temp.link) {
							// 인접 노드가 자신과 같은 집합에 속하면 이분그래프 아님
							if(group[temp.vertex] == group[current]) {
								isBipartite = false;
								break label;
							}
							
							// 인접 노드에  자신과 반대 집합 넘버 부여
							group[temp.vertex] = otherGroup;
							
							if(!visited[temp.vertex]) { // 방문하지 않은 정점이라면
								// 큐에 넣기
								queue.add(temp.vertex);
								visited[temp.vertex] =true;
							}
						}
					}
				}
			}
			
			if(isBipartite) System.out.println("YES");
			else System.out.println("NO");
		}
	}

}
