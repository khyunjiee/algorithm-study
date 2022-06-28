package 문제집.backjoon.줄세우기BJ2631;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * date: 22.06.20
 * 참조링크: https://velog.io/@kjihye0340/%EB%B0%B1%EC%A4%80-2631%EC%A4%84%EC%84%B8%EC%9A%B0%EA%B8%B0Java
 */

public class Main {
    static int N;
    static int[] arr;
    static int[] dp;
    public static void main(String[] args) throws NumberFormatException, IOException {
        System.setIn(new FileInputStream("./src/문제집/backjoon/줄세우기/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        dp = new int[N];

        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        dp[0] = 1;
        int max = 0;

        for(int i=1;i<N;i++){
            dp[i] = 1;

            for(int j=0;j<i;j++){
                if(arr[i] > arr[j]){
                    dp[i] = Math.max(dp[i],dp[j]+1);
                }
            }
            max = Math.max(dp[i],max);
        }
        System.out.println(N-max);
    }
}
