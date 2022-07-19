package bj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ7453 {
    /* 7453. 합이 0인 네 정수
    정렬
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        int[] B = new int[N];
        int[] C = new int[N];
        int[] D = new int[N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
            B[i] = Integer.parseInt(st.nextToken());
            C[i] = Integer.parseInt(st.nextToken());
            D[i] = Integer.parseInt(st.nextToken());
        }

        int[] AB = new int[N*N];
        int[] CD = new int[N*N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                AB[N*i+j] = A[i] + B[j];
                CD[N*i+j] = -(C[i] + D[j]);
            }
        }
        Arrays.sort(AB);
        Arrays.sort(CD);

        long result = 0;
        int i = 0;
        int j = 0;
        long lCnt = 0;
        long rCnt = 0;
        while (i < N*N-1 || j < N*N-1){
            if (AB[i] == CD[j]) {
                lCnt = 1;
                rCnt = 1;
                while (i+1 < N*N && AB[i+1] == AB[i]) {
                    lCnt++;
                    i++;
                }
                while (j+1 < N*N && CD[j+1] == CD[j]) {
                    rCnt++;
                    j++;
                }
                result += (lCnt * rCnt);
                if (i+1 < N*N) i++;
                if (j+1 < N*N) j++;
                continue;
            }

            if (i == N*N-1) j++;
            else if (j == N*N-1) i++;
            else if (AB[i] > CD[j]) j++;
            else if (AB[i] < CD[j]) i++;
        }
        System.out.println(result);
    }
}
