package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BJ1043 {
    /* 1043. 거짓말
    union-find
    */
    public static void union(int[] p, int a, int b) {
        int pA = find(p, a);
        int pB = find(p, b);
        if (pA != pB) {
            if (pB < pA) {
                int temp = pB;
                pB = pA;
                pA = temp;
            }
            p[pA] += p[pB];
            p[pB] = pA;
        }
    }

    public static int find(int[] p, int n) {
        if (p[n] < 0) return n;
        return p[n] = find(p, p[n]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 사람의 수, 파티의 수
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] parent = new int[N+1];
        for (int i = 0; i < N+1; i++) parent[i] = -1;
        // 진실을 아는 사람
        st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken());
        for (int i = 0; i < p; i++) {
            int j = Integer.parseInt(st.nextToken());
            union(parent, 0, j);
        }
        // 각 파티마다 오는 사람
        long[] party = new long[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int q = Integer.parseInt(st.nextToken());
            party[i] = 1L << q;
            for (int j = 0; j < t - 1; j++) {
                int r = Integer.parseInt(st.nextToken());
                union(parent, q, r);
                party[i] = party[i] | (1L << r);
            }
        }
        // 파티의 최댓값
        int result = 0;
        long T = 0;
        for (int i = 1; i < N+1; i++)
            if (find(parent, i) == 0) T = T | (1L << i);
        for (int i = 0; i < M; i++)
            if ((T & party[i]) == 0) result += 1;
        System.out.println(result);
    }
}
