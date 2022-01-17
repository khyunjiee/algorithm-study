package com.ssafy.algo.study.week20;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 문제:사회망 서비스(SNS)
 * 링크:https://www.acmicpc.net/problem/2533
 * 
 * 접근:
 * 
 * 
 * 시간복잡도:
 * 
 * 
 * 소요 시간:
 * 
 */
public class BJ_2533_SNS {
	
	
	static List<Integer>[] graph;
	static int N;
	static int[][] dp;
	static boolean[] visited;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(in.readLine());
		graph = new ArrayList[N+1];
		
		for(int i = 1; i < N+1; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for (int i = 0; i < N-1; i++) {
			
			StringTokenizer st = new StringTokenizer(in.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			graph[u].add(v);
			graph[v].add(u);
		}
		
		visited = new boolean[N+1];
		dp = new int[N+1][2];
		dfs(1);
		System.out.println(Math.min(dp[1][0], dp[1][1]));
	}
	
	private static void dfs(int i) {
		visited[i] = true;
		dp[i][0] = 0;
		dp[i][1] = 1;
		
		for(int child: graph[i]) {
			if(visited[child]) continue;
			dfs(child);
			dp[i][0] += dp[child][1];
			dp[i][1] += Math.min(dp[child][0], dp[child][1]);
		}
	}

}
