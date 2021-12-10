package com.ssafy.algo.study.week15;

import java.util.Scanner;

public class 동철이의일분배_1865_swExpertAcademy {
	/*
	 * 접근:
	 * 1. dfs로 모든 경우의 수 체크
	 * 2. 가지치기(기존 max 확률보다 낮아지는 경우 더이상 탐색하지 않도록)
	 * 
	 * 
	 * 시간복잡도:
	 * 최악의 경우 O(N!)
	 * 가지치키로 극복
	 * 
	 * 공간복잡도:
	 * ?
	 * 
	 * 풀이에 걸린 시간:
	 * 
	 * 
	 */
	
	static double[][] P;
	static boolean[] used;
	static int N;
	static double maxPossibility;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int T = sc.nextInt();
		
		for (int tc = 1; tc <= T; tc++) {
			N = sc.nextInt();
			
			P = new double[N][N];
			used = new boolean[N];
			maxPossibility = 0;
			
			for (int i = 0; i < N; i++) 
				for (int j = 0; j < N; j++)
					P[i][j] = (sc.nextDouble()/100);
		
			
			dfs(0,1);
			
			System.out.println("#" + tc + " " + String.format("%.6f", maxPossibility*100));
		
		}
	}
	private static void dfs(int cnt, double possibility) {
		
		if(possibility<=maxPossibility) return;
		
		if(cnt==N) {
			maxPossibility = (maxPossibility>possibility)?maxPossibility:possibility;
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if(used[i]) continue;
			used[i] = true;
			dfs(cnt+1, possibility*P[cnt][i]);
			used[i] = false;
		}
	}

}
