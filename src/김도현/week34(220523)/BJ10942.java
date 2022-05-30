package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ10942 {
    /* 10942. 팰린드롬?
    DP
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        boolean[][] dp = new boolean[N][N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i][i] = true;
        }
        for (int i = 0; i < N-1; i++)
            if (arr[i] == arr[i+1]) dp[i][i+1] = true;
        for (int i = 2; i < N; i++)
            for (int j = 0; j < N-i; j++)
                if (dp[j+1][j+i-1] && arr[j] == arr[j+i]) dp[j][j+i] = true;

        int s, e;
        int M = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());
            e = Integer.parseInt(st.nextToken());
            if (dp[s-1][e-1]) sb.append(1);
            else sb.append(0);
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
