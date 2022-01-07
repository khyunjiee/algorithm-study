package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ1238 {
    /* 1238. 파티
    N개의 node에서 최단거리 => Floyd-Warshall 알고리즘
    */
    final static int MAX_N = 1000;
    final static int MAX_T = 100;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int[][] DP = new int[N+1][N+1];
        for (int i = 0; i < N + 1; i++) {
            Arrays.fill(DP[i], MAX_N * MAX_T);
            DP[i][i] = Integer.MIN_VALUE;
        }
        int A, B, T, result = Integer.MIN_VALUE;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken());
            B = Integer.parseInt(st.nextToken());
            T = Integer.parseInt(st.nextToken());
            DP[A][B] = T;
        }
        // Floyd-Warshall 알고리즘
        for (int k = 1; k < N+1; k++) {
            for (int i = 1; i < N+1; i++) {
                if (i == k) continue;
                for (int j = 1; j < N+1; j++) {
                    if (i == j || j == k) continue;
                    DP[i][j] = Math.min(DP[i][j], DP[i][k] + DP[k][j]);
                }
            }
        }
//        for (int i = 1; i < N + 1; i++) {
//            for (int j = 1; j < N + 1; j++)
//                System.out.print(DP[i][j] + " ");
//            System.out.println();
//        }
        // 대칭원소 합의 최댓값
        for (int i = 1; i < N + 1; i++) result = Math.max(result, DP[i][X] + DP[X][i]);
        System.out.println(result);
    }
}
