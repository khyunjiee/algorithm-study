package baekjoon.aug.aug28;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 백준 11657 타임머신
 *
 * 접근 방법:
 * 벨만-포드 알고리즘을 활용해서 풀었습니다.
 * 간선의 가중치가 음수인 것도 있기 때문에, 다익스선트라 알고리즘을 활용하지 못합니다.
 * 그래서 벨만-포드 알고리즘을 활용했고, 이 문제에서는 오버플로를 유의해서 int 대신 long을 사용해야 합니다.
 *
 * 시간 복잡도:
 * O(N*E) 정점 * 간
 *
 */

public class b11657 {

    static class Edge {
        int from, to, weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    static int N, M;
    static long min[], INFINITE;
    static Edge[] edges;

    public static void main(String[] args) throws IOException {
        /* input */
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        INFINITE = Long.MAX_VALUE;

        min = new long[N+1];
        edges = new Edge[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edges[i] = new Edge(from, to, weight);
        }
        Arrays.fill(min, INFINITE); // 최단 거리를 저장할 min 배열에 MAX_VALUE 를 저장
        /* input */

        // bellmanford 메소드 리턴이 true인 경우 : 음수 cycle이 존재
        if (!bellmanford(1)) {  // 음수 cycle이 존재하지 않는 경우
            // 갈 수 없는 정점 (INFINITE) 에는 -1 출력, 나머지는 최단 경로를 출력
            for(int i = 2; i <= N; i++) {
                System.out.println((min[i] == INFINITE? -1: min[i]));
            }
        } else {    // 음수 cycle이 존재하는 경우
            System.out.println(-1); // 이 경우가 문제에서 말하는 시간을 무한히 오래 전으로 되돌릴 수 있는 경우
        }
    }

    // 벨만-포드 알고리즘
    // true: 음수 cycle 존재, false: 음수 cycle 없음
    private static boolean bellmanford(int start) {
        min[start] = 0; // 시작 정점은 최단 경로가 무조건 0이다
        boolean result = false; // 사이클 존재 여부

        // 정점 수만큼 반복
        LOOP: for(int i = 1; i <= N; i++) {
            // 모든 간선을 확인
            for(Edge e: edges) {
                // 만약 출발 정점의 min이 INFINITE 상태라면 다음 간선을 확인
                if (min[e.from] == INFINITE) continue;
                // 만약 현재 도착 정점의 min이 해당 간선을 이용하는 것보다 크다면
                // to의 min값 업데이트
                if (min[e.to] > min[e.from] + e.weight) {
                    min[e.to] = min[e.from] + e.weight;
                    // 음수 사이클이 없다면 N-1 번으로 최단 거리를 찾을 수 있음
                    // N번째에 최단 거리를 찾았다면 음수 사이클이 있는 것
                    if (i == N) {
                        result = true;
                        break LOOP;
                    }
                }
            }
        }
        return result;
    }
}
