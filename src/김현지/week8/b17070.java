package baekjoon.sep.sep27;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 백준 17070 파이프 옮기기 1 [G5]
 *
 * 접근 방식:
 * DP로 접근했습니다.
 * pipes 배열은 해당 위치에서 가로, 세로, 대각선으로 올 수 있는 경로를 체크합니다.
 * 가로와 세로는 벽을 체크하지 않았지만, 대각선의 경우 왼쪽과 위쪽에 벽이 있는지 체크했습니다.
 *
 * 시간 복잡도:
 * O(N^2)
 *
 * 소요 시간:
 * 40분
 */

public class b17070 {

    static int N, map[][], pipes[][][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N+1][N+1];
        pipes = new int[N+1][N+1][3];   // 가로, 대각선, 세로

        for (int i = 1; i <= N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 초기 파이프는 가로. 중복 덧셈을 방지하기 위해 끝에만 1로 초기화
        pipes[1][2][0] = 1;
        System.out.println(dp());
    }

    private static int dp() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                // 벽은 파이프를 놓을 수 없음
                if (map[i][j] == 1) continue;

                // 가로로 놓기
                pipes[i][j][0] += pipes[i][j-1][0] + pipes[i][j-1][1];
                // 세로로 놓기
                pipes[i][j][2] += pipes[i-1][j][1] + pipes[i-1][j][2];

                // 대각선으로 놓기
                if (map[i-1][j] == 0 && map[i][j-1] == 0) {
                    pipes[i][j][1] += pipes[i-1][j-1][0] + pipes[i-1][j-1][1] + pipes[i-1][j-1][2];
                }
            }
        }

        // 마지막 (N,N) 위치의 가로, 대각선, 세로를 더해서 return
        int[] result = pipes[N][N];
        return result[0] + result[1] + result[2];
    }
}
