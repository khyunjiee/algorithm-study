package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ10026 {
    /* 10026. 적록색약
    DFS
    */
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static boolean isValid(int n, int r, int c) {
        return r >= 0 && r < n && c >= 0 && c < n;
    }

    public static void dfs(char[][] map, char color, int n, int r, int c, boolean isNormal) {
        map[r][c] = 0;
        int nr, nc;
        for (int i = 0; i < 4; i++) {
            nr = r + dr[i];
            nc = c + dc[i];
            if (!isNormal && (color == 'R' || color == 'G')) {
                if (isValid(n, nr, nc) && (map[nr][nc] == 'R' || map[nr][nc] == 'G'))
                    dfs(map, color, n, nr, nc, isNormal);
            }else {
                if (isValid(n, nr, nc) && map[nr][nc] == color)
                    dfs(map, color, n, nr, nc, isNormal);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[][] map = new char[N][N];
        for (int i = 0; i < N; i++) map[i] = br.readLine().toCharArray();
        char[][] pMap = new char[N][N];
        char[][] qMap = new char[N][N];
        for (int i = 0; i < N; i++) {
            pMap[i] = map[i].clone();
            qMap[i] = map[i].clone();
        }
        int pResult = 0;
        int qResult = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (pMap[i][j] != 0) {
                    dfs(pMap, pMap[i][j], N, i, j, true);
                    pResult++;
                }
                if (qMap[i][j] != 0) {
                    dfs(qMap, qMap[i][j], N, i, j, false);
                    qResult++;
                }
            }
        }

        System.out.println(pResult+" "+qResult);
    }
}
