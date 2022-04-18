package src.backjoon.스도쿠;

import java.io.*;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/backjoon/스도쿠/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = 9;
        map = new int[N][N];
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine()," ");
            for(int j=0;j<N;j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0,0);
    }

    public static void dfs(int row, int col) {
        if (col == N) {
            dfs(row + 1, 0);
            return;
        }
        if (row == N) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    sb.append(map[i][j]).append(' ');
                }
                sb.append('\n');
            }
            System.out.println(sb);
            System.exit(0);
        }

        if (map[row][col] == 0) {
            for (int i = 1; i <= 9; i++) {
                if (check(row, col, i)) {
                    map[row][col] = i;
                    dfs(row, col + 1);
                    map[row][col] = 0;
                }
            }
            return;
        }
        dfs(row, col + 1);
    }

    public static boolean check(int row, int col, int value) {
        for (int i = 0; i < N; i++) {
            if (map[row][i] == value) {
                return false;
            }
        }

        for (int i = 0; i < N; i++) {
            if (map[i][col] == value) {
                return false;
            }
        }

        int maxRow = (row / 3) * 3;
        int maxCol = (col / 3) * 3;

        for (int i = maxRow; i < maxRow + 3; i++) {
            for (int j = maxCol; j < maxCol + 3; j++) {
                if (map[i][j] == value) {
                    return false;
                }
            }
        }

        return true;
    }
}
