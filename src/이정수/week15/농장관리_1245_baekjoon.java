package com.ssafy.algo.study.week15;

import java.util.Arrays;
import java.util.Scanner;

public class 농장관리_1245_baekjoon {
	/*
	 * 접근:
	 * dfs로 동일한 높이의 인접 격자들의 집합 구하기
	 * dfs 수행중 인접 격자의 높이가 자신보다 높으면 산봉우리가 아님
	 * 모든 격자에 대해 dfs 수행하여 산봉우리 개수 구하기
	 * 
	 * 시간복잡도:
	 * 하나의 격자당 8방 탐색, 전체 격자의 수는 최대 7000개
	 * O(NM)
	 * 
	 * 공간 복잡도:
	 * 최악의 경우 dfs depth 7000
	 * 
	 * 풀이에 걸린 시간:
	 * 30m
	 * 
	 */
	
	static int[][] map; // 농장
	static boolean[][] visited; // 방문 여부 표시
	static int[][] delta = {{-1,1,0,0,1,1,-1,-1},{0,0,-1,1,1,-1,1,-1}}; // 8방 탐색을 위한 2차원 배열
	static int N,M; // 입력값(행, 열)
	
	public static void main(String[] args) {
		
		// 입력 받기
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		
		map = new int[N][M];
		visited = new boolean[N][M];
		
		for (int i = 0; i < N; i++) 
			for (int j = 0; j < M; j++) 
				map[i][j] = sc.nextInt();
		
		int cnt = 0; // 산봉우리 개수
		
		for (int i = 0; i < N; i++) 
			for (int j = 0; j < M; j++) 
				if(!visited[i][j]) { // 아직 방문한 곳이 아닌 경우에만 dfs 수행
					if(dfs(i,j)) 
						cnt++;
				}
		
		System.out.println(cnt);
	}

	private static boolean dfs(int i, int j) {
		
		boolean isTop = true;
		visited[i][j] = true; // 방문 처리
		
		for (int d = 0; d < 8; d++) {
			int newRow = i + delta[0][d];
			int newCol = j + delta[1][d];
			
			if(isValid(newRow, newCol)) { // 방문 가능한 자리면
				
				// 현재 위치보다 높아지는 경우
				if(map[newRow][newCol]>map[i][j]) {
					isTop = false; // 산봉우리가 아님
				}
				
				// 동일 높이인 경우
				if(map[newRow][newCol]==map[i][j] && !visited[newRow][newCol]) {
					if(!dfs(newRow, newCol)) { // 이동해서 산봉우리 여부 체크
						isTop = false;
					}
				}
			}
		}
		
		return isTop;
	}
	
	// 유효한 행, 열 값인지 확인
	private static boolean isValid(int newRow, int newCol) {
		return newRow>=0 && newCol>=0 && newRow <N && newCol<M;
	}
	
}
