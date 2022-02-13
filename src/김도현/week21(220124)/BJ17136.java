package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ17136 {
    /* 17136. 색종이 붙이기
    DFS
     */
    public static int MAP_SIZE = 10;
    public static int PAPER_MAX_SIZE = 5;
    public static int PAPER_NUM = 5;
    public static int[][] map;
    public static int[] paper;
    public static int result = Integer.MAX_VALUE;

    public static boolean doFilter(int r, int c, int n) {
        if (r + n > MAP_SIZE || c + n > MAP_SIZE) return false;
        for (int i = r; i < r + n; i++)
            for (int j = c; j < c + n; j++)
                if (map[i][j] == 0) return false;
        return true;
    }

    public static void doPaper(int r, int c, int n, int v) {
        for (int i = r; i < Math.min(r + n, MAP_SIZE); i++)
            for (int j = c; j < Math.min(c + n, MAP_SIZE); j++)
                map[i][j] = v;
    }

    public static void dfs(int r, int c, int cnt) {
        if (r >= MAP_SIZE || c >= MAP_SIZE) {
            result = Math.min(result, cnt);
            return;
        }
        if (map[r][c] == 0) {
            if (c + 1 < MAP_SIZE) dfs(r, c + 1, cnt);
            else dfs(r + 1, 0, cnt);
            return;
        }

        for (int k = 1; k <= PAPER_MAX_SIZE; k++) {
            if (r + k > MAP_SIZE || c + k > MAP_SIZE || paper[k] >= PAPER_NUM) continue;
            if (doFilter(r, c, k)) {
                doPaper(r, c, k, 0);
                paper[k]++;
                if (c + k < MAP_SIZE) dfs(r, c + k, cnt + 1);
                else dfs(r + 1, 0, cnt + 1);
                doPaper(r, c, k, 1);
                paper[k]--;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        map = new int[MAP_SIZE][MAP_SIZE];
        for (int i = 0; i < MAP_SIZE; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < MAP_SIZE; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        paper = new int[PAPER_NUM+1];
        dfs(0, 0, 0);

        if (result == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(result);
    }
}
