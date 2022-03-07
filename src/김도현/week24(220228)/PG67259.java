package pg;

import java.util.*;

public class PG67259 {
    /* Level 3. 경주로 건설
    BFS + 3차원 배열
    */
    public static void main(String[] args) {
        PG67259.Solution test = new PG67259.Solution();
        System.out.println(test.solution(new int[][] {{0,0,0},{0,0,0},{0,0,0}}));
        System.out.println(test.solution(new int[][] {{0,0,0,0,0,0,0,1},{0,0,0,0,0,0,0,0},{0,0,0,0,0,1,0,0},{0,0,0,0,1,0,0,0},{0,0,0,1,0,0,0,1},{0,0,1,0,0,0,1,0},{0,1,0,0,0,1,0,0},{1,0,0,0,0,0,0,0}}));
        System.out.println(test.solution(new int[][] {{0,0,1,0},{0,0,0,0},{0,1,0,1},{1,0,0,0}}));
        System.out.println(test.solution(new int[][] {{0,0,0,0,0,0},{0,1,1,1,1,0},{0,0,1,0,0,0},{1,0,0,1,0,1},{0,1,0,0,0,1},{0,0,0,0,0,0}}));
        System.out.println(test.solution(new int[][] {{0, 0, 1, 0, 1, 1, 0, 0, 0, 0},{0, 0, 0, 0, 1, 0, 1, 1, 0, 1},{1, 0, 0, 0, 0, 1, 1, 0, 1, 0},{0, 0, 0, 0, 0, 0, 1, 0, 0, 0},{0, 0, 0, 0, 1, 0, 1, 0, 1, 1},
                {0, 0, 1, 0, 1, 1, 0, 1, 0, 1},{0, 1, 0, 0, 1, 0, 0, 0, 1, 0},{1, 0, 0, 1, 0, 0, 0, 0, 0, 0},{0, 0, 0, 0, 0, 1, 0, 1, 0, 0},{1, 0, 0, 0, 0, 0, 0, 0, 1, 0}}));
    }

    static final int[] dr = {0,1,0,-1};
    static final int[] dc = {1,0,-1,0};

    static boolean isValid(int r, int c, int n) {
        return r >= 0 && r < n && c >= 0 && c < n;
    }

    static class Solution {
        public int solution(int[][] board) {
            int n = board.length;
            boolean[][][] visited = new boolean[n][n][dr.length];
            visited[0][0][0] = visited[0][0][1] = visited[0][0][2] = visited[0][0][3] = true;
            PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o[3]));

            pq.offer(new int[] {0, 0, -1, 0});
            int[] p;
            int r, c, rc, dist = 0, nr, nc, cost;

            while (!pq.isEmpty()) {
                p = pq.poll();
                r = p[0];   c = p[1];   rc = p[2];  dist = p[3];
                if (r == n-1 && c == n-1) break;
                for (int i = 0; i < dr.length; i++) {
                    nr = r + dr[i]; nc = c + dc[i];
                    if (isValid(nr, nc, n) && board[nr][nc] != 1) {
                        cost = dist + 100;
                        if (rc != -1 && (rc + i) % 2 == 1) cost += 500;
                        if (!visited[nr][nc][i] || cost <= board[nr][nc]) {
                            pq.offer(new int[] {nr, nc, i, cost});
                            visited[nr][nc][i] = true;
                            board[nr][nc] = cost;
                        }
                    }
                }
            }
            return dist;
        }
    }
}
