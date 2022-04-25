package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ11049 {
    /* 11049. 행렬 곱셈 순서
    DP
    */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] dims = new int[N][2];
        int[][] DP = new int[N][N];
        for (int i = 0; i < N; i++) Arrays.fill(DP[i], Integer.MAX_VALUE);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            dims[i][0] = Integer.parseInt(st.nextToken());
            dims[i][1] = Integer.parseInt(st.nextToken());
            DP[i][i] = 0;
        }

        for (int i = 0; i < N-1; i++) DP[i][i+1] = dims[i][0] * dims[i][1] * dims[i+1][1];

        for (int i = N-3; i >= 0; i--) {
            for (int j = i + 2; j < N; j++) {
                for (int k = i; k < j; k++) {
                    DP[i][j] = Math.min(DP[i][j], DP[i][k] + DP[k+1][j] + dims[i][0] * dims[k][1] * dims[j][1]);
                }
            }
        }

        System.out.println(DP[0][N-1]);
    }
}
