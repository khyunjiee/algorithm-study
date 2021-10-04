package programmers.oct.oct04;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 프로그래머스 60063 블록 이동하기 [Lv.3]
 *
 * 접근 방식:
 * bfs로 풀었습니다.
 * Robot 상태를 저장할 클래스를 만들어서,
 * 첫번째 바퀴의 좌표 (r1, c1) 와 두번째 바퀴의 좌표 (r2, c2) 를 기억하고
 * 현재 시간과 가로 또는 세로 타입 여부를 저장했습니다.
 * 그 후 bfs 탐색하면서 N-1, N-1 좌표까지 찾았습니다.
 *
 * 시간 복잡도:
 * O(N^2)
 *
 * 소요 시간:
 * 1시간30분
 */

public class p60063 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution(new int[][]{ {0, 0, 0, 1, 1}, {0, 0, 0, 1, 0}, {0, 1, 0, 1, 1}, {1, 1, 0, 0, 1}, {0, 0, 0, 0, 0} }));
    }
}

class Solution {

    int N;
    int[][] delta = { {1, 0}, {-1, 0}, {0, -1}, {0, 1} };

    public int solution(int[][] board) {
        int answer = 0;
        N = board.length;

        Queue<Robot> queue = new LinkedList<>();
        boolean[][][] visited = new boolean[N][N][2];   // 가로로 방문한 경우와 세로로 방문한 경우를 방문체크
        queue.offer(new Robot(0, 0, 0, 1, 0, 0));   // 초기 로봇의 위치 큐에 추가

        while (!queue.isEmpty()) {
            Robot curr = queue.poll();
            int r1 = curr.r1, c1 = curr.c1, r2 = curr.r2, c2 = curr.c2;
            int time = curr.time, type = curr.type;

            // 방문 처리 -> 두 칸이 모두 true여야만 continue
            if (visited[r1][c1][type] && visited[r2][c2][type]) continue;
            visited[r1][c1][type] = visited[r2][c2][type] = true;

            // 도착지에 도착했다면 break
            if ((r1 == N-1 && c1 == N-1) || (r2 == N-1 && c2 == N-1)){
                answer = time;
                break;
            }

            // 상하좌우
            for (int d = 0; d < 4; d++) {
                int nr1 = r1 + delta[d][0], nc1 = c1 + delta[d][1];
                int nr2 = r2 + delta[d][0], nc2 = c2 + delta[d][1];

                // 다음 위치의 좌표가 유효하고 모두 비어있으면 큐에 추가
                if (isValid(nr1, nc1) && isValid(nr2, nc2) && board[nr1][nc1] == 0 && board[nr2][nc2] == 0) {
                    queue.offer(new Robot(nr1, nc1, nr2, nc2, time+1, type));
                }
            }

            // 현재 로봇이 세로로 놓여져있다면
            if (type == 1) {
                // 왼쪽으로 90도 회전
                // ㅣ 에서 왼쪽 두 칸이 비어있어야 함
                if (c1-1 >= 0 && board[r1][c1-1] == 0 && board[r2][c2-1] == 0) {
                    queue.offer(new Robot(r1, c1, r1, c1-1, time+1, 0));    // 두번째 바퀴가 반시계로 회전
                    queue.offer(new Robot(r2, c2, r2, c2-1, time+1, 0));    // 첫번째 바퀴가 시계로 회전
                }
                // 오른쪽으로 90도 회전
                // ㅣ 에서 오른쪽 두 칸이 비어있어야 함
                if (c1+1 < N && board[r1][c1+1] == 0 && board[r2][c2+1] == 0) {
                    queue.offer(new Robot(r1, c1, r1, c1+1, time+1, 0));    // 두번째 바퀴가 시계로 회전
                    queue.offer(new Robot(r2, c2, r2, c2+1, time+1, 0));    // 첫번째 바퀴가 반시계로 회전
                }
            }
            // 현재 로봇이 가로로 놓여져있다면
            else {
                // 위쪽으로 90도 회전
                // ㅡ 에서 위쪽 두 칸이 비어있어야 함
                if (r1-1 >= 0 && board[r1-1][c1] == 0 && board[r2-1][c2] == 0) {
                    queue.offer(new Robot(r1, c1, r1-1, c1, time+1, 1));    // 두번째 바퀴가 반시계로 회전
                    queue.offer(new Robot(r2, c2, r2-1, c2, time+1, 1));    // 첫번째 바퀴가 시계로 회전
                }
                // 아래쪽으로 90도 회전
                // ㅡ 에서 아래쪽 두 칸이 비어있어야 함
                if (r1+1 < N && board[r1+1][c1] == 0 && board[r2+1][c2] == 0) {
                    queue.offer(new Robot(r1, c1, r1+1, c1, time+1, 1));    // 두번째 바퀴가 시계로 회전
                    queue.offer(new Robot(r2, c2, r2+1, c2, time+1, 1));    // 첫번째 바퀴가 반시계로 회전
                }
            }
        }

        return answer;
    }

    // 좌표 유효성 검사
    private boolean isValid(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}

class Robot {
    // 첫번째 바퀴의 좌표 r1, c1
    // 두번째 바퀴의 좌표 r2, c2
    // 현재 로봇 위치에서의 시간
    // type 0: 가로, 1: 세로
    int r1, c1, r2, c2, time, type;

    public Robot(int r1, int c1, int r2, int c2, int time, int type) {
        this.r1 = r1;
        this.c1 = c1;
        this.r2 = r2;
        this.c2 = c2;
        this.time = time;
        this.type = type;
    }
}