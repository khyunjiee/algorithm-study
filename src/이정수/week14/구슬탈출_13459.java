package com.ssafy.algo.study.week14;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 구슬탈출_13459 {
	/*
	 * 접근:
	 * bfs + 시뮬
	 * 
	 * 풀이 실패...
	 * 
	 * 시간복잡도:
	 * 
	 * 
	 * 풀이에 걸린 시간:
	 * 
	 * 
	 */
	
	static char[][] board;
	static int N,M;
	static int[][] delta = {{-1,0,1,0},{0,-1,0,1}};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		
		int[] R=null,B=null;
		
		board = new char[N][M];
		
		for (int i = 0; i < N; i++) {
			char[] line = sc.next().toCharArray();
			for (int j = 0; j < M; j++) {
				if(line[j]=='R') {
					R = new int[] {i,j};
					board[i][j] = '.';
				}else if(line[j]=='B') {
					B = new int[] {i,j};
					board[i][j] = '.';
				}else {
					board[i][j] = line[j];
				}
			}
		}
		
		bfs(R, B);
		
		System.out.println(Arrays.deepToString(board));
	}
	
	
	private static void bfs(int[] r, int[] b) {
		Queue<int[][]> q = new LinkedList<>();
		q.add(new int[][]{r,b});
		int cnt=0;
		while(!q.isEmpty()) {
			int size = q.size();
			while(--size>=0) {
				int[][] positions= q.poll();
				
				for (int d = 0; d < 4; d++) {
					// 보드에 표시
					int[] redMarble = positions[0];
					int[] blueMarble = positions[1];
					
					board[redMarble[0]][redMarble[1]] = 'R';
					board[blueMarble[0]][blueMarble[1]] = 'B';
					boolean didRedMarbleMoved =true;
					boolean didBlueMarbleMoved =true;
					
					// 한칸씩 움직이기
					while(didRedMarbleMoved || didBlueMarbleMoved) {
						// 빨간 구슬 움직이기
						
						// 파란 구슬 움직이기
						
						// 파란 구슬이 구멍에 빠진 경우
						
						// 빨간 구슬이 구멍에 빠진 경우
					}
					
					
				}
				
				
			}
			cnt++;
		}
	}


	private static void rollMarbles(int[][] positions, int d) {
		
		
		
	}

}
