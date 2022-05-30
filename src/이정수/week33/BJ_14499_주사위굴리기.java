package week33;

import java.util.Scanner;

/**
 * 문제:주사위 굴리기
 * 링크:https://www.acmicpc.net/problem/14499
 * 
 * 접근:
 * 
 * 
 * 시간복잡도:
 * 
 * 
 * 소요 시간:
 * 
 */
public class BJ_14499_주사위굴리기 {
	static class Dice{
		int top, bottom, east, west, north, south;

		public Dice(int top, int bottom, int east, int west, int north, int south) {
			super();
			this.top = top;
			this.bottom = bottom;
			this.east = east;
			this.west = west;
			this.north = north;
			this.south = south;
		}
		
		public void rollDown(){
			int temp  = this.bottom;
			this.bottom = this.south;
			this.south = this.top;
			this.top = this.north;
			this.north = temp;
		}
		
		public void rollUp(){
			int temp  = this.north;
			this.north = this.top;
			this.top = this.south;
			this.south = this.bottom;
			this.bottom = temp;
		}
		
		public void rollLeft(){
			int temp  = this.west;
			this.west = this.top;
			this.top = this.east;
			this.east = this.bottom;
			this.bottom = temp;
		}
		
		public void rollRight(){
			int temp  = this.east;
			this.east = this.top;
			this.top = this.west;
			this.west = this.bottom;
			this.bottom = temp;
		}
		
		public void roll(int direction) {
			switch(direction) {
				case 1:
					this.rollRight();
					break;
				case 2:
					this.rollLeft();
					break;
				case 3:
					this.rollUp();
					break;
				case 4:
					this.rollDown();
					break;
			}
		}
		
		public int mark(int numberOnBoard) {
			if(numberOnBoard==0) {
				return this.bottom;
			}else {
				this.bottom = numberOnBoard;
				return 0;
			}
		}

		@Override
		public String toString() {
			return "Dice [top=" + top + ", bottom=" + bottom + ", east=" + east + ", west=" + west + ", north=" + north
					+ ", south=" + south + "]";
		}
		
	}
	
	static int[][] delta = {{0,0,0,-1,1},{0,1,-1,0,0}};
	static int N,M;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		int x = sc.nextInt();
		int y = sc.nextInt();
		int K = sc.nextInt();
		
		int[][] board = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				board[i][j] = sc.nextInt();
			}
		}
		
		Dice dice = new Dice(0,0,0,0,0,0);
		
		for (int i = 0; i < K; i++) {
			int input = sc.nextInt();
			
			int newX = x + delta[0][input];
			int newY = y + delta[1][input];
			
			if(!isValid(newX, newY)) continue;
			x = newX;
			y = newY;
			dice.roll(input);
			board[x][y]=dice.mark(board[x][y]);
			System.out.println(dice.top);
		}
	}
	private static boolean isValid(int newX, int newY) {
		return newX>=0 && newX<N && newY>=0 && newY<M;
	}

}
