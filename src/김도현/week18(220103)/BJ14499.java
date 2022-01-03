package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14499 {
    /* 14499. 주사위 굴리기
    시뮬레이션
    */
    final static int[] dx = {0, 0, -1, 1};
    final static int[] dy = {1, -1, 0, 0};
    public static void rollDice(int[] dice, int d) {
        int temp = dice[0];
        switch (d) {
            case 1:
                dice[0] = dice[2];
                dice[2] = dice[5];
                dice[5] = dice[3];
                dice[3] = temp;
                break;
            case 2:
                dice[0] = dice[3];
                dice[3] = dice[5];
                dice[5] = dice[2];
                dice[2] = temp;
                break;
            case 3:
                dice[0] = dice[4];
                dice[4] = dice[5];
                dice[5] = dice[1];
                dice[1] = temp;
                break;
            case 4:
                dice[0] = dice[1];
                dice[1] = dice[5];
                dice[5] = dice[4];
                dice[4] = temp;
                break;
            default:
                break;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        int[] cmd = new int[K];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) cmd[i] = Integer.parseInt(st.nextToken());
        int[] dice = new int[6];

        for (int c:cmd) {
            if (x + dx[c-1] < 0 || x + dx[c-1] >= N || y + dy[c-1] < 0 || y + dy[c-1] >= M) continue;
            x += dx[c-1];
            y += dy[c-1];
            rollDice(dice, c);
            if (map[x][y] == 0) {
                map[x][y] = dice[5];
            }else {
                dice[5] = map[x][y];
                map[x][y] = 0;
            }
            System.out.println(dice[0]);
        }
    }
}
