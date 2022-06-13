package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ12026 {
    /* 12026. BOJ 거리
    완전탐색
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Integer> B = new ArrayList<>();
        ArrayList<Integer> O = new ArrayList<>();
        ArrayList<Integer> J = new ArrayList<>();
        int[] arr = new int[N];
        Arrays.fill(arr, -1);
        arr[0] = 0;
        B.add(0);
        char[] boj = br.readLine().toCharArray();
        int temp;
        for (int i = 1; i < N; i++) {
            temp = Integer.MAX_VALUE;
            if (boj[i] == 'B') {
                for (int j:J) {
                    temp = Math.min(temp, arr[j] + (i - j) * (i - j));
                }
                if (temp != Integer.MAX_VALUE) {
                    arr[i] = temp;
                    B.add(i);
                }
            } else if (boj[i] == 'O') {
                for (int b:B) {
                    temp = Math.min(temp, arr[b] + (i - b) * (i - b));
                }
                if (temp != Integer.MAX_VALUE) {
                    arr[i] = temp;
                    O.add(i);
                }
            } else if (boj[i] == 'J') {
                for (int o:O) {
                    temp = Math.min(temp, arr[o] + (i - o) * (i - o));
                }
                if (temp != Integer.MAX_VALUE) {
                    arr[i] = temp;
                    J.add(i);
                }
            }
        }
        System.out.println(arr[N-1]);
    }
}
