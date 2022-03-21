package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1012 {
    /* 1012. 유기농 배추
    DFS
    */
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    static int M, N, K, result;
    static boolean[][] map;

    public static boolean isValid(int m, int n, int r, int c) {
        return r >= 0 && r < m && c >= 0 && c < n;
    }

    public static void dfs(int r, int c) {
        map[r][c] = false;
        int nr, nc;
        for (int i = 0; i < 4; i++) {
            nr = r + dr[i];
            nc = c + dc[i];
            if (isValid(M, N, nr, nc) && map[nr][nc])
                dfs(nr, nc);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int X, Y;
        int T = Integer.parseInt(br.readLine());
        for (int i = 0; i < T; i++) {
            result = 0;
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new boolean[M][N];
            for (int j = 0; j < K; j++) {
                st = new StringTokenizer(br.readLine());
                X = Integer.parseInt(st.nextToken());
                Y = Integer.parseInt(st.nextToken());
                map[X][Y] = true;
            }
            for (int j = 0; j < M; j++) {
                for (int k = 0; k < N; k++) {
                    if (map[j][k]) {
                        dfs(j, k);
                        result++;
                    }
                }
            }
            System.out.println(result);
        }
    }
}
