package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ13460 {
    /* 17281. 구슬 탈출2
    시뮬레이션
    [움직이는 최소 회수, 움직이는 회수는 10회 이하] => 4^10 => BFS
     */
    final static int[] dr = {0, 0, -1, 1};
    final static int[] dc = {-1, 1, 0, 0};

    public static int bfs(char[][] map, int bR, int bC, int rR, int rC){
        int result = -1;
        int nbR, nbC, nrR, nrC, dep;
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[] {bR, bC, rR, rC, 0});
        int[] temp;
        boolean red = false, blue = false;

        outer:while (!q.isEmpty()) {
            temp = q.poll();
            dep = temp[4];
            if (dep >= 10) break;
            for (int i = 0; i < dr.length; i++) {
                nbR = temp[0];  nbC = temp[1];
                nrR = temp[2];  nrC = temp[3];
                red = false; blue = false;
                // 빨간 구슬이 동일선상에서 파란 구슬보다 앞에 있는 경우
                if (Math.signum(nrR - nbR) == dr[i] && Math.signum(nrC - nbC) == dc[i]) {
                    // 빨간 구슬을 먼저 움직임
                    while (map[nrR][nrC] == '.') {
                        nrR += dr[i];   nrC += dc[i];
                    }
                    if (map[nrR][nrC] == '#') {
                        nrR -= dr[i]; nrC -= dc[i];
                        map[nrR][nrC] = '#';
                    } else {
                        nrR -= dr[i]; nrC -= dc[i];
                        red = true;
                    }
                    // 파란 구슬을 움직임
                    while (map[nbR][nbC] == '.') {
                        nbR += dr[i];   nbC += dc[i];
                    }
                    if (map[nbR][nbC] == '#') {
                        nbR -= dr[i]; nbC -= dc[i];
                    } else blue = true;
                    map[nrR][nrC] = '.';
                    // 빨간 구슬과 파란 구슬의 구멍 도착 여부를 통해 다음 과정을 수행
                    if (red && !blue) {
                        result = dep + 1;
                        break outer;
                    } else if (!red && !blue)
                        q.offer(new int[] {nbR, nbC, nrR, nrC, dep+1});
                } else {    // 그렇지 않은 경우
                    // 파란 구슬을 먼저 움직임
                    while (map[nbR][nbC] == '.') {
                        nbR += dr[i];   nbC += dc[i];
                    }
                    if (map[nbR][nbC] == '#') {
                        nbR -= dr[i]; nbC -= dc[i];
                        map[nbR][nbC] = '#';
                    } else {
                        nbR -= dr[i]; nbC -= dc[i];
                        blue = true;
                    }
                    // 빨간 구슬을 움직임
                    while (map[nrR][nrC] == '.') {
                        nrR += dr[i];   nrC += dc[i];
                    }
                    if (map[nrR][nrC] == '#') {
                        nrR -= dr[i]; nrC -= dc[i];
                    } else red = true;
                    map[nbR][nbC] = '.';
                    // 빨간 구슬과 파란 구슬의 구멍 도착 여부를 통해 다음 과정을 수행
                    if (red && !blue) {
                        result = dep + 1;
                        break outer;
                    }  else if (!red && !blue)
                        q.offer(new int[] {nbR, nbC, nrR, nrC, dep+1});
                }
            }
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        char[][] map = new char[N][];
        int bR = 0, bC = 0, rR = 0, rC = 0;
        for (int i = 0; i < N; i++) map[i] = br.readLine().toCharArray();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'B') {
                    bR = i; bC = j;
                    map[i][j] = '.';
                } else if(map[i][j] == 'R') {
                    rR = i; rC = j;
                    map[i][j] = '.';
                }
            }
        }
        System.out.println(bfs(map, bR, bC, rR, rC));
    }
}
