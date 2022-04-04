package week28;

import java.util.Scanner;

/**
 * 문제: Ezreal 여눈부터 가네 ㅈㅈ
 * 링크: https://www.acmicpc.net/problem/20500
 * 
 * 풀이:
 * 
 * 
 * 시간복잡도:
 * 
 * 
 * 풀이에 걸린 시간:
 * 
 *
 */
public class BJ_20500_Ezreal여눈부터가네ㅈㅈ {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		
		if(N==1) {
			System.out.println(0);
			return;
		}
		
		int[][] dp = new int[N+1][3];
		
		dp[2][0]  = 1;
		dp[2][1] = 1;
		
		for (int i =3; i < dp.length; i++) {
			for (int j = 0; j < 3; j++) {
				dp[i][(j+1)%3] += dp[i-1][j];
				dp[i][(j+1)%3] %= 1000000007;
				dp[i][(j+5)%3] += dp[i-1][j];
				dp[i][(j+5)%3] %= 1000000007;
			}
		}
		
		System.out.println(dp[N][0]);
	}

}
