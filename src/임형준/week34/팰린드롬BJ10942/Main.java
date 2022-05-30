package src.backjoon.팰린드롬BJ10942;

import java.io.*;
import java.util.StringTokenizer;

/**
 * date: 22.05.23
 */

public class Main {
    static int N,M;
    static int[] arr;
    static int[][] dp;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/backjoon/팰린드롬BJ10942/input.txt"));

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        dp = new int[N][N];
        arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        M = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<M;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken())-1;
            int end = Integer.parseInt(st.nextToken())-1;

            int res = check(start,end);

            sb.append(res == 1 ? 1 : 0).append("\n");
        }
        sb.setLength(sb.length()-1);
        System.out.println(sb);
    }

    static int check(int start, int end){
        if(start >= end ) return 1;

        if(dp[start][end] != 0) return dp[start][end];

        if(arr[start] == arr[end]){
            return dp[start][end] = check(start+1,end-1);
        }
        return -1;
    }
}
