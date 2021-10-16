package com.ssafy.algo.study.week11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class 어른상어_19237 {
	/*
	 * 접근:
	 * 1. 각 상어의 위치는 리스트로 관리
	 * 2. 맵의 상태는 3차원 배열로 관리
	 * 
	 * 
	 * 시간복잡도:
	 * 상어들의 방향 결정 4^M
	 * 맵 상태 갱신 N^2
	 * 
	 * 풀이에 걸린 시간:
	 * 1h 25m
	 *
	 */
	static class Shark{
		int row, col, direction;

		public Shark(int row, int col, int direction) {
			super();
			this.row = row;
			this.col = col;
			this.direction = direction;
		}
	}
	
	static int[][][] directionPriority, map;
	static int[][] delta = {{-2,-1,1,0,0},{-2,0,0,-1,1}};
	static int N,M,k;
	static Map<Integer, Shark> sharks;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		
		sharks = new HashMap<>();
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				int val = Integer.parseInt(st.nextToken());
				if(val != 0) sharks.put(val, new Shark(i,j,-1));
			}
		}
		
		// 상어 방향 입력
		st = new StringTokenizer(in.readLine());
		for (int i = 1; i <= M; i++) 
			sharks.get(i).direction = Integer.parseInt(st.nextToken());
		
		// 상어 방향에 따른 우선순위 입력
		directionPriority = new int[M+1][5][5];
		
		for (int i = 0; i < M; i++) {
			for (int j = 1; j <= 4; j++) {
				st = new StringTokenizer(in.readLine());
				for (int d = 1; d <= 4; d++) 
					directionPriority[i+1][j][d] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		map = new int[N][N][]; // 상어 냄새 기록하는 맵
		
		// 시뮬 시작
		int time = 0;
		while(++time<=1000) {
			// 냄새뿌리기
			leaveSmell();
			
			// 방향 결정
			selectDirection();
			
			// 이동
			checkCross();
			if(sharks.size()==1) break; // 상어가 하나만 남으면 종료
			
			// 맵에 남은 냄새 시간 줄이기
			updateMap();
		}
		
		if(time>1000) {
			System.out.println(-1);
		}else {
			System.out.println(time);
		}
	}
	
	// 냄새 남기기
	private static void leaveSmell() {
		for (int sharkNum = 1; sharkNum <= M; sharkNum++) {
			if(!sharks.containsKey(sharkNum)) continue;
			Shark shark = sharks.get(sharkNum);
			map[shark.row][shark.col] = new int[] {sharkNum, k};
		}
	}
	
	
	// 상어 이동방향 결정
	private static void selectDirection() {
		for (int sharkNum = 1; sharkNum <= M; sharkNum++) {
			if(!sharks.containsKey(sharkNum)) continue;
			Shark shark = sharks.get(sharkNum);
			boolean isEmptySpace = false;
			for (int d = 1; d <= 4; d++) { // 4방향 시도
				int nr = shark.row + delta[0][directionPriority[sharkNum][shark.direction][d]];
				int nc = shark.col + delta[1][directionPriority[sharkNum][shark.direction][d]];
				
				if(isValid(nr, nc) && map[nr][nc] == null) {
					shark.row = nr;
					shark.col = nc;
					shark.direction = directionPriority[sharkNum][shark.direction][d];
					isEmptySpace = true;
					break;
				}
			}
			
			if(!isEmptySpace) { // 빈공간이 없으면 자신의 냄새가 들어있는칸으로 이동
				for (int d = 1; d <= 4; d++) { // 4방향 시도
					int nr = shark.row + delta[0][directionPriority[sharkNum][shark.direction][d]];
					int nc = shark.col + delta[1][directionPriority[sharkNum][shark.direction][d]];
					
					if(isValid(nr, nc) && map[nr][nc][0]==sharkNum) {
						shark.row = nr;
						shark.col = nc;
						shark.direction = directionPriority[sharkNum][shark.direction][d];
						isEmptySpace = true;
						break;
					}
				}
			}
		}
		
	}
	
	// 시간 경과에 따른 남은 냄새 시간 갱신
	private static void updateMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(map[i][j]== null) continue;
				if(--map[i][j][1] == 0) map[i][j] = null; // 냄새 없애기
			}
		}
	}
	
	
	// 상어끼리 겹침 여부 확인
	private static void checkCross() {
		boolean visited[][] = new boolean[N][N];
		for (int sharkNum = 1; sharkNum <= M; sharkNum++) {
			if(!sharks.containsKey(sharkNum)) continue;
			Shark shark = sharks.get(sharkNum);
			
			if(visited[shark.row][shark.col]) { // 겹치면
				sharks.remove(sharkNum); // 상어 삭제
				continue;
			}
			
			visited[shark.row][shark.col] = true;
		}
		
	}

	// 맵 안에 있는지 확인
	private static boolean isValid(int nr, int nc) {
		return nr>=0 && nc>=0 && nr<N && nc<N ;
	}
}
