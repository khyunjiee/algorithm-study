package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ1700 {
    /* 1700. 멀티탭 스케줄링
    구현
    */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[K];
        for (int i = 0; i < K; i++) arr[i] = Integer.parseInt(st.nextToken());
        int[] plug = new int[N];
        int s = 0, e = 0;
        outer:while (s < N && e < K) {
            for (int i = 0; i < s; i++)
                if (plug[i] == arr[e]) {
                    e++;
                    continue outer;
                }
            plug[s++] = arr[e++];
        }
        int[] schedule = new int[K+1];
        for (int i = 1; i < K+1; i++) schedule[i] = K;
        int result = 0;

        outer:for (int i = N; i < K; i++) {
            for (int p:plug)
                if (p == arr[i]) continue outer;
            for (int p:plug) {
                schedule[p] = K;
                for (int k = i + 1; k < K; k++)
                    if (p == arr[k]) {
                        schedule[p] = k;
                        break;
                    }
            }

            int index = 0, val = 0;
            for (int j = 0; j < N; j++)
                if (schedule[plug[j]] > val) {
                    val = schedule[plug[j]];
                    index = j;
                }
            plug[index] = arr[i];
            result++;
        }

        System.out.println(result);
    }
}
