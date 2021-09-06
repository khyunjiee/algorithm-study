package baekjoon.sep.sep06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 백준 17472 다리만들기2
 *
 * 접근 방법:
 * bfs를 적극 활용했습니다.
 * 섬에 숫자로 체크를 해서 map을 완성하고,
 * map에서 바다를 나타내는 공간에 인접 섬에서부터의 거리가 어떻게 되는지 확인했습니다.
 * 위와 같은 플로우로 map 배열을 업데이트한 후 make, find, union 메소드로 섬을 모두 합쳤습니다.
 */

public class b17472 {
    // 섬 class
    static class Island implements Comparable<Island> {
        int from, to, weight;

        public Island(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Island o) {
            return this.weight-o.weight;
        }
    }

    // make set
    private static void make() {
        parents = new int[cnt+1];

        for (int i = 1; i <= cnt; i++) {
            parents[i] = i;
        }
    }

    // union
    private static void union(int a, int b) {
        int aRoot = find(a);
        int bRoot = find(b);

        if (aRoot != bRoot) parents[aRoot] = bRoot;
    }

    // find parent
    private static int find(int a) {
        if (a == parents[a]) return a;
        return parents[a] = find(parents[a]);
    }

    static int N, M, cnt, parents[], input[][], map[][];    // input은 입력 그대로, map은 섬 위치를 섬 번호로 표시
    // 상 하 좌 우
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        input = new int[N][M];
        map = new int[N][M];

        // input 처리
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                input[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        mapInit();  // 섬 체크
        bfs();
    }

    // map을 bfs로 탐색하면서 우선순위 큐에 거리를 누적해서 저장
    private static void bfs() {
        PriorityQueue<Island> pq = new PriorityQueue<>();
        int distance = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0) {   // 섬이라면
                    for (int d = 0; d < 4; d++) {   // 사방탐색
                        int nr = i + dr[d];
                        int nc = j + dc[d];

                        if (isValid(nr, nc) && map[nr][nc] == 0) {  // 다음 좌표가 바다라면
                            distance = 0;
                            while (isValid(nr, nc)) {   // 거리 계산
                                if (map[nr][nc] != 0) {
                                    if (distance > 1) { // 거리가 1 이상이면 pq에 넣는다
                                        pq.offer(new Island(map[i][j], map[nr][nc], distance));
                                    }
                                    break;  // 섬이 나오면 while문 탈출
                                }
                                // 좌표 이동
                                nr += dr[d];
                                nc += dc[d];
                                distance++; // 거리 계속 누적
                            }
                        }
                    }
                }
            }
        }

        // 최소 도로 수 계산
        calculation(pq);
    }

    // 도로 수 계산
    private static void calculation(PriorityQueue<Island> pq) {
        int result = 0;
        int num = 0;
        Island island;
        make();

        while (!pq.isEmpty()) {
            island = pq.poll();

            // 같은 집합이 아니라면
            if (find(island.from) != find(island.to)) {
                union(island.from, island.to);  // 합친다
                result += island.weight;    // 도로를 만들어서
                num++;  // 연결된 섬 개수 count
            }

            if (num == cnt-1) break;
        }
        System.out.println(num == cnt-1? result: -1);   // num이 섬 개수가 아니라면, 모두 다 연결하지 못했으므로 -1
    }

    // 섬 위치에 맞게 숫자를 저장한다. 바다면 0, 섬이면 1, 2, ...
    private static void mapInit() {
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0 && input[i][j] == 1) {   // 맵에 표시안된 섬이라면
                    map[i][j] = ++cnt;  // 섬에 숫자를 저장
                    queue.offer(new int[]{i, j});

                    // bfs로 섬 면적(인접 부분들)을 찾음
                    while (!queue.isEmpty()) {
                        int[] loc = queue.poll();
                        int r = loc[0];
                        int c = loc[1];

                        for (int d = 0; d < 4; d++) {
                            int nr = r + dr[d];
                            int nc = c + dc[d];

                            if (isValid(nr, nc) && map[nr][nc] == 0 && input[nr][nc] == 1) {
                                map[nr][nc] = cnt;
                                queue.offer(new int[]{nr, nc});
                            }
                        }
                    }
                }
            }
        }
    }

    // 배열 유효성 검사
    private static boolean isValid(int r, int c) {
        if (r>=0 && r<N && c>=0 && c<M) return true;
        return false;
    }
}
