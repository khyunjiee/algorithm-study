package com.ssafy.algo.study.week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 평범한배낭_12865 {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine().trim());
		int N = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());
		
		int[] D = new int[K+1];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(in.readLine().trim());
			int weight = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			for (int w = K; w >=weight ; w--) D[w] = Math.max(D[w], D[w-weight] + value);
		}
		System.out.println(D[K]);
	}
}
