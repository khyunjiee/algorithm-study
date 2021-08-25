package baekjoon.aug.aug25;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 백준 1753 최단 경로
 *
 * 접근 방식:
 * 인접 리스트, 최소 힙, 다익스트라를 활용했습니다.
 * 인접 리스트에 현재 정점에서 인접한 정점들을 link로 저장합니다.
 * 그 후에 start 부터 최소 힙에 넣고 distance가 갱신될 때마다 힙에 넣어줍니다.
 * 힙이 빌때까지 반복합니다.
 *
 * 시간 복잡도:
 * (V+E)logV
 */

public class b1753 {
    // 정점 클래스
    static class Node {
        int vertex, weight;
        Node link;  // 인접한 정점들을 저장할 링크

        public Node(int vertex, int weight, Node link) {
            this.vertex = vertex;
            this.weight = weight;
            this.link = link;
        }
    }

    // 최단 경로를 체크할 정점 클래스
    static class CheckNode implements Comparable<CheckNode>{
        int no, distance;

        public CheckNode(int no, int distance) {
            this.no = no;
            this.distance = distance;
        }

        // 큐에 넣을 때 distance가 작은 순서대로 정렬
        @Override
        public int compareTo(CheckNode o) {
            return this.distance - o.distance;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine());

        // 정점이 1부터 시작이므로 모두 V+1 로 초기화
        Node[] nodes = new Node[V+1];
        boolean[] visited = new boolean[V+1];
        int[] distance = new int[V+1];
        Arrays.fill(distance, Integer.MAX_VALUE);   // 최대값으로 채운다

        // 인접 리스트 초기화
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            nodes[u] = new Node(v, w, nodes[u]);
        }

        // 우선순위 큐에 CheckNode 넣어서 관리
        PriorityQueue<CheckNode> pq = new PriorityQueue<>();
        pq.offer(new CheckNode(start, 0));   // 시작점의 distance는 0이다.
        distance[start] = 0;

        while (!pq.isEmpty()) {
            CheckNode e = pq.poll();

            // 이미 최단 경로를 고려한 정점이면 continue
            if (visited[e.no]) continue;

            // 최단 경로를 고려할 정점 방문 처리
            visited[e.no] = true;
            Node node = nodes[e.no];    // 인접 정점 리스트

            for(Node currNode = node; currNode != null; currNode = currNode.link) {
                // 방문한 정점이 아니고 distance를 갱신할 수 있으면
                if (!visited[currNode.vertex] && distance[e.no] + currNode.weight < distance[currNode.vertex]) {
                    distance[currNode.vertex] = distance[e.no] + currNode.weight;   // distance 업데이트
                    pq.offer(new CheckNode(currNode.vertex, distance[currNode.vertex])); // 큐에 해당 간선 삽입
                }
            }
        }

        /* output */
        for (int i = 1; i <= V; i++) {
            if (distance[i] == Integer.MAX_VALUE) System.out.println("INF");    // 갈 수 없는 정점
            else System.out.println(distance[i]);       // 해당 정점까지의 최단 경로
        }
        /* output */
    }
}
