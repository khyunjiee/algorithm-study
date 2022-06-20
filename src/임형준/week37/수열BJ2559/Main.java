package 문제집.backjoon.수열BJ2559;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * date: 22.06.20
 */

public class Main {
    static int N,K;
    static int[] arr;
    static int[] prefix;
    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/문제집/backjoon/수열BJ2559/input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr = new int[N];
        prefix = new int[N+1];
        st = new StringTokenizer(br.readLine());

        for(int i=0;i<N;i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=1;i<N+1;i++){
            prefix[i] = prefix[i-1] + arr[i-1];
        }
        int max = Integer.MIN_VALUE;
        for(int k=K;k<N+1;k++){
            int sum = prefix[k] - prefix[k-K];
            max = Math.max(max,sum);
        }
        System.out.println(max);
    }
}
