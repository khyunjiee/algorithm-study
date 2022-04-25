package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ4991 {
    /* 4991. 로봇 청소기
    BFS, TSP
    */
    private final static int INF = 4001;
    private static final int[] dr = new int[] {-1, 0, 1, 0};
    private static final int[] dc = new int[] {0, 1, 0, -1};
    private static final ArrayList<int[]> arrayList = new ArrayList<>();
    private static int h, w;

    public static boolean isValid(int h, int w, int r, int c) {
        return r >= 0 && r < h && c >= 0 && c < w;
    }

    public static int bfs(char[][] map, int s, int e) {
        int[] start = arrayList.get(s);
        int[] end = arrayList.get(e);
        int[][] visited = new int[h][w];
        visited[start[0]][start[1]] = 1;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {start[0], start[1]});

        int[] cur;
        int nr, nc, cnt;
        while(!q.isEmpty()) {
            cur = q.poll();
            cnt = visited[cur[0]][cur[1]];
            for (int i = 0; i < 4; i++) {
                nr = cur[0] + dr[i]; nc = cur[1] + dc[i];
                if (isValid(h, w, nr, nc) && map[nr][nc] != 'x' && visited[nr][nc] == 0) {
                    if (nr == end[0] && nc == end[1]) {
                        return cnt;
                    }
                    visited[nr][nc] = cnt + 1;
                    q.offer(new int[] {nr, nc});
                }
            }
        }
        return -1;
    }

    public static int TSP(int[][] W, int[][] DP, int N, int i, int V) {
        if (V == (1 << N) - 1) {
            if (W[i][0] == 0) return INF;
            return 0;
        }
        if (DP[i][V] < INF) return DP[i][V];
        for (int j = 0; j < N; j++)
            if (((1 << j) & V) == 0 && W[i][j] != 0)
                DP[i][V] = Math.min(DP[i][V], W[i][j] + TSP(W, DP, N, j, V | (1 << j)));
        return DP[i][V];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        outer:while(true) {
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            if (w == 0 && h == 0) break;
            char[][] map = new char[h][];
            for (int i = 0; i < h; i++) map[i] = br.readLine().toCharArray();
            arrayList.clear();

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (map[i][j] == '*') arrayList.add(new int[] {i, j});
                    else if (map[i][j] == 'o')
                        arrayList.add(0, new int[] {i, j});
                }
            }

            int N = arrayList.size();
            int[][] W = new int[N][N];
            int[][] DP = new int[N][(1 << N) - 1];

            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    W[i][j] = bfs(map, i, j);
                    W[j][i] = W[i][j];
                    if (W[i][j] < 0) {
                        System.out.println(-1);
                        continue outer;
                    }
                }
            }

            for (int i = 0; i < N; i++) Arrays.fill(DP[i], INF);
            System.out.println(TSP(W, DP, N, 0, 1));
        }
    }
}
