package week36;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class BJ_12026_BOJ°Å¸® {
	static final int INF = Integer.MAX_VALUE;
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int N = sc.nextInt();
		sc.nextLine();
		String road = sc.nextLine();
		
		int[] dp = new int[road.length()];
		
		Arrays.fill(dp, INF);
		dp[0]=0;
		HashMap<Character, Integer> map = new HashMap<>();
		map.put('B', 0);
		map.put('O', 1);
		map.put('J', 2);
		char[] BOJ = {'B','O','J'};
		int cnt = 0;
		int k = 0;
		int cost = 0;
		boolean jump = false;
		for (int i = 1; i < N; i++) {
			int j = i;
			char target = BOJ[(map.get(road.charAt(i))+2)%3];
			while(j>0) {
				if(road.charAt(--j)==target && dp[j]!=INF && dp[i]>dp[j] + (i-j) * (i-j)) {
					dp[i] = dp[j] + (i-j) * (i-j);
				}
			}
		}
		System.out.println(dp[N-1]==INF?-1:dp[N-1]);
	}

}
