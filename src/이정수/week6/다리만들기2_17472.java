package com.sssafy.algo.study.week5;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class 다리만들기2_17472 {
	/*
	 * 접근: 가능한 다리를 모두 생성한 뒤 프림 알고리즘으로 최소 신장 트리를 구합니다.
	 * 
	 * 시간복잡도:
	 * 엣지 생성 => 가로와 세로 방향으로 모든 셀을 확인하며 엣지를 생성 10*10*2 =200개의 셀 방문
	 * 프림 알고리즘 => 최소힙 사용시 O(ElogV) V는 최대 6 ? E는 최대 2개짜리 엣지 180개
	 * 시간 초과 일어나지 않음
	 */
	static class Node implements Comparable<Node>{
		int vertex, weight;
		Node link;
		public Node(int vertex, int weight, Node link) {
			super();
			this.vertex = vertex;
			this.weight = weight;
			this.link = link;
		}
		@Override
		public String toString() {
			return "Node [vertex=" + vertex + ", weight=" + weight + ", link=" + link + "]";
		}
		@Override
		public int compareTo(Node o) {
			return Integer.compare(this.weight, o.weight);
		}
		
	}
	
	static int N,M;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		int[][] map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) map[i][j]= sc.nextInt();
		}
		
		// 섬 그룹핑
		int numOfIslands = numberIslands(map);
		
		Node[] adjList = new Node[numOfIslands+1];
		
		// 가능한 모든 다리 생성
		createBridges(map,adjList);
		
		// 프림
		int result =prim(adjList, numOfIslands);
		System.out.println(result);
	}

	private static int prim(Node[] adjList, int numOfIslands) {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		boolean[] visited = new boolean[numOfIslands+1];
		
		// 시작점 세팅
		pq.offer(new Node(1,0,null));
		int cnt=0, totalLen=0;
		while(!pq.isEmpty()) {
			Node current = pq.poll(); // 최단 거리 다음 지점 방문
			if(visited[current.vertex]) continue;
			visited[current.vertex] = true;
			cnt++;
			totalLen += current.weight;
			if(cnt==numOfIslands) return totalLen;
			// 아직 방문하지 않은 섬과 연결된 다리 pq에 추가
			for(Node temp = adjList[current.vertex]; temp != null; temp = temp.link) {
				if(!visited[temp.vertex]) pq.offer(temp);
			}
		}
		
		return -1;
	}

	private static void createBridges(int[][] map, Node[] adjList) {
		// 가로방향 다리
		for (int i = 0; i < N; i++) {
			int start=-1, end=-1; //시작열, 끝열
			for (int j = 0; j < M-1; j++) {
				if(map[i][j]!=0 && start==-1  && map[i][j+1]==0) start= j+1; // 시작점
				else if(start!=-1 && end==-1 && map[i][j+1]!=0 && map[i][start-1]!=map[i][j+1]) { // 끝점
					end = j;
					int len = end-start+1;
					if(len>=2) { // 다리 길이가 2이상이면
						// 다리 생성
						int island1 = map[i][start-1];
						int island2 = map[i][end+1];
						adjList[island1] = new Node(island2, len, adjList[island1]);
						adjList[island2] = new Node(island1, len, adjList[island2]);
					}
					// 다시 시작점 찾기
					start = -1; end = -1;
				}
			}
		}
		
		// 세로방향 다리
		for (int j = 0; j < M; j++) {
			int start=-1, end=-1; //시작열, 끝열
			for (int i = 0; i < N-1; i++) {
				if(map[i][j]!=0 && start==-1  && map[i+1][j]==0) start= i+1; // 시작점
				else if(start!=-1 && end==-1 && map[i+1][j]!=0 && map[start-1][j]!=map[i+1][j]) { // 끝점
					end = i;
					int len = end-start+1;
					
					if(len>=2) { // 다리 길이가 2이상이면
						// 다리 생성
						int island1 = map[start-1][j];
						int island2 = map[end+1][j];
						adjList[island1] = new Node(island2, len, adjList[island1]);
						adjList[island2] = new Node(island1, len, adjList[island2]);
					}
					// 다시 시작점 찾기
					start = -1; end = -1;
				}
			}
		}
	}

	private static int numberIslands(int[][] map) {
		boolean[][] visited = new boolean[map.length][map[0].length];
		int islandNumber = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(!visited[i][j] && map[i][j]==1) { // 방문안된 섬이면 bfs 수행
					bfs(map, visited, i, j, ++islandNumber);
				}
			}
		}
		return islandNumber; //섬의 개수 리턴
	}

	private static void bfs(int[][] map, boolean[][] visited, int row, int col, int islandNumber) {
		Queue<int[]>queue = new LinkedList<>(); // 다음 방문할 위치의 행렬값 배열 저장하는 큐
		int[] dc= {0,1,0,-1};
		int[] dr= {1,0,-1,0};
		
		queue.offer(new int[] {row,col}); // 초기 시작 위치
		visited[row][col] = true;
		
		while(!queue.isEmpty()) {
			int[] current = queue.poll();
			map[current[0]][current[1]] = islandNumber;
			for (int k = 0; k < 4; k++) {
				int nr = current[0] + dr[k];
				int nc = current[1] + dc[k];
				if(nr>=0 && nc>=0 && nr<N && nc<M && !visited[nr][nc] && map[nr][nc]==1) {
					queue.offer(new int[] {nr,nc});
					visited[nr][nc] = true;// 방문여부 표시
				}
			}	
		}
	}

}
