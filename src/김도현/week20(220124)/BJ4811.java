package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ4811 {
    /* 4811. 알약
    N <= 30 => 30C15 >= 10^8 => DP
    */
    public static final int MAX_NUM = 30;

    public static void main(String[] args) throws IOException {
        long[][] DP = new long[MAX_NUM+1][MAX_NUM+1];
        for (int i = 1; i < MAX_NUM+1; i++) DP[0][i] = 1;
        for (int i = 1; i < MAX_NUM+1; i++) {
            for (int j = i; j < MAX_NUM+1; j++) {
                DP[i][j] = DP[i-1][j] + DP[i][j-1];
            }
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            int N = Integer.parseInt(br.readLine());
            if (N == 0) break;
            System.out.println(DP[N][N]);
        }
    }
}
