package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ1005 {
    /* 1005. ACM Craft
    건설 순서 규칙 => 위상정렬
    */
    static int[] dist;
    // 건물 w를 짓기 위해 필요한 건물들을 DFS
    public static void dfs(ArrayList<Integer>[] nodeList, int[] cost, int w) {
        if (nodeList[w].size() == 0) {
            dist[w] = cost[w];
            return;
        }
        int temp = Integer.MIN_VALUE;
        for (int x:nodeList[w]) {
            // dist[x] >= 0이면 이미 비용이 계산된 건물
            if (dist[x] < 0) dfs(nodeList, cost, x);
            temp = Math.max(temp, dist[x]);
        }
        dist[w] = temp + cost[w];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        int N, K, X, Y, W;
        int[] cost;
        ArrayList<Integer>[] nodeList;
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            cost = new int[N+1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i < N + 1; i++) cost[i] = Integer.parseInt(st.nextToken());
            nodeList = new ArrayList[N+1];
            for (int i = 1; i < N + 1; i++) nodeList[i] = new ArrayList<>();
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                X = Integer.parseInt(st.nextToken());
                Y = Integer.parseInt(st.nextToken());
                nodeList[Y].add(X);
            }
            W = Integer.parseInt(br.readLine());
            dist = new int[N+1];
            Arrays.fill(dist, Integer.MIN_VALUE);
            dfs(nodeList, cost, W);
            System.out.println(dist[W]);
        }
    }
}
