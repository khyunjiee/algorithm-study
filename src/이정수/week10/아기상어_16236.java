package com.ssafy.algo.study.week10;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class 아기상어_16236 {
	/*
	 * 접근:
	 * 1. bfs로 가장 가까이에 있는 먹을 수 있는 물고기 위치 모두 탐색
	 * 2. 위치는 클래스로 만들어서 먹을 수 있는 물고기 위치 pq에 저장(위쪽, 왼쪽 우선)
	 * 3. 먹을 수 있는 물고기 없으면 시간 리턴
	 *  
	 * 시간복잡도:
	 * 매번 bfs로 최대 400번 탐색 모든 위치의 물고기 먹으면 400*400
	 * O(N^4)
	 * 
	 * 풀이에 걸린 시간:
	 * 1h
	 * 
	 */
	
	// 위치 저장 클래스
	static class Position implements Comparable<Position>{
		int r;
		int c;
		public Position(int r, int c) {
			super();
			this.r = r;
			this.c = c;
		}
		@Override
		public String toString() {
			return "Position [r=" + r + ", c=" + c + "]";
		}
		@Override
		public int compareTo(Position o) {
			if(this.r == o .r) { // 행이 같으면
				return this.c - o.c; // 열 비교
			}
			return this.r-o.r;
		}
		
	}
	static int map[][], time=0, N, babySharkSize=2;
	static Position babyShark;
	static int currentFishes=0; // 먹은 물고기 수
	public static void main(String[] args) {
		// 입력
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
				if(map[i][j]==9)  // 아기 상어 초기 위치 저장
					babyShark = new Position(i, j);
			}
		}
		
		while(bfs()) {} // 먹을 수 있는 물고기가 없을때 까지 계속 탐색
		
		System.out.println(time);
	}
	
	// 디버깅을 위한 맵 상태 출력 메소드
	private static void printMap() {
		System.out.println("shark Size: "  + babySharkSize);
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				System.out.print(map[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
	
	
	
	static boolean[][] visited;
	// bfs로 가장 가까운 물고기 위치 탐색
	private static boolean bfs() {
		int[][] delta = {{-1,1,0,0},{0,0,-1,1}};
		visited = new boolean[N][N];  // 방문 여부
		Queue<Position> q = new LinkedList<>();// bfs를 위한 큐
		PriorityQueue<Position> pq = new PriorityQueue<>(); // 먹을 수 있는 물고기 위치 저장
		boolean stopFlag = false; // bfs 탐색을 멈추는 flag(먹을 수 있는 물고기를 찾으면 해당 시간에 가능한 모든 지점 방문 후 멈추기)
		
		q.add(babyShark);
		visited[babyShark.r][babyShark.c] = true;
		int cnt=0; // 움직인 거리 카운트
		
		while(!q.isEmpty()) {
			int size = q.size();
			
			while(--size>=0) {
				Position current = q.poll();
				
				for (int d = 0; d < 4; d++) {
					int nr = current.r + delta[0][d];
					int nc = current.c + delta[1][d];
					
					if(isValid(nr, nc)) {
						if(map[nr][nc]<babySharkSize && map[nr][nc]>0) {// 먹을 수 있는 물고기인 경우
							// pq에 저장
							pq.add(new Position(nr, nc));
							
							// 이 시간까지 탐색
							stopFlag = true;
						}else{ // 이동가능한 공간인 경우
							q.add(new Position(nr, nc));
							visited[nr][nc] = true;
						}
					}
				}
			}
			cnt++;
			if(stopFlag) break; // 탐색 중지
		}
		
		
		if(pq.size()==0) return false; // 먹을 수 있는 물고기 없는 경우
		
		
		// 물고기 먹기
		Position newPosition = pq.poll();
		map[babyShark.r][babyShark.c] = 0; // 원래 상어 위치 비우기
		babyShark = newPosition;// 아기 상어 위치 이동
		map[babyShark.r][babyShark.c] = 9; 
		time += cnt; // 이동거리 만큼 시간 추가
		
		if(++currentFishes== babySharkSize) {// 먹은 물고기 수가 상어 크기와 같으면
			babySharkSize++; // 상어 크기 키우기
			currentFishes = 0; // 먹은 물고기 수 초기화
		}
		return true;
	}
	
	private static boolean isValid(int nr, int nc) {
		return nr>=0 && nc>=0 && nr<N && nc<N && !visited[nr][nc] && map[nr][nc]<=babySharkSize;
	}
}
