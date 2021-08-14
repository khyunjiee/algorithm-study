package baekjoon.aug.aug14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 백준 11559 Puyo Puyo
 *
 * 접근 방법:
 * 뿌요뿌요 상태가 담긴 배열 map을 마지막 행부터 위로 올라가면서 탐색합니다.
 * '.' 가 아닌, 색깔 캐릭터가 나오면 얼마나 연결되어있는 블록인지 사방탐색을 진행하는 search() 메소드를 실행합니다.
 * search 메소드는 재귀로 구성되어있으며, 현재 위치에서 사방을 탐색하고 만약 같은 색깔 블록이 존재한다면 visit을 true로 설정하고 위치를 그 블록으로 바꾸어서 재귀를 호출합니다.
 * 같은 블록을 만날 때마다 puyoCnt를 1씩 더해주고 더 이상 찾을 것이 없으면 game() 메소드로 다시 리턴됩니다.
 * 만약 puyoCnt 가 4를 넘는다면 터져야 하는 블록이므로 블록을 터뜨릴 수 있다는 의미로 isPuyo를 true로 바꿔줍니다.
 * 그 후에 순회가 끝내면 해당 블록들은 없어져야하므로 visit이 true로 설정된 블록들을 '.' 으로 바꾸어줍니다.
 * visit을 초기화해야하므로 없어져야하는 블록은 isDown 배열에 위치에 맞게 true로 설정합니다.
 * 그 후 puyoCnt가 4를 넘지 않아도 visit 배열과 puyoCnt 는 초기화시켜야 합니다.
 * 모든 순회가 끝나면 한 번의 연쇄가 가능한 것이므로 isPuyo가 true 상태라면 연쇄가 성공한 것입니다.
 * 그런 경우는 result를 1 추가해주며 만약 순회를 다 했음에도 isPuyo가 false라면 게임이 종료됩니다.
 * 순회가 끝나면 터진 블록들을 없애줘야하므로 downColumn() 메소드를 실행합니다.
 * downColumn 메소드에서는 없애야하는 블록이 아닌 것을 열을 기준으로 아래 행부터 큐에 삽입합니다.
 * 그 후 큐가 빌 때까지 map에 누적해 블록을 없애주고, 위쪽에 업데이트되지 않은 부분들은 '.' 으로 초기화합니다.
 *
 * 시간 복잡도:
 * 시뮬레이터 문제는 시간 복잡도를 체크하기 어려운 것 같습니다.
 *
 */

public class b11559 {

    // 결과값, 행(12), 열(6), 연속해서 있는 블록 개수
    static int result, N, M, puyoCnt;
    static char[][] map;                    // 뿌요뿌요 맵
    static boolean visit[][], isDown[][];   // 블록 방문 여부, 없애야할 블록 여부

    // 상 하 좌 우
    static int[] dr = { 1, -1, 0, 0 };
    static int[] dc = { 0, 0, -1, 1 };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        result = 0;

        // 변수 초기화
        N = 12;
        M = 6;
        puyoCnt = 0;
        map = new char[N][M];
        visit = new boolean[N][M];
        isDown = new boolean[N][M];

        // 배열 초기화
        for (int i = 0; i < N; ++i) {
            map[i] = br.readLine().toCharArray();
        }

        // 게임 시작
        game();

        System.out.println(result);
    }

    // 뿌요뿌요 게임
    private static void game() {
        // 연쇄가 없을 때까지 반복
        while (true) {
            boolean isPuyo = false; // 연쇄 여부
            for (int i = N-1; i >= 0; --i) {    // 배열 순회
                for (int j = 0; j < M; j++) {
                    if (map[i][j] == '.') continue; // 색깔 블록이 아니면 continue
                    search(map[i][j], i, j);    // 색깔 블록이라면 얼만큼 같은 블록이 이어져있는지 탐색

                    // 만약 같은 블록이 4개 이상이면
                    if (puyoCnt >= 4) {
                        isPuyo = true;  // 연쇄를 true로 설정
                        init();         // map의 해당 블록들을 . 으로 설정
                    }
                    // 초기화
                    visit = new boolean[N][M];
                    puyoCnt = 0;
                }
            }
            if (!isPuyo) break; // 연쇄가 끝났으면 break
            else {
                result++;   // 1연쇄 추가
            }
            downColumn();   // 터진 블록들 없애는 처리
        }
    }

    // 같은 블록 찾기
    private static void search(char color, int r, int c) {
        for (int d = 0; d < 4; ++d) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (isValid(nr, nc) && !visit[nr][nc] && map[nr][nc] == color) {
                visit[nr][nc] = true;
                puyoCnt++;
                search(color, nr, nc);
            }
        }
    }

    // 터진 블록 . 처리
    private static void init() {
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                if (visit[i][j]) {          // 터져야하는 블록이라면
                    isDown[i][j] = true;    // 해당 위치 블록은 탐색이 끝나면 없애야 한다
                    map[i][j] = '.';
                }
            }
        }
    }

    // 터진 블록들 없애고 위의 블록들이 내려와야 함
    private static void downColumn() {
        Queue<Character> queue = new LinkedList<>();

        for (int j = 0; j < M; ++j) {
            for (int i = N-1; i >= 0; --i) {
                // 터지지 않는 블록을 아래 행부터 차례로 큐에 삽입
                if (!isDown[i][j]) queue.offer(map[i][j]);
            }

            // 아래 행부터 저장
            int index = N-1;
            while (!queue.isEmpty()) {
                map[index--][j] = queue.poll(); // 큐가 빌때까지 index를 줄여가면서 값을 저장
            }

            // 남은 인덱스에서 0행까지 . 저장
            for (int i = index; i >= 0; --i) {
                map[i][j] = '.';
            }
        }

        // 초기화
        isDown = new boolean[N][M];
    }

    // 맵 밖으로 나갔는지 좌표 유효성 체크
    private static boolean isValid(int r, int c) {
        if (r<0 || r>=N || c<0 || c>=M) return false;
        return true;
    }

}
