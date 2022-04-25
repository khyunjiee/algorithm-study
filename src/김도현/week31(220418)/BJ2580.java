package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ2580 {
    /* 2580. 스도쿠
    완전탐색
    */
    private static final int MAX_NUM = 9;
    private static final boolean[][] rowCheck = new boolean[MAX_NUM][MAX_NUM+1];
    private static final boolean[][] colCheck = new boolean[MAX_NUM][MAX_NUM+1];
    private static final boolean[][] squareCheck = new boolean[MAX_NUM][MAX_NUM+1];
    private static final ArrayList<int[]> arrayList = new ArrayList<>();

    private static void dfs(int[][] map, int cnt) {
        if (cnt == arrayList.size()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < MAX_NUM; i++) {
                for (int j = 0; j < MAX_NUM; j++) {
                    sb.append(map[i][j]).append(" ");
                }
                sb.append("\n");
            }
            System.out.println(sb);
            System.exit(0);
        }
        int r = arrayList.get(cnt)[0];
        int c = arrayList.get(cnt)[1];
        for (int i = 1; i <= MAX_NUM; i++) {
            if (rowCheck[r][i] || colCheck[c][i] || squareCheck[(r/3)*3+(c/3)][i]) continue;
            rowCheck[r][i] = true;
            colCheck[c][i] = true;
            squareCheck[(r/3)*3+(c/3)][i] = true;
            map[r][c] = i;
            dfs(map, cnt + 1);
            rowCheck[r][i] = false;
            colCheck[c][i] = false;
            squareCheck[(r/3)*3+(c/3)][i] = false;
            map[r][c] = 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int[][] map = new int[MAX_NUM][MAX_NUM];
        for (int i = 0; i < MAX_NUM; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < MAX_NUM; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for (int i = 0; i < MAX_NUM; i++) {
            for (int j = 0; j < MAX_NUM; j++) {
                if (map[i][j] != 0) {
                    rowCheck[i][map[i][j]] = true;
                    colCheck[j][map[i][j]] = true;
                }else arrayList.add(new int[] {i, j});
            }
        }
        int r, c;
        for (int i = 0; i < MAX_NUM; i++) {
            r = (i / 3) * 3; c = (i % 3) * 3;
            for (int j = r; j < r+3; j++)
                for (int k = c; k < c+3; k++)
                    squareCheck[i][map[j][k]] = true;
        }
        dfs(map, 0);
    }
}
