package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ2631 {
    /* 2631. 줄세우기
    DP
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int[] DP = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            DP[i] = 1;
        }
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[j] <= arr[i] && DP[j] + 1 > DP[i]) DP[i] = DP[j] + 1;
            }
        }

        int maxVal = 0;
        for (int i = 0; i < N; i++) maxVal = Math.max(maxVal, DP[i]);
        System.out.println(N - maxVal);
    }
}
