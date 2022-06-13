package week35;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제: 연속합
 * 링크: https://www.acmicpc.net/problem/1912
 * 
 * 풀이:
 * dp문제
 * dp[i] = max(0부터 i-1번째 까지 수의 합 + i번째 수의 합 , i번째 수)
 * 
 * 
 * 시간복잡도:
 * O(N)
 * 
 * 
 * 풀이에 걸린 시간:
 * 30m
 *
 */
public class BJ_1912_연속합 {

	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		
		st = new StringTokenizer(br.readLine());
		
		int[] dp = new int[n];
		
		dp[0] = Integer.parseInt(st.nextToken());
		
		int max = dp[0];
		
		for (int i = 1; i < n; i++) {
			int in = Integer.parseInt(st.nextToken());
			dp[i] = Math.max(in, dp[i-1] + in);
			max = Math.max(max, dp[i]);
		}
		
		System.out.println(max);
		
	}

}
