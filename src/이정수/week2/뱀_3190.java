package com.ssafy.algoritm.study.week2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 뱀_3190 {
	/*
	 * 접근: 뱀이 최대 움직일 수있는 횟수는 X의 최대값인 10000으로 시간복잡도를 고려할 필요가 없
	 * 습니다. 뱀의 몸이 좌표상에 위치하는 곳을 큐의 형태로 관리하며 매초마다
	 * 머리가 이동하는 좌표값을 큐에 추가하고 꼬리가 따라 이동하는 경우 큐에서 제거하면 됩니다.
	 * 보드에 각 숫자가 의미하는 바는 사과:1, 뱀 몸통 or 경계:3, 이동가능 공간:0 입니다.
	 * 
	 * 시간 복잡도: X가 10000 이하이므로 고려할 필요없음
	 */
	
	static int [][]board;
	static int[] dr = {0,1,0,-1}; //우,하,좌,상
	static int[] dc = {1,0,-1,0}; //우,하,좌,상
	static int direction;
	
	public static void main(String[] args) {
		
		Scanner sc =  new Scanner(System.in);
		int N = sc.nextInt();
		Queue<int[]> queue = new LinkedList<>();
		
		// 보드 생성
		board = new int[N+2][N+2];
		
		for(int i=0;i<N+2;i++) { // 경계 생성
			board[0][i]=3;
			board[N+1][i]=3;
			board[i][0]=3;
			board[i][N+1]=3;
		}
		
		// 사과 심기
		int K = sc.nextInt(), r,c;
		for(int i=0;i<K;i++) {
			r = sc.nextInt();
			c = sc.nextInt();
			board[r][c] = 1;
		}
		
		
		board[1][1] = 3; // 뱀 초기화
		int row = 1;
		int col = 1;
		queue.add(new int[] {1,1});
		direction=0; // 방향 우로 초기화
		
		
		int time=1;
		int L = sc.nextInt(), X=1, C=0;
		
		while(true) {
			if (time==X+1) {//입력대로 움직인 이후 다시 입력 받음
				// 방향전환
				if (C =='L')direction = (direction+3)%4;
				else if(C=='D')direction = (++direction)%4; 
				if(L--!=0) {
					X = sc.nextInt();
					C = sc.next().charAt(0);
				}
			}
			
			
			row += dr[direction];
			col += dc[direction];
			queue.add(new int[] {row, col});
			if(board[row][col]==1) {// 사과일때
				board[row][col]=3;
			}else if(board[row][col]==3){// 이동 불가 공간인 경우
				break;
			}else {// 일반적인 경우 꼬리칸 비우기
				board[row][col]=3;
				int[] tailPosition = queue.poll();
				board[tailPosition[0]][tailPosition[1]]=0;
			}
			
			time++; // 시간 증가
		}
		
		System.out.println(time);
		

	}
	
	static void printBoard() {
		for(int i=0;i<board.length;i++) {
			for(int j=0;j<board[0].length;j++) {
				System.out.print(board[i][j]+" ");
			}
			System.out.println();
		}
	}

}
