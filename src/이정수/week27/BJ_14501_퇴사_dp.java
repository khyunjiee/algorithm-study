package week27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 문제: 퇴사
 * 링크: https://www.acmicpc.net/problem/14501
 * 
 * 풀이: 
 * dp
 * dp[i]는  i~N 작업을 포함했을 때 얻는 최대 이익
 * i=N부터 1까지 거꾸로 계산
 * dp는 항상 어렵습니다...
 * 
 * 시간복잡도:
 * O(N)
 * 
 * 풀이에 걸린 시간:
 * 1h
 *
 */
public class BJ_14501_퇴사_dp {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		
		int[] P = new int[N+2];
		int[] T = new int[N+2];
		int[] dp = new int[N+2];
		
		for (int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(in.readLine());
			T[i] = Integer.parseInt(st.nextToken());
			P[i] = Integer.parseInt(st.nextToken());
		}
		
		for (int startDay = N; startDay >0; startDay--) {
			int aDayAfterendDay = startDay + T[startDay];
			
			if(aDayAfterendDay>N+1) {
				dp[startDay] =dp[startDay+1];
				continue;
						
			}
			
			dp[startDay] = Math.max(dp[startDay+1], P[startDay] + dp[aDayAfterendDay]);
		}
		
		System.out.println(dp[1]);
	}

}
