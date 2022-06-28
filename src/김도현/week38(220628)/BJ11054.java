package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ11054 {
    /* 11054. 가장 긴 바이토닉 부분 수열
    DP
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());
        int[] forward = new int[N];
        int[] reverse = new int[N];
        Arrays.fill(forward, 1);
        Arrays.fill(reverse, 1);

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (arr[i] > arr[j] && forward[j] + 1 > forward[i]) forward[i] = forward[j] + 1;
                if (arr[N-1-i] > arr[N-1-j] && reverse[N-1-j] + 1 > reverse[N-1-i]) reverse[N-1-i] = reverse[N-1-j] + 1;
            }
        }
        int result = 0;
        for (int i = 0; i < N; i++) result = Math.max(result, forward[i] + reverse[i] - 1);
        System.out.println(result);
    }
}
