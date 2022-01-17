package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class bj16234 {
    /* 16234. 인구이동
    시뮬레이션,DFS
     */
    public static final int[] dr = {-1, 0, 1, 0};
    public static final int[] dc = {0, -1, 0, 1};

    public static boolean isValid(int r, int c, int n) {
        return r >= 0 && r < n && c >= 0 && c < n;
    }

    public static void printMap(int[][] map, int n){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(map[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public static int simulate(int n, int lef, int rig, int[][] map) {
        int result = 0;
        while(true) {
            boolean[][] visited = new boolean[n][n];    // 날마다 방문 표시
            int count = 0;  // 연합의 수
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (visited[i][j]) continue;
                    ArrayList<int[]> united = new ArrayList<>();    // 연합인 나라들의 좌표
                    int sum = dfs(n, lef, rig, map, visited, united, i, j, 0);
                    if (united.size() > 1) {    // 연합이 존재하면
                        int val = sum / united.size();
                        for (int[] contry:united) map[contry[0]][contry[1]] = val;
                        count++;
                    }
                }
            }
//            printMap(map, n);
//            System.out.println(count);
            if (count == 0) break;  // 인구 이동X
            result++;   // 날짜 카운트
        }
        return result;
    }

    public static int dfs(int n, int lef, int rig, int[][] map, boolean[][] visited, ArrayList<int[]> united,
                          int r, int c, int sum) {
        visited[r][c] = true;
        united.add(new int[] {r, c});
        sum = map[r][c];
        for (int i = 0; i < dr.length; i++) {
            int nr = r + dr[i];
            int nc = c + dc[i];
            if (isValid(nr, nc, n) && !visited[nr][nc]) {
                int diff = Math.abs(map[r][c] - map[nr][nc]);
                if (diff >= lef && diff <= rig) sum += dfs(n, lef, rig, map, visited, united, nr, nc, sum);
            }
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
        System.out.println(simulate(N, L, R, map));
    }
}
