package com.ssafy.algo.study.week21;

import java.util.Scanner;

/**
 * 문제:알약
 * 링크:https://www.acmicpc.net/problem/4811
 * 
 * 접근:
 * dp는 항상 어렵습니다...
 * dp[w][h] = dp[w-1][h] + dp[w][h-1]
 * 
 * 
 * 시간복잡도:
 * 
 * 
 * 소요 시간:
 * 
 */
public class BJ_4811_알약 {
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		long dp[][] = new long[31][31];
		
		
		for (int h = 0; h < 31; h++) {
			for (int w = 0; w < 31; w++) {
				if(h>w) continue; // h는 w보다 클수 없음
				if(h==0) dp[w][h] = 1; // 초기 조건
				else dp[w][h] = dp[w-1][h] + dp[w][h-1];
			}
		}
		
		int N = sc.nextInt();
		
		while(N!=0) {
			System.out.println(dp[N][N]);
			N = sc.nextInt();
		}
		
	}

}
