package d220523;

import java.io.*;
import java.util.*;

public class BJ_10942 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        int[] num = new int[n];
        boolean[][] dp = new boolean[n][n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < n; i++) {
            dp[i][i] = true;

            if (i == n - 1) {
                break;
            }

            if (num[i] == num[i + 1]) {
                dp[i][i + 1] = true;
            }

        }

        for (int i = 2; i <= n - 1; i++) {
            for (int j = 0; j < n - i; j++) {
                if (num[j] == num[j + i] && dp[j + 1][j + i - 1]) {
                    dp[j][j + i] = true;
                }
            }
        }

        int m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;

            if (dp[from][to]) {
                sb.append(1 + "\n");
            } else {
                sb.append(0 + "\n");
            }
        }

        System.out.println(sb);
    }
}
