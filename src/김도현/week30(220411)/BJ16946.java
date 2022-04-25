package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BJ16946 {
    /* 16946. 벽 부수고 이동하기 4
    DFS
    */
    private static int N, M, count;
    private static final int[] dr = new int[] {-1, 0, 1, 0};
    private static final int[] dc = new int[] {0, 1, 0, -1};
    private static final ArrayList<int[]> temp = new ArrayList<>();

    public static boolean isValid(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < M;
    }

    public static void dfs(char[][] map, boolean[][] visited, int r, int c) {
        visited[r][c] = true;
        count++;
        temp.add(new int[] {r, c});
        int nr, nc;
        for (int i = 0; i < 4; i++) {
            nr = r + dr[i]; nc = c + dc[i];
            if (isValid(nr, nc) && map[nr][nc] == '0' && !visited[nr][nc]) {
                dfs(map, visited, nr, nc);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] move = new int[N][M];
        int[][] label = new int[N][M];
        int K = 1;
        char[][] map = new char[N][];
        for (int i = 0; i < N; i++) {
            map[i] = br.readLine().toCharArray();
        }
        boolean[][] visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == '0' && !visited[i][j]) {
                    count = 0;
                    temp.clear();
                    dfs(map, visited, i, j);
                    for (int[] rc:temp) {
                        move[rc[0]][rc[1]] = count;
                        label[rc[0]][rc[1]] = K;
                    }
                    K++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        int num, nr, nc;
        HashSet<Integer> set = new HashSet<Integer>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == '0') sb.append('0');
                else {
                    num = 1;
                    set.clear();
                    for (int k = 0; k < 4; k++) {
                        nr = i + dr[k]; nc = j + dc[k];
                        if (isValid(nr, nc) && !set.contains(label[nr][nc])) {
                            set.add(label[nr][nc]);
                            num += move[nr][nc];
                        }
                    }
                    sb.append(num % 10);
                }
            }
            sb.append('\n');
        }
        System.out.println(sb);
    }
}
