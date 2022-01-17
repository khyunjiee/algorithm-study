package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BJ2533 {
    /* 2533. 사회망 서비스(SNS)
    다른 node와 연결된 개수가 가장 많은 node부터 선택하고 제거 => 시간초과
    블로그 참조 DF S& DP: https://hqjang.tistory.com/104
     */
    public static void dfs(ArrayList<Integer>[] nodeList, int[][] dp, int parent, boolean[] visited) {
        visited[parent] = true;
        dp[parent][0] = 1;
        for (int child: nodeList[parent]) {
            if (visited[child]) continue;
            dfs(nodeList, dp, child, visited);
            dp[parent][0] += Math.min(dp[child][0], dp[child][1]);  // parent가 얼리어답터일 경우
            // child는 얼리어답터이어도 아니어도 됨
            dp[parent][1] += dp[child][0];  // 아닐 경우
            // child는 반드시 얼리어답터
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer>[] nodeList = new ArrayList[N];
        for (int i = 0; i < N; i++) nodeList[i] = new ArrayList<>();
        int A, B;
        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            A = Integer.parseInt(st.nextToken()) - 1;
            B = Integer.parseInt(st.nextToken()) - 1;
            nodeList[A].add(B);
            nodeList[B].add(A);
        }
        int[][] DP = new int[N][2];
        boolean[] visited = new boolean[N];
        dfs(nodeList, DP, 0, visited);
        System.out.println(Math.min(DP[0][0], DP[0][1]));
    }
}
