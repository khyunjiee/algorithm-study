package com.ssafy.algo.study.week16;

import java.util.Arrays;
import java.util.Scanner;

public class BJ_1520 {
	/*
	 * 문제: 내리막 길 
	 * 링크: https://www.acmicpc.net/problem/1520
	 * 
	 * 접근:
	 * dfs 에서 가지치기를 아무리 시도 해도 시간초과가 나서 구글링을 참고했습니다.
	 * dp 테이블을 만들어서 이미 방문한 지점에서 목적지까지 가는 길의 개수를 표시하여 재사용했습니다.
	 * 
	 * 시간복잡도:
	 * ?
	 * 
	 * 풀이에 걸린 시간:
	 * 45m
	 * 
	 */
	
	static int[][] map, dp;
	static boolean[][] visited;
	static int N,M;
	static int[][] delta = {{-1,1,0,0},{0,0,-1,1}};
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		M = sc.nextInt();
		N = sc.nextInt();
		
		map = new int[M][N];
		visited = new boolean[M][N];
		dp = new int[M][N];
		
		for (int i = 0; i < dp.length; i++) {
			Arrays.fill(dp[i], -1); // 방문하지 않은 곳 -1로 표시(dp 테이블 초기화)
		}
		
		for (int i = 0; i < M; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}
		
		System.out.println(dfs(0,0));
	}

	private static int dfs(int i, int j) {
		int cnt = 0;
		
		if(i==M-1 && j==N-1) {
			return 1;
		}
		
		for (int d = 0; d < 4; d++) {
			int newRow = i + delta[0][d];
			int newCol = j + delta[1][d];
			
			if(isValid(newRow, newCol) && (map[i][j]>map[newRow][newCol])) {
				if(dp[newRow][newCol]>-1) { // 이미 방문한적 있는 격자이면
					cnt += dp[newRow][newCol]; // dp 테이블 값 사용
					continue;
				}
				cnt += dfs(newRow, newCol);
			}
		}
		
		dp[i][j] = cnt; // i행 j열 격자에서 목적지에 도착하는 길의 가짓수 표시
		
		return cnt;
	}

	private static boolean isValid(int newRow, int newCol) {
		return newRow>=0 && newCol>=0 && newRow<M && newCol<N;
	}

}
