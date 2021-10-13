package com.ssafy.algo.study.week10;

import java.util.Arrays;

public class 합승택시요금_72413 {
	/*
	 * 접근:
	 * 브루트 포스 
	 * 1. 플로이드 워셜로 모든 정점간 최단 거리 구하기
	 * 2. 선택 가능한 모든 중간 지점 선택
	 * 3. 선택한 중간 지점까지 가는데 걸리는 비용에 A와 B에 이르는 최단거리 더하기
	 * 
	 * 시간복잡도:
	 * O(N^3)
	 * N<=200
	 * 
	 * 
	 * 풀이에 걸린 시간:
	 * 30m
	 * 
	 */

	public static void main(String[] args) {
		System.out.println(solution(6, 4, 6, 2, new int[][] {{4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25}}));
	}
	
	public static int solution(int n, int s, int a, int b, int[][] fares) {
		// 1. 플로이드 워셜로 모든 정점간 최단 거리 구하기
		int[][] dp = new int [n+1][n+1];
		for (int i = 0; i < dp.length; i++) {
			Arrays.fill(dp[i], 20000000);
			dp[i][i] = 0;
		}
		
		for (int i = 0; i < fares.length; i++) {
			int c = fares[i][0];
			int d = fares[i][1];
			int f = fares[i][2];
			
			dp[c][d] = dp[d][c] = f;
		}
		
		// 플로이드 워샬
		for (int k = 1; k <= n; k++) {
			for (int i = 1; i <= n; i++) {
				if(i==k) continue;
				for (int j = 1; j <= n; j++) {
					if(i==j||j==k) continue;
					dp[i][j] = dp[j][i] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
				}
			}
		}
		
		
		// 2. 선택 가능한 모든 중간 지점 선택
		int answer = Integer.MAX_VALUE;
		for (int mid = 1; mid <= n; mid++) {
			// 3. 선택한 중간 지점까지 가는데 걸리는 비용에 A와 B에 이르는 최단거리 더하기
			answer = Math.min(answer, dp[s][mid] + dp[mid][a] + dp[mid][b]);
		}
		
        return answer;
    }
}
