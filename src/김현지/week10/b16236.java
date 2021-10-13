package baekjoon.oct.oct13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/*
백준 16236 아기상어 [G4]

접근 방식:
bfs로 탐색했습니다.
아기 상어의 크기와 같을 때는 이동만 가능하고, 크기보다 작은 것은 먹을 수 있으므로
이동이 가능하다면 큐에 저장하고, 먹을 수 있다면 우선순위 큐에 넣었습니다.
큐가 빌 때까지 bfs 탐색한 후 우선순위 큐에 있는 것을 하나 빼서 먹습니다.
그 후에 큐에 현재 상어의 위치를 저장해 while 문을 반복합니다.

소요 시간:
2시간

23612KB 168ms
 */

public class b16236 {

    // 우선순위 큐에 들어갈 물고기 클래스
    static class Fish implements Comparable<Fish>{
        int r, c, distance;

        public Fish(int r, int c, int distance) {
            this.r = r;
            this.c = c;
            this.distance = distance;
        }

        // 거리가 짧은 것 우선
        // 거리가 같다면 위에 있는 것 우선
        // 행이 같다면 왼쪽에 있는 것 우선
        @Override
        public int compareTo(Fish o) {
            if (this.distance == o.distance) {
                if (this.r == o.r) {
                    return this.c - o.c;
                }
                return this.r - o.r;
            }
            return this.distance - o.distance;
        }
    }

    static int N, map[][];
    // 상 좌 하
    static int[][] delta = { {-1, 0}, {0, -1}, {1, 0}, {0, 1} };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        int sharkR = 0, sharkC = 0;

        // input 값 초기화
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num > 0 && num < 9) {   // 물고기의 위치라면
                    map[i][j] = num;
                } else if (num == 9) {      // 아기 상어의 위치라면
                    sharkR = i;
                    sharkC = j;
                }
            }
        }

        System.out.println(bfs(sharkR, sharkC));
    }

    // 초기 상어의 위치 sharkR, sharkC
    private static int bfs(int sharkR, int sharkC) {
        // 먹을 수 있는 물고기 큐
        PriorityQueue<Fish> eatPQ = new PriorityQueue<>();
        // 이동만 가능한 물고기 큐
        Queue<Fish> queue = new LinkedList<>();
        // 현재 아기 상어의 위치를 저장하고 bfs 탐색 시작
        queue.offer(new Fish(sharkR, sharkC, 0));
        int size = 2, eatCnt = 0, time = 0;

        while (true) {
            int[][] dist = new int[N][N];   // 아기 상어로부터 거리를 누적할 배열

            // 이동 가능한 좌표를 돌면서 먹을 수 있는 물고기가 있는지 bfs 탐색
            while (!queue.isEmpty()) {
                Fish curr = queue.poll();

                for (int d = 0; d < 4; d++) {
                    int nr = curr.r + delta[d][0];
                    int nc = curr.c + delta[d][1];

                    // dist가 0이 아니라면 이미 방문한 곳
                    if (isValid(nr, nc) && dist[nr][nc] == 0 && map[nr][nc] <= size) {
                        dist[nr][nc] = dist[curr.r][curr.c] + 1;        // 거리는 무조건 1씩 증가
                        queue.offer(new Fish(nr, nc, dist[nr][nc]));    // 이동 가능하므로 큐에 저장
                        if (map[nr][nc] > 0 && map[nr][nc] < size) {    // 먹을 수 있으면 우선순위 큐에 저장
                            eatPQ.offer(new Fish(nr, nc, dist[nr][nc]));
                        }
                    }
                }
            }

            // 먹을 수 있는 물고기가 없으면 엄마 상어 호출 (종료)
            if (eatPQ.size() == 0) break;

            // 우선순위 큐에서 하나를 빼면 무조건 최선일 것
            // 시간을 더해주고 map을 0으로 초기화
            Fish eaten = eatPQ.poll();
            time += eaten.distance;
            map[eaten.r][eaten.c] = 0;
            // 먹은 횟수를 1 증가
            eatCnt++;

            // 아기 상어의 크기가 커질 수 있는 경우
            if (eatCnt == size) {
                size++;
                eatCnt = 0;
            }
            eatPQ.clear();  // 다음 위치에서 먹을 수 있는 물고기들의 거리가 달라지니까 clear();
            queue.offer(new Fish(eaten.r, eaten.c, 0)); // 현재 아기 상어가 움직인 위치로 큐에 저장 후 while문 반복
        }

        return time;
    }

    // 좌표 유효성 검사
    private static boolean isValid(int r, int c) {
        return r >= 0 && r < N && c >= 0 && c < N;
    }
}
