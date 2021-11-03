package com.ssafy.algo.study.week13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 사과나무_20002 {
	/*
	 * 접근:
	 * 완탐 + 누적합
	 * 
	 * 시간복잡도:
	 * O(N^3)...?
	 * 
	 * 
	 * 풀이에 걸린 시간:
	 * 
	 * 
	 */
	
	static int[][] orchard;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(in.readLine());
		StringTokenizer st;
		
		orchard = new int[N][N];	// 과수원
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			for (int j = 0; j < N; j++) {
				if(j>0) orchard[i][j] = Integer.parseInt(st.nextToken()) + orchard[i][j-1];	// 누적합
				else orchard[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int maxProfit = orchard[0][0];	// 최대이익 저장 변수
		
		for (int k = 1; k <= N; k++) {
			for (int i = 0; i <= N-k; i++) {
				for (int j = 0; j <= N-k; j++) {
					maxProfit = Math.max(maxProfit, calcProfit(i,j,k));
				}
			}
		}
		
		System.out.println(maxProfit);
	}
	
	// i행, j열을 왼쪽 위 꼭지점으로 하는 k*k 정사각형안에서 사과를 수확할때 얻는 이익
	private static int calcProfit(int i, int j, int k) {
		
		int profit = 0;
		
		if(j==0) {
			for (int y = i; y < i+k; y++)
				profit += orchard[y][j+k-1];
		}else {
			for (int y = i; y < i+k; y++)
				profit += orchard[y][j+k-1] - orchard[y][j-1];
		}
		
		return profit;
	}

}
