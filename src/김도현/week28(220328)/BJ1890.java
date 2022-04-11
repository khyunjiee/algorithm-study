package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1890 {
    /* 1890. 점프
    DP
    */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        long[][] DP = new long[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }

        DP[0][0] = 1;
        map[N-1][N-1] = 1;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (DP[i][j] != 0 ) {
                    A:if (i + map[i][j] < N) {
                        for (int k = i + 1; k <= i + map[i][j]; k++)
                            if (map[k][j] == 0) break A;
                        DP[i + map[i][j]][j] += DP[i][j];
                    }
                    B:if (j + map[i][j] < N) {
                        for (int k = j + 1; k <= j + map[i][j]; k++)
                            if (map[i][k] == 0) break B;
                        DP[i][j + map[i][j]] += DP[i][j];
                    }
                }
//                System.out.println(i+" "+j);
//                for (int k = 0; k < N; k++) {
//                    for (int l = 0; l < N; l++) {
//                        System.out.print(DP[k][l]+" ");
//                    }
//                    System.out.println();
//                }
//                System.out.println();
            }
        }
        System.out.println(DP[N-1][N-1]);
    }

}
