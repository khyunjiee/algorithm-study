package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class BJ9207 {
    /* 9207. 페크 솔리테어
    DFS
     */
    private static final int MAX_ROW = 5;
    private static final int MAX_COL = 9;
    private static final int MAX_NUM = 8;
    private static final int[] dr = {-1, 0, 1, 0};
    private static final int[] dc = {0, 1, 0, -1};
    private static int minPin, minMove;

    public static boolean isValid(int r, int c) {
        return r >= 0 && r < MAX_ROW && c >= 0 && c < MAX_COL;
    }
    public static void dfs(char[][] map, HashSet<Integer> set, int cnt) {
        int r, c, br, bc, nr, nc;
        List<Integer> list = new ArrayList<>(set);
        for (int s:list) {
            r = s / MAX_COL;
            c = s % MAX_COL;
            for (int i = 0; i < 4; i++) {
                br = r + dr[i]; bc = c + dc[i];
                nr = r + 2 * dr[i]; nc = c + 2 * dc[i];
                if (isValid(nr, nc) && map[nr][nc] == '.' && map[br][bc] == 'o') {
                    map[r][c] = '.'; map[br][bc] = '.'; map[nr][nc] = 'o';
                    set.remove(r * MAX_COL + c);
                    set.remove(br * MAX_COL + bc);
                    set.add(nr * MAX_COL + nc);
                    dfs(map, set, cnt+1);
                    map[r][c] = 'o'; map[br][bc] = 'o'; map[nr][nc] = '.';
                    set.remove(nr * MAX_COL + nc);
                    set.add(br * MAX_COL + bc);
                    set.add(r * MAX_COL + c);
                }
            }
        }
        int pin = 0;
        for (int j = 0; j < MAX_ROW; j++)
            for (int k = 0; k < MAX_COL; k++)
                if (map[j][k] == 'o') pin++;

        if (pin < minPin) {
            minPin = pin;
            minMove = cnt;
        }else if (pin == minPin && cnt < minMove) minMove = cnt;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        char[][] map = new char[MAX_ROW][MAX_COL];
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < N; i++) {
            set.clear();
            for (int j = 0; j < MAX_ROW; j++) map[j] = br.readLine().toCharArray();
            for (int j = 0; j < MAX_ROW; j++)
                for (int k = 0; k < MAX_COL; k++)
                    if (map[j][k] == 'o') set.add(j * MAX_COL + k);
            minPin = MAX_NUM;
            minMove = Integer.MAX_VALUE;
            dfs(map, set, 0);
            br.readLine();
            System.out.println(minPin + " " + minMove);
        }
    }
}
