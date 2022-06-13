package 문제집.backjoon.연속합;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * date: 22.05.30
 */

public class Main {
    static int N;
    static int[] arr;
    static int[] dp;
    static int max;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/문제집/backjoon/연속합/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N];


//        bottomUp();

        dp[0] = arr[0];
        max = dp[0];
        topDown(N-1);
        System.out.println(Arrays.toString(dp));
        System.out.println(max);
    }

    private static int topDown(int n) {
        if(n==0){
            return dp[0];
        }
        dp[n] = Math.max(topDown(n-1)+arr[n],arr[n]);
        max = Math.max(max,dp[n]);
        return dp[n];
    }

    private static void bottomUp() {
        dp[0] = arr[0];

        for(int i=1;i<N;i++){
            dp[i] = Math.max(dp[i-1]+arr[i],arr[i]);
            max = Math.max(max,dp[i]);
        }
    }
}
