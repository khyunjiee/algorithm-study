package com.ssafy.algo.study.week18;

import java.util.Scanner;

/**
 * 문제:주사위 굴리기
 * 링크:https://www.acmicpc.net/problem/14499
 * 
 * 접근:
 * 1. 주사위를 클래스로 구성
 * 	멤버 변수: 주사위의 위, 아래, 동,서,남,북 방향에 쓰여있는 숫자
 *  메소드: 위,아래, 좌, 우로 굴리기, 주사위 숫자 바꾸기
 * 2. 시뮬 구현
 * 
 * 
 * 시간복잡도:
 * O(K)
 * 
 * 소요 시간:
 * 40m
 * 
 */
public class BJ_14499_주사위굴리기 {
	
	// 주사위 클래스
	static class Dice{
		// 주사위 6면에 쓰인숫자 저장 변수
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
		
		// 굴리기
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
		
		// 주사위가 놓인 칸의 숫자를 입력으로 받아 주사위 바닥의 숫자 변경후 바뀌어야할 칸의 숫자 리턴
		public int mark(int numberOnBoard) {
			if(numberOnBoard==0) {
				return this.bottom;
			}else {
				this.bottom = numberOnBoard;
				return 0;
			}
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
		
		// 주사위 객체 생성(0으로 초기화)
		Dice dice = new Dice(0,0,0,0,0,0);
		
		for (int i = 0; i < K; i++) {
			int input = sc.nextInt();
			
			int newX = x + delta[0][input];
			int newY = y + delta[1][input];
			
			// 주사위를 바깥으로 이동시키는 명령 무시
			if(!isValid(newX, newY)) continue;
			
			x = newX;
			y = newY;
			
			dice.roll(input);	// 주사위 굴리기
			board[x][y]=dice.mark(board[x][y]); // 주사위 바닥면과 칸의 숫자 변경
			System.out.println(dice.top);
		}
	}
	private static boolean isValid(int newX, int newY) {
		return newX>=0 && newX<N && newY>=0 && newY<M;
	}

}
