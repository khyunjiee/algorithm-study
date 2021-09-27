package com.ssafy.algo.study.week6;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
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
	static class Edge implements Comparable<Edge>{
		int start, end, weight;
		
		public Edge(int start, int end, int weight) {
			super();
			this.start = start;
			this.end = end;
			this.weight = weight;
		}
		
		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}
	
	static int N,M;
	static int[] dr= {0, -1, 0, 1}; // 좌, 상, 우, 하
	static int[] dc= {-1, 0, 1, 0};
	static List<int[]> bridgePoints;
	static int parents[];
	
	public static void main(String[] args) {
		// 입력
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		int[][] map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) map[i][j]= sc.nextInt();
		}
		
		// 섬 그룹핑
		bridgePoints = new LinkedList<int[]>();
		int numberOfIslands = markIslands(map); // 섬의 숫자 , map에 섬
		
		// 가능한 모든 다리 생성
		List<Edge> edgeList = new LinkedList<>(); // 간선 리스트
		createBridges(map,edgeList); // 생성가능한 모든 간선을 간선 리스트에 추가

		// MST 크루스칼
		int result =kruskal(edgeList, numberOfIslands); // 연결된 간선의 크기의 합 반환
		System.out.println(result);
	}
	
	private static void printMap(int[][] map) {
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[0].length; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static void makeSet(int numOfIslands) {
		parents = new int[numOfIslands+1];
		for (int i = 0; i < numOfIslands+1; i++) parents[i] = i;
	}
	
	private static int find(int a) {
		if(parents[a]==a) return a;
		return parents[a] = find(parents[a]);
	}
	
	private static boolean union(int a, int b) {
		int aRoot = find(a);
		int bRoot = find(b);
		if(aRoot==bRoot) return false;
		parents[bRoot] = aRoot;
		return true;
	}

	private static int kruskal(List<Edge> edgeList, int numOfIslands) {
		Collections.sort(edgeList); // 간선 오름차순 정렬
		
		makeSet(numOfIslands); // 서로소 집합 생성
		
		int cnt =0, totalLength = 0;
		for (Edge edge : edgeList) {
			if(union(edge.start, edge.end)) { //서로소 집합이면 union
				totalLength += edge.weight; // 연결된 간선(다리) 길이
				if(++cnt==numOfIslands-1) return totalLength;
			}
		}
		return -1;
	}


	// 생성 가능한 다리 모두 edgeList에 추가
	private static void createBridges(int[][] map, List<Edge> edgeList) {
		for (int[] bridgePoint: bridgePoints) {
			int row = bridgePoint[0];
			int col = bridgePoint[1];
			int direction = bridgePoint[2];
			int startPointNum = bridgePoint[3];
			int lenCnt = 0;
			
			while(row>=0 && col>=0 && row<N && col<M ) {
				if(map[row][col]!=0) { // 다른 섬에 도달한 경우
					if(lenCnt>=2 && map[row][col]!=startPointNum) edgeList.add(new Edge(startPointNum, map[row][col], lenCnt)); // 다리의 길이가 2 이상인 경우만
					break;
				}
				row += dr[direction];
				col += dc[direction];
				lenCnt++;
			}
			
		}
	}
	
	// map에 섬번호 표기
	private static int markIslands(int[][] map) {
		boolean[][] visited = new boolean[map.length][map[0].length];
		int numberOfIslands = 0;
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if(!visited[i][j] && map[i][j]==1) { // 방문안된 섬이면 bfs 수행
					bfs(map, visited, i, j, ++numberOfIslands);
				}
			}
		}
		return numberOfIslands; //섬의 개수 리턴
	}
	
	// bfs 수행하며 섬에 숫자 표기 + 경계부 저장
	private static void bfs(int[][] map, boolean[][] visited, int row, int col, int islandNumber) {
		Queue<int[]>queue = new LinkedList<>(); // 다음 방문할 위치의 행렬값 배열 저장하는 큐
		
		queue.offer(new int[] {row,col}); // 초기 시작 위치
		visited[row][col] = true; // 방문 처리
		
		while(!queue.isEmpty()) {
			int[] current = queue.poll();
			map[current[0]][current[1]] = islandNumber; // 섬 번호 표기
			
			for (int k = 0; k < 4; k++) {
				int nr = current[0] + dr[k];
				int nc = current[1] + dc[k];
				
				if(nr>=0 && nc>=0 && nr<N && nc<M && !visited[nr][nc]) {
					if(map[nr][nc]==1) {
						queue.offer(new int[] {nr,nc});
						visited[nr][nc] = true;// 방문여부 표시
					}else {
						bridgePoints.add(new int[] {nr, nc, k, islandNumber}); // 행, 열, 방향, 출발섬 번호
					}
				}
			}	
		}
	}
}
