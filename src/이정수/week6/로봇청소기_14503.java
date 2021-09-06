package com.sssafy.algo.study.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 로봇청소기_14503 {
	/*
	 * 접근: 
	 * 시뮬 문제입니다. 요구사항을 각각의 메소드로 만들어 구현합니다.
	 * 
	 * 시간복잡도:
	 */
	
	static int[][] map;
	static int r,c,d, N,M;
	static int[] dr = {-1,0,1,0}; // 상 우 하 좌
	static int[] dc = {0,1,0,-1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][M];
		
		st = new StringTokenizer(in.readLine());
		
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < M; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		int turn = 0;
		int cnt=0;
		while(true) {
			if(turn==4) { // 네 방향 모두 청소가 이미 되어있는 경우
				if(!isBackwardPossible()) break; // 뒤쪽 방향이 벽 or 맵밖인 경우
				back();
				turn = 0;
			}
			
			// 1. 현재 위치 청소
			if(map[r][c]==0) {
				map[r][c] = 2;
				cnt++;
			}
			
			// 2. 인접칸 탐색
			if(isLeftZero()) {
				turnAndForward();
				turn = 0;
			}else {// 청소할 공간이 없다면
				turn();
				turn++; // 회전수 증가
			}
		}
		
		System.out.println(cnt);
		
	}
	private static void turn() {
		d = (d+3)%4;
	}
	
	private static void back() {
		d = (d+2)%4; // 방향 반전
		r += dr[d];
		c += dc[d];
		d = (d+2)%4; // 방향 반전
	}
	
	private static boolean isBackwardPossible() {
		int nd = (d+2)%4;
		int nr = r + dr[nd];
		int nc = c + dc[nd];
		if(nr>0 && nc>0 && nr<N && nc<M && (map[nr][nc]==0 || map[nr][nc]==2)) return false;
		return true;
	}
	
	private static void turnAndForward() {
		d = (d+3)%4;
		r += dr[d];
		c += dc[d];
	}
	
	private static boolean isLeftZero() {
		int nd = (d+3)%4;
		int nr = r + dr[nd];
		int nc = c + dc[nd];
		if(nr>0 && nc>0 && nr<N && nc<M && map[nr][nc]==0) return true;
		return false;
	}
}
