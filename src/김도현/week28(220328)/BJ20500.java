package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ20500 {
    /* 20500. Ezreal 여눈부터 가네 ㅈㅈ
    DP
    */
    static int MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long[][] DP = new long[N+1][3];
        DP[1][2] = 1;
        for (int i = 2; i <= N; i++) {
            for (int j = 0; j < 3; j++) {
                if (DP[i-1][j] != 0) {
                    DP[i][(j+1)%3] = (DP[i][(j+1)%3] + DP[i-1][j]) % MOD;
                    DP[i][(j+5)%3] = (DP[i][(j+5)%3] + DP[i-1][j]) % MOD;
                }
            }
        }

        System.out.println(DP[N][0]);
    }
}
