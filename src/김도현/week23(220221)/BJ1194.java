package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ1194 {
    /* 1194. 달이 차오른다, 가자.
    BFS(3차원)
    */
    private static final int[] dr = {-1, 0, 1, 0};
    private static final int[] dc = {0, 1, 0, -1};

    public static boolean isValid(int r, int c, int n, int m) {
        return r >= 0 && r < n && c >= 0 && c < m;
    }

    public static int bfs(char[][] maze, int[][][] map, int n, int m) {
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (maze[i][j] == '0') {
                    q.offer(new int[]{0, i, j});
                    map[0][i][j] = 1;
                }
            }
        }

        int nr, nc, k, d;
        int[] p;
        char next;
        while (!q.isEmpty()) {
            p = q.poll();
            k = p[0];
            d = map[k][p[1]][p[2]];
            for (int i = 0; i < dr.length; i++) {
                nr = p[1] + dr[i]; nc = p[2] + dc[i];
                if (!isValid(nr, nc, n, m) || map[k][nr][nc] != 0) continue;
                next = maze[nr][nc];
                if (next == '.' || next == '0') {
                    q.offer(new int[]{k, nr, nc});
                    map[k][nr][nc] = d + 1;
                } else if (next == '#') {
                    continue;
                } else if (next >= 'a' && next <= 'f') {
                    q.offer(new int[] {k | (1 << next - 'a'), nr, nc});
                    map[k | (1 << next - 'a')][nr][nc] = d + 1;
                } else if (next >= 'A' && next <= 'F') {
                    if (((1 << next - 'A') & k) != 0) q.offer(new int[] {k, nr, nc});
                    map[k][nr][nc] = d + 1;
                } else if (next == '1') {
                    return d;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        char[][] maze = new char[N][];
        int[][][] map = new int[(1 << 6)][N][M];
        for (int i = 0; i < N; i++)
            maze[i] = br.readLine().toCharArray();
        System.out.println(bfs(maze, map, N, M));
    }
}
