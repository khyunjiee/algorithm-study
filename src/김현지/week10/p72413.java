package programmers.oct.oct13;

import java.util.Arrays;

/*
프로그래머스 Lv.3 합승 택시 요금

접근 방식:
플로이드-와샬 알고리즘으로 풀었습니다.
와샬 알고리즘으로 s~a, s~b 까지의 최단 경로를 도출한 후에
합승하는 경우를 체크하기 위해 다시 경유지를 돌면서 result를 업데이트 해줬습니다.

시간 복잡도:
O(N^3)

소요 시간:
50분
 */

public class p72413 {
    public static void main(String[] args) {
        Solution_p72413 solution = new Solution_p72413();
        System.out.println(solution.solution(6, 4, 6, 2,
                new int[][]{ {4, 1, 10}, {3, 5, 24}, {5, 6, 2}, {3, 1, 41}, {5, 1, 24}, {4, 6, 50}, {2, 4, 66}, {2, 3, 22}, {1, 6, 25} }));
    }
}

class Solution_p72413 {

    // 지점 최대 개수 * 최대 요금 + 1
    private final int INF = 200 * 100000 + 1;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        // 최단 경로를 저장할 dp 테이블
        int[][] floyd = new int[n+1][n+1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(floyd[i], INF);
            floyd[i][i] = 0;    // 자기 자신으로는 못간다
        }

        // dp 테이블에 간선 정보 업데이트
        for (int[] edge: fares) {
            int start = edge[0], end = edge[1], weight = edge[2];
            floyd[start][end] = floyd[end][start] = weight;
        }

        for (int k = 1; k <= n; k++) {              // 경유지
            for (int i = 1; i <= n; i++) {          // 출발지
                if (i == k) continue;
                for (int j = 1; j <= n; j++) {      // 도착지
                    // 경유지를 거치는게 더 빠르다면, 업데이트
                    floyd[i][j] = Math.min(floyd[i][j], floyd[i][k] + floyd[k][j]);
                }
            }
        }
        // 합승 없이 따로 가는 경우
        int result = floyd[s][a] + floyd[s][b];
        // 합승하는 경우
        for (int k = 1; k <= n; k++) {
            // 1 ~ n 까지의 경유지를 또 고려해본다
            result = Math.min(result, floyd[s][k] + floyd[k][a] + floyd[k][b]);
        }
        return result;
    }
}