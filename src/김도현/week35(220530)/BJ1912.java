package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1912 {
    /* 1912. 연속합
    DP
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] dp = new int[N];
        int ans = -1000;
        for (int i = 0; i < N; i++) dp[i] = Integer.parseInt(st.nextToken());
        for (int i = 1; i < N; i++) dp[i] = Math.max(dp[i], dp[i] + dp[i-1]);
        for (int i = 0; i < N; i++) ans = Math.max(ans, dp[i]);
        System.out.println(ans);
    }
}
