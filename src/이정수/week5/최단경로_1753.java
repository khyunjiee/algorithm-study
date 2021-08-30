import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main1753_이정수 {
	/*
	 * 접근:
	 * V의 수가 2만으로 매우 크기 때문에 인접리스트로 그래프를 표현해야합니다.
	 * 다익스트라 알고리즘으로 shortest distance를 구합니다.
	 * priority queue를 사용하여 빠르게 최단 거리를 뽑아 냅니다.
	 * 경로가 존재하지 않으면 visited 가 false입니다.
	 * 
	 * 시간복잡도: O(V) 
	 */
	static class Vertex implements Comparable<Vertex>{
		int no, distance;

		public Vertex(int no, int distance) {
			super();
			this.no = no;
			this.distance = distance;
		}

		@Override
		public int compareTo(Vertex o) {
			
			return Integer.compare(this.distance, o.distance);
		}
	}
	
	static class Node{
		int no, weight;
		Node link;
		public Node(int no,int weight, Node link) {
			super();
			this.no = no;
			this.link = link;
			this.weight = weight;
		}
		
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader in =new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(in.readLine());
		
		int[] distance = new int[V+1];
		boolean[] visited = new boolean[V+1];
		Node[] adjList = new Node[V+1]; 
		
		// 인접 리스트 생성
		for(int i=0;i<E;i++) {
			st = new StringTokenizer(in.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			
			adjList[u] = new Node(v, w,adjList[u]); // 인접 리스트에 원소 추가
		}
		
		// Dijkstra
		final int INFINITY = Integer.MAX_VALUE;
		Arrays.fill(distance, INFINITY);
		
		PriorityQueue<Vertex> pq = new PriorityQueue<>();
		distance[K] = 0;
		pq.offer(new Vertex(K, 0));
		
		while(!pq.isEmpty()) {
			// 아직 방문하지 않은 가장 가까운 정점 찾기
			Vertex current = pq.poll();
			
//			if(visited[current.no]) continue; // 이미 방문했던 곳이면 패스
			visited[current.no] = true; // 방문 체크
				
			// distance 배열 업데이트
			for(Node temp = adjList[current.no];temp!=null;temp = temp.link) {
				if(!visited[temp.no] && distance[temp.no]>temp.weight + distance[current.no]) { // 방문하지 않았고 새로운 경로가 더 짧은 경우
					distance[temp.no] = temp.weight + distance[current.no];
					pq.offer(new Vertex(temp.no, distance[temp.no]));
				}
			}
		}
		
		for(int i=1;i<V+1;i++) {
			if(visited[i]) System.out.println(distance[i]);
			else System.out.println("INF");
		}
	}

}
