package com.ssafy.algo.study.week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 파일합치기_11066 {
	/*
	 * 처음 접근:
	 * 1. 덧셈에 중복적으로 계산되는 값을 최소로 해야함
	 * 2. k가 짝수일 경우 순서대로 2개씩 묶어서 더하기
	 * 3. k가 홀수 일 경우 세개의 수를 더하는 일이 발생
	 * 		두수를 더한 합이 가장 작은 수 옆에 나머지 하나의 수를 
	 * 		더해야한다.
	 * => 로직 오류
	 * 
	 * 새로운 접근:
	 * 어려워서 구글링했습니다.
	 * DP로 해결합니다.
	 * dp[i][j]는 i번부터 j번 인덱스까지의 최소합입니다.
	 * i==j일때 dp[i][j] = 0
	 * i+1==j일때 dp[i][j] = SUM(chapters[i~j])
	 * 점화식
	 * dp[i][j] = MIN(dp[i][k]+dp[k+1][j]+SUM(chapters[i~j]), (단, i < k < j)
	 * 가능한 모든 k에 대해 시도했을때 최소값이 dp[i][j]입니다.
	 * 
	 * 시간복잡도:
	 * O(K^3)
	 * 
	 * 풀이에 걸린 시간:
	 * 
	 * 
	 */
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(in.readLine());
		
		for (int tc = 1; tc <= T; tc++) {
			int K = Integer.parseInt(in.readLine());
			int[] chapters = new int[K+1];
			int[] sum = new int[K+1]; // 누적합 저장 배열
			StringTokenizer st = new StringTokenizer(in.readLine());
			for (int i = 1; i <= K; i++) {
				chapters[i] = Integer.parseInt(st.nextToken());
				sum[i] = sum[i-1] + chapters[i];
			}
			
			int[][] dp = new int[K+1][K+1];
			for (int size = 1; size <= K; size++) { // 묶음의 크기(size+1)
				for (int from = 1; from <= K-size; from++) { // 묶음의 시작 위치
					int to  = from + size;
					dp[from][to] = Integer.MAX_VALUE;
					for (int divide = from; divide < to; divide++) {
						dp[from][to] = Math.min(dp[from][to], dp[from][divide] + dp[divide+1][to] + (sum[to]-sum[from-1]));
					}
				}
			}
			
			System.out.println(dp[1][K]);
		}
	}
}