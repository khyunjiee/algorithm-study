package baekjoon.oct.oct03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 백준 21611 마법사 상어와 블리자드 [G2]
 *
 * 접근 방식:
 * 구슬 옮기는 방법을 이차원 배열로 시프트 하려다가 큐를 사용했습니다.
 * 블리자드 마법이 실행될 때는 구슬이 터질 위치를 저장해놨다가 큐에 해당 좌표의 값을 삽입하지 않습니다.
 * 그 이후에 큐에 들어있는 값으로 맵을 다시 초기화합니다.
 * 큐의 폭발과 변화도 마찬가지로 맵에 저장될 값들만 큐에 저장해놨다가 맵을 초기화합니다.
 * 큐로 조작하면 공간 복잡도가 많이 나올 것을 알고 있었지만 쉽게 접근하기 위해 사용했습니다.
 *
 * 시간 복잡도:
 * O(N^2 * M)..?
 *
 * 소요 시간:
 * 2시간
 * 42004KB 356ms
 */

public class b21611 {

    static int N, map[][], sharkR, sharkC, result;
    static Queue<Integer> queue;
    // 상 하 좌 우
    static int[][] delta = { {-1, 0}, {1, 0}, {0, -1}, {0, 1} };
    // 좌 하 우 상 인덱스
    static int[] moveIndex = { 2, 1, 3, 0 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new int[N][N];        // 맵 초기화
        sharkR = sharkC = N/2;      // 상어의 위치 좌표
        queue = new LinkedList<>();

        // 맵 입력값 저장
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 상어 위치 -1로 초기화
        map[sharkR][sharkC] = -1;

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int d = Integer.parseInt(st.nextToken()) - 1;           // 블리자드 마법 방향
            int s = Integer.parseInt(st.nextToken());               // 블리자드 마법 거리
            blizard(d, s);                                          // 블리자드 마법으로 파괴할 구슬 제외하고 큐에 삽입
            store();                                                // 큐에 들어있는 값들을 맵에 초기화 (구슬 이동)
            while (bomb()) {                                        // 터질 수 있을 때까지
                store();                                            // 큐에 있는 값들을 맵에 초기화
            }
            change();                                               // 구슬 변화하기
            store();                                                // 변화한 값들 맵에 초기화
        }
        System.out.println(result);
    }

    // 블리자드 마법
    private static void blizard(int d, int s) {
        int r = sharkR, c = sharkC, cnt = 1;
        queue.offer(-1);

        // 마법의 영향을 받는 첫번째 좌표
        int br = r + delta[d][0];
        int bc = c + delta[d][1];

        LOOP: while (isValid(r, c)) {
            for (int i = 0; i < 4; i++) {
                if (i == 2) cnt++;
                int index = moveIndex[i];
                for (int j = 0; j < cnt; j++) {
                    int nr = r + delta[index][0];
                    int nc = c + delta[index][1];

                    if (!isValid(nr, nc)) {
                        break LOOP;
                    } else if (nr == br && nc == bc && s-- > 0) {   // 만약 마법에 영향을 받는다면
                        // 큐에 추가하지 않고 영향받는 좌표 업데이트
                        br += delta[d][0];
                        bc += delta[d][1];
                    } else {    // 마법에 영향을 받지 않는 곳만 큐에 추가
                        queue.offer(map[nr][nc]);
                    }
                    r = nr;
                    c = nc;
                }
            }
            cnt++;
        }
    }

    // 큐에 일렬로 담겨 있는 값들을 맵에 초기화
    private static void store() {
        int[][] temp = new int[N][N];
        int r = sharkR, c = sharkC, cnt = 1;

        LOOP: while (!queue.isEmpty() && isValid(r, c)) {
            for (int i = 0; i < 4; i++) {
                if (i == 2) cnt++;
                int index = moveIndex[i];
                for (int j = 0; j < cnt && !queue.isEmpty(); j++) {
                    int nr = r + delta[index][0];
                    int nc = c + delta[index][1];

                    if (!isValid(nr, nc)) {
                        break LOOP;
                    }
                    temp[r][c] = queue.poll();
                    r = nr;
                    c = nc;
                }
            }
            cnt++;
        }

        map = temp;
        queue.clear();
    }

    // 구슬 폭발
    private static boolean bomb() {
        int r = sharkR, c = sharkC, cnt = 1;
        int num = -1, numCnt = 1;
        boolean isBomb = false; // 폭발 여부

        LOOP: while (isValid(r, c)) {
            for (int i = 0; i < 4; i++) {
                if (i == 2) cnt++;  // 좌하 -> 우상 순이므로, 2 방향 간격으로 cnt+1
                int index = moveIndex[i];

                for (int j = 0; j < cnt; j++) {
                    int nr = r + delta[index][0];
                    int nc = c + delta[index][1];

                    if (!isValid(nr, nc)) {
                        break LOOP;
                    }
                    if (map[nr][nc] != num) {
                        if (numCnt < 4) {   // 4번 연속이 아니라면 numCnt 개수만큼 추가
                            for (int k = 0; k < numCnt; k++) {
                                queue.offer(num);
                            }
                        } else {            // 4번 이상 연속이라면 폭발시키고 result에 추가
                            isBomb = true;
                            result += (num * numCnt);
                        }
                        num = map[nr][nc];
                        numCnt = 1;
                    } else {
                        numCnt++;
                    }
                    r = nr;
                    c = nc;
                }
            }
            cnt++;
        }
        return isBomb;
    }

    // 구슬 변화
    private static void change() {
        int r = sharkR, c = sharkC, cnt = 1;
        int num = -1, numCnt = 1;

        LOOP: while (isValid(r, c)) {
            for (int i = 0; i < 4; i++) {
                if (i == 2) cnt++;
                int index = moveIndex[i];
                for (int j = 0; j < cnt; j++) {
                    int nr = r + delta[index][0];
                    int nc = c + delta[index][1];

                    if (!isValid(nr, nc)) {
                        break LOOP;
                    }
                    if (map[nr][nc] != num) {
                        if (num != -1) {    // 상어가 아니라면 큐에 변화된 구슬 추가
                            queue.offer(numCnt);
                            queue.offer(num);
                        } else {            // 상어라면 큐에 그냥 추가
                            queue.offer(-1);
                        }
                        num = map[nr][nc];
                        numCnt = 1;
                    } else {
                        numCnt++;
                    }
                    r = nr;
                    c = nc;
                }
            }
            cnt++;
        }
    }

    // 좌표 유효성 검사
    private static boolean isValid(int r, int c) {
        return (r >= 0 && r < N && c >= 0 && c < N);
    }
}
