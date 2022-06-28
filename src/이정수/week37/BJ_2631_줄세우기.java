package week37;

import java.io.IOException;
import java.util.Scanner;

/**
 * 문제: 줄 세우기
 * 링크: https://www.acmicpc.net/problem/2631
 * 
 * 풀이:
 * LIS
 * 
 * 
 * 시간복잡도:
 * 
 * 
 * 풀이에 걸린 시간:
 * 
 *
 */
public class BJ_2631_줄세우기 {

	
	public static void main(String[] args) throws IOException {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] children = new int[N];
		
		for (int i = 0; i < N; i++) {
			children[i] = sc.nextInt();
		}
		
		int[] dp = new int[N];
		dp[0] = 1;
		int max = 0;
		for (int i = 1; i < dp.length; i++) {
			dp[i] = 1;
			for (int j = 0; j <= i; j++) {
				if(children[j]<children[i] && dp[j]+1>dp[i]) {
					dp[i] = dp[j] +1;
				}
				max = Math.max(dp[i], max);
			}
		}
		
		System.out.println(N-max);
	}

}
