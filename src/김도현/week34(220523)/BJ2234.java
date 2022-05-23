package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BJ2234 {
    /* 2234. 성곽
    DFS
     */
    private static final int[] dr = {0, -1, 0, 1};
    private static final int[] dc = {-1, 0, 1, 0};
    private static int cnt;

    public static boolean isValid(int r, int c, int m, int n) {
        return r >= 0 && r < m && c >= 0 && c < n;
    }

    public static void dfs(int r, int c, boolean[][][] map, int[][] visited, int idx) {
        visited[r][c] = idx;
        cnt++;
        int nr, nc;
        for (int i = 0; i < 4; i++) {
            if (map[r][c][i]) {
                nr = r + dr[i]; nc = c + dc[i];
                if (visited[nr][nc] == 0) dfs(nr, nc, map, visited, idx);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        boolean[][][] map = new boolean[M][N][4];
        int[][] visited = new int[M][N];
        int temp, p;
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                temp = Integer.parseInt(st.nextToken());
                p = 8;
                for (int k = 0; k < 4; k++) {
                    if (temp >= p) temp -= p;
                    else map[i][j][3-k] = true;
                    p /= 2;
                }
            }
        }

        int idx = 0;
        int maxSize = 0;
        HashMap<Integer, Integer> sizeMap = new HashMap<>();
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                if (visited[i][j] == 0) {
                    cnt = 0;
                    dfs(i, j, map, visited, ++idx);
                    maxSize = Math.max(maxSize, cnt);
                    sizeMap.put(idx, cnt);
                }
            }
        }

        int maxSumSize = 0;
        int nr, nc;
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                for (int k = 0; k < 4; k++) {
                    if (!map[i][j][k]) {
                        nr = i + dr[k]; nc = j + dc[k];
                        if (isValid(nr, nc, M, N) && visited[i][j] != visited[nr][nc])
                           maxSumSize = Math.max(maxSumSize, sizeMap.get(visited[i][j]) + sizeMap.get(visited[nr][nc]));
                    }
                }
            }
        }
        System.out.println(idx);
        System.out.println(maxSize);
        System.out.println(maxSumSize);
    }
}
