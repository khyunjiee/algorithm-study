package baekjoon.nov.nov10;

import java.util.*;
import java.io.*;

/*
백준 13459 구슬찾기 [G3]

접근 방식:
시뮬레이션으로 풀었습니다.
한 가지 고려해야할 점은 빈칸으로 두 개의 구슬이 동시에 있을 때 위치 조정입니다.
그 외에는 단계별로 풀었습니다.

소요 시간:
1시간 30분
 */

public class b13459 {
    static int N, M, result;
    static char[][] map;
    static int delta[][] = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        int redR = 0, redC = 0, blueR = 0, blueC = 0;
        // 맵 초기화 & 구슬들 위치 저장
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            map[i] = st.nextToken().toCharArray();
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'R') {
                    redR = i; redC = j;
                    map[i][j]='.';
                } else if (map[i][j] == 'B') {
                    blueR = i; blueC = j;
                    map[i][j]='.';
                }
            }
        }

        // 게임 해보기
        dfs(0, redR, redC, blueR, blueC);
        // 결과값 출력
        System.out.println(result);
    }

    // cnt : 기울인 횟수
    // redR, redC : 빨간 구슬 좌표
    // blueR, blueC : 파랑 구슬 좌표
    static void dfs(int cnt, int redR, int redC, int blueR, int blueC) {
        // 정답일 경우
        if (result == 1)
            return;
        // 횟수를 모두 사용했을 경우
        if (cnt == 10) {
            return;
        }
        // 사방으로 기울여보기
        for (int d = 0; d < 4; d++) {
            int[] red = { redR, redC };
            int[] blue = { blueR, blueC };
            // 구슬 이동
            slide(red, d);
            slide(blue, d);

            // 0: 실패, 1: 성공, 2: 빈칸
            int balls = ballCheck(red, blue);
            if (balls == 0) {
                continue;
            } else if (balls == 1) {
                result = 1;
                return;
            } else {
                // 구슬이 한 칸에 겹쳐있을 때 한 칸 씩 들어가도록 조정
                if (red[0] == blue[0] && red[1] == blue[1]) {
                    if (d == 0) {
                        if (redR < blueR) blue[0] += 1;
                        else red[0] += 1;
                    }else if(d == 2) {
                        if (redR > blueR) blue[0] -= 1;
                        else red[0] -= 1;
                    }else if(d == 1) {
                        if (redC > blueC) blue[1] -= 1;
                        else red[1] -= 1;
                    }
                    else {
                        if (redC < blueC) blue[1] += 1;
                        else red[1] += 1;
                    }
                }
            }
            dfs(cnt + 1, red[0], red[1], blue[0], blue[1]);
        }
    }

    // 구슬 위치 체크
    static int ballCheck(int red[], int blue[]) {
        int r = red[0], c = red[1], r2 = blue[0], c2 = blue[1];
        if ((map[r][c] == 'O' && map[r2][c2] == 'O') || (map[r][c] != 'O' && map[r2][c2] == 'O')) return 0;
        else if (map[r][c] == 'O' && map[r2][c2] != 'O') return 1;
        else if (map[r][c] != 'O' && map[r2][c2] != 'O') return 2;
        return 0;
    }

    // 기울여보기
    static void slide(int ball[], int d) {
        int r = ball[0], c = ball[1];
        // d 방향으로 쭉 기울여보기
        while (true) {
            r += delta[d][0];
            c += delta[d][1];
            if (map[r][c] == '#') {
                r -= delta[d][0];
                c -= delta[d][1];
                break;
            } else if (map[r][c] == 'O') {
                break;
            }
        }
        ball[0] = r;
        ball[1] = c;
    }
}