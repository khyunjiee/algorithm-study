package baekjoon.sep.sep06;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 백준 1162 도로포장
 *
 * 접근 방법:
 * 다익스트라를 활용해서 풀었습니다.
 * 도로 포장을 한 경우와 도로 포장을 하지 않은 경우를 분기처리해서
 * 알고리즘을 적용했습니다.
 *
 * 주의할 점:
 * 가중치가 최대 1,000,000 이므로 int형으로 선언하면 절대 통과할 수 없습니다.
 * long 으로 선언해서 해결했습니다.
 *
 * 소요 시간:
 * 1시간
 */

public class b1162 {
    // 도로 클래스
    static class Load implements Comparable<Load> {
        int no, pack;
        long distance;

        // input에서 연결된 도로를 저장할 객체의 생성자
        public Load(int no, long distance) {
            this.no = no;
            this.distance = distance;
        }

        // 다익스트라에서 포장 여부도 포함해서 pq에 넣을 객체의 생성자
        public Load(int no, int pack, long distance) {
            this.no = no;
            this.pack = pack;
            this.distance = distance;
        }

        @Override
        public int compareTo(Load o) {
            return Long.compare(this.distance, o.distance);
        }
    }

    static int N, M, K;
    static long INFINITE, min[][];
    static ArrayList<ArrayList<Load>> adjList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        INFINITE = Long.MAX_VALUE;

        min = new long[N+1][K+1]; // K개의 도로를 포장했을 때, N까지의 최단경로
        adjList = new ArrayList<>();

        // min 배열과 adjList 초기화
        for (int i = 0; i <= N; i++) {
            Arrays.fill(min[i], INFINITE);
            adjList.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            // 양방향이므로 양쪽에 모두 distance 추가
            adjList.get(from).add(new Load(to, distance));
            adjList.get(to).add(new Load(from, distance));
        }

        dijkstra(1);
        // 포장된 경우 중 가장 작은 것을 택한다.
        long result = INFINITE;
        for(int i = 0; i <= K; i++) {
            result = (result > min[N][i])? min[N][i]: result;
        }

        System.out.println(result);
    }

    // 다익스트라
    static void dijkstra(int start) {
        // 1번 도로부터 시작한다.
        PriorityQueue<Load> pq = new PriorityQueue<>();
        min[start][0] = 0;
        pq.offer(new Load(start, 0, min[start][0]));    // 현재 포장 상태는 0

        while (!pq.isEmpty()) {
            Load currLoad = pq.poll();
            int currNo = currLoad.no;
            int currPack = currLoad.pack;
            long currDistance = currLoad.distance;

            // 가중치가 현재까지의 최단 경로보다 크다면 continue
            if (min[currNo][currPack] < currDistance) continue;

            // 인접 도로들을 모두 검사
            for (Load load: adjList.get(currLoad.no)) {
                int adjNo = load.no;    // 인접 도로 번호
                long adjDistance = load.distance;   // 인접 도로의 거리 가중치

                // 도로 포장을 하는 경우
                // 포장 횟수가 K보다 작거나 같고
                // 인접 도로에서 포장을 1회 더 했을 때 > 현재 도로까지 연결된 상태 ---> 최단 경로를 update
                // 포장된 도로는 거리가 추가되지 않으므로 현재 도로까지 연결된 상태로 바꿈
                if (currPack+1 <= K && min[adjNo][currPack+1] > min[currNo][currPack]) {
                    min[adjNo][currPack+1] = min[currNo][currPack];
                    pq.offer(new Load(adjNo, currPack+1, min[adjNo][currPack+1]));
                }

                // 도로 포장을 하지 않는 경우
                // 인접 도로의 상태 > 현재 도로까지 연결된 상태 + 인접 도로 연결 가중치 ---> 최단 경로를 update
                // 포장을 하지 않고도 인접 도로의 상태보다 작다면 바꿈
                if (min[adjNo][currPack] > min[currNo][currPack] + adjDistance) {
                    min[adjNo][currPack] = min[currNo][currPack] + adjDistance;
                    pq.offer(new Load(adjNo, currPack, min[adjNo][currPack]));
                }
            }
        }
    }
}
