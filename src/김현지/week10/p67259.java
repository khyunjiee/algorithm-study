package programmers.oct.oct13;

/*
프로그래머스 Lv.3 67259 경주로 건설

접근 방식:
bfs 문제같은데, dfs로 풀어보고싶어서 dfs로 풀었습니다.
수직, 수평일 때 해당 좌표의 비용을 cost 배열로 관리하고 가지치기를 해주었습니다.

소요 시간:
1시간
 */

public class p67259 {
    public static void main(String[] args) {
        Solution_p67259 solutionP67259 = new Solution_p67259();
        System.out.println(solutionP67259.solution(new int[][] { {0, 0, 0}, {0, 0, 0}, {0, 0, 0} }));
        System.out.println(solutionP67259.solution(new int[][] { {0,0,0,0,0,0,0,1}, {0,0,0,0,0,0,0,0}, {0,0,0,0,0,1,0,0}, {0,0,0,0,1,0,0,0}, {0,0,0,1,0,0,0,1}, {0,0,1,0,0,0,1,0}, {0,1,0,0,0,1,0,0}, {1,0,0,0,0,0,0,0} }));
    }
}

class Solution_p67259 {

    // 하 상 우 좌
    int[][] delta = { {1, 0}, {-1, 0},{0, 1}, {0, -1} };
    int N, min, board[][], cost[][][];

    final int VERTICAL = 0;     // 수직인 경우, (하/상)
    final int HORIZONTAL = 1;   // 수평인 경우, (우/좌)
    final int INF = 987654321;

    public int solution(int[][] board) {
        this.board = board;
        this.N = board.length;
        // 0: 수직일 때 1: 수평일 때
        this.cost = new int[N][N][2];
        this.min = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                cost[i][j][0] = cost[i][j][1] = INF;
            }
        }

        boolean[][] visited = new boolean[N][N];
        cost[0][0][0] = cost[0][0][1] = 0;
        visited[0][0] = true;
        dfs(0, 0, 0, -1, visited);  // -1은 처음 시작 단계
        return min;
    }

    private void dfs(int r, int c, int price, int beforeDir, boolean[][] visited) {
        // beforeDir 가 -1이면 초기이므로 바로 로직 시작
        // 수직 또는 수평이면 가지치기 수행
        if (beforeDir >= 0) {
            // 현재 좌표의 비용이 INF가 아니고, price보다 작다면 이미 최적이므로 가지치기
            if (cost[r][c][beforeDir] != INF && cost[r][c][beforeDir] < price) return;
            // 가지치기가 안되었으면 cost 비용 업데이트
            cost[r][c][beforeDir] = Math.min(cost[r][c][beforeDir], price);
        }

        // min을 초과했으면 가지치기
        if (price >= min) return;
        // 도착지에 도착했으면 min 업데이트
        if (r == N-1 && c == N-1) {
            min = Math.min(min, price);
            return;
        }

        // 사방탐색
        for (int d = 0; d < 4; d++) {
            int nr = r + delta[d][0];
            int nc = c + delta[d][1];

            // 좌표가 유효하고, 한번도 고려하지 않았고, 벽이 아니라면 해당 좌표를 고려함
            if (isValid(nr, nc) && !visited[nr][nc] && board[nr][nc] == 0) {
                visited[nr][nc] = true;     // 방문처리
                if (d < 2) {    // 수직인 경우
                    // 방향이 바뀌었을 때
                    if (beforeDir != VERTICAL && beforeDir != -1) dfs(nr, nc, price+600, VERTICAL, visited);
                    // 그대로일 때
                    else dfs(nr, nc, price+100, VERTICAL, visited);
                } else {        // 수평인 경우
                    // 방향이 바뀌었을 때
                    if (beforeDir != HORIZONTAL && beforeDir != -1) dfs(nr, nc, price+600, HORIZONTAL, visited);
                    // 그대로일 때
                    else dfs(nr, nc, price+100, HORIZONTAL, visited);
                }
                visited[nr][nc] = false;    // 방문처리 초기화
            }
        }
    }

    // 좌표 유효성 검사
    private boolean isValid(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}
