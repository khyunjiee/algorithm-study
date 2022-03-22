package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ14501 {
    /* 14501. 퇴사
    DP
    */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] TP = new int[N+1];
        int T, P, result = 0;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            T = Integer.parseInt(st.nextToken());
            P = Integer.parseInt(st.nextToken());
            result = Math.max(TP[i], result);
            if (i + T <= N) {
                if (TP[i+T] < result + P) TP[i+T] = result + P;
            }
        }
        result = Math.max(TP[N], result);
        System.out.println(result);
    }
}
