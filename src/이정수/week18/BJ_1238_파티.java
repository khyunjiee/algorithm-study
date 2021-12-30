package com.ssafy.algo.study.week18;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 문제:파티
 * 링크:https://www.acmicpc.net/problem/1238
 * 
 * 접근:
 * 모든 정점간의 최단경로를 구하기 위해 플로이드 워샬 알고리즘을 사용
 * 각 학생들이 파티에 오고가는 최단 경로를 구해 비교
 * 
 * 
 * 시간복잡도:
 * O(N^3)
 * 
 * 
 * 소요 시간:
 * 20m
 * 
 */
public class BJ_1238_파티 {

	public static void main(String[] args) {
		final int INFINITE = 1000000;
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		int M = sc.nextInt();
		int X = sc.nextInt();
		
		int[][] dist = new int[N+1][N+1];
		
		for (int i = 0; i < dist.length; i++) {
			Arrays.fill(dist[i], INFINITE);
			dist[i][i] = 0;
		}
		
		for (int i = 0; i < M; i++) {
			int from = sc.nextInt();
			int to = sc.nextInt();
			int time = sc.nextInt();
			
			dist[from][to] = time;
		}

		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <= N; i++) {
				for (int j = 1; j <= N; j++) {
					dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
				}
			}
		}
		
		int maxConsumingTime = 0;
		
		for (int i = 1; i < N+1; i++) {
			int totalTime = dist[i][X] + dist[X][i];
			maxConsumingTime = Math.max(maxConsumingTime, totalTime);
		}
		
		System.out.println(maxConsumingTime);
		
	}

}
