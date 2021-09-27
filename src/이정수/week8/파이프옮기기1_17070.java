package com.ssafy.algo.study.week8;

import java.util.Scanner;

public class 파이프옮기기1_17070 {
	/*
	 * 접근: 
	 * 단순 dfs 사용시 매경우 평균 2.3개의 옵션이 존재하고 우측으로 최대 15 아래로 최대 16 이동하므로
	 *  2.3^32 --> 시간초과
	 *  
	 *  dp, 각 방향 파이프마다 테이블 한개씩 해서 총 3개의 테이블로 풀어야함
	 *  
	 *  "파이프가 벽을 긁으면 안 된다. 즉, 파이프는 항상 빈 칸만 차지해야 한다."라는 조건 때문에 고생했습니다.
	 *  대각선으로 갈때 아래와 옆공간을 침범한다는 것에 주의해야 합니다.
	 *  
	 *  시간복잡도: O(N^2)
	 * 
	 */
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] map  = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++)	map[i][j] = sc.nextInt();
		}
		// 0: -, 1: \, 2: l
		int[][][] D  = new int[3][N][N];
		D[0][0][1] = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 2; j < N; j++) {
				if(map[i][j] == 1) continue;
				
				// 수평 파이프로 연결되는 경우(수평-수평 연결 + 대각선-수평 연결)
				D[0][i][j] = D[0][i][j-1] + D[1][i][j-1];
				
				// 대각선 파이프로 연결되는 경우(수평-수평 연결 + 대각선-수평 연결 + 수직-수직 연결)
				if(i-1>=0 && map[i-1][j]!=1 && map[i][j-1]!=1) D[1][i][j] = D[0][i-1][j-1] + D[1][i-1][j-1] + D[2][i-1][j-1];
				
				// 수직 파이프로 연결되는 경우(대각선-수평 연결 + 수직-수직 연결)
				if(i-1>=0) D[2][i][j] = D[1][i-1][j] + D[2][i-1][j];
			}
		}
		
		System.out.println(D[0][N-1][N-1] + D[1][N-1][N-1] + D[2][N-1][N-1]); // 모든 방법 더하기
	}

}
