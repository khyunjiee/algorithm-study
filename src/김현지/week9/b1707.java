package baekjoon.sep.sep30;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 백준 1707 이분 그래프 [G4]
 *
 * 접근 방식:
 * bfs 탐색으로 접근했습니다.
 * 간선들을 미리 다 받지 않고 입력을 받으면서 처리하다가 버그가 생겼습니다.
 * 그래서 간선을 미리 다 받아서 ArrayList 로 만들어놓고,
 * bfs 탐색으로 그룹핑을 해주어 만약 그룹핑이 불가능한 상황이 나온다면 NO를 출력합니다.
 *
 * 시간 복잡도:
 * O(V^2)
 *
 * 소요 시간:
 * 3시간..?
 * 306312KB 992ms
 */

public class b1707 {

    static final int FIRST = 1, SECOND = 2;
    static int V;
    static ArrayList<Integer>[] adjList;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int TC = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= TC; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            V = Integer.parseInt(st.nextToken());   // 정점의 개수
            int E = Integer.parseInt(st.nextToken());   // 간선의 개수
            adjList = new ArrayList[V+1];

            for (int i = 1; i <= V; i++) {
                adjList[i] = new ArrayList<>();
            }

            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                // 인접한 정점들 모두 추가하기
                if (a != b) {
                    adjList[a].add(b);
                    adjList[b].add(a);
                }
            }

            String result = "YES";
            boolean[] visited = new boolean[V+1];
            for (int i = 1; i <= V; i++) {
                // 아직 방문하지 않은 정점에 대해 bfs 탐색
                // 탐색 결과가 false라면 이분 그래프가 아님
                if (!visited[i] && !bfs(i, visited)) {
                    result = "NO";
                    break;
                }
            }
            sb.append(result + "\n");
        }
        System.out.println(sb);
    }

    // start: 그룹핑할 시작 정점
    // visited: 방문 여부 배열
    private static boolean bfs(int start, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        int[] groups = new int[V+1];    // 각 정점의 그룹들
        int grouping = FIRST;
        groups[start] = grouping;

        while (!queue.isEmpty()) {
            int curr = queue.poll();

            // 방문 체크
            if (visited[curr]) continue;
            visited[curr] = true;

            // 현재 정점의 그룹과 다른 그룹으로 그룹핑해야 함
            grouping = (groups[curr] == SECOND)? FIRST: SECOND;

            // 현재 정점의 인접한 정점들에 대해
            for (int vertex: adjList[curr]) {
                // 그룹이 같다면 false
                if (groups[vertex] == groups[curr]) return false;
                // 그룹이 다르다면 그룹핑 후 큐에 추가
                groups[vertex] = grouping;
                queue.offer(vertex);
            }
        }
        return true;    // while 문이 끝났다면 유효한 이분 그래프인 것
    }
}
