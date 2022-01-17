package com.ssafy.algo.study.week19;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;


/**
 * 문제:구슬 탈출 2
 * 링크:https://www.acmicpc.net/problem/13460
 * 
 * 접근:
 * 1. bfs 탐색
 * 2. 기울이는 방향으로 더 앞서 있는 구슬 먼저 굴리기 시작(동일선상에있는 경우 앞쪽 구슬을 먼저 굴려야하므로)
 * 
 * 시간복잡도:
 * 
 * 
 * 소요 시간:
 * 
 * 
 */
public class BJ_13460_구슬탈출2 {
	
	
	// 구슬을 움직인 결과를 나타내는 클래스
	static class Result{
		boolean moved, ended; // 움직임 여부, 종료(구멍으로 들어갔는지) 여부

		public Result(boolean moved, boolean ended) {
			super();
			this.moved = moved;
			this.ended = ended;
		}
		
	}
	
	static char[][] board;
	static int N,M;
	static int[][] delta = {{-1,0,1,0},{0,-1,0,1}}; // 상,좌,하,우
	
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
		
		System.out.println(bfs(R, B));
		
	}
	
	
	private static int bfs(int[] r, int[] b) {
		
		Queue<int[][]> queue = new LinkedList<>();
		queue.add(new int[][]{r,b});
		int cnt=0;
		
		while(!queue.isEmpty()) {
			
			int size = queue.size();
			if(cnt==10) return -1;
			
			while(--size>=0) {
				int[][] positions= queue.poll();
				
				
				for (int d = 0; d < 4; d++) {
					int[] redMarblePosition = positions[0].clone();
					int[] blueMarblePosition = positions[1].clone();
					
					// 어떤 구슬을 먼저 움직일지 선택
					String firstMarbleToMove = decideMarbleToMoveFirst(redMarblePosition, blueMarblePosition, d);
					Result redMarbleResult = null;
					Result blueMarbleResult = null;
					
					if(firstMarbleToMove.equals("redFirst")) { // 빨간 구슬 먼저 움직이는 경우
						redMarbleResult = rollMarble(redMarblePosition, blueMarblePosition, d);
						blueMarbleResult = rollMarble(blueMarblePosition, redMarblePosition, d);
					}else if(firstMarbleToMove.equals("blueFirst")) { // 파란 구슬 먼저 움직이는 경우
						blueMarbleResult = rollMarble(blueMarblePosition, redMarblePosition, d);
						redMarbleResult = rollMarble(redMarblePosition, blueMarblePosition, d);
					}
					
					if(blueMarbleResult.ended) // 파란구슬이 구멍으로 들어간 경우
						continue;
					else if(redMarbleResult.ended) // 빨간구슬만 구멍으로 들어간 경우
						return cnt+1;
					else if(!blueMarbleResult.moved && !redMarbleResult.moved) // 두구슬 모두 움직임이 없는 경우
						continue;
						
					queue.add(new int[][] {redMarblePosition, blueMarblePosition});
				}
			}
			cnt++;
		}
		
		return -1;
	}

	
	// 빨간 구슬이 먼저 움직여야하는지 여부 파악
	private static String decideMarbleToMoveFirst(int[] redMarblePosition, int[] blueMarblePosition, int direction) {
		
		if(direction==0 && redMarblePosition[0]>blueMarblePosition[0]) { // 위로 기울였고 파란 구슬이 더 위쪽에 있는 경우
			return "blueFirst";
		}else if(direction==1 && redMarblePosition[1]>blueMarblePosition[1]) { // 왼쪽으로 기울였고 파란 구슬이 더 왼쪽에 있는 경우
			return "blueFirst";
		}else if(direction==2 && redMarblePosition[0]<blueMarblePosition[0]) { // 아래쪽으로 기울였고 파란 구슬이 더 아래쪽에 있는 경우
			return "blueFirst";
		}else if(direction==3 && redMarblePosition[1]<blueMarblePosition[1]) { // 오른쪽으로 기울였고 파란 구슬이 더 오른쪽에 있는 경우
			return "blueFirst";
		}
		
		return "redFirst";
		
	}
	
	// marblePosition에 위치한 구슬을 d방향으로 굴리기
	private static Result rollMarble( int[] marblePosition, int[] marblePosition2, int d) {
		
		int originalRow = marblePosition[0];
		int originalCol = marblePosition[1];
		int newRow = marblePosition[0] + delta[0][d];
		int newCol = marblePosition[1] + delta[1][d];
		
		// 다음 공간이 빈공간이 아닐때까지 이동
		while(board[newRow][newCol]=='.' && !(marblePosition2[0]==newRow && marblePosition2[1]==newCol)) {
			newRow += delta[0][d];
			newCol += delta[1][d];
		}
		
		if(board[newRow][newCol]=='O') {
			// 구멍을 만난경우 뒤에 움직이는 구슬의 움직임에 영향을 주지 않기 위해 위치를 -1,-1로 변경
			marblePosition[0] = -1;
			marblePosition[1] = -1;
			return new Result(true, true); // 움직이지 않았고 구멍에 빠짐
		}else {
			int currentRow = newRow-delta[0][d];
			int currentCol = newCol-delta[1][d];
			
			marblePosition[0] = currentRow;
			marblePosition[1] = currentCol;
			if(currentRow == originalRow && currentCol == originalCol) // 처음위치와 같으면
				return new Result(false, false); // 움직이지 않았고 구멍에 빠지지 않음
			return new Result(true, false); // 움직였고 구멍에 빠지지 않음
		}
		
	}

}
