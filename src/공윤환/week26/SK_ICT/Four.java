package SK;

import java.util.*;

public class Four {

    public static void main(String[] args) {
        System.out.println(solution(5, new int[][]{{0, 1}, {0, 2}, {1, 3}, {1, 4}}));
        System.out.println(solution(4, new int[][]{{0, 1}, {1, 2},{2,3}}));
    }

    static List<Integer>[] graph;
    static boolean visited[];
    static int N, root;
    static long answer;

    public static long solution(int n, int[][] edges) {
        answer = 0;
        N = n;
        graph = new List[N];
        for (int i = 0; i < N; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];

            graph[from].add(to);
            graph[to].add(from);
        }

        for (int i = 0; i < N; i++) {
            root = i;
            visited = new boolean[N];
            dfs(i, false);
        }

        return answer;
    }

    static void dfs(int cur, boolean via) {
        if (visited[cur]) {
            return;
        }

        visited[cur] = true;

        for (int next : graph[cur]) {

            if (cur == root) {
                dfs(next, false);
                continue;
            }

            if (!via) {
                dfs(next, true);
                dfs(next, false);
                continue;
            }

            if (via) {
                dfs(next, true);
                answer++;
            }
        }
    }
}
