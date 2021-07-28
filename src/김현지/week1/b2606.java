package baekjoon.july;

import java.util.*;

/*
백준 2606 바이러스
풀이법 : BFS 알고리즘 사용
시간복잡도 : 인접행렬을 사용했기 때문에 O(N^2)
더 공부할 것 : BFS를 인접리스트로 구현하는 방법
 */

public class b2606 {
    static int graph[][];
    static boolean visit[];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        graph = new int[N+1][N+1];
        visit = new boolean[N+1];
        for (int i = 0; i < M; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            graph[from][to] = 1;
            graph[to][from] = 1;
        }

        bfs(1);
    }

    private static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();

        visit[start] = true;
        queue.offer(start);

        int result = 0;

        while (!queue.isEmpty()) {
            int num = queue.poll();

            for (int i = 1; i < graph.length; i++) {
                if (graph[num][i] == 1 && !visit[i]) {
                    queue.offer(i);
                    visit[i] = true;
                    result++;
                }
            }
        }

        System.out.println(result);
    }
}
