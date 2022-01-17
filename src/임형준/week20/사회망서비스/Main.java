package src.backjoon.사회망서비스;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * date : 22.01.14
 * link : https://www.acmicpc.net/problem/2533
 *
 * 틀려서 풀이 참조했습니다 ( Sol.java )
 * 원래 풀었던 풀이는 MainFail.java 에 있습니다.
 */


public class Main {
    static int N;

    static int[][] dp;
    static boolean[] visited;
    static List<Integer>[] graph;

    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./src/backjoon/사회망서비스/input.txt"));
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();

        dp = new int[N+1][2];
        visited = new boolean[N+1];
        graph = new ArrayList[N+1];
        for(int i=1;i<N+1;i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0;i<N-1;i++){
            int start = sc.nextInt();
            int end = sc.nextInt();

            graph[start].add(end);
            graph[end].add(start);
        }

        dfs(1);
        System.out.println(Math.min(dp[1][0],dp[1][1]));
    }

    private static void dfs(int current) {
        visited[current] = true;
        dp[current][0] = 0;
        dp[current][1] = 1;

        for(int child : graph[current]){
            if(visited[child]) continue;
            dfs(child);

            dp[current][0] += dp[child][1];
            dp[current][1] += Math.min(dp[child][0], dp[child][1]);
        }
    }
}
