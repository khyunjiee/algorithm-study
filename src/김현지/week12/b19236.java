package baekjoon.oct.oct27;

import java.util.*;
import java.io.*;

/*
백준 19236 청소년 상어 [G2]

접근 방식:
시뮬레이션이라서 단계별로 처리했습니다.
'상어가 물고기를 먹음 -> 물고기가 이동함 -> 상어가 이동함' 의 반복으로
배열 복사에 주의해야 합니다.
또한 물고기는 번호 순서대로 이동해야 하므로 fish 배열에 1번부터 16번까지의 물고기 정보를 담습니다.

소요 시간:
1시간 30분
 */

public class b19236 {
    static int map[][], sum;
    static Fish[] fishes; //물고기 정보 저장

    // 상, 상좌, 좌, 좌하, 하, 하우, 우, 상우
    static int[] dr = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dc = {0, -1, -1, -1, 0, 1, 1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        map = new int[4][4];        // 물고기 번호 저장 (상어: -1, 빈칸: 0)
        fishes = new Fish[17];      // 물고기 번호별로 상태 저장
        for(int i = 0; i < 4; i++) {
            st  = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < 4; j++) {
                int num = Integer.parseInt(st.nextToken());              // 물고기 번호
                int dir = Integer.parseInt(st.nextToken())-1;            // 물고기 방향
                fishes[num] = new Fish(num, i, j, dir, true);      // 물고기 정보 저장
                map[i][j] = num;                                         // map에 물고기 번호 저장
            }
        }


        int dir = fishes[map[0][0]].dir;    // 초기 상어의 방향
        int eatNum = map[0][0];             // 먹은 물고기 수
        fishes[map[0][0]].alive = false;    // 먹은 물고기 죽이기
        map[0][0] = -1;                     // 상어 위치 -1로 표시

        dfs(0, 0, dir, eatNum);

        System.out.println(sum);
    }

    public static void dfs(int r, int c, int dir, int eatNum) {
        sum = Math.max(sum, eatNum); //이전에 먹었던 물고기 번호 합 max 비교해 sum에 저장

        //map 배열 복사
        int[][] copyMap = getMap();
        //fish 배열 복사
        Fish[] copyFish = getFish();

        // 물고기 이동
        moveFish();

        // 상어 이동
        // 총 4칸이 있으므로 최대 3칸 이동 가능
        for(int i = 1; i < 4; i++) {
            // 다음 좌표 계산
            int nr = r + dr[dir] * i;
            int nc = c + dc[dir] * i;

            // 좌표가 유효하고 물고기가 있으면
            if(isValid(nr, nc) && map[nr][nc] != 0) {
                int eatFish = map[nr][nc];          // 먹을 물고기 번호
                int nd = fishes[eatFish].dir;       // 다음으로 이동할 방향
                map[r][c] = 0;                      // 물고기 자리 빈칸으로 세팅
                map[nr][nc] = -1;                   // 다음 위치로 상어 위치 변경
                fishes[eatFish].alive = false;      // 해당 번호 물고기 죽이기

                // 재귀
                dfs(nr, nc, nd, eatNum+eatFish);

                fishes[eatFish].alive = true;       // 해당 번호 물고기 살리기
                map[r][c] = -1;                     // 다시 현재 위치에 상어 오기
                map[nr][nc] = eatFish;              // 다음 위치에 살릴 물고기 번호 놓기
            }
        }

        // 맵 상태, 물고기 정보 되돌리기
        for(int j = 0; j < map.length; j++)
            map[j] = Arrays.copyOf(copyMap[j], 4);

        for(int i=1; i<=16; i++) {
            Fish f = copyFish[i];
            fishes[i] = new Fish(f.num, f.r, f.c, f.dir, f.alive);
        }
    }

    // fishes 배열 복사 함수
    private static Fish[] getFish() {
        Fish[] copyFish = new Fish[17];

        for(int i = 1; i <= 16; i++){
            Fish f = fishes[i];
            copyFish[i] = new Fish(f.num, f.r, f.c, f.dir, f.alive);
        }
        return copyFish;
    }

    // map 배열 복사 함수
    private static int[][] getMap() {
        int[][] copyMap = new int[4][4];
        for(int i = 0; i < 4; i++) {
            copyMap[i] = Arrays.copyOf(map[i], 4);
        }
        return copyMap;
    }

    //물고기 이동 함수
    private static void moveFish() {
        for(int i = 1; i < 17; i++) { // i는 현재 물고기의 번호
            Fish curr = fishes[i];
            if(!curr.alive) { // 죽은 물고기라면 넘김
                continue;
            }

            int cnt = 0;
            int dir = curr.dir;     // 현재 i번째 물고기의 방향
            int nr = 0, nc = 0;     // 물고기가 이동할 칸의 좌표

            while(cnt < 8) {        // 이동할 수 있는 위치를 찾을때까지 45도 방향 바꾸며 반복
                dir %= 8;           // 방향 나머지 연산
                curr.dir = dir;     // 바꾼 방향 적용

                // 다음 좌표 찾음
                nr = fishes[i].r + dr[dir];
                nc = fishes[i].c + dc[dir];

                // 좌표가 유효하고, 상어가 없다면 이동 가능
                if(isValid(nr, nc) && map[nr][nc] != -1) {
                    // 빈칸
                    if(map[nr][nc] == 0) {
                        map[curr.r][curr.c] = 0;    // 기존 위치 빈칸으로
                        curr.r = nr; curr.c = nc;
                        map[nr][nc] = i;            // 다음 위치에 물고기 번호
                    }
                    // 다른 물고기가 있는 경우
                    else {
                        // 바꿀 물고기 위치 변경
                        int changeFish = fishes[map[nr][nc]].num;
                        fishes[changeFish].r = curr.r; fishes[changeFish].c = curr.c;
                        map[fishes[changeFish].r][fishes[changeFish].c] = changeFish;

                        //현재 물고기 위치 변경
                        curr.r = nr; curr.c = nc;
                        map[nr][nc] = i;
                    }
                    break;
                } else {
                    dir++;
                    cnt++;
                }
            }
        }
    }

    // 좌표 유효성 검사
    private static boolean isValid(int r, int c) {
        return r >= 0 && r < 4 && c >= 0 && c < 4;
    }

}

// 물고기 클래스
class Fish {
    int num, r, c, dir;
    boolean alive;

    Fish(int num, int r, int c, int dir, boolean alive) {
        this.num = num;
        this.r = r;
        this.c = c;
        this.dir = dir;
        this.alive = alive;
    }
}