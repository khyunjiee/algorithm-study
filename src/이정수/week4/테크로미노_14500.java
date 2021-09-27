package com.ssafy.algo.study.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 테크로미노_14500 {
	/*
	 * 아이디어:
	 * 2 1 8 4 4
	 * 1. 모든 테토로미노 모양을 하드 코딩으로 만들어서 대조
	 * 2. 90도씩 네번 회전 하면서 좌우 반전하여 확인(츄라이)
	 * 
	 * 
	 * 시간복잡도:
	 * O(NM)
	 * 250,000 * 4*4*2 = 8,000,000
	 * 
	 * 
	 * 풀이에 걸린 시간:
	 * 1h 35m
	 * 
	 * 
	 */
	
	static int[][] map;
	static int max = 0;
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine().trim());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine().trim());
			for (int j = 0; j < M; j++) map[i][j] = Integer.parseInt(st.nextToken());
		}
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				
				// 4*1
				if(j+3<M) {
					// ****
					int[][] dr = {{0,0,0,0}}; 
					int[][] dc = {{0,1,2,3}};
					sum(i, j, dr, dc);
				}
				
				// 1*4
				if(i+3<N) {
					// *
					// *
					// *
					// *
					int[][] dr = {{0,1,2,3}};
					int[][] dc = {{0,0,0,0}}; 
					sum(i, j, dr, dc);
				}
				
				// 2*2
				if(i+1<N && j+1<M) {
					// **
					// **
					int[][] dr = {{0, 0, 1, 1}};
					int[][] dc = {{0, 1, 0, 1}}; 
					sum(i, j, dr, dc);
				}
				
				// 2*3
				if(i+2<N && j+1<M) {
					// *      *    **    **
					// *      *    *      *
					// **    **    *      *
					int[][] dr = {{0, 1, 2, 2}, {0, 1, 2, 2}, {0, 1, 2, 0}, {0, 1, 2, 0}};
					int[][] dc = {{0, 0, 0, 1}, {1, 1, 1, 0}, {0, 0, 0, 1}, {1, 1, 1, 0}}; 
					sum(i, j, dr, dc);
					
					// *      *
					// **    **
					//  *    *
					if(i+2<N && j+1<M) {
						dr = new int[][]{{0,1,1,2},{0,1,1,2}};
						dc = new int[][]{{0,0,1,1},{1,1,0,0}}; 
						sum(i, j, dr, dc);
					}
					
					// *     *
					// **   **
					// *     *
					dr = new int[][]{{0,1,1,2},{0,1,1,2}};
					dc = new int[][]{{0,0,1,0},{1,0,1,1}}; 
					sum(i, j, dr, dc);
				}
				
				// 3*2
				if(i+1<N && j+2<M) {
					// *      ***      *    ***  
					// ***    *      ***      *
					int[][] dr = {{0,1,1,1},{0,0,0,1},{1,1,1,0},{0,0,0,1}};
					int[][] dc = {{0,0,1,2},{0,1,2,0},{0,1,2,2},{0,1,2,2}}; 
					sum(i, j, dr, dc);
					
					//  **    **
					// **      **
					dr = new int[][]{{1,1,0,0},{0,0,1,1}};
					dc = new int[][]{{0,1,1,2},{0,1,1,2}}; 
					sum(i, j, dr, dc);
					
					//   *   ***
					//  ***   *
					dr = new int[][]{{0,1,1,1},{0,0,0,1}};
					dc = new int[][]{{1,0,1,2},{0,1,2,1}}; 
					sum(i, j, dr, dc);
				}
			}
		}
		System.out.println(max);
		
	}

	private static void sum(int i, int j, int[][] dr, int[][] dc) {
		for (int x = 0; x < dr.length; x++) {
			int temp  = 0;
			for (int k = 0; k < 4; k++) {
				int nr = i + dr[x][k];
				int nc = j + dc[x][k];
				temp += map[nr][nc];
			}
			max = max<temp?temp:max; // 최대값 갱신
		}
	}
}
