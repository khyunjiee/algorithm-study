package com.ssafy.algo.study.week17;

import java.util.Arrays;

/**
 * 문제:보행자 천국
 * 링크:https://programmers.co.kr/learn/courses/30/lessons/1832
 * 
 * 접근:
 * dp + dfs
 * 그냥 dfs를 하면 시간초과 발생
 * 각지점에서 목적지까지 도달하는 방법의 수를 dp테이블로 구성하여 재방문시 사용
 * dp 테이블은 3차원으로 구분하여 움직이는 방향에 따라 구분
 * 
 * 
 * 시간복잡도:
 * ?
 * 
 * 소요 시간:
 * 2h
 * 
 */
public class PG_1832_보행자천국 {
	

	public static void main(String[] args) {
		System.out.println(solution(3,6,new int[][]{{0, 2, 0, 0, 0, 2}, {0, 0, 2, 0, 1, 0}, {1, 0, 0, 2, 2, 0}}));
	}
	
	static int MOD = 20170805;
	static int[][][] dp;
	static int[][] delta = {{0,1},{1,0}};
	static int M,N;
	static int[][] CityMap;
	
    static public int solution(int m, int n, int[][] cityMap) {
    	M = m;
    	N = n;
    	dp = new int[2][m][n]; // dp 테이블
    	CityMap = cityMap;
    	
    	// 미방문을 나타내는 -1로 초기화
    	for (int i = 0; i < 2; i++) {
			for (int j = 0; j < m; j++) {
				Arrays.fill(dp[i][j], -1); 
			}
		}
    	
    	dfs(0,0,-1);
    	
        int answer = dp[0][0][0] + dp[1][0][0];
        return answer;
    }
    
	private static int dfs(int i, int j, int postDir) {
		int cnt = 0;
		if(i==M-1 && j==N-1) {
			return 1;
		}
		
		for (int d = 0; d < delta.length; d++) {
			int newRow = i + delta[0][d];
			int newCol = j + delta[1][d];
			
			if(!isValid(newRow, newCol)) continue;
			
			// 다음 위치가 갈수없는 곳인 경우
			if(CityMap[newRow][newCol]==1) {
				dp[d][i][j] = 0;
				continue;
			}
			
			// 현재 위치가 턴이 불가능한 지역인 경우
			if(CityMap[i][j]==2 && postDir!=d) {
				continue;
			}
			
			// 이미 방문했던 지점인 경우
			if(dp[d][i][j]!=-1) {
				cnt += dp[d][i][j];
				continue;
			}
			
			dp[d][i][j] = dfs(newRow, newCol, d);
			cnt += dp[d][i][j];
		}
		
		return cnt;
	}

	private static boolean isValid(int newRow, int newCol) {
		return newRow>=0 && newRow<M && newCol>=0 && newCol<N;
	} 
}
