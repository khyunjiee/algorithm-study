package com.ssafy.algo.study.week10;

import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JTable.PrintMode;

public class Easy2048_12100 {
	/*
	 * 접근:
	 * 완탐 + 시뮬
	 * 1. 4방향으로 움직임을 5번 반복(dfs)
	 * 2. 각각의 경우에 합쳐지는 것을 수행
	 * 3. 움직이는 방향에 있는 블록부터 움직이기
	 * 4. 충돌이 일어나는 공간을 변수로 나타내기
	 * 
	 * 시간복잡도:
	 * 4^5 * N^2
	 * 
	 * 풀이에 걸린 시간:
	 * 1h 30m
	 * 
	 */
	static int[][] delta = {{-1,1,0,0},{0,0,-1,1}};
	static int N, max=0;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		int[][] board = new int[N][N]; 
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)
				board[i][j] = sc.nextInt();
		}
		int[][] newBoard = moveBlocks(board, 3);
		dfs(0, board);
		System.out.println(max);
	}

	private static void printBoard(int[][] newBoard) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(newBoard[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}

	private static void dfs(int depth, int[][] board) {
		if(depth==5) {
			max = Math.max(max, calcMaxBlock(board));
			return;
		}
		
		for (int d = 0; d < 4; d++) {
			int[][] newBoard = moveBlocks(board, d);
			dfs(depth+1, newBoard);
		}
	}

	private static int calcMaxBlock(int[][] board) {
		int max = 0;
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				max = Math.max(max, board[i][j]);
			}
		}
		return max;
	}

	private static int[][] moveBlocks(int[][] board, int d) {
		int[][] newBoard  = new int[N][N];
		boolean[][] merged = new boolean[N][N];
		for (int i = 0; i < N; i++) 
			newBoard[i] = Arrays.copyOf(board[i], N);
		
		
		if(d==0) {// 위로
			for (int i = 0; i < N; i++) {
				int target = 0;
				for (int j = 0; j < N; j++) {
					if(newBoard[j][i]==0) continue;
					if(j==target) continue;
					if(newBoard[target][i]==0) {
						newBoard[target][i] = newBoard[j][i];
						newBoard[j][i] = 0;
						continue;
					}
					if(newBoard[j][i]==newBoard[target][i]) {// 합치기
						newBoard[target][i] += newBoard[j][i];
						newBoard[j][i] = 0;
						target++;
					}else {
						target++;
						j--;
					}
				}
			}
		}else if(d==1) {// 아래로
			for (int i = 0; i < N; i++) {
				int target = N-1;
				for (int j = N-1; j >=0; j--) {
					if(newBoard[j][i]==0) continue;
					if(j==target) continue;
					if(newBoard[target][i]==0) {
						newBoard[target][i] = newBoard[j][i];
						newBoard[j][i] = 0;
						continue;
					}
					if(newBoard[j][i]==newBoard[target][i]) {// 합치기
						newBoard[target][i] += newBoard[j][i];
						newBoard[j][i] = 0;
						target--;
					}else {
						target--;
						j++;
					}
				}
			}
		}else if(d==2) {// 왼쪽
			for (int i = 0; i < N; i++) {
				int target = 0;
				for (int j = 0; j < N; j++) {
					if(newBoard[i][j]==0) continue;
					if(j==target) continue;
					if(newBoard[i][target]==0) {
						newBoard[i][target] = newBoard[i][j];
						newBoard[i][j] = 0;
						continue;
					}
					if(newBoard[i][j]==newBoard[i][target]) {// 합치기
						newBoard[i][target] += newBoard[i][j];
						newBoard[i][j] = 0;
						target++;
					}else {
						target++;
						j--;
					}
				}
			}
		}else if(d==3) {// 오른쪽
			for (int i = 0; i < N; i++) {
				int target = N-1;
				for (int j = N-1; j >= 0; j--) {
					if(newBoard[i][j]==0) continue;
					if(j==target) continue;
					if(newBoard[i][target]==0) {
						newBoard[i][target] = newBoard[i][j];
						newBoard[i][j] = 0;
						continue;
					}
					if(newBoard[i][j]==newBoard[i][target]) {// 합치기
						newBoard[i][target] += newBoard[i][j];
						newBoard[i][j] = 0;
						target--;
					}else {
						target--;
						j++;
					}
				}
			}
		}
		return newBoard;
	}

	private static boolean isValid(int nr, int nc) {
		return nr>=0 && nc>=0 && nc<N && nr<N;
	}

}
